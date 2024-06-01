package quizzbus.data;

import java.util.Objects;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Question {
	// -------
	// Données observables
	// -------

	private final ObjectProperty<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty enonce = new SimpleStringProperty();
	private final ObjectProperty<Astuce> astuce = new SimpleObjectProperty<>();
	private final ObservableList<Media> medias = FXCollections.observableArrayList();
	

	// -------
	// AUtres Données observables
	// -------
	private final ObservableList<Reponse>	reponses	= FXCollections.observableArrayList();

	// -------
	// Getters & Setters
	// -------

	
	public ObservableList<Media> getMedias() {
		return medias;
	}

	public ObservableList<Reponse> getReponses() {
		return reponses;
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

	public final StringProperty enonceProperty() {
		return this.enonce;
	}

	public final String getEnonce() {
		return this.enonceProperty().get();
	}

	public final void setEnonce(final String enonce) {
		this.enonceProperty().set(enonce);
	}

	public final ObjectProperty<Astuce> astuceProperty() {
		return this.astuce;
	}

	public final Astuce getAstuce() {
		return this.astuceProperty().get();
	}

	public final void setAstuce(final Astuce astuce) {
		this.astuceProperty().set(astuce);
	}

	@Override
	public int hashCode() {
		return Objects.hash(enonce);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(enonce, other.enonce);
	}

	@Override
	public String toString() {

		return getEnonce();
	}

}
