package quizzbus.view.systeme;

import quizzbus.view.ManagerGui;

//dfgbhnj,nbv
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jfox.javafx.view.ControllerAbstract;

public class ViewPageConnexion extends ControllerAbstract {

	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private TextField		userName;
	@FXML
	private PasswordField	password;
	
	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui		managerGui;
	@Inject
	private ModelConnexion	modelConnexion;
	@Inject
	private ModelInfo		modelInfo;
	
	//-------
	// Initialisations
	//-------
	
	@FXML
	private void initialize() {

		var draft = modelConnexion.getDraft();
		
		// Data binding
		bindBidirectional( userName, draft.nomProperty() );
		bindBidirectional( password, draft.motDePasseProperty() );

	}
	
	@Override
	public void refresh() {
		// Ferem la session si elle est ouverte
		if ( modelConnexion.getCompteActif() != null ) {
			modelConnexion.fermerSessionUtilisateur();
		}
	}

	//-------
	// Actions
	//-------
	 @FXML
	    void AccountCreate(ActionEvent event) {

	    }
	@FXML
	private void doConnexion() {
		managerGui.execTask( () -> {
			modelConnexion.ouvrirSessionUtilisateur();
			Platform.runLater( () -> {
         			modelInfo.setTitre( "Bienvenue" );
        			modelInfo.setMessage( "Connexion réussie" );
        			managerGui.showView( ViewMenu.class );
            }) ;
		} );
	}

}
