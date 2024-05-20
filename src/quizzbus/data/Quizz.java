package quizzbus.data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Quizz {
	//-------
	// Données observables
	//-------
		
	private final ObjectProperty<Integer>  id			= new SimpleObjectProperty<>();
	private final StringProperty		   description		= new SimpleStringProperty();
	private final ObjectProperty<Theme>	   theme = new SimpleObjectProperty<>();
	private final ObservableList<Question> questions = FXCollections.observableArrayList();
	private final ObservableList<Joueur>   joueurs = FXCollections.observableArrayList();
	private final ObservableList<Parcours> parcours = FXCollections.observableArrayList();
	private final ObservableList<Resultat> resultats = FXCollections.observableArrayList();

	//-------
	// Getters & Setters
	//-------
	public ObservableList<Question> getQuestions() {
		return questions;
	}
	public ObservableList<Joueur> getJoueurs() {
		return joueurs;
	}
	public ObservableList<Resultat> getResultats() {
		return resultats;
	}
	public ObservableList<Parcours> getParcours() {
		return parcours;
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
	
	public final StringProperty descriptionProperty() {
		return this.description;
	}
	
	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	
	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}
	
	public final ObjectProperty<Theme> themeProperty() {
		return this.theme;
	}
	
	public final Theme getTheme() {
		return this.themeProperty().get();
	}
	
	public final void setTheme(final Theme theme) {
		this.themeProperty().set(theme);
	}
	
	
	
	

}
