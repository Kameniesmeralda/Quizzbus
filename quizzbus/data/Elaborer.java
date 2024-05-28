package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Elaborer {

	//-------
	// Données observables
	//-------
			
	private final ObjectProperty<Compte>compte = new SimpleObjectProperty<>();
	private final ObjectProperty<Configuration_Poste>	ConfigurationPoste = new SimpleObjectProperty<>();
	
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
	
	public final ObjectProperty<Configuration_Poste> ConfigurationPosteProperty() {
		return this.ConfigurationPoste;
	}
	
	public final Configuration_Poste getConfigurationPoste() {
		return this.ConfigurationPosteProperty().get();
	}
	
	public final void setConfigurationPoste(final Configuration_Poste ConfigurationPoste) {
		this.ConfigurationPosteProperty().set(ConfigurationPoste);
	}
	
	
	
}
