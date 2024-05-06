package test.git;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Test_FWAB extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		System.out.println( "fake-master 1"  );

		var root = FXMLLoader.<Parent>load( getClass().getResource("View_FWAB.fxml") );
		stage.setScene( new Scene(root) );
		stage.show();
		
		System.out.println( "Stop"  );
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
