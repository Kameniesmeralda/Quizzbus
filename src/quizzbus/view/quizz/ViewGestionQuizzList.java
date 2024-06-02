package quizzbus.view.quizz;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.ControllerAbstract;
import jfox.javafx.view.Mode;
import quizzbus.data.Quizz;
import quizzbus.view.ManagerGui;

public class ViewGestionQuizzList extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------

	@FXML
	private TableColumn<Quizz, String> coloneQuizz;

	@FXML
	private TableColumn<Quizz, String> coloneTheme;

	@FXML
	private TableView<Quizz> tableQuizz;

	@FXML
	private Button btnModifier;
	@FXML
	private Button btnSupprimer;

	// -------
	// Autres champs
	// -------

	@Inject
	private ManagerGui managerGui;
	@Inject
	private ModelQuizz modelQuizz;

	// -------
	// Initialisations
	// -------

	@FXML
	private void initialize() {
		// Liste des reponses
		tableQuizz.setItems(modelQuizz.getList());
		bindBidirectional(tableQuizz, modelQuizz.currentProperty(), modelQuizz.flagRefreshingListProperty());
		tableQuizz.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		UtilFX.setValueFactory(coloneTheme, "theme");
		UtilFX.setValueFactory(coloneQuizz, "description");

		// Configuraiton des boutons
		tableQuizz.getSelectionModel().selectedItemProperty().addListener(obs -> {
			configurerBoutons();
		});
		configurerBoutons();

	}

	@Override
	public void refresh() {
		modelQuizz.refreshList();
	}

	// -------
	// Actions
	// -------

	@FXML
	private void doAjouter() {
		modelQuizz.initDraft(Mode.NEW);
		managerGui.showView(ViewGestionQuizzForm.class);
	}

	@FXML
	private void doModifier() {
		modelQuizz.initDraft(Mode.EDIT);

		managerGui.showView(ViewGestionQuizzForm.class);
	}

	@FXML
	private void doSupprimer() {
		if (managerGui.showDialogConfirm("Confirmez-vous la suppresion ?")) {
			modelQuizz.deleteCurrent();
			refresh();
		}
	}

	// -------
	// Gestion des évènements
	// -------

	// Clic sur la liste
	@FXML
	private void gererClicSurListe(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if (tableQuizz.getSelectionModel().getSelectedIndex() == -1) {
					managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
				} else {
					doModifier();
				}
			}
		}
	}

	// -------
	// Méthodes auxiliaires
	// -------

	private void configurerBoutons() {
		var flagDisable = tableQuizz.getSelectionModel().getSelectedItem() == null;
		btnModifier.setDisable(flagDisable);
		btnSupprimer.setDisable(flagDisable);
	}

}
