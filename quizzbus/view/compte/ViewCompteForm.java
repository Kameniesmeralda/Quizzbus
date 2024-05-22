package quizzbus.view.compte;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;

public class ViewCompteForm extends ControllerAbstract {
	
	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private Label			labId;
	@FXML
	private TextField		txfNom;
	@FXML
	private TextField		txfMotDePasse;
	@FXML
	private TextField		txfEmail;
	@FXML
	private CheckBox		ckbAdmin;
	@FXML
	private Button			btnValider;

	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui		managerGui;
	@Inject
	private ModelCompte	modelCompte;

	//-------
	// Initialisation du Controller
	//-------

	@FXML
	private void initialize() {
		
		var draft = modelCompte.getDraft();

		// Id
		bind( labId, draft.idProperty(), new ConverterInteger() );
		
		// Pseudo
		bindBidirectional( txfNom, draft.nomProperty() );
		validator.addRuleNotBlank( txfNom );
		validator.addRuleMinLength( txfNom, 3 );
		validator.addRuleMaxLength( txfNom, 25 );
		validator.addRule(txfNom, "Ce nom est déjà utilisé", modelCompte::verifierUniciteNom  );
		
		// Mot de passe
		bindBidirectional( txfMotDePasse, draft.motDePasseProperty() );
		validator.addRuleNotBlank( txfMotDePasse );
		validator.addRuleMinLength( txfMotDePasse, 3 );
		validator.addRuleMaxLength( txfMotDePasse, 25 );
		
		// Adresse e-mail
		bindBidirectional( txfEmail, draft.emailProperty() );
		validator.addRuleNotBlank( txfEmail );
		validator.addRuleMaxLength( txfEmail, 100 );
		validator.addRuleEmail( txfEmail );
		
		// Flag Admin
		bindBidirectional( ckbAdmin, draft.flagAdminProperty() );
		
		// Bouton VAlider
		btnValider.disableProperty().bind( validator.invalidProperty() );
	}
	
	@Override
	public void refresh() {
		txfNom.requestFocus();
	}
	
	//-------
	// Actions
	//-------
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( ViewCompteList.class );
	}
	
	@FXML
	private void doValider() {
		modelCompte.saveDraft();
		managerGui.showView( ViewCompteList.class );
	}

}
