package quizzbus.view.question;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.ControllerAbstract;
import jfox.javafx.view.Mode;
import quizzbus.data.Question;
import quizzbus.view.ManagerGui;
import quizzbus.view.quizz.ModelQuizz;

public class ViewQuestionList extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------

	@FXML
	private ListView<Question> lsvQuestion;
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
	private ModelQuestion modelQuestion;
	@Inject
	private ModelQuizz modelQuizz;

	// -------
	// Initialisations
	// -------

	@FXML
	private void initialize() {

		// ListView
		lsvQuestion.setItems(modelQuestion.getList());
		UtilFX.setCellFactory(lsvQuestion, "enonce");
		bindBidirectional(lsvQuestion, modelQuestion.currentProperty(), modelQuestion.flagRefreshingListProperty());

		// Configuraiton des boutons
		lsvQuestion.getSelectionModel().selectedItemProperty().addListener(obs -> {
			configurerBoutons();
		});
		configurerBoutons();

	}

	@Override
	public void refresh() {
		modelQuestion.refreshList();
		lsvQuestion.requestFocus();
	}

	// -------
	// Actions
	// -------

	@FXML
	private void doAjouter() {
		Question quest = lsvQuestion.getSelectionModel().getSelectedItem();
		modelQuizz.getDraft().getQuestions().add(quest);
		managerGui.closeDialog();
	}

	@FXML
	private void doCreation() {
		modelQuestion.initDraft(Mode.NEW);
		;
		managerGui.showView(ViewQuestionList.class);
	}

	@FXML
	private void doModifier() {
		modelQuestion.initDraft(Mode.EDIT);
		;
		managerGui.showView(ViewQuestionList.class);
	}

	@FXML
	private void doSupprimer() {
		if (managerGui.showDialogConfirm("Confirmez-vous la suppresion ?")) {
			modelQuestion.deleteCurrent();
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
				if (lsvQuestion.getSelectionModel().getSelectedIndex() == -1) {
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
		var flagDisable = lsvQuestion.getSelectionModel().getSelectedItem() == null;
		btnModifier.setDisable(flagDisable);
		btnSupprimer.setDisable(flagDisable);
	}

}
