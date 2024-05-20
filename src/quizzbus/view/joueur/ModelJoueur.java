package quizzbus.view.joueur;

import jakarta.inject.Inject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.Mode;
import quizzbus.commun.IMapper;
import quizzbus.dao.DaoJoueur;
import quizzbus.data.Joueur;

public class ModelJoueur {
	
	//-------
	// Données observables 
	//-------
	
	private final ObservableList<Joueur>	list 	= FXCollections.observableArrayList(); 
	
	private final BooleanProperty			flagRefreshingList = new SimpleBooleanProperty();
	
	private final Joueur					draft 	= new Joueur();
	
	private final ObjectProperty<Joueur>	current	= new SimpleObjectProperty<>();
	
	//-------
	// Autres champs
	//-------
	
	private Mode		mode = Mode.NEW;
    @Inject
	private IMapper		mapper;
    @Inject
	private DaoJoueur	daoJoueur;

	//-------
	// Getters & Setters
	//-------
	
	public ObservableList<Joueur> getList() {
		return list;
	}

	public BooleanProperty flagRefreshingListProperty() {
		return flagRefreshingList;
	}

	public Joueur getDraft() {
		return draft;
	}

	public Property<Joueur> currentProperty() {
		return current;
	}

	public Joueur getCurrent() {
		return current.get();
	}

	public void setCurrent(Joueur item) {
		current.set(item);
	}
	
	public Mode getMode() {
		return mode;
	}
	
	//-------
	// Actions
	//-------
	
	public void refreshList() {
		// flagRefreshingList vaut true pendant la durée  
		// du traitement de mise à jour de la liste
		flagRefreshingList.set(true);
		list.setAll( daoJoueur.listerTout() );
		flagRefreshingList.set(false);
 	}

	public void initDraft(Mode mode) {
		this.mode = mode;
		if( mode == Mode.NEW ) {
			mapper.update( draft, new Joueur() );
		} else {
			setCurrent( daoJoueur.retrouver( getCurrent().getId() ) );
			mapper.update( draft, getCurrent() );
		}
	}
	
	
	
	public void saveDraft() {

		// Vérifie la validité des données
		
//		StringBuilder message = new StringBuilder();
//		
//		if ( ! daoCompte.verifierUnicitePseudo( draft.getPseudo(), draft.getId() ) ) {
//			message.append( "\nLe pseudo " + draft.getPseudo() + " est déjà utilisé." );
//		}
//		
//		if ( message.length() > 0 ) {
//			throw new ExceptionValidation( message.toString().substring(1) );
//		}
		
		// Enregistre les données dans la base
		
		if ( mode == Mode.NEW ) {
			// Insertion
			daoJoueur.inserer( draft );
			// Actualise le courant
			setCurrent( mapper.update( new Joueur(), draft ) );
		} else {
			// modficiation
			daoJoueur.modifier( draft );
			// Actualise le courant
			mapper.update( getCurrent(), draft );
		}
	}
	
	public void deleteCurrent() {
		// Effectue la suppression
		daoJoueur.supprimer( getCurrent().getId() );
		// Détermine le nouveau courant
		setCurrent( UtilFX.findNext( list, getCurrent() ) );
	}

}
