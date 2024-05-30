package quizzbus.view.systeme;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import quizzbus.data.Media;
import quizzbus.data.Question;
import quizzbus.data.Quizz;
import quizzbus.data.Reponse;

public class ViewQuestion {

	@FXML
    private Label questionLabel;

    @FXML
    private ImageView questionImage;

    @FXML
    private RadioButton answer1;

    @FXML
    private RadioButton answer2;

    @FXML
    private RadioButton answer3;

    private List<Question> questions;
    private int currentIndex;

    public void initialize() {
        // Retrieve questions from the database
        questions = Reponse.getQuestion();
        currentIndex = 0;
        showQuestion(currentIndex);
    }

    private void showQuestion(int index) {
        Question question = questions.get(index);
        questionLabel.setText(question.enonceProperty().get());

        // Load the first media image, if available
        if (!question.getMedias().isEmpty()) {
            questionImage.setImage(new Image(getClass().getResourceAsStream(question.getMedias().get(0).getUrl())));
        } else {
            questionImage.setImage(null); // Or a default image
        }

        // Display the responses
        List<Reponse> reponses = question.getReponses();
        answer1.setText(reponses.get(0).libelleProperty().get());
        answer2.setText(reponses.get(1).libelleProperty().get());
        answer3.setText(reponses.size() > 2 ? reponses.get(2).libelleProperty().get() : "");
    }

    @FXML
    private void NextButtonAction() {
        if (currentIndex < questions.size() - 1) {
            currentIndex++;
            showQuestion(currentIndex);
        }
    }

    @FXML
    private void BackButtonAction() {
        if (currentIndex > 0) {
            currentIndex--;
            showQuestion(currentIndex);
        }
    }

    @FXML
    private void handleHintButtonAction() {
        // Show hint (not implemented)
    }
}
