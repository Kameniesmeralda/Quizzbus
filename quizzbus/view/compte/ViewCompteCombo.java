package quizzbus.view.compte;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jfox.javafx.util.UtilFX;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;
import jfox.javafx.view.Mode;
import quizzbus.data.Compte;
import quizzbus.view.ManagerGui;

public class ViewCompteCombo extends ControllerAbstract {
	
	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private ListView<Compte>	lsvComptes;
	@FXML
	private Button				btnAjouter;
	@FXML
	private Button				btnSupprimer;
	
	@FXML
	private Label				labId;
	@FXML
	private TextField			txfNom;
	@FXML
	private TextField			txfPrenom;
	@FXML
	private TextField			txfDate;
	@FXML
	private TextField			txfMotDePasse;
	@FXML
	private TextField			txfEmail;
	@FXML
	private CheckBox			ckbAdmin;
	
	@FXML
	private Button				btnValider;
	
	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui			managerGui;
	@Inject
	private ModelCompte			modelCompte;
	
	//-------
	// Initialisations
	//-------
	
	@FXML
	private void initialize() {
		
		// Partie liste
		
		// ListView des comptes
		lsvComptes.setItems( modelCompte.getList() );
		UtilFX.setCellFactory( lsvComptes, "nom" );
		bindBidirectional( lsvComptes, modelCompte.currentProperty(), modelCompte.flagRefreshingListProperty() );

		// Comportement si modificaiton de la séleciton
		lsvComptes.getSelectionModel().selectedItemProperty().addListener( (obs, ov, nv) -> {
			initDraft();
			configurerBoutons( );
		});
		initDraft();
		configurerBoutons();
		
		
		// Partie formulaire
		
		var draft = modelCompte.getDraft();

		// Id
		bind( labId, draft.idProperty(), new ConverterInteger() );
		
		// Nom
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
    	
    	// Bouton Valider
    	btnValider.disableProperty().bind( validator.invalidProperty() );
	}
	
	@Override
	public void refresh() {
		modelCompte.refreshList();
		lsvComptes.requestFocus();
	}
	
	//-------
	// Actions
	//-------

	@FXML
	private void doAjouter() {
		lsvComptes.getSelectionModel().select(null);
		txfNom.requestFocus();
	}

	@FXML
	private void doSupprimer() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelCompte.deleteCurrent();
			refresh();
		}
	}
	
	@FXML
	private void doAnnuler() {
		if( modelCompte.getCurrent() == null ) {
			initDraft();
		}
		refresh();
	}
	
	@FXML
	private void doValider() {
		modelCompte.saveDraft();
		refresh();
	}

	//-------
	// Méthodes auxiliaires
	//-------
	
	private void initDraft() {
		if ( lsvComptes.getSelectionModel().getSelectedItem() == null ) {
			modelCompte.initDraft( Mode.NEW );
		} else {
			modelCompte.initDraft( Mode.EDIT );
		}
		validator.reset();
	}
	
	private void configurerBoutons() {
		var flagDisable = lsvComptes.getSelectionModel().getSelectedItem() == null;
		btnSupprimer.setDisable( flagDisable );
	}

}
