package quizzbus.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import jfox.javafx.view.ManagerGuiAbstract;
import jfox.javafx.view.View;
import quizzbus.view.systeme.ViewMenu;
//import quizzbus.view.systeme.ViewConnexion;
import quizzbus.view.systeme.ViewPageConnexion;
import quizzbus.view.systeme.ViewVeille;

public class ManagerGui extends ManagerGuiAbstract {
	
	//-------
	// Actions
	//-------

	@Override
	public void configureStage()  {
		
		// Choisit la vue à afficher
		showView( ViewVeille.class );
		
		// Configure le stage
		stage.setTitle( "Aau fil de l'eau" );
		stage.setWidth(806);
		stage.setHeight(700);
		stage.setMinWidth(350);
		stage.setMinHeight(300);
//		stage.sizeToScene();
		stage.setResizable( true );
		stage.getIcons().add(new Image(getClass().getResource("icone.png").toExternalForm()));
		
//		stage.initStyle(StageStyle.UNDECORATED);
		
		
		// Configuration par défaut pour les boîtes de dialogue
		typeConfigDialogDefault = ConfigDialog.class;
	}
	
	@Override
	public Scene createScene( View view ) {

		BorderPane paneRoot = new BorderPane( view.getRoot() );
		paneRoot.setTop( (Node) factoryController.call( MenuBarAppli.class ) );
		
		
		
		// Ajout du menu à gauche
				if( view.getController() != null && view.getController().getClass() != ViewPageConnexion.class && view.getController().getClass() != ViewVeille.class  ) {
					var name = ViewMenu.class.getSimpleName() + ".fxml";
					var location = ViewMenu.class.getResource( name );
					FXMLLoader loader = new FXMLLoader( location );
					loader.setControllerFactory(factoryController);
					Pane menu;
					try {
						menu = loader.load();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					paneRoot.setLeft(menu);
				}

		
		
		
		Scene scene = new Scene( paneRoot );
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}
	
}