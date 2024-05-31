package quizzbus.view.joueur;
import java.sql.SQLException;

import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.dao.DaoJoueur;
import quizzbus.dao.DaoParcours;
import quizzbus.data.Parcours;
//import org.kordamp.ikonli.javafx.FontIcon;
import quizzbus.view.ManagerGui;




public class ViewConnexionJoueur extends ControllerAbstract {

    @FXML
    private ComboBox<String> cbCategories;

    @FXML
    private ComboBox<String> cbVilles;
    
    @FXML
    private ComboBox<Parcours> cbParcours;
    
    @Inject
	private ManagerGui managerGui;
    
	@Inject
	private ModelJoueur modelJoueur;
	
	@Inject
	private ModelParcours modelParcours;
	
	@Inject
	private DaoJoueur daoJoueur;
	
	@Inject
	private DaoParcours daoParcours;
	
	
	@FXML
	private void initialize() throws SQLException {

		var draft = modelJoueur.getDraft();
		var draft1 = modelParcours.getDraft();


		//Catégories
		cbCategories.setItems(daoJoueur.listerCategories());
		bindBidirectional(cbCategories, draft.categorieProperty());
		//UtilFX.setCellFactory(cbCategories, "nom");


		//Villes
		cbVilles.setItems(daoJoueur.listerVilles());
		bindBidirectional(cbVilles, draft.villeProperty());
		//UtilFX.setCellFactory(cbCategories, "nom");
		
		// Parcours
		ObservableList<Parcours> observableParcoursList = FXCollections.observableArrayList(daoParcours.listerTout());
	    cbParcours.setItems(observableParcoursList);

	    // Manual bidirectional binding for ComboBox<Parcours> and StringProperty
	    cbParcours.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
	        if (newVal != null) {
	            draft1.modeProperty().set(newVal.getMode());
	        }
	    });

	    draft1.modeProperty().addListener((obs, oldVal, newVal) -> {
	        for (Parcours parcours : observableParcoursList) {
	            if (parcours.getMode().equals(newVal)) {
	                cbParcours.getSelectionModel().select(parcours);
	                break;
	            }
	        }
	    });

	    UtilFX.setCellFactory(cbParcours, "mode");
	

	}

    @FXML
    void doConnect(ActionEvent event) {
    	Parcours mode= cbParcours.getValue();
    	String parcours= mode.getMode();
    	if(parcours=="Quizz individuel") {
    		//managerGui.showView(ViewParcoursClassique.class);
    	}
    }

}
