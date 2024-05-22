package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Administrer {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Compte>compte = new SimpleObjectProperty<>();
	private final ObjectProperty<Poste>	poste = new SimpleObjectProperty<>();
	
	//-------
	// Getters & Setters
	//-------
	public final ObjectProperty<Compte> compteProperty() {
		return this.compte;
	}
	
	public final Compte getCompte() {
		return this.compteProperty().get();
	}
	
	public final void setCompte(final Compte compte) {
		this.compteProperty().set(compte);
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
	
	
}
