package quizzbus.view.systeme;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ViewDashboard implements Initializable{
    
	@FXML 
	private Label exit;
	@FXML
	private StackPane contentArea;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("ViewDashboard.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(fxml);
		}catch(IOException ex){
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,ex);
		}
	}

	
	 public   void Dashboard(javafx.event.ActionEvent event) throws IOException {
		 Parent fxml = FXMLLoader.load(getClass().getResource("ViewDashbord.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(fxml);
	    }
	 
	 public   void QuestionQuizz(javafx.event.ActionEvent event) throws IOException {
		 Parent fxml = FXMLLoader.load(getClass().getResource("ViewQuestion.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(fxml);
	    }
	 
	 public   void Session(javafx.event.ActionEvent event) throws IOException {
		 Parent fxml = FXMLLoader.load(getClass().getResource("ViewDashbord.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(fxml);
	    }
	 
	 public   void Utilisateur(javafx.event.ActionEvent event) throws IOException {
		 Parent fxml = FXMLLoader.load(getClass().getResource("ViewUser.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(fxml);
	    }
	
}
