package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Contenir {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Quizz>quizz = new SimpleObjectProperty<>();
	private final ObjectProperty<Question>	question = new SimpleObjectProperty<>();

	//-------
	// Getters & Setters
	//-------
	
	public final ObjectProperty<Quizz> quizzProperty() {
		return this.quizz;
	}
	
	public final Quizz getQuizz() {
		return this.quizzProperty().get();
	}
	
	public final void setQuizz(final Quizz quizz) {
		this.quizzProperty().set(quizz);
	}
	
	public final ObjectProperty<Question> questionProperty() {
		return this.question;
	}
	
	public final Question getQuestion() {
		return this.questionProperty().get();
	}
	
	public final void setQuestion(final Question question) {
		this.questionProperty().set(question);
	}

	@Override
	public String toString() {
		return "Contenir [quizz=" + quizz + ", question=" + question + "]";
	}
	
	
	
	
	
}
