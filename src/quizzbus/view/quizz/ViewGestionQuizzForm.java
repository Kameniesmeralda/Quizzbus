package quizzbus.view.quizz;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.stage.FileChooser;
import jfox.javafx.util.UtilFX;
import jfox.javafx.util.converter.ConverterBigDecimal;
import jfox.javafx.util.converter.ConverterInteger;
import jfox.javafx.util.converter.ConverterLocalDate;
import jfox.javafx.util.converter.ConverterLocalTime;
import jfox.javafx.view.ConfigView;
import jfox.javafx.view.ControllerAbstract;
import jfox.javafx.view.Mode;
import quizzbus.data.Question;
import quizzbus.data.Theme;
import quizzbus.view.ManagerGui;

@ConfigView( flagTransient = false )
public class ViewGestionQuizzForm extends ControllerAbstract {
	
	//-------
	// Composants de la vue
	//-------
	
	@FXML
	private Label			labId;
	@FXML
	private TextField		txfDescription;
	@FXML
	private TextArea		txaDescription;
	
	
	@FXML
	private ListView<Question> lsvQuestions;
	
	@FXML
	private ListView<Theme> lsvThemes;
	
	@FXML
	private Button			btnValider;

	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui		managerGui;
	@Inject
	private ModelQuizz		modelQuizz;
	

	//-------
	// Initialisation du Controller
	//-------

	@FXML
	private void initialize() {
		
		var draft = modelQuizz.getDraft();

		// Id
		bind( labId, draft.idProperty(), new ConverterInteger() );
		
		// Titre
		bindBidirectional( txfDescription, draft.descriptionProperty()  );
		validator.addRuleNotBlank(txfDescription);
		validator.addRuleMaxLength(txfDescription, 50 );
		validator.addRuleMinLength(txfDescription, 4);

		// Description
		bindBidirectional( txaDescription, draft.descriptionProperty() );
		
		
		// Abonnés
		lsvQuestions.setItems( draft.getQuestions() );
		UtilFX.setCellFactory( lsvQuestions, "pseudo" );
		lsvQuestions.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
		
		
		// Configuraiton des boutons
		
		lsvQuestions.getSelectionModel().selectedItemProperty().addListener( obs ->  {
			configurerBoutonsAbonnes();
		});
		configurerBoutonsAbonnes();

		
		
		// Bouton VAlider
		btnValider.disableProperty().bind( validator.invalidProperty() );
	}
	
	@Override
	public void refresh() {
		txfDescription.requestFocus();
		validator.reinit();
				
	}
	
	//-------
	// Actions
	//-------
	
	@FXML
	private void doAnnuler() {
		managerGui.showView( ViewGestionQuizzList.class );
	}
	
	@FXML
	private void doValider() {
		modelQuizz.saveDraft();
		managerGui.showView( ViewGestionQuizzList.class );
	}
	
	@FXML
	private void doQuestionAjouter() {
		managerGui.showDialog( ViewQuestionForm.class );
	}

	@FXML
	private void doQuestionSupprimer() {
		var items = lsvAbonnes.getSelectionModel().getSelectedItems();
		lsvAbonnes.getItems().removeAll( items );
	}
	
	@FXML
	private void doActeurAjouter() {
		modelActeur.initDraft( Mode.NEW );
		managerGui.showDialog( ViewMemoActeur.class );
		tbvActeurs.requestFocus();
	}

	@FXML
	private void doActeurModifier() {
		modelActeur.initDraft( Mode.EDIT );
		managerGui.showDialog( ViewMemoActeur.class );
		tbvActeurs.requestFocus();
	}

	@FXML
	private void doActeurSupprimer() {
		var items = tbvActeurs.getSelectionModel().getSelectedItems();
		tbvActeurs.getItems().removeAll( items );
	}	

	@FXML
	private void doImageChoisir() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un fichier image");
		File chemin = fileChooser.showOpenDialog( managerGui.getStage() );
		if ( chemin != null ) {
			imvImage.setImage( new Image( chemin.toURI().toString() ) );
		}		
	}
		
	@FXML
	private void doImageOuvrir() {
		try {
			Desktop.getDesktop().open( modelMemo.getCheminImageCourante() );
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
		
	@FXML
	private void doImageSupprimer() {
		imvImage.setImage(null);
	}
	
	//-------
	// Gestion des évènements
	//-------

	// Clic sur la liste des acteurs
	@FXML
	private void gererClicSurListeActeurs( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( tbvActeurs.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doActeurModifier();
				}
			}
		}
	}
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	private void configurerBoutonsAbonnes() {
		
    	if( lsvAbonnes.getSelectionModel().getSelectedItem() == null ) {
			btnAbonneSupprimer.setDisable(true);
		} else {
			btnAbonneSupprimer.setDisable(false);
		}
	}
	
	private void configurerBoutonsActeurs() {
		
    	if( tbvActeurs.getSelectionModel().getSelectedItem() == null ) {
			btnActeurModifier.setDisable(true);
			btnActeurSupprimer.setDisable(true);
		} else {
			btnActeurModifier.setDisable(false);
			btnActeurSupprimer.setDisable(false);
		}
	}
	
}
