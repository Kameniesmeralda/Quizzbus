package quizzbus.view.configuration_poste;

import java.time.LocalTime;

import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.dao.DaoParcours;
import quizzbus.dao.DaoTheme;
import quizzbus.data.Configuration_Poste;
import quizzbus.data.Parcours;
import quizzbus.data.Poste;
import quizzbus.data.Theme;
import quizzbus.view.ManagerGui;
import quizzbus.view.joueur.ViewJeuForm;
import quizzbus.view.systeme.ModelConnexion;

public class ViewConfiguration_Poste extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------
	@FXML
	private ChoiceBox<Parcours> chbModeJeu;
	
	@FXML
	private ChoiceBox<Theme> chbTheme;

	@FXML
	private Label lbNbrPoste;

	@FXML
	private Label lbNomAdmin;

	@FXML
	private ListView<Poste> lvListeDePoste;

	@FXML
	private TableView<Configuration_Poste> table;

	@FXML
	private TableColumn<Configuration_Poste, LocalTime> tableHeure;

	@FXML
	private TableColumn<Configuration_Poste, String> tableModeJeu;

	@FXML
	private TableColumn<Configuration_Poste, String> tableTheme;
    @FXML
    private TableColumn<Configuration_Poste, Integer> tableQuestions;

    @FXML
    private TableColumn<Configuration_Poste, Integer> tableScore;

	@FXML
	private Button btnAjouter;

	@FXML
	private Button btnDemarrerSession;

	@FXML
	private Button btnSupprimer;

	@FXML
	private Button btnSupprimerSession;

	// -------
	// Autres champs
	// -------

	@Inject
	private ManagerGui managerGui;
	@Inject
	private ModelConfiguration_Poste modelConfiPoste;
	@Inject
	private ModelConnexion modelConnexion;
	
	@Inject
	private DaoParcours daoParcours;
	
	@Inject
	private DaoTheme daoTheme;
	
	private int i=0;

	// -------
	// Initialisation du Controller
	// -------
	@FXML
	public void initialize() {

		//var draft = modelConfiPoste.getDraft();

		// TableView
		table.setItems(modelConfiPoste.getList());
		// UtilFX.setCellFactory(lsvQuestion, "description");
		bindBidirectional(table, modelConfiPoste.currentProperty(), modelConfiPoste.flagRefreshingListProperty());
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		/*
		// Configurer les colonnes de la TableView
        tableHeure.setCellValueFactory(cellData -> cellData.getValue().heureProperty());
        tableModeJeu.setCellValueFactory(cellData -> cellData.getValue().parcoursProperty().asString());
        tableTheme.setCellValueFactory(cellData -> cellData.getValue().themeProperty().asString());
        tableQuestions.setCellValueFactory(cellData -> cellData.getValue().questionProperty().asObject());
        tableScore.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());

		

		UtilFX.setValueFactory(tableModeJeu, "parcours");
		UtilFX.setValueFactory(tableTheme, "theme");
		UtilFX.setValueFactory(tableHeure, "heure");
		UtilFX.setValueFactory(tableQuestions, "question");
		UtilFX.setValueFactory(tableScore, "score");*/
		
		tableHeure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        tableModeJeu.setCellValueFactory(cellData -> cellData.getValue().parcoursProperty().asString());
        tableTheme.setCellValueFactory(cellData -> cellData.getValue().themeProperty().asString());
        tableQuestions.setCellValueFactory(new PropertyValueFactory<>("question"));
        tableScore.setCellValueFactory(new PropertyValueFactory<>("score"));


		// Configuraiton des boutons
		table.getSelectionModel().selectedItemProperty().addListener(obs -> {
			configurerBoutons();
		});
		configurerBoutons();
		
		

		lbNomAdmin.setText(modelConnexion.getCompteActif().getNom() + " " +modelConnexion.getCompteActif().getPrenom());
		
		

		
		
		ObservableList<Parcours> observableParcoursList = FXCollections.observableArrayList(daoParcours.listerTout());
		
		chbModeJeu.setItems(observableParcoursList);
		chbModeJeu.getSelectionModel().selectFirst();
		
		chbTheme.setItems( FXCollections.observableArrayList(daoTheme.listerTout()));
		chbTheme.getSelectionModel().selectFirst();	
		//refresh();
	}
	
	@Override
	public void refresh() {
		modelConfiPoste.refreshList();
	}

	// -------
	// Actions
	// -------
	@FXML
	void doAjouterPosteASession() {
		
		Poste pers = lvListeDePoste.getSelectionModel().getSelectedItem();
		if (pers == null) {
			Alert alerte = new Alert(AlertType.ERROR);
			alerte.setTitle("Erreur");
			alerte.setHeaderText("Aucune personne sélectionnée");
			alerte.setContentText("Veuillez sélectionner une personne à modifier.");
			alerte.showAndWait();
		} else {
			Alert confirmation = new Alert(AlertType.CONFIRMATION);
			confirmation.setTitle("Confirmation de l'ajout");
			confirmation.setHeaderText("Ajouter " + pers.getLibelle() + " ?");
			confirmation.setContentText("Êtes-vous sûr de vouloir ajouter ce poste ?");
			confirmation.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					i++;
					lbNbrPoste.setText(Integer.toString(i));
				}
			});
		}
	}

	@FXML
	void doDemarrerSession() {
		//modelConfiPoste.saveDraft();
		modelConfiPoste.getDraft().setParcours(chbModeJeu.getSelectionModel().getSelectedItem());
		modelConfiPoste.getDraft().setTheme(chbTheme.getSelectionModel().getSelectedItem());
		managerGui.showView(ViewJeuForm.class);

	}

	@FXML
	void doRechercher() {

	}

	@FXML
	void doSupprimerPosteDeSession(ActionEvent event) {
		var items = lvListeDePoste.getSelectionModel().getSelectedItems();
		lvListeDePoste.getItems().removeAll(items);
	}

	@FXML
	void doSupprimerSession(ActionEvent event) {
		var items = table.getSelectionModel().getSelectedItems();
		table.getItems().removeAll(items);
	}
	
	// Clic sur la liste
		@FXML
		private void gererClicSurListe( MouseEvent event ) {
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				if (event.getClickCount() == 2) {
					if ( table.getSelectionModel().getSelectedIndex() == -1 ) {
						managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
					} else {
						doAjouterPosteASession();
					}
				}
			}
		}

	// -------
	// Méthodes auxiliaires
	// -------

	private void configurerBoutons() {
		var flagDisable = table.getSelectionModel().getSelectedItem() == null;
		btnAjouter.setDisable(flagDisable);
		btnSupprimer.setDisable(flagDisable);
		//btnDemarrerSession.setDisable(flagDisable);
		btnSupprimerSession.setDisable(flagDisable);
	}
	
//	private void chargerEtAfficherPostesDeJeux() {
//        ObservableList<Poste> postes = ModelConfiguration_Poste.getPostes();
//        lvListeDePoste.setItems(postes);
//    }
	/*
	 private void initializePostesList() {
	        modelConfiPoste.refreshPostesList();
	        lvListeDePoste.setItems(modelConfiPoste.getListPostes());
	    }*/

}
