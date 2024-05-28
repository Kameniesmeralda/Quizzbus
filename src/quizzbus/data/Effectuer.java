package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Effectuer {
	
	//-------
	// Données observables
	//-------
			
	@Override
	public String toString() {
		return "Effectuer [quizz=" + quizz + ", joueur=" + joueur + "]";
	}


	private final ObjectProperty<Quizz>quizz = new SimpleObjectProperty<>();
	private final ObjectProperty<Joueur>joueur = new SimpleObjectProperty<>();

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

	public final ObjectProperty<Joueur> joueurProperty() {
		return this.joueur;
	}
	

	public final Joueur getJoueur() {
		return this.joueurProperty().get();
	}
	

	public final void setJoueur(final Joueur joueur) {
		this.joueurProperty().set(joueur);
	}
	
	
	
	
	
	
	
	
}
