package quizzbus.view.joueur;


import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.text.Text;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.view.ManagerGui;
import quizzbus.view.configuration_poste.ModelConfiguration_Poste;
import quizzbus.view.configuration_poste.ViewConfiguration_Poste;
import quizzbus.view.systeme.ViewDashBoard;

public class ViewResultatForm extends ControllerAbstract {

	@FXML
	private PieChart stat;

	@FXML
	private Text txtModeJeu;

	@FXML
	private Text txtNumQuestion;

	@FXML
	private Text txtScore;

	@Inject
	private ManagerGui managerGui;

	@Inject
	private ModelConfiguration_Poste modelConfiguration_Poste;

	@FXML
	void acceuil(ActionEvent event) {
		managerGui.showView(ViewDashBoard.class);
	}

	@FXML
	void nouveau(ActionEvent event) {
		managerGui.showView(ViewConfiguration_Poste.class);
	}

	@FXML
	private void initialize() {
		refresh();
	}
	@Override
	public void refresh() {
		var draft = modelConfiguration_Poste.getDraft();
		txtNumQuestion.setText(draft.getQuestion() + "/" + draft.getQuestion());
		txtScore.setText(draft.getScore() + "/" + draft.getQuestion());

		ObservableList<Data> score = FXCollections.observableArrayList();
		score.add(new PieChart.Data("Trouvé ("+draft.getScore()+")",draft.getScore()));
		score.add(new PieChart.Data("Raté ("+(draft.getQuestion()-draft.getScore())+")",draft.getQuestion()-draft.getScore()));

		stat.setData(score);
	}
}
