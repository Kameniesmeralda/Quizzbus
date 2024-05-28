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
	private final ObjectProperty<Reponse> reponse = new SimpleObjectProperty<Reponse>();
	private final ObjectProperty<Reponse> reponse2 = new SimpleObjectProperty<Reponse>();
	private final ObjectProperty<Reponse> reponse3 = new SimpleObjectProperty<Reponse>();
	private final ObjectProperty<Reponse> reponse4 = new SimpleObjectProperty<Reponse>();

	// -------
	// Getters & Setters
	// -------

	public ObservableList<Media> getMedias() {
		return medias;
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

	public final ObjectProperty<Reponse> reponseProperty() {
		return this.reponse;
	}

	public final Reponse getReponse() {
		return this.reponseProperty().get();
	}

	public final void setReponse(final Reponse reponse) {
		this.reponseProperty().set(reponse);
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
		return "Question [id=" + id + ", enonce=" + enonce + ", astuce=" + astuce
				+ ", medias=" + medias + ", reponse=" + reponse + "]";
	}

	public final ObjectProperty<Reponse> reponse2Property() {
		return this.reponse2;
	}
	

	public final Reponse getReponse2() {
		return this.reponse2Property().get();
	}
	

	public final void setReponse2(final Reponse reponse2) {
		this.reponse2Property().set(reponse2);
	}
	

	public final ObjectProperty<Reponse> reponse3Property() {
		return this.reponse3;
	}
	

	public final Reponse getReponse3() {
		return this.reponse3Property().get();
	}
	

	public final void setReponse3(final Reponse reponse3) {
		this.reponse3Property().set(reponse3);
	}
	

	public final ObjectProperty<Reponse> reponse4Property() {
		return this.reponse4;
	}
	

	public final Reponse getReponse4() {
		return this.reponse4Property().get();
	}
	

	public final void setReponse4(final Reponse reponse4) {
		this.reponse4Property().set(reponse4);
	}
	
	
	

}
