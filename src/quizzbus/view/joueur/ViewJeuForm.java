package quizzbus.view.joueur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import jfox.javafx.control.EditingCell;
import jfox.javafx.util.UtilFX;
//import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.dao.DaoQuestion;
import quizzbus.dao.DaoTheme;
import quizzbus.data.Question;
import quizzbus.data.Reponse;
import quizzbus.data.Theme;
import quizzbus.view.ManagerGui;
import quizzbus.view.configuration_poste.ModelConfiguration_Poste;
import quizzbus.view.question.ModelQuestion;

public class ViewJeuForm extends ControllerAbstract {

	// -------
	// Composants de la vue
	// -------
	//	@FXML
	//	private TableColumn<Reponse, String> tableReponse;

	//	@FXML
	//	private TableView<Reponse> tableView;
	//
	//	@FXML
	//	private TableColumn<Reponse, Boolean> tableVraie;
	//	@FXML
	//	private CheckBox ckbVraie;
	//
	//	@FXML
	//	private TextArea txaReponse;
	//
	//	@FXML
	//	private ImageView imvImage;
	//
	//	@FXML
	//	private Label lbNomImage;
	//
	//	@FXML
	//	private TextArea txaAstuce;
	//
	//	@FXML
	//	private TextArea txaQuestion;
	//
	//	@FXML
	//	private TextField txfTheme;

	@FXML
	private RadioButton choice1;

	@FXML
	private RadioButton choice2;

	@FXML
	private RadioButton choice3;

	@FXML
	private RadioButton choice4;

	@FXML
	private TextField textfield1;

	@FXML
	private TextField textfield2;

	@FXML
	private TextField textfield3;

	@FXML
	private TextField textfield4;

	@FXML
	private ToggleGroup toggleGroup;

	@FXML
	private TextArea txtEnonce;

	@FXML
	private Button btnAnnuler;

	@FXML
	private Button btnValider;

	@FXML
	private Button btnPrecedent;

	@FXML
	private Button btnSuivant;

	@FXML
	private Text txtNumQuestion;

	@FXML
	private Text txtModeJeu;

	// -------
	// Autres champs
	// -------

	@Inject
	private ManagerGui managerGui;

	@Inject
	private ModelQuestion modelQuestion;

	@Inject
	private ModelJoueur modelJoueur;

	@Inject
	private ModelConfiguration_Poste modelConfiguration_Poste;

	@Inject
	private DaoQuestion daoQuestion;

	@Inject
	private DaoTheme daoTheme;

	private ObservableList<Question> questions;
	private List<Integer> reponses;
	// -------
	// Initialisation du Controller
	// -------

	private int quizz;

	private int index = 0;

	private void setInterface() {
		txtNumQuestion.setText((index+1)+"/"+questions.size());
		Question q = questions.get(index);
		txtEnonce.setText(q.getEnonce());
		textfield1.setText(q.getReponse1());
		textfield2.setText(q.getReponse2());
		textfield3.setText(q.getReponse3());
		textfield4.setText(q.getReponse4());
		if(reponses.get(index)==1)
			choice1.setSelected(true);
		else if(reponses.get(index)==2)
			choice2.setSelected(true);
		else if(reponses.get(index)==3)
			choice3.setSelected(true);
		else if(reponses.get(index)==4)
			choice4.setSelected(true);

		System.out.println(getScore());

		btnSuivant.setText((questions.size() == index+1? "Voir les resultats":"Suivant"));



		btnPrecedent.setDisable(index==0);

	}
	private int getScore() {
		int score=0;
		for(int i = 0; i<questions.size(); i++) 
			score+=(questions.get(i).getReponse() == reponses.get(i)? 1:0);

		return score;
	}

	@FXML
	private void initialize() {
		refresh();


	}

	private void initializeQuestionsAndReponses(Theme theme) throws SQLException {
		// Récupérer 5 questions par thème
		questions.addAll(daoQuestion.listerQuestionsParTheme(theme, 5));

		// Initialiser la liste des réponses avec des 1
		reponses = new ArrayList<>();
		for (int i = 0; i < questions.size(); i++) {
			reponses.add(1);
			System.out.println(questions.get(i).getEnonce());
		}

	}

	@Override
	public void refresh() {
		txtEnonce.requestFocus();
		validator.reinit();
		if(questions ==null)
			questions = FXCollections.observableArrayList(new ArrayList<>());
		questions.clear();
		try {
			if(modelConfiguration_Poste.getDraft().getParcours().getId() == 1) {
				Theme theme = new Theme(); // Remplacez par l'objet de thème réel
				initializeQuestionsAndReponses(modelConfiguration_Poste.getDraft().getTheme());
			}else {
				for(Theme theme: daoTheme.listerTout())
					initializeQuestionsAndReponses(theme);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		index = 0;
		setInterface();

	}

	// -------
	// Actions
	// -------

	@FXML
	void doSuivant(ActionEvent event) throws SQLException {
		//		modelQuestion.saveDraft();
		//		managerGui.showView(ViewQuestionList.class);
		if(index+1 == questions.size()) {
			setInterface();
			var draft = modelConfiguration_Poste.getDraft();
			draft.setScore(getScore());
			draft.setQuestion(questions.size());
			modelConfiguration_Poste.saveDraft();
			managerGui.showView(ViewResultatForm.class);
		}
		if(index<questions.size()-1) {
			reponses.set(index,(toggleGroup.getSelectedToggle() == choice1)?1
					:(toggleGroup.getSelectedToggle() == choice2)?2
							:(toggleGroup.getSelectedToggle() == choice3)?3
									:4);
			index++;
			setInterface();
			btnPrecedent.setDisable(false);
		}


	}

	@FXML
	void doPrecedent(ActionEvent event) {
		//		managerGui.showView(ViewQuestionList.class);
		if(index>0) {
			reponses.set(index,(toggleGroup.getSelectedToggle() == choice1)?1
					:(toggleGroup.getSelectedToggle() == choice2)?2
							:(toggleGroup.getSelectedToggle() == choice3)?3
									:4);
			index--;
			setInterface();
			btnSuivant.setText("Suivant");
		}
	}

	@FXML
	void doChoisirImage() {
		var fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un fichier image");
		var chemin = fileChooser.showOpenDialog(managerGui.getStage());
		//		if (chemin != null) {
		//			imvImage.setImage(new Image(chemin.toURI().toString()));
		//		}
	}

	@FXML
	private void doImageSupprimer() {
		//		imvImage.setImage(null);
	}

	@FXML
	void doAjouterNewReponse() throws SQLException {
		//		managerGui.showView(ViewQuestionList.class);
		//		tableView.getItems().add(new Reponse());
		//		tableView.requestFocus();
		//		var index = tableView.getItems().size() - 1;
		//		tableView.getSelectionModel().select(index);
		//		tableView.scrollTo(index);

		//		Reponse rep = new Reponse();
		//		rep.setLibelle(txaReponse.getText());
		//		rep.setVraie(ckbVraie.isSelected());
		//		modelReponse.setCurrent(rep);
		//		modelReponse.saveDraft();
		//		modelQuestion.getDraft().getReponses().add(rep);
		modelQuestion.getDraft();
		//		modelQuestion.saveDraft();
		modelQuestion.saveDraft();
		//		managerGui.showView(ViewQuestionList.class);
	}

	@FXML
	void doSupprimerReponse() {
		if (managerGui.showDialogConfirm("Confirmez-vous la suppresion ?")) {
			//			modelReponse.deleteCurrent();
			refresh();
		}
	}

	// Clic sur la liste
	@FXML
	private void gererClicSurListe(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				//				if (tableView.getSelectionModel().getSelectedIndex() == -1) {
				//					managerGui.showDialogError("Aucun élément n'est sélectionné dans la liste.");
				//				} 
			}
		}
	}

	// -------
	// Méthodes auxiliaires
	// -------

}
