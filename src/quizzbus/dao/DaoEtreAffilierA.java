package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Parcours;
import quizzbus.data.Quizz;

public class DaoEtreAffilierA extends DaoAbstract {
	//-------
		// Champs
		//-------

		@Inject
		private DaoParcours	daoParcours;
		
		//-------
		// Méthodes auxiliaires
		//-------
		
		protected void setData( Query query, Quizz quizz, Parcours parcours) throws SQLException {
			query.set( "idquizz",	quizz.getId() );
			query.set( "idparcours",parcours.getId() );
		}
		
		protected Parcours build( Query query ) throws SQLException {
			var parcours = new Parcours();
			parcours.setId( query.get( "idparcours", Integer.class ) );
			return parcours;
		}
		
		//-------
		// Actions
		//-------

		public void mettreAJourPourQuizz( Quizz quizz ) {
			var query = createQuery(  "SELECT * FROM etre_affilier_à WHERE idquizz = ?" );
			query.setParam( 1, quizz.getId() );
			query.updateChildren( quizz.getParcours(), this::build, (q, item) -> setData( q, quizz, item ) );
		}

		public List<Parcours> listerPourQuizz(Quizz quizz) {
			var query = createQuery(  "SELECT p.* FROM etre_affilier_à e JOIN Parcours p ON e.idparcours = p.idparcours WHERE e.idquizz = ? ORDER BY p.mode" );
			query.setParam( 1, quizz.getId() );
			return query.getResultList( daoParcours::build );
		}
}
