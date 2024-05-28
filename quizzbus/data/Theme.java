package quizzbus.data;


import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Theme {
	
	//-------
	// Données observables
	//-------
		
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			nom		= new SimpleStringProperty();
	private final StringProperty			description	= new SimpleStringProperty();
	
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
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
	public final StringProperty descriptionProperty() {
		return this.description;
	}
	
	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	
	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}
	
	//-------
	// hashCode() & equals()
	//-------
	@Override
	public int hashCode() {
		return Objects.hash(description, id, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Theme other = (Theme) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(nom, other.nom);
	}
	

	
	
}
