package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Generer {
	
	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Resultat>resultat = new SimpleObjectProperty<>();
	private final ObjectProperty<Statistique>statistique = new SimpleObjectProperty<>();
	
	//-------
	// Getters & Setters
	//-------
	
	public final ObjectProperty<Resultat> resultatProperty() {
		return this.resultat;
	}
	
	public final Resultat getResultat() {
		return this.resultatProperty().get();
	}
	
	public final void setResultat(final Resultat resultat) {
		this.resultatProperty().set(resultat);
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
		return "Generer [resultat=" + resultat + ", statistique=" + statistique + "]";
	}
	

	
	
}
