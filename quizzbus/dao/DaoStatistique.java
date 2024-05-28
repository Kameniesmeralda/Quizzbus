package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import quizzbus.data.Statistique;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoStatistique extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Statistique WHERE idstatistique = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Statistique statistique ) throws SQLException {
		query.set( "libelle",		statistique.getLibelle() );
	}
	
	protected Statistique build( Query query ) throws SQLException {
		var statistique = new Statistique();
		statistique.setId(			query.get( "idstat", Integer.class ) );
		statistique.setLibelle(			query.get( "libelle", String.class ) );
		return statistique;
	}

	//-------
	// Actions
	//-------

	public void inserer( Statistique statistique)  {
		var query = createQuery( sqlDefault );
		query.insertRow( statistique, this::setData, true );
		statistique.setId( query.get( "idstat", Integer.class ));
		query.close();
	}

	public void modifier( Statistique statistique)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, statistique.getId() );
		query.updateRow( statistique, this::setData );
	}

	public void supprimer( int idstatistique)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idstatistique );
		query.deleteRow();
	}

	public Statistique retrouver( int idStatistique )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idStatistique );
		return query.getSingleResult( this::build );
	}

	public List<Statistique> listerTout()   {
		var query = createQuery(  "SELECT * FROM Statistique ORDER BY libelle" );
		return query.getResultList( this::build );
	}


	
}
