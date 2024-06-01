package quizzbus.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Etre_associer {
	private final BooleanProperty			vraie	= new SimpleBooleanProperty();
	private final ObjectProperty<Quizz>quizz = new SimpleObjectProperty<>();
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
	
	public final ObjectProperty<Quizz> quizzProperty() {
		return this.quizz;
	}
	
	public final Quizz getQuizz() {
		return this.quizzProperty().get();
	}
	
	public final void setQuizz(final Quizz quizz) {
		this.quizzProperty().set(quizz);
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
