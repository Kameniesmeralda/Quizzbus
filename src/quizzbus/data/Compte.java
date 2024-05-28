package quizzbus.data;

import java.time.LocalDate;
import java.util.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Compte  {
	
	//-------
	// Données observables
	//-------
	
	private final ObjectProperty<Integer>	id			= new SimpleObjectProperty<>();
	private final StringProperty			nom		= new SimpleStringProperty();
	private final StringProperty			prenom		= new SimpleStringProperty();
	private final ObjectProperty<LocalDate> dateNaissance = new SimpleObjectProperty<>();
	private final StringProperty			motDePasse	= new SimpleStringProperty();
	private final StringProperty			email 		= new SimpleStringProperty();
	private final BooleanProperty			flagAdmin	= new SimpleBooleanProperty();
	private final ObservableList<Poste> administrations = FXCollections.observableArrayList();
	private final ObservableList<Statistique> statistiques = FXCollections.observableArrayList();
	private final ObservableList<Configuration_Poste> configurations = FXCollections.observableArrayList();

	//-------
	// Getters & Setters
	//-------

	public ObservableList<Poste> getAdministrations() {
		return administrations;
	}
	public ObservableList<Statistique> getStatistiques() {
		return statistiques;
	}
	public ObservableList<Configuration_Poste> getConfigurations() {
		return configurations;
	}

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

	public final StringProperty motDePasseProperty() {
		return this.motDePasse;
	}

	public final String getMotDePasse() {
		return this.motDePasseProperty().get();
	}

	public final void setMotDePasse(final String motDePasse) {
		this.motDePasseProperty().set(motDePasse);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	public final BooleanProperty flagAdminProperty() {
		return this.flagAdmin;
	}

	public final boolean isFlagAdmin() {
		return this.flagAdminProperty().get();
	}

	public final void setFlagAdmin(final boolean flagAdmin) {
		this.flagAdminProperty().set(flagAdmin);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	

	public final String getPrenom() {
		return this.prenomProperty().get();
	}
	

	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}

	public final ObjectProperty<LocalDate> dateNaissanceProperty() {
		return this.dateNaissance;
	}
	

	public final LocalDate getDateNaissance() {
		return this.dateNaissanceProperty().get();
	}
	

	public final void setDateNaissance(final LocalDate dateNaissance) {
		this.dateNaissanceProperty().set(dateNaissance);
	}
	
	
	//-------
	// hashCode() & equals()
	//-------

	@Override
	public int hashCode() {
		return Objects.hash(id.get() );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(id.get(), other.id.get() );
	}
	@Override
	public String toString() {
		return "Compte [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", motDePasse=" + motDePasse + ", email=" + email + ", flagAdmin=" + flagAdmin + ", administrations="
				+ administrations + ", statistiques=" + statistiques + ", configurations=" + configurations + "]";
	}


	
	
	
}
