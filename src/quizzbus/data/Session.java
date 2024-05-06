package quizzbus.data;

import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Session {
	
	//-------
	// Données observables
	//-------
	
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final ObjectProperty<LocalTime> heure = new SimpleObjectProperty<>();


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
	
	public final ObjectProperty<LocalTime> heureProperty() {
		return this.heure;
	}
	
	public final LocalTime getHeure() {
		return this.heureProperty().get();
	}
	
	public final void setHeure(final LocalTime heure) {
		this.heureProperty().set(heure);
	}

	@Override
	public int hashCode() {
		return Objects.hash(heure, id);
	}


	//-------
	// hashCode() & equals()
	//-------
		
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(heure, other.heure) && Objects.equals(id, other.id);
	}
	
	
	
	
}
