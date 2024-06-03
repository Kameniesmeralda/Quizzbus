package quizzbus.view.question;

import java.sql.SQLException;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import jfox.javafx.control.EditingCell;
import jfox.javafx.util.UtilFX;
//import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.data.Reponse;
import quizzbus.view.ManagerGui;

public class ViewQuestionForm extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------
	@FXML
	private TableColumn<Reponse, String> tableReponse;

	@FXML
	private TableView<Reponse> tableView;

	@FXML
	private TableColumn<Reponse, Boolean> tableVraie;
	@FXML
	private CheckBox ckbVraie;

	@FXML
	private TextArea txaReponse;

	@FXML
	private ImageView imvImage;

	@FXML
	private Label lbNomImage;

	@FXML
	private TextArea txaAstuce;

	@FXML
	private TextArea txaQuestion;

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
		var draft1 = modelReponse.getDraft();

		//bindBidirectional(ckbVraie, draft1.vraieProperty());
		bindBidirectional(txaReponse, draft1.libelleProperty());

		validator.addRuleNotBlank(txaReponse);

		// Question
		bindBidirectional(txaQuestion, draft.enonceProperty());
		validator.addRuleNotBlank(txaQuestion);
		validator.addRuleMaxLength(txaQuestion, 100);

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
		
		//Liste des reponses
		//tableView.setItems(modelQuestion.getDraft().getReponses());
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		UtilFX.setValueFactory( tableReponse, "libelle" );
		UtilFX.setValueFactory( tableVraie, "vraie" );
		
		tableReponse.setCellFactory(  p -> new EditingCell<>() );
		
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
	void doAjouterNewQuestion(ActionEvent event) throws SQLException {
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
		tableView.getItems().add(new Reponse());
		tableView.requestFocus();
		var index = tableView.getItems().size() - 1;
		tableView.getSelectionModel().select(index);
		tableView.scrollTo(index);
		
//		Reponse rep = new Reponse();
//		rep.setLibelle(txaReponse.getText());
//		rep.setVraie(ckbVraie.isSelected());
//		modelReponse.setCurrent(rep);
//		modelReponse.saveDraft();
//		modelQuestion.getDraft().getReponses().add(rep);
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
				if (tableView.getSelectionModel().getSelectedIndex() == -1) {
					managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
				} 
			}
		}
	}

	// -------
	// Méthodes auxiliaires
	// -------

}
