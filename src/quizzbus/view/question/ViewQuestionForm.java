package quizzbus.view.question;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfox.javafx.view.ControllerAbstract;

public class ViewQuestionForm extends ControllerAbstract {
	
	@FXML
	private Label			labId;
	@FXML
	private TextField		txfDescription;
	@FXML
	private TextArea		txaDescription;
}
