package quizzbus.data;

import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Configuration_Poste {
	
	//-------
	// Données observables
	//-------
	
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final ObjectProperty<LocalTime> heure = new SimpleObjectProperty<>();
	private final ObjectProperty<Parcours>	parcours = new SimpleObjectProperty<>();
	private final ObjectProperty<Theme>	theme = new SimpleObjectProperty<>();
	private final IntegerProperty question = new SimpleIntegerProperty();
	private final IntegerProperty score = new SimpleIntegerProperty();


	//-------
	// Getters & Setters
	//-------
	
	
	public final ObjectProperty<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().set(id);
	}
	
	
	public final  ObjectProperty<Theme> themeProperty() {
		return this.theme;
	}
	
	public final Theme getTheme() {
		return this.themeProperty().get();
	}
	
	public final void setTheme(final Theme obj) {
		this.themeProperty().set(obj);
	}
	
	
	public final IntegerProperty scoreProperty() {
		return this.score;
	}
	
	public final Integer getScore() {
		return this.scoreProperty().get();
	}
	
	public final void setScore(final Integer obj) {
		this.scoreProperty().set(obj);
	}
	
	
	public final IntegerProperty questionProperty() {
		return this.question;
	}
	
	public final Integer getQuestion() {
		return this.questionProperty().get();
	}
	
	public final void setQuestion(final Integer obj) {
		this.questionProperty().set(obj);
	}
	
	public final ObjectProperty<Parcours> parcoursProperty() {
		return this.parcours;
	}
	
	public final Parcours getParcours() {
		return this.parcoursProperty().get();
	}
	
	public final void setParcours(final Parcours obj) {
		this.parcoursProperty().set(obj);
	}
	
	public final ObjectProperty<LocalTime> heureProperty() {
		return this.heure;
	}
	
	public final LocalTime getHeure() {
		return this.heureProperty().get();
	}
	
	public final void setHeure(final LocalTime heure) {
		this.heureProperty().set(heure);
	}

	//-------
	// hashCode() & equals()
	//-------
	@Override
	public int hashCode() {
		return Objects.hash(heure, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration_Poste other = (Configuration_Poste) obj;
		return Objects.equals(heure, other.heure) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Configuration_Poste [id=" + id + ", heure=" + heure;
	}

	
	
	
	
	
}
