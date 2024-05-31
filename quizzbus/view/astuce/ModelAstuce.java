package quizzbus.view.astuce;

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
import quizzbus.dao.DaoAstuce;
import quizzbus.data.Astuce;

public class ModelAstuce {
	
	//-------
	// Données observables 
	//-------
	
	private final ObservableList<Astuce>	list 	= FXCollections.observableArrayList(); 
	
	private final BooleanProperty			flagRefreshingList = new SimpleBooleanProperty();
	
	private final Astuce					draft 	= new Astuce();
	
	private final ObjectProperty<Astuce>	current	= new SimpleObjectProperty<>();
	
	//-------
	// Autres champs
	//-------
	
	private Mode		mode = Mode.NEW;
    @Inject
	private IMapper		mapper;
    @Inject
	private DaoAstuce	daoAstuce;

	//-------
	// Getters & Setters
	//-------
	
	public ObservableList<Astuce> getList() {
		return list;
	}

	public BooleanProperty flagRefreshingListProperty() {
		return flagRefreshingList;
	}

	public Astuce getDraft() {
		return draft;
	}

	public Property<Astuce> currentProperty() {
		return current;
	}

	public Astuce getCurrent() {
		return current.get();
	}

	public void setCurrent(Astuce item) {
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
		list.setAll( daoAstuce.listerTout() );
		flagRefreshingList.set(false);
 	}

	public void initDraft(Mode mode) {
		this.mode = mode;
		if( mode == Mode.NEW ) {
			mapper.update( draft, new Astuce() );
		} else {
			setCurrent( daoAstuce.retrouver( getCurrent().getId() ) );
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
			daoAstuce.inserer( draft );
			// Actualise le courant
			setCurrent( mapper.update( new Astuce(), draft ) );
		} else {
			// modficiation
			daoAstuce.modifier( draft );
			// Actualise le courant
			mapper.update( getCurrent(), draft );
		}
	}
	
	public void deleteCurrent() {
		// Effectue la suppression
		daoAstuce.supprimer( getCurrent().getId() );
		// Détermine le nouveau courant
		setCurrent( UtilFX.findNext( list, getCurrent() ) );
	}

}
