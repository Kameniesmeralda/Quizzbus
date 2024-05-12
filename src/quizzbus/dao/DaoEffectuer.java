package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Joueur;
import quizzbus.data.Quizz;

public class DaoEffectuer extends DaoAbstract {
	//-------
		// Champs
		//-------

		@Inject
		private DaoJoueur	daoJoueur;
		
		//-------
		// Méthodes auxiliaires
		//-------
		
		protected void setData( Query query, Quizz quizz, Joueur joueur ) throws SQLException {
			query.set( "idquizz",	quizz.getId() );
			query.set( "idjoueur",joueur.getId() );
		}
		
		protected Joueur build( Query query ) throws SQLException {
			var joueur = new Joueur();
			joueur.setId( query.get( "idjoueur", Integer.class ) );
			return joueur;
		}
		
		//-------
		// Actions
		//-------

		public void mettreAJourPourQuizz( Quizz quizz ) {
			var query = createQuery(  "SELECT * FROM effectuer WHERE idquizz = ?" );
			query.setParam( 1, quizz.getId() );
			query.updateChildren( quizz.getJoueurs(), this::build, (q, item) -> setData( q, quizz, item ) );
		}

		public List<Joueur> listerPourQuizz(Quizz quizz) {
			var query = createQuery(  "SELECT j.* FROM effectuer e JOIN Joueur j ON e.idjoueur = j.idjoueur WHERE e.idquizz = ? ORDER BY j.categorie" );
			query.setParam( 1, quizz.getId() );
			return query.getResultList( daoJoueur::build );
		}
}
