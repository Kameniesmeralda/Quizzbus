package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class EtreAffilierA {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Quizz>quizz = new SimpleObjectProperty<>();
	private final ObjectProperty<Parcours>parcours = new SimpleObjectProperty<>();

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

	public final ObjectProperty<Parcours> parcoursProperty() {
		return this.parcours;
	}
	

	public final Parcours getParcours() {
		return this.parcoursProperty().get();
	}
	

	public final void setParcours(final Parcours parcours) {
		this.parcoursProperty().set(parcours);
	}
	
	
	
	
	
	
	
}
