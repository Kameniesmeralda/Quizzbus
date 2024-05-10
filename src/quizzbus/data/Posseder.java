package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Posseder {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Quizz>quizz = new SimpleObjectProperty<>();
	private final ObjectProperty<Resultat>	resultat = new SimpleObjectProperty<>();

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
	
	public final ObjectProperty<Resultat> resultatProperty() {
		return this.resultat;
	}
	
	public final Resultat getResultat() {
		return this.resultatProperty().get();
	}
	
	public final void setResultat(final Resultat resultat) {
		this.resultatProperty().set(resultat);
	}
	
	
	
	
}
