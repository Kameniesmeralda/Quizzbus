package quizzbus.view.joueur;
import java.sql.SQLException;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import jfox.javafx.view.ControllerAbstract;
import quizzbus.dao.DaoJoueur;

//import org.kordamp.ikonli.javafx.FontIcon;
import quizzbus.view.ManagerGui;




public class ViewConnexionJoueur extends ControllerAbstract {

    @FXML
    private ComboBox<String> cbCategories;

    @FXML
    private ComboBox<String> cbVilles;
    
    
    @Inject
	private ManagerGui managerGui;
	@Inject
	private ModelJoueur modelJoueur;
	@Inject
	private DaoJoueur daoJoueur;
	@FXML
	private void initialize() throws SQLException {

		var draft = modelJoueur.getDraft();



		//Catégories
		cbCategories.setItems(daoJoueur.listerCategories());
		bindBidirectional(cbCategories, draft.categorieProperty());
		//UtilFX.setCellFactory(cbCategories, "nom");


		//Villes
		cbVilles.setItems(daoJoueur.listerVilles());
		bindBidirectional(cbVilles, draft.villeProperty());
		//UtilFX.setCellFactory(cbCategories, "nom");

	}

    @FXML
    void doConnect(ActionEvent event) {
    

    }

}
