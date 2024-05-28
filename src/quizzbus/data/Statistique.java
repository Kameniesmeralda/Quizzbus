package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Statistique {
	
	//-------
	// Données observables
	//-------
		
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			libelle		= new SimpleStringProperty();
	
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


	public final StringProperty libelleProperty() {
		return this.libelle;
	}
	


	public final String getLibelle() {
		return this.libelleProperty().get();
	}
	


	public final void setLibelle(final String libelle) {
		this.libelleProperty().set(libelle);
	}


	@Override
	public String toString() {
		return "Statistique [id=" + id + ", libelle=" + libelle + "]";
	}
	
	
	
	
}
