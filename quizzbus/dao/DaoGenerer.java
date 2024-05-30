package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Resultat;
import quizzbus.data.Statistique;

public class DaoGenerer extends DaoAbstract {
	//-------
		// Champs
		//-------

		@Inject
		private DaoStatistique	daoStatistique;
		
		//-------
		// Méthodes auxiliaires
		//-------
		
		protected void setData( Query query, Resultat resultat, Statistique stat ) throws SQLException {
			query.set( "idresultat",resultat.getId() );
			query.set( "idstat",	stat.getId() );
			
		}
		
		protected Statistique build( Query query ) throws SQLException {
			var stat = new Statistique();
			stat.setId( query.get( "idstat", Integer.class ) );
			return stat;
		}
		
		//-------
		// Actions
		//-------

		public void mettreAJourPourResultat( Resultat resultat ) {
			var query = createQuery(  "SELECT * FROM generer WHERE idresultat = ?" );
			query.setParam( 1, resultat.getId() );
			query.updateChildren( resultat.getStats(), this::build, (q, item) -> setData( q, resultat, item ) );
		}

		public List<Statistique> listerPourResultat(Resultat resultat) {
			var query = createQuery(  "SELECT s.* FROM generer g JOIN Statistiques s ON g.idstat = s.idstat WHERE g.idquizz = ? ORDER BY s.libelle" );
			query.setParam( 1, resultat.getId() );
			return query.getResultList( daoStatistique::build );
		}
}
