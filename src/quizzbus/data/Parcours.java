package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Parcours {
	//-------
	// Données observables
	//-------
		
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			mode		= new SimpleStringProperty();
	
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
	
	public final StringProperty modeProperty() {
		return this.mode;
	}
	
	public final String getMode() {
		return this.modeProperty().get();
	}
	
	public final void setMode(final String mode) {
		this.modeProperty().set(mode);
	}

	@Override
	public String toString() {
		return "Parcours [id=" + id + ", mode=" + mode + "]";
	}
	
	
	
}
