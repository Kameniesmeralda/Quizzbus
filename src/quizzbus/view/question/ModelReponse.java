package quizzbus.view.question;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

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
import quizzbus.dao.DaoReponse;
import quizzbus.dao.DaoMedia;
import quizzbus.data.Reponse;
import quizzbus.view.systeme.ModelConfig;
import quizzbus.data.Etre_associer;
import quizzbus.data.Media;
//import quizzbus.view.systeme.ModelConfig;

public class ModelReponse {



		// -------
		// Données observables
		// -------

		private final ObservableList<Reponse> list = FXCollections.observableArrayList();

		private final BooleanProperty flagRefreshingList = new SimpleBooleanProperty();

		private final Reponse draft = new Reponse();

		private final ObjectProperty<Reponse> reponse = new SimpleObjectProperty<>();

		// Aaron
		private final ObjectProperty<Image> image = new SimpleObjectProperty<Image>();// Aaron

		private final ObservableList<Media> listMedia = FXCollections.observableArrayList();
		
		private final BooleanProperty flag = new SimpleBooleanProperty();


		// -------
		// Autres champs
		// -------

		private Mode mode = Mode.NEW;
		@Inject
		private IMapper mapper;
		@Inject
		private DaoReponse daoReponse;

		@Inject
		private DaoMedia daoMedia;
		@Inject
		private ModelQuestion modelQuestion;
		//@Inject
		//private ModelConfig modelConfig; // Aaron

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

		public ObservableList<Reponse> getList() {
			return list;
		}

		public BooleanProperty flagRefreshingListProperty() {
			return flagRefreshingList;
		}

		public Reponse getDraft() {
			return draft;
		}

		public Property<Reponse> currentProperty() {
			return reponse;
		}

		public Reponse getCurrent() {
			return reponse.get(); 
		}

		public void setCurrent(Reponse item) {
			reponse.set(item);
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
			list.setAll(daoReponse.listerTout());
			flagRefreshingList.set(false);
		}

		public void initDraft(Mode mode) {
			refreshList();
			this.mode = mode;
			if (mode == Mode.NEW) {
				mapper.update(draft, new Reponse());

			} else {
				setCurrent(daoReponse.retrouver(getCurrent().getId()));
				mapper.update(draft, getCurrent());
			}
			/*// Aaron
			var chemin = getCheminImageCourante();
			if (chemin.exists()) {
				image.set(new Image(chemin.toURI().toString()));
			} else {
				image.set(null);
			} // Aaron*/
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
			 * if (mode == Mode.NEW) { // Insertion daoReponse.inserer(draft); // Actualise
			 * le courant setCurrent(mapper.update(new Reponse(), draft)); } else { //
			 * modficiation daoReponse.modifier(draft); // Actualise le courant
			 * mapper.update(getCurrent(), draft); }
			 * 
			 **/
			// Effectue la mise à jour
			
					if ( mode == Mode.NEW ) {
						// Ajout à la question
						var newItem = mapper.update( new Reponse(), draft );
						//modelQuestion.getDraft().getReponses().addAll( newItem );
						// Actualise le corant
						setCurrent( newItem );
					} else {
						// Actualise le courant
						var newItem = mapper.update( getCurrent(), draft );
						//modelQuestion.getDraft().getReponses().add( newItem );
					}
	
			/*if (flagModifImage) {
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
						//ImageIO.write(newImage, "JPG", getCheminImageCourante());
					} catch (IOException e) {
						// Gérer l'exception en cas de problème lors de l'écriture dans le fichier
						e.printStackTrace();
						// Vous pouvez choisir de lancer une exception plus spécifique ou de gérer
						// autrement l'erreur
					}
				}

			}*/

		}

		public void deleteCurrent() {
			// Effectue la suppression
			daoReponse.supprimer(getCurrent().getId());
			// Détermine le nouveau courant
			setCurrent(UtilFX.findNext(list, getCurrent()));

			// Aaron
			//getCheminImageCourante().delete();
		}

		// Aaron
		public ObservableList<Media> getImagesFromDatabase() {
			listMedia.setAll(daoMedia.listerTout());
			return listMedia;
		}

		// -------
		// Méthodes auxiliaires
		// -------
		/*public File getCheminImageCourante() {// Aaron
			var nomFichier = String.format("%06d.jpg", draft.getId());
			var dossierImages = ModelConfig.getDossierImages();
			return new File(dossierImages, nomFichier);
		}*/

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
