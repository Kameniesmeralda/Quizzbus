package quizzbus.data;

import java.time.LocalTime;
import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Configuration_Poste {
	
	//-------
	// Données observables
	//-------
	
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final ObjectProperty<LocalTime> heure = new SimpleObjectProperty<>();
	private final ObjectProperty<Poste>	poste = new SimpleObjectProperty<>();


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

	public final ObjectProperty<Poste> posteProperty() {
		return this.poste;
	}
	

	public final Poste getPoste() {
		return this.posteProperty().get();
	}
	

	public final void setPoste(final Poste poste) {
		this.posteProperty().set(poste);
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
		return "Configuration_Poste [id=" + id + ", heure=" + heure + ", poste=" + poste + "]";
	}

	
	
	
	
	
}
