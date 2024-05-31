package quizzbus.data;

import java.util.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reponse {
	//-------
	// Données observables
	//-------
		
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			libelle		= new SimpleStringProperty();
	private final BooleanProperty			vraie	= new SimpleBooleanProperty();
	

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
	
	public final BooleanProperty vraieProperty() {
		return this.vraie;
	}
	
	public final boolean isVraie() {
		return this.vraieProperty().get();
	}
	
	public final void setVraie(final boolean vraie) {
		this.vraieProperty().set(vraie);
	}

	//-------
	// hashCode() & equals()
	//-------
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, libelle, vraie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reponse other = (Reponse) obj;
		return Objects.equals(id, other.id) && Objects.equals(libelle, other.libelle)
				&& Objects.equals(vraie, other.vraie);
	}

	@Override
	public String toString() {
		return "Reponse [id=" + id + ", libelle=" + libelle + ", vraie=" + vraie + "]";
	}


	
	
	
		
	
}
