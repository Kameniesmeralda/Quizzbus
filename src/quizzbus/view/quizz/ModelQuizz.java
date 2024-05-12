package quizzbus.view.quizz;

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
import quizzbus.dao.DaoQuizz;
import quizzbus.data.Quizz;

public class ModelQuizz {
	
	//-------
	// Données observables 
	//-------
	
	private final ObservableList<Quizz>	list 	= FXCollections.observableArrayList(); 
	
	private final BooleanProperty			flagRefreshingList = new SimpleBooleanProperty();
	
	private final Quizz					draft 	= new Quizz();
	
	private final ObjectProperty<Quizz>	current	= new SimpleObjectProperty<>();
	
	//-------
	// Autres champs
	//-------
	
	private Mode		mode = Mode.NEW;
    @Inject
	private IMapper		mapper;
    @Inject
	private DaoQuizz	daoQuizz;

	//-------
	// Getters & Setters
	//-------
	
	public ObservableList<Quizz> getList() {
		return list;
	}

	public BooleanProperty flagRefreshingListProperty() {
		return flagRefreshingList;
	}

	public Quizz getDraft() {
		return draft;
	}

	public Property<Quizz> currentProperty() {
		return current;
	}

	public Quizz getCurrent() {
		return current.get();
	}

	public void setCurrent(Quizz item) {
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
		list.setAll( daoQuizz.listerTout() );
		flagRefreshingList.set(false);
 	}

	public void initDraft(Mode mode) {
		this.mode = mode;
		if( mode == Mode.NEW ) {
			mapper.update( draft, new Quizz() );
		} else {
			setCurrent( daoQuizz.retrouver( getCurrent().getId() ) );
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
			daoQuizz.inserer( draft );
			// Actualise le courant
			setCurrent( mapper.update( new Quizz(), draft ) );
		} else {
			// modficiation
			daoQuizz.modifier( draft );
			// Actualise le courant
			mapper.update( getCurrent(), draft );
		}
	}
	
	public void deleteCurrent() {
		// Effectue la suppression
		daoQuizz.supprimer( getCurrent().getId() );
		// Détermine le nouveau courant
		setCurrent( UtilFX.findNext( list, getCurrent() ) );
	}

}
