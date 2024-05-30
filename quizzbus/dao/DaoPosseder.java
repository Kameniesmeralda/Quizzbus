package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Quizz;
import quizzbus.data.Resultat;

public class DaoPosseder extends DaoAbstract {
	//-------
		// Champs
		//-------

		@Inject
		private DaoResultat	daoResultat;
		
		//-------
		// Méthodes auxiliaires
		//-------
		
		protected void setData( Query query, Quizz quizz, Resultat resultat ) throws SQLException {
			query.set( "idquizz",	quizz.getId() );
			query.set( "idresultat",resultat.getId() );
		}
		
		protected Resultat build( Query query ) throws SQLException {
			var res = new Resultat();
			res.setId( query.get( "idresultat", Integer.class ) );
			return res;
		}
		
		//-------
		// Actions
		//-------

		public void mettreAJourPourQuizz( Quizz quizz ) {
			var query = createQuery(  "SELECT * FROM posseder WHERE idquizz = ?" );
			query.setParam( 1, quizz.getId() );
			query.updateChildren( quizz.getResultats(), this::build, (q, item) -> setData( q, quizz, item ) );
		}

		public List<Resultat> listerPourQuizz(Quizz quizz) {
			var query = createQuery(  "SELECT r.* FROM posseder p JOIN Resultat r ON p.idresultat = r.idresultat WHERE p.idquizz = ? ORDER BY r.score" );
			query.setParam( 1, quizz.getId() );
			return query.getResultList( daoResultat::build );
		}
}
