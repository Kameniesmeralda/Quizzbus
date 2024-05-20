package quizzbus.view.systeme;


import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.data.Quizz;
import quizzbus.view.quizz.ModelQuizz;

public class ViewDashBoard extends ControllerAbstract{
	   @FXML
	    private ListView<Quizz> lvQuizz;
	   
	   @Inject
		private ModelQuizz	    modelQuizz;
	   
		
		@FXML
		private void initialize() {
			
			// Partie liste
			
			// ListView des comptes
			lvQuizz.setItems( modelQuizz.getList() );
			UtilFX.setCellFactory( lvQuizz, "pseudo" );
			bindBidirectional( lvQuizz, modelQuizz.currentProperty(), modelQuizz.flagRefreshingListProperty() );

		

		}
}
