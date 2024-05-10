package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalTime;
import quizzbus.data.Resultat;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoResultat extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Resultat  WHERE idresultat = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Resultat  resultat  ) throws SQLException {
		query.set( "heure",		resultat.getHeure() );
		query.set("score", resultat.getScore());
	}
	
	protected Resultat build( Query query ) throws SQLException {
		var resultat = new Resultat();
		resultat.setId(			query.get( "idresultat", Integer.class ) );
		resultat.setHeure(			query.get( "heure", LocalTime.class ) );
		resultat.setScore(			query.get( "score", Integer.class ) );
		return resultat;
	}

	//-------
	// Actions
	//-------

	public void inserer( Resultat  resultat )  {
		var query = createQuery( sqlDefault );
		query.insertRow( resultat, this::setData, true );
		resultat.setId( query.get( "idresultat", Integer.class ));
		query.close();
	}

	public void modifier(Resultat  resultat )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, resultat.getId() );
		query.updateRow( resultat, this::setData );
	}

	public void supprimer( int idResultat )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idResultat );
		query.deleteRow();
	}

	public Resultat retrouver( int idResultat )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idResultat );
		return query.getSingleResult( this::build );
	}

	public List<Resultat> listerTout()   {
		var query = createQuery(  "SELECT * FROM Resultat ORDER BY score" );
		return query.getResultList( this::build );
	}


	
}
