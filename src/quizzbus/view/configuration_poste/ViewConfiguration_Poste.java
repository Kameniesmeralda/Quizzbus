package quizzbus.view.configuration_poste;

import java.time.LocalTime;

import jakarta.inject.Inject;
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
import jfox.javafx.view.ControllerAbstract;
import quizzbus.data.Configuration_Poste;
import quizzbus.data.Parcours;
import quizzbus.data.Poste;
import quizzbus.view.ManagerGui;

public class ViewConfiguration_Poste extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------
	@FXML
	private ChoiceBox<Parcours> chbModeJeu;

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
	private TableColumn<Configuration_Poste, Integer> tableIdPoste;

	@FXML
	private TableColumn<Configuration_Poste, Integer> tableIdSession;

	@FXML
	private TableColumn<Configuration_Poste, String> tableModeJeu;

	@FXML
	private TableColumn<Configuration_Poste, String> tableStatus;

	@FXML
	private TableColumn<Configuration_Poste, String> tableTheme;

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
//		for (int i = 0; i < table.getItems().size(); i++) {
//			table.getSelectionModel().select(i);
//		}

		// Configuraiton des boutons
		table.getSelectionModel().selectedItemProperty().addListener(obs -> {
			configurerBoutons();
		});
		configurerBoutons();

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
		modelConfiPoste.saveDraft();
		//managerGui.showView(ViewPoste.class);
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

}
