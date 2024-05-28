package quizzbus.view.question;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;

public class ViewQuestionForm extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------

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
	private Label lbId;

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

	// -------
	// Initialisation du Controller
	// -------

	@FXML
	private void initialize() {
		var draft = modelQuestion.getDraft();

		// Id
		bind(lbId, draft.idProperty(), new ConverterInteger());

		// Question
		bindBidirectional(txaQuestion, draft.enonceProperty());
		validator.addRuleNotBlank(txaQuestion);
		validator.addRuleMaxLength(txaQuestion, 100);

		// propsition 1
		bindBidirectional(txfProp1, draft.enonceProperty());
		validator.addRuleNotBlank(txfProp1);
		validator.addRuleMaxLength(txfProp1, 100);

		// propsition 2
		bindBidirectional(txfProp2, draft.enonceProperty());
		validator.addRuleNotBlank(txfProp2);
		validator.addRuleMaxLength(txfProp2, 50);

		// propsition 3
		bindBidirectional(txfProp3, draft.enonceProperty());
		validator.addRuleNotBlank(txfProp3);
		validator.addRuleMaxLength(txfProp3, 100);

		// propsition 4
		bindBidirectional(txfProp4, draft.enonceProperty());
		validator.addRuleNotBlank(txfProp4);
		validator.addRuleMaxLength(txfProp4, 100);

		// Reponse
		bindBidirectional(txaQuestion, draft.enonceProperty());
		validator.addRuleNotBlank(txaQuestion);
		validator.addRuleMaxLength(txaQuestion, 100);

		// Astuce
		bindBidirectional(txaAstuce, draft.enonceProperty());
		validator.addRuleNotBlank(txaAstuce);
		validator.addRuleMaxLength(txaAstuce, 500);

		// Flag
		//bindBidirectional( chbProp1, draft.reponseProperty() );
//		bindBidirectional(chbProp1.selectedProperty(), getReponseProperty(draft.reponseProperty(), 0));
//		bindBidirectional(chbProp2.selectedProperty(), getReponseProperty(draft.reponseProperty(), 1));
//		bindBidirectional(chbProp3.selectedProperty(), getReponseProperty(draft.reponseProperty(), 2));
//		bindBidirectional(chbProp4.selectedProperty(), getReponseProperty(draft.reponseProperty(), 3));

//		cmbCategorie.setItems(modelMemo.getCategories());
//		bindBidirectional(cmbCategorie, draft.categorieProperty());
//		UtilFX.setCellFactory(cmbCategorie, "libelle");

		// Bouton VAlider
		btnValider.disableProperty().bind(validator.invalidProperty());

		// Bouton VAlider
		btnAnnuler.disableProperty().bind(validator.invalidProperty());

		// imageview
		imvImage.imageProperty().bindBidirectional(modelQuestion.imageProperty());
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
	// -------
	// Méthodes auxiliaires
	// -------

}
