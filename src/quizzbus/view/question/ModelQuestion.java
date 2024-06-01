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
	
	private final StringProperty proposition = new SimpleStringProperty();
	private final BooleanProperty flag = new SimpleBooleanProperty();
	
	private final StringProperty astuce = new SimpleStringProperty();
	
	// -------
	// Autres champs
	// -------

	private Mode mode = Mode.NEW;
	@Inject
	private IMapper mapper;
	@Inject
	private DaoQuestion daoQuestion;
	@Inject
	private ModelReponse modelReponse;

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
			mapper.update(draft, getCurrent());
			setCurrent(daoQuestion.retrouver(getCurrent().getId()));
            
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

		
		if (mode == Mode.NEW) {
			// Insertion
			daoQuestion.inserer(draft);
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

	public final StringProperty astuceProperty() {
		return this.astuce;
	}

	public final String getAstuce() {
		return this.astuceProperty().get();
	}

	public final void setAstuce(final String astuce) {
		this.astuceProperty().set(astuce);
	}

	public final StringProperty propositionProperty() {
		return this.proposition;
	}
	

	public final String getProposition() {
		return this.propositionProperty().get();
	}
	

	public final void setProposition(final String proposition) {
		this.propositionProperty().set(proposition);
	}
	

	public final BooleanProperty flagProperty() {
		return this.flag;
	}
	

	public final boolean isFlag() {
		return this.flagProperty().get();
	}
	

	public final void setFlag(final boolean flag) {
		this.flagProperty().set(flag);
	}
}
