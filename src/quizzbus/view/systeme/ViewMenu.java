package quizzbus.view.systeme;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;

public class ViewMenu extends ControllerAbstract {
	////s
	
	@Inject
	private ManagerGui		managerGui;
	@FXML
	private void doDashBoard() {
		System.out.println("Dashboard");
		managerGui.showView( ViewDashBoard.class );
	}
	
	
}
