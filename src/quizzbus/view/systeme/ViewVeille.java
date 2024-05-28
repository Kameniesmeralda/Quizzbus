package quizzbus.view.systeme;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;

public class ViewVeille extends ControllerAbstract  {
	@FXML
	private Button touchMeButton;

	@Inject
	private ManagerGui managerGui;

	@FXML
	private void handleButtonAction(ActionEvent event) {
		System.out.println("Dashboard");
		managerGui.showView(ViewPageConnexion.class);
	}
}
