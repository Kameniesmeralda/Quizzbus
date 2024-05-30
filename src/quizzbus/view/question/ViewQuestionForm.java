package quizzbus.view.question;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import jfox.javafx.util.UtilFX;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.data.Reponse;
import quizzbus.view.ManagerGui;

public class ViewQuestionForm extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------

	@FXML
	private ListView<Reponse> lsvReponse;

	@FXML
	private CheckBox chbProp1;

	@FXML
	private CheckBox chbProp2;

	@FXML
	private CheckBox chbProp3;

	@FXML
	private CheckBox chbProp4;

	@FXML
	private ImageView imvImage;

	@FXML
	private Label lbNomImage;

	@FXML
	private TextArea txaAstuce;

	@FXML
	private TextArea txaQuestion;

	@FXML
	private TextField txfProp1;

	@FXML
	private TextField txfProp2;

	@FXML
	private TextField txfProp3;

	@FXML
	private TextField txfProp4;

	@FXML
	private TextField txfTheme;

	@FXML
	private Button btnAnnuler;

	@FXML
	private Button btnValider;

	// -------
	// Autres champs
	// -------

	@Inject
	private ManagerGui managerGui;
	@Inject
	private ModelQuestion modelQuestion;

	@Inject
	private ModelReponse modelReponse;

	// -------
	// Initialisation du Controller
	// -------

	@FXML
	private void initialize() {
		var draft = modelQuestion.getDraft();

		// Question
		bindBidirectional(txaQuestion, draft.enonceProperty());
		validator.addRuleNotBlank(txaQuestion);
		validator.addRuleMaxLength(txaQuestion, 100);
//
//		// propsition 1
//		bindBidirectional(txfProp1, modelQuestion.proposition1Property());
//		bindBidirectional(chbProp1, modelQuestion.flag1Property());
//		validator.addRuleNotBlank(txfProp1);
//		validator.addRuleMaxLength(txfProp1, 100);
//
//		// propsition 2
//		bindBidirectional(txfProp2, modelQuestion.proposition2Property());
//		bindBidirectional(chbProp2, modelQuestion.flag2Property());
//		validator.addRuleNotBlank(txfProp2);
//		validator.addRuleMaxLength(txfProp2, 100);
//
//		// propsition 3
//		bindBidirectional(txfProp3, modelQuestion.proposition3Property());
//		bindBidirectional(chbProp3, modelQuestion.flag3Property());
//		validator.addRuleNotBlank(txfProp2);
//		validator.addRuleMaxLength(txfProp2, 100);
//
//		// propsition 4
//		bindBidirectional(txfProp1, modelQuestion.proposition4Property());
//		bindBidirectional(chbProp1, modelQuestion.flag4Property());
//		validator.addRuleNotBlank(txfProp2);
//		validator.addRuleMaxLength(txfProp2, 100);
//
		// Astuce
		bindBidirectional(txaAstuce, modelQuestion.astuceProperty());
		validator.addRuleNotBlank(txaAstuce);
		validator.addRuleMaxLength(txaAstuce, 500);

		// Bouton VAlider
		btnValider.disableProperty().bind(validator.invalidProperty());

		// Bouton VAlider
		btnAnnuler.disableProperty().bind(validator.invalidProperty());

		// imageview
		imvImage.imageProperty().bindBidirectional(modelQuestion.imageProperty());

		// ListView
		lsvReponse.setItems(modelReponse.getList());
		UtilFX.setCellFactory(lsvReponse, "libelle");
		bindBidirectional(lsvReponse, modelReponse.currentProperty(), modelReponse.flagRefreshingListProperty());
	}

//	@Override
//	public void refresh() {
//		txfDescription.requestFocus();
//		validator.reinit();
//
//	}

	// -------
	// Actions
	// -------

	@FXML
	void doAjouterNewQuestion(ActionEvent event) {
		modelQuestion.saveDraft();
		managerGui.showView(ViewQuestionList.class);
	}

	@FXML
	void doAnnulerNewQuestion(ActionEvent event) {
		managerGui.showView(ViewQuestionList.class);
	}

	@FXML
	void doChoisirImage() {
		var fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un fichier image");
		var chemin = fileChooser.showOpenDialog(managerGui.getStage());
		if (chemin != null) {
			imvImage.setImage(new Image(chemin.toURI().toString()));
		}
	}

	@FXML
	private void doImageSupprimer() {
		imvImage.setImage(null);
	}

	@FXML
	void doAjouterNewReponse() {
		managerGui.showView(ViewReponseForm.class);
	}

	@FXML
	void doSupprimerReponse() {
		if (managerGui.showDialogConfirm("Confirmez-vous la suppresion ?")) {
			modelReponse.deleteCurrent();
			refresh();
		}
	}

	// Clic sur la liste
	@FXML
	private void gererClicSurListe(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if (lsvReponse.getSelectionModel().getSelectedIndex() == -1) {
					managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
				} else {
					doSupprimerReponse();
				}
			}
		}
	}

	// -------
	// Méthodes auxiliaires
	// -------

}
