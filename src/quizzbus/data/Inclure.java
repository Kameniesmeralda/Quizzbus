package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Inclure {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Media>media = new SimpleObjectProperty<>();
	private final ObjectProperty<Question>	question = new SimpleObjectProperty<>();
	
	//-------
	// Getters & Setters
	//-------
	
	public final ObjectProperty<Media> mediaProperty() {
		return this.media;
	}
	
	public final Media getMedia() {
		return this.mediaProperty().get();
	}
	
	public final void setMedia(final Media media) {
		this.mediaProperty().set(media);
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
		return "Inclure [media=" + media + ", question=" + question + "]";
	}
	

	
	
	
}
