package quizzbus.view.systeme;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;
import quizzbus.view.compte.ViewCompteCombo;
import quizzbus.view.compte.ViewCompteForm;
import quizzbus.view.configuration_poste.ViewConfiguration_Poste;
import quizzbus.view.joueur.ViewConnexionJoueur;
import quizzbus.view.quizz.ViewGestionQuizzList;

public class ViewMenu extends ControllerAbstract {
	////s

	@FXML
	private Button utilisateur;
	
	@FXML
	private Button quizz;

	@Inject
	private ManagerGui		managerGui;

	@FXML
	private void doDashBoard() {
		managerGui.showView( ViewDashBoard.class );
	}

	@FXML
	private void doGestionQuizz() {
		managerGui.showView( ViewGestionQuizzList.class );
	}

	@FXML
	private void doUtilisateur() {
		managerGui.showView( ViewCompteCombo.class );
	}

	@FXML
	private void doSession() {
		managerGui.showView( ViewConfiguration_Poste.class );
	}
	@Inject
	private ModelConnexion modelConnexion;

	@FXML
	public void initialize() {
		if(!modelConnexion.getCompteActif().isFlagAdmin()) {
			utilisateur.setVisible(false);
			quizz.setVisible(false);
		}
	}
}
