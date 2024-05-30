package quizzbus.data;


import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Joueur {
	
	//-------
	// Données observables
	//-------
		
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			ville 		= new SimpleStringProperty();
	private final StringProperty			categorie	= new SimpleStringProperty();
	private final ObjectProperty<Poste>		poste = new SimpleObjectProperty<>();
	
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
	
	public final StringProperty villeProperty() {
		return this.ville;
	}
	
	public final String getVille() {
		return this.villeProperty().get();
	}
	
	public final void setVille(final String ville) {
		this.villeProperty().set(ville);
	}
	
	public final StringProperty categorieProperty() {
		return this.categorie;
	}
	
	public final String getCategorie() {
		return this.categorieProperty().get();
	}
	
	public final void setCategorie(final String categorie) {
		this.categorieProperty().set(categorie);
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
		return Objects.hash(categorie, id, poste, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		return Objects.equals(categorie, other.categorie) && Objects.equals(id, other.id)
				&& Objects.equals(poste, other.poste) && Objects.equals(ville, other.ville);
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", ville=" + ville + ", categorie=" + categorie + ", poste=" + poste + "]";
	}
	
	
	
	
}
