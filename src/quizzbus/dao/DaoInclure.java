package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;


import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Question;
import quizzbus.data.Media;

public class DaoInclure extends DaoAbstract {
	//-------
		// Champs
		//-------

		@Inject
		private DaoMedia	daoMedia;
		
		//-------
		// Méthodes auxiliaires
		//-------
		
		protected void setData( Query query, Question question, Media media ) throws SQLException {
			query.set( "idquestion",question.getId() );
			query.set( "idmedia",	media.getId() );
			
		}
		
		protected Media build( Query query ) throws SQLException {
			var media = new Media();
			media.setId( query.get( "idmedia", Integer.class ) );
			return media;
		}
		
		//-------
		// Actions
		//-------

		/*public void mettreAJourPourQuestion( Question question) {
			var query = createQuery(  "SELECT * FROM inclure WHERE idquestion = ?" );
			query.setParam( 1, question.getId() );
			query.updateChildren( question.getMedias(), this::build, (q, item) -> setData( q, question, item ) );
		}*/

		public List<Media> listerPourQuestion( Question question) {
			var query = createQuery(  "SELECT m.* FROM inclure i JOIN Media m ON i.idmedia = m.idmedia WHERE i.idquestion = ? ORDER BY m.titre" );
			query.setParam( 1, question.getId() );
			return query.getResultList( daoMedia::build );
		}
}
