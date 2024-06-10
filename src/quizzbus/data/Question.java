package quizzbus.data;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {
	// -------
	// Données observables
	// -------

	private final ObjectProperty<Integer> id = new SimpleObjectProperty<>();
	private final StringProperty enonce = new SimpleStringProperty();
	private final IntegerProperty reponse = new SimpleIntegerProperty();
	private final StringProperty reponse1 = new SimpleStringProperty();
	private final StringProperty reponse2 = new SimpleStringProperty();
	private final StringProperty reponse3 = new SimpleStringProperty();
	private final StringProperty reponse4 = new SimpleStringProperty();
	private final StringProperty astuce = new SimpleStringProperty();
	private final ObjectProperty<Quizz>		quizz = new SimpleObjectProperty<>();

	

	

	// -------
	// Getters & Setters
	// -------


	

	public final ObjectProperty<Quizz> quizzProperty() {
		return this.quizz;
	}
	
	public final Quizz getQuizz() {
		return this.quizzProperty().get();
	}
	
	public final void setQuizz( Quizz quizz) {
		this.quizzProperty().set(quizz);
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
	

	public final IntegerProperty reponseProperty() {
		return this.reponse;
	}

	public final Integer getReponse() {
		return this.reponseProperty().get();
	}

	public final void setReponse(final Integer obj) {
		this.reponseProperty().set(obj);
	}
	

	public final StringProperty reponse1Property() {
		return this.reponse1;
	}

	public final String getReponse1() {
		return this.reponse1Property().get();
	}

	public final void setReponse1(final String obj) {
		this.reponse1Property().set(obj);
	}
	

	public final StringProperty reponse2Property() {
		return this.reponse2;
	}

	public final String getReponse2() {
		return this.reponse2Property().get();
	}

	public final void setReponse2(final String obj) {
		this.reponse2Property().set(obj);
	}
	

	public final StringProperty reponse3Property() {
		return this.reponse3;
	}

	public final String getReponse3() {
		return this.reponse3Property().get();
	}

	public final void setReponse3(final String obj) {
		this.reponse3Property().set(obj);
	}
	

	public final StringProperty reponse4Property() {
		return this.reponse4;
	}

	public final String getReponse4() {
		return this.reponse4Property().get();
	}

	public final void setReponse4(final String obj) {
		this.reponse4Property().set(obj);
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
	public final StringProperty astuceProperty() {
		return this.astuce;
	}
	
	public final String getAstuce() {
		return this.astuceProperty().get();
	}
	
	public final void setAstuce(final String astuce) {
		this.astuceProperty().set(astuce);
	}
	
	
	

}

/*package quizzbus.data;

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
	private final ObservableList<Etre_associer> reponses = FXCollections.observableArrayList();

	

	

	// -------
	// Getters & Setters
	// -------

	
	public ObservableList<Media> getMedias() {
		return medias;
	}

	public ObservableList<Etre_associer> getReponses() {
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

}*/
