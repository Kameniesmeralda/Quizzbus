package quizzbus.view.question;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.Mode;
import quizzbus.commun.IMapper;
import quizzbus.dao.DaoMedia;
import quizzbus.dao.DaoQuestion;
//import quizzbus.dao.DaoReponse;
import quizzbus.data.Media;
import quizzbus.data.Question;
//import quizzbus.data.Reponse;
import quizzbus.view.systeme.ModelConfig;

public class ModelQuestion {

	// -------
	// Données observables
	// -------

	private final ObservableList<Question> list = FXCollections.observableArrayList();

	private final BooleanProperty flagRefreshingList = new SimpleBooleanProperty();

	private final Question draft = new Question();

	private final ObjectProperty<Question> current = new SimpleObjectProperty<>();

	// Aaron
	private final ObjectProperty<Image> image = new SimpleObjectProperty<Image>();// Aaron

	private final ObservableList<Media> listMedia = FXCollections.observableArrayList();

	private final StringProperty proposition1 = new SimpleStringProperty();
	private final StringProperty proposition2 = new SimpleStringProperty();
	private final StringProperty proposition3 = new SimpleStringProperty();
	private final StringProperty proposition4 = new SimpleStringProperty();

	private final StringProperty astuce = new SimpleStringProperty();

	private final BooleanProperty flag1 = new SimpleBooleanProperty();
	private final BooleanProperty flag2 = new SimpleBooleanProperty();
	private final BooleanProperty flag3 = new SimpleBooleanProperty();
	private final BooleanProperty flag4 = new SimpleBooleanProperty();

	// -------
	// Autres champs
	// -------

	private Mode mode = Mode.NEW;
	@Inject
	private IMapper mapper;
	@Inject
	private DaoQuestion daoQuestion;

	@Inject
	private DaoMedia daoMedia;
	@Inject
	private ModelConfig modelConfig; // Aaron

	private boolean flagModifImage;

	// -------
	// Getters & Setters
	// -------

	@PostConstruct
	public void init() {
		image.addListener(obs -> flagModifImage = true);
	}

	public ObjectProperty<Image> imageProperty() {
		return image;
	}

	public ObservableList<Question> getList() {
		return list;
	}

	public BooleanProperty flagRefreshingListProperty() {
		return flagRefreshingList;
	}

	public Question getDraft() {
		return draft;
	}

	public Property<Question> currentProperty() {
		return current;
	}

	public Question getCurrent() {
		return current.get();
	}

	public void setCurrent(Question item) {
		current.set(item);
	}

	public Mode getMode() {
		return mode;
	}

	// -------
	// Actions
	// -------

	public void refreshList() {
		// flagRefreshingList vaut true pendant la durée
		// du traitement de mise à jour de la liste
		flagRefreshingList.set(true);
		list.setAll(daoQuestion.listerTout());
		flagRefreshingList.set(false);
	}

	public void initDraft(Mode mode) {
		this.mode = mode;
		if (mode == Mode.NEW) {
			mapper.update(draft, new Question());

		} else {
			setCurrent(daoQuestion.retrouver(getCurrent().getId()));
			mapper.update(draft, getCurrent());

//			setProposition1(draft.getReponse().getLibelle());
//			setFlag1(draft.getReponse().isVraie());
//
//			setProposition2(draft.getReponse2().getLibelle());
//			setFlag2(draft.getReponse2().isVraie());
//
//			setProposition3(draft.getReponse3().getLibelle());
//			setFlag3(draft.getReponse3().isVraie());
//
//			setProposition4(draft.getReponse4().getLibelle());
//			setFlag4(draft.getReponse4().isVraie());

			setAstuce(draft.getAstuce().getLibelle());
		}
		// Aaron
		var chemin = getCheminImageCourante();
		if (chemin.exists()) {
			image.set(new Image(chemin.toURI().toString()));
		} else {
			image.set(null);
		} // Aaron
	}

	// Méthode pour modifier l'indicateur flagModifImage
	public void setFlagModifImage(boolean flag) {
		this.flagModifImage = flag;
	}

	// Méthode pour vérifier si l'indicateur flagModifImage est vrai
	public boolean isFlagModifImage() {
		return flagModifImage;
	}

	public void saveDraft() {
		/**
		 * // Vérifie la validité des données
		 * 
		 * // StringBuilder message = new StringBuilder(); // // if ( !
		 * daoCompte.verifierUnicitePseudo( draft.getPseudo(), draft.getId() ) ) { //
		 * message.append( "\nLe pseudo " + draft.getPseudo() + " est déjà utilisé." );
		 * // } // // if ( message.length() > 0 ) { // throw new ExceptionValidation(
		 * message.toString().substring(1) ); // }
		 * 
		 * // Enregistre les données dans la base
		 * 
		 * if (mode == Mode.NEW) { // Insertion daoQuestion.inserer(draft); // Actualise
		 * le courant setCurrent(mapper.update(new Question(), draft)); } else { //
		 * modficiation daoQuestion.modifier(draft); // Actualise le courant
		 * mapper.update(getCurrent(), draft); }
		 * 
		 **/
//		draft.getReponse().setLibelle(getProposition1());
//		draft.getReponse().setVraie(isFlag1());
//
//		draft.getReponse2().setLibelle(getProposition2());
//		draft.getReponse2().setVraie(isFlag2());
//
//		draft.getReponse3().setLibelle(getProposition3());
//		draft.getReponse3().setVraie(isFlag3());
//
//		draft.getReponse4().setLibelle(getProposition4());
//		draft.getReponse4().setVraie(isFlag4());
//		
//		draft.getAstuce().setLibelle(getAstuce());

		daoQuestion.inserer(draft);
		if (mode == Mode.NEW) {
			// Insertion
			// Actualise le courant
			setCurrent(mapper.update(new Question(), draft));
		} else {
			// modficiation
			daoQuestion.modifier(draft);
			// Actualise le courant
			mapper.update(getCurrent(), draft);
		}

		if (flagModifImage) {
			if (image.get() == null) {
				File imageFile = getCheminImageCourante();
				if (imageFile.exists()) {
					imageFile.delete();
				}
			} else {
				// Situation où une image est affichée dans la vue
				// Enregistrer l'image affichée dans un fichier JPEG dans le dossier img
				try {
					var original = SwingFXUtils.fromFXImage(image.get(), null);
					var newImage = new BufferedImage(original.getWidth(), original.getHeight(),
							BufferedImage.TYPE_INT_BGR);
					newImage.createGraphics().drawImage(original, 0, 0, Color.white, null);
					ImageIO.write(newImage, "JPG", getCheminImageCourante());
				} catch (IOException e) {
					// Gérer l'exception en cas de problème lors de l'écriture dans le fichier
					e.printStackTrace();
					// Vous pouvez choisir de lancer une exception plus spécifique ou de gérer
					// autrement l'erreur
				}
			}

		}

	}

	public void deleteCurrent() {
		// Effectue la suppression
		daoQuestion.supprimer(getCurrent().getId());
		// Détermine le nouveau courant
		setCurrent(UtilFX.findNext(list, getCurrent()));

		// Aaron
		getCheminImageCourante().delete();
	}

	// Aaron
	public ObservableList<Media> getImagesFromDatabase() {
		listMedia.setAll(daoMedia.listerTout());
		return listMedia;
	}

	// -------
	// Méthodes auxiliaires
	// -------
	public File getCheminImageCourante() {// Aaron
		var nomFichier = String.format("%06d.jpg", draft.getId());
		var dossierImages = modelConfig.getDossierImages();
		return new File(dossierImages, nomFichier);
	}

	public final StringProperty proposition1Property() {
		return this.proposition1;
	}

	public final String getProposition1() {
		return this.proposition1Property().get();
	}

	public final void setProposition1(final String proposition1) {
		this.proposition1Property().set(proposition1);
	}

	public final StringProperty proposition2Property() {
		return this.proposition2;
	}

	public final String getProposition2() {
		return this.proposition2Property().get();
	}

	public final void setProposition2(final String proposition2) {
		this.proposition2Property().set(proposition2);
	}

	public final StringProperty proposition3Property() {
		return this.proposition3;
	}

	public final String getProposition3() {
		return this.proposition3Property().get();
	}

	public final void setProposition3(final String proposition3) {
		this.proposition3Property().set(proposition3);
	}

	public final StringProperty proposition4Property() {
		return this.proposition4;
	}

	public final String getProposition4() {
		return this.proposition4Property().get();
	}

	public final void setProposition4(final String proposition4) {
		this.proposition4Property().set(proposition4);
	}

	public final BooleanProperty flag1Property() {
		return this.flag1;
	}

	public final boolean isFlag1() {
		return this.flag1Property().get();
	}

	public final void setFlag1(final boolean flag1) {
		this.flag1Property().set(flag1);
	}

	public final BooleanProperty flag2Property() {
		return this.flag2;
	}

	public final boolean isFlag2() {
		return this.flag2Property().get();
	}

	public final void setFlag2(final boolean flag2) {
		this.flag2Property().set(flag2);
	}

	public final BooleanProperty flag3Property() {
		return this.flag3;
	}

	public final boolean isFlag3() {
		return this.flag3Property().get();
	}

	public final void setFlag3(final boolean flag3) {
		this.flag3Property().set(flag3);
	}

	public final BooleanProperty flag4Property() {
		return this.flag4;
	}

	public final boolean isFlag4() {
		return this.flag4Property().get();
	}

	public final void setFlag4(final boolean flag4) {
		this.flag4Property().set(flag4);
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
