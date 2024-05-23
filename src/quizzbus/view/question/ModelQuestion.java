package quizzbus.view.question;

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
import quizzbus.dao.DaoQuestion;
import quizzbus.data.Question;

public class ModelQuestion {
	
	//-------
	// Données observables 
	//-------
	
	private final ObservableList<Question>	list 	= FXCollections.observableArrayList(); 
	
	private final BooleanProperty			flagRefreshingList = new SimpleBooleanProperty();
	
	private final Question					draft 	= new Question();
	
	private final ObjectProperty<Question>	current	= new SimpleObjectProperty<>();
	
	//-------
	// Autres champs
	//-------
	
	private Mode		mode = Mode.NEW;
    @Inject
	private IMapper		mapper;
    @Inject
	private DaoQuestion	daoQuestion;

	//-------
	// Getters & Setters
	//-------
	
	public ObservableList<Question> getList() {
		return list;
	}

	public BooleanProperty flagRefreshingListProperty() {
		return flagRefreshingList;
	}

	public Question getDraft() {
		return draft;
	}

	public Property<Question> currentProperty() {
		return current;
	}

	public Question getCurrent() {
		return current.get();
	}

	public void setCurrent(Question item) {
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
		list.setAll( daoQuestion.listerTout() );
		flagRefreshingList.set(false);
 	}

	public void initDraft(Mode mode) {
		this.mode = mode;
		if( mode == Mode.NEW ) {
			mapper.update( draft, new Question() );
		} else {
			setCurrent( daoQuestion.retrouver( getCurrent().getId() ) );
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
			daoQuestion.inserer( draft );
			// Actualise le courant
			setCurrent( mapper.update( new Question(), draft ) );
		} else {
			// modficiation
			daoQuestion.modifier( draft );
			// Actualise le courant
			mapper.update( getCurrent(), draft );
		}
	}
	
	public void deleteCurrent() {
		// Effectue la suppression
		daoQuestion.supprimer( getCurrent().getId() );
		// Détermine le nouveau courant
		setCurrent( UtilFX.findNext( list, getCurrent() ) );
	}

}
