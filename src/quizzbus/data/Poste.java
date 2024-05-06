package quizzbus.data;

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Poste {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();


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
	
	//-------
	// hashCode() & equals()
	//-------
		

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poste other = (Poste) obj;
		return Objects.equals(id, other.id);
	}
	

	
	
}
