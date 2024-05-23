package quizzbus.view.quizz;

import quizzbus.data.Quizz;
import quizzbus.view.ManagerGui;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.ControllerAbstract;
import jfox.javafx.view.Mode;

public class ViewGestionQuizzList extends ControllerAbstract {
	
	//-------
	// Composants de la vue
	//-------

	@FXML
	private ListView<Quizz>	lsvQuizz;
	@FXML
	private Button			btnModifier;
	@FXML
	private Button			btnSupprimer;

	//-------
	// Autres champs
	//-------
	
	@Inject
	private ManagerGui		managerGui;
	@Inject
	private ModelQuizz		modelQuizz;
	
	//-------
	// Initialisations
	//-------

	@FXML
	private void initialize() {

		// ListView
		lsvQuizz.setItems( modelQuizz.getList() );
		UtilFX.setCellFactory( lsvQuizz, "description" );
		bindBidirectional( lsvQuizz, modelQuizz.currentProperty(), modelQuizz.flagRefreshingListProperty() );
		
		// Configuraiton des boutons
		lsvQuizz.getSelectionModel().selectedItemProperty().addListener(obs -> {
			configurerBoutons();
		});
		configurerBoutons();
		
	}
	
	@Override
	public void refresh() {
		modelQuizz.refreshList();
		lsvQuizz.requestFocus();
	}
	
	//-------
	// Actions
	//-------
	
	@FXML
	private void doAjouter() {
		modelQuizz.initDraft( Mode.NEW );;
		managerGui.showView( ViewGestionQuizzForm.class );
	}

	@FXML
	private void doModifier() {
		modelQuizz.initDraft( Mode.EDIT );;
		managerGui.showView( ViewGestionQuizzForm.class );
	}

	@FXML
	private void doSupprimer() {
		if ( managerGui.showDialogConfirm( "Confirmez-vous la suppresion ?" ) ) {
			modelQuizz.deleteCurrent();
			refresh();
		}
	}
	
	//-------
	// Gestion des évènements
	//-------

	// Clic sur la liste
	@FXML
	private void gererClicSurListe( MouseEvent event ) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
			if (event.getClickCount() == 2) {
				if ( lsvQuizz.getSelectionModel().getSelectedIndex() == -1 ) {
					managerGui.showDialogError( "Aucun élément n'est sélectionné dans la liste.");
				} else {
					doModifier();
				}
			}
		}
	}
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	private void configurerBoutons() {
		var flagDisable = lsvQuizz.getSelectionModel().getSelectedItem() == null;
		btnModifier.setDisable(flagDisable);
		btnSupprimer.setDisable(flagDisable);
	}

}
