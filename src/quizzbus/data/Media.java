package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Media {
	//-------
	// Données observables
	//-------
		
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			titre		= new SimpleStringProperty();
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
	
	public final StringProperty titreProperty() {
		return this.titre;
	}
	
	public final String getTitre() {
		return this.titreProperty().get();
	}
	
	public final void setTitre(final String titre) {
		this.titreProperty().set(titre);
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

	@Override
	public String toString() {
		return "Media [id=" + id + ", titre=" + titre + ", description=" + description + "]";
	}
	
	
	
}
