package quizzbus.view.configuration_poste;

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
import quizzbus.dao.DaoConfigurationPoste;
import quizzbus.data.Configuration_Poste;

public class ModelConfiguration_Poste {
	
	//-------
	// Données observables 
	//-------
	
	private final ObservableList<Configuration_Poste>	list 	= FXCollections.observableArrayList(); 
	
	private final BooleanProperty			flagRefreshingList = new SimpleBooleanProperty();
	
	private final Configuration_Poste					draft 	= new Configuration_Poste();
	
	private final ObjectProperty<Configuration_Poste>	current	= new SimpleObjectProperty<>();
	
	//-------
	// Autres champs
	//-------
	
	private Mode		mode = Mode.NEW;
    @Inject
	private IMapper		mapper;
    @Inject
	private DaoConfigurationPoste	daoConfigurationPoste;

	//-------
	// Getters & Setters
	//-------
	
	public ObservableList<Configuration_Poste> getList() {
		return list;
	}

	public BooleanProperty flagRefreshingListProperty() {
		return flagRefreshingList;
	}

	public Configuration_Poste getDraft() {
		return draft;
	}

	public Property<Configuration_Poste> currentProperty() {
		return current;
	}

	public Configuration_Poste getCurrent() {
		return current.get();
	}

	public void setCurrent(Configuration_Poste item) {
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
		list.setAll( daoConfigurationPoste.listerTout() );
		flagRefreshingList.set(false);
 	}

	public void initDraft(Mode mode) {
		this.mode = mode;
		if( mode == Mode.NEW ) {
			mapper.update( draft, new Configuration_Poste() );
		} else {
			setCurrent( daoConfigurationPoste.retrouver( getCurrent().getId() ) );
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
			daoConfigurationPoste.inserer( draft );
			// Actualise le courant
			setCurrent( mapper.update( new Configuration_Poste(), draft ) );
		} else {
			// modficiation
			daoConfigurationPoste.modifier( draft );
			// Actualise le courant
			mapper.update( getCurrent(), draft );
		}
	}
	
	public void deleteCurrent() {
		// Effectue la suppression
		daoConfigurationPoste.supprimer( getCurrent().getId() );
		// Détermine le nouveau courant
		setCurrent( UtilFX.findNext( list, getCurrent() ) );
	}

}
