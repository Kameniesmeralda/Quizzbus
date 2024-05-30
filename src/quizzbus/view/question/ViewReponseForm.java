package quizzbus.view.question;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;

public class ViewReponseForm extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------
	@FXML
	private Button btnCreer;

	@FXML
	private CheckBox ckbVrai;

	@FXML
	private TextField txtReponse;

	// -------
	// Autres champs
	// -------

	@Inject
	private ManagerGui managerGui;
	@Inject
	private ModelReponse modelReponse;
	@Inject
	private ModelQuestion modelQuestion;

	// -------
	// Initialisations
	// -------

	@FXML
	private void initialize() {
		var draft = modelReponse.getDraft();

		bindBidirectional(ckbVrai, draft.vraieProperty());

		bindBidirectional(txtReponse, draft.libelleProperty());
		validator.addRuleNotBlank(txtReponse);

	}

	// -------
	// Actions
	// -------

	@FXML
	private void doAjouter() {
		modelQuestion.getDraft().getReponses().add(modelReponse.getDraft());
		managerGui.showView(ViewQuestionForm.class);
//		managerGui.closeDialog();
	}

	@FXML
	void doAnnuler() {
		managerGui.showView(ViewQuestionForm.class);
//		managerGui.closeDialog();
	}

	// -------
	// Gestion des évènements
	// -------

	// -------
	// Méthodes auxiliaires
	// -------



}
