package quizzbus.view.joueur;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import quizzbus.view.ManagerGui;


public class ViewParcoursAventure {
	
	@Inject
	private ManagerGui managerGui;
	
	@FXML
	private void doDemarrerAventure(ActionEvent event) {
		System.out.println("Dashboard");
		managerGui.showView(ViewQuestion.class);
	}
}
