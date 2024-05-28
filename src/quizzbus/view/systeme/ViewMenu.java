package quizzbus.view.systeme;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;
import quizzbus.view.configuration_poste.ViewConfiguration_Poste;
import quizzbus.view.quizz.ViewGestionQuizzList;

public class ViewMenu extends ControllerAbstract {
	////s
	
	@Inject
	private ManagerGui		managerGui;
	
	@FXML
	private void doDashBoard() {
		System.out.println("Dashboard");
		managerGui.showView( ViewDashBoard.class );
	}
	
	@FXML
	private void doGestionQuizz() {
		System.out.println("Dashboard");
		managerGui.showView( ViewGestionQuizzList.class );
	}
	
	@FXML
	private void doUtilisateur() {
		System.out.println("Dashboard");
		managerGui.showView( ViewDashBoard.class );
	}
	
	@FXML
	private void doSession() {
		System.out.println("Dashboard");
		managerGui.showView( ViewConfiguration_Poste.class );
	}
}
