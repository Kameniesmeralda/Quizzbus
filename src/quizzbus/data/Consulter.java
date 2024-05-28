package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Consulter {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Compte>compte = new SimpleObjectProperty<>();
	private final ObjectProperty<Statistique>statistique = new SimpleObjectProperty<>();
	
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

	public final ObjectProperty<Statistique> statistiqueProperty() {
		return this.statistique;
	}
	

	public final Statistique getStatistique() {
		return this.statistiqueProperty().get();
	}
	

	public final void setStatistique(final Statistique statistique) {
		this.statistiqueProperty().set(statistique);
	}

	@Override
	public String toString() {
		return "Consulter [compte=" + compte + ", statistique=" + statistique + "]";
	}
	
	
	
	
}
