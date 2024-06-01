package quizzbus.view.quizz;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.Mode;
import quizzbus.commun.IMapper;
import quizzbus.dao.DaoQuizz;
import quizzbus.data.Quizz;
import quizzbus.data.Theme;
import quizzbus.view.systeme.ModelConfig;
import quizzbus.view.theme.ModelTheme;

public class ModelQuizz {

	// -------
	// Données observables
	// -------

	private final ObservableList<Quizz> list = FXCollections.observableArrayList();

	private final BooleanProperty flagRefreshingList = new SimpleBooleanProperty();

	private final Quizz draft = new Quizz();

	private final ObjectProperty<Quizz> current = new SimpleObjectProperty<>();
	// Aaron
	private final ObjectProperty<Image> image = new SimpleObjectProperty<Image>();

	// -------
	// Autres champs
	// -------

	private Mode mode = Mode.NEW;
	@Inject
	private IMapper mapper;
	@Inject
	private DaoQuizz daoQuizz;
	@Inject
	private ModelTheme modelTheme;
	@Inject
	private ModelConfig modelConfig; // Aaron

	private boolean flagModifImage;
	// -------
	// Getters & Setters
	// -------

	public ObservableList<Quizz> getList() {
		return list;
	}

	public BooleanProperty flagRefreshingListProperty() {
		return flagRefreshingList;
	}

	public Quizz getDraft() {
		return draft;
	}

	public ObservableList<Theme> getTheme() {
		return modelTheme.getList();
	}

	public Property<Quizz> currentProperty() {
		return current;
	}

	public Quizz getCurrent() {
		return current.get();
	}

	public void setCurrent(Quizz item) {
		current.set(item);
	}

	public Mode getMode() {
		return mode;
	}

	// -------
	// Actions
	// -------

	@PostConstruct
	public void init() {
		image.addListener(obs -> flagModifImage = true);
		
	}

	public void refreshList() {
		// flagRefreshingList vaut true pendant la durée
		// du traitement de mise à jour de la liste
		flagRefreshingList.set(true);
		list.setAll(daoQuizz.listerTout());
		flagRefreshingList.set(false);
	}

	public void initDraft(Mode mode) {
		refreshList();
		this.mode = mode;
		if (mode == Mode.NEW) {
			mapper.update(draft, new Quizz());
		} else {
			mapper.update(draft, getCurrent());
			setCurrent(daoQuizz.retrouver(getCurrent().getId()));			
		}

		var chemin = getCheminImageCourante();
		if (chemin.exists()) {
			image.set(new Image(chemin.toURI().toString()));
		} else {
			image.set(null);
		}

		flagModifImage = false;
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

		// Vérifie la validité des données

//		StringBuilder message = new StringBuilder();
//		
//		if ( ! daoCompte.verifierUnicitePseudo( draft.getPseudo(), draft.getId() ) ) {
//			message.append( "\nLe pseudo " + draft.getPseudo() + " est déjà utilisé." );
//		}
//		
//		if ( message.length() > 0 ) {
//			throw new ExceptionValidation( message.toString().substring(1) );
//		}

		// Enregistre les données dans la base

		if (mode == Mode.NEW) {
			// Insertion
			daoQuizz.inserer(draft);
			// Actualise le courant
			setCurrent(mapper.update(new Quizz(), draft));
		} else {
			// modficiation
			daoQuizz.modifier(draft);
			// Actualise le courant
			mapper.update(getCurrent(), draft);
		}
		if ( flagModifImage ) {
			if ( image.get() == null ) {
				 File imageFile = getCheminImageCourante();
		            if (imageFile.exists()) {
		                imageFile.delete();
		            }
			} else {
				// Situation où une image est affichée dans la vue
	            // Enregistrer l'image affichée dans un fichier JPEG dans le dossier img
	            try {
	            	var original = SwingFXUtils.fromFXImage( image.get(), null );
	            	var newImage = new BufferedImage( original.getWidth(),
	            	original.getHeight(), BufferedImage.TYPE_INT_BGR );
	            	newImage.createGraphics().drawImage(original, 0, 0, Color.white, null);
	            	ImageIO.write( newImage, "JPG", getCheminImageCourante() );
	            } catch (IOException e) {
	                // Gérer l'exception en cas de problème lors de l'écriture dans le fichier
	                e.printStackTrace();
	                // Vous pouvez choisir de lancer une exception plus spécifique ou de gérer autrement l'erreur
	            }
			}
			
		}
	}

	public void deleteCurrent() {
		// Effectue la suppression
		daoQuizz.supprimer(getCurrent().getId());
		// Détermine le nouveau courant
		setCurrent(UtilFX.findNext(list, getCurrent()));
		getCheminImageCourante().delete();
	}

	// -------
	// Méthodes auxiliaires
	// -------
	public File getCheminImageCourante() {// Aaron
		var nomFichier = String.format("%06d.jpg", draft.getId());
		var dossierImages = modelConfig.getDossierImages();
		return new File(dossierImages, nomFichier);
	}

}
