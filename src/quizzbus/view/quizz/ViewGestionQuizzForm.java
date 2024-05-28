package quizzbus.view.quizz;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfox.javafx.util.UtilFX;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;
import jfox.javafx.view.Mode;
import quizzbus.data.Question;
import quizzbus.data.Theme;
import quizzbus.view.ManagerGui;
<<<<<<< HEAD
=======
import quizzbus.view.question.ModelQuestion;
>>>>>>> e960eac7f34648cdd8a13ed0e5a7297d56734fc1
import quizzbus.view.question.ViewQuestionList;

public class ViewGestionQuizzForm extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------

	@FXML
	private Label labId;
	@FXML
	private TextField txfDescription;
	@FXML
	private TextArea txaDescription;

	@FXML
	private ListView<Question> lsvQuestions;

	@FXML
	private ComboBox<Theme> cbThemes;

	@FXML
	private Button btnValider;
	@FXML
	private Button btnQuestionsSupprimer;

	// -------
	// Autres champs
	// -------

	@Inject
	private ManagerGui managerGui;
	@Inject
	private ModelQuizz modelQuizz;
	@Inject
	private ModelQuestion modelQuestion;

	// -------
	// Initialisation du Controller
	// -------

	@FXML
	private void initialize() {

		var draft = modelQuizz.getDraft();

		// Id
		bind(labId, draft.idProperty(), new ConverterInteger());

		// Titre
		bindBidirectional(txfDescription, draft.descriptionProperty());
		validator.addRuleNotBlank(txfDescription);
		validator.addRuleMaxLength(txfDescription, 50);
		validator.addRuleMinLength(txfDescription, 4);

		// Description
		bindBidirectional(txaDescription, draft.descriptionProperty());

		// Questions
		lsvQuestions.setItems(draft.getQuestions());
		UtilFX.setCellFactory(lsvQuestions, "enonce");
		lsvQuestions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// Thèmes
		cbThemes.setItems(modelQuizz.getTheme());
		bindBidirectional(cbThemes, draft.themeProperty());
		UtilFX.setCellFactory(cbThemes, "nom");

		// Configuraiton des boutons

		lsvQuestions.getSelectionModel().selectedItemProperty().addListener(obs -> {
			configurerBoutonsQuestions();
		});
		configurerBoutonsQuestions();

		// Bouton VAlider
		btnValider.disableProperty().bind(validator.invalidProperty());
	}

	@Override
	public void refresh() {
		txfDescription.requestFocus();
		validator.reinit();

	}

	// -------
	// Actions
	// -------

	@FXML
	private void doAnnuler() {
		managerGui.showView(ViewGestionQuizzList.class);
	}

	@FXML
	private void doValider() {
		modelQuizz.saveDraft();
		managerGui.showView(ViewGestionQuizzList.class);
	}

	@FXML
	private void doQuestionAjouter() {
		modelQuizz.initDraft( Mode.NEW );
		managerGui.showDialog(ViewQuestionList.class);
	}

	@FXML
	private void doQuestionSupprimer() {
		var items = lsvQuestions.getSelectionModel().getSelectedItems();
		lsvQuestions.getItems().removeAll(items);
	}

	@FXML
	private void doQuestionModier() {
		modelQuestion.initDraft(Mode.EDIT);
		managerGui.showDialog(ViewGestionQuizzForm.class);
	}

	// -------
	// Gestion des évènements
	// -------

	// -------
	// Méthodes auxiliaires
	// -------

	private void configurerBoutonsQuestions() {

		if (lsvQuestions.getSelectionModel().getSelectedItem() == null) {
			btnQuestionsSupprimer.setDisable(true);
		} else {
			btnQuestionsSupprimer.setDisable(false);
		}
	}

}