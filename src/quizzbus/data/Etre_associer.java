package quizzbus.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Etre_associer {
	private final BooleanProperty			vraie	= new SimpleBooleanProperty();
	private final ObjectProperty<Question>question = new SimpleObjectProperty<>();
	private final ObjectProperty<Reponse>reponse = new SimpleObjectProperty<>();
	
	public final BooleanProperty vraieProperty() {
		return this.vraie;
	}
	
	public final boolean isVraie() {
		return this.vraieProperty().get();
	}
	
	public final void setVraie(final boolean vraie) {
		this.vraieProperty().set(vraie);
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
	
	public final ObjectProperty<Reponse> reponseProperty() {
		return this.reponse;
	}
	
	public final Reponse getReponse() {
		return this.reponseProperty().get();
	}
	
	public final void setReponse(final Reponse reponse) {
		this.reponseProperty().set(reponse);
	}
	
	
	
}
