package quizzbus.data;

import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Resultat {
	
	//-------
	// Données observables
	//-------
	
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final ObjectProperty<LocalTime> heure = new SimpleObjectProperty<>();
	private final ObjectProperty<Integer>	score			= new SimpleObjectProperty<>();
	private final ObservableList<Statistique> stats = FXCollections.observableArrayList();

	//-------
	// Getters & Setters
	//-------
	public ObservableList<Statistique> getStats() {
		return stats;
	}	
	public final ObjectProperty<Integer> idProperty() {
		return this.id;
	}
	
	public final Integer getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final Integer id) {
		this.idProperty().set(id);
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
	
	public final ObjectProperty<Integer> scoreProperty() {
		return this.score;
	}
	
	public final Integer getScore() {
		return this.scoreProperty().get();
	}
	
	public final void setScore(final Integer score) {
		this.scoreProperty().set(score);
	}

	//-------
	// hashCode() & equals()
	//-------
		

	@Override
	public int hashCode() {
		return Objects.hash(heure, id, score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resultat other = (Resultat) obj;
		return Objects.equals(heure, other.heure) && Objects.equals(id, other.id) && Objects.equals(score, other.score);
	}
	@Override
	public String toString() {
		return "Resultat [id=" + id + ", heure=" + heure + ", score=" + score + ", stats=" + stats + "]";
	}
	
	
	
}
