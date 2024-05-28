package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import quizzbus.data.Astuce;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoAstuce extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Astuce WHERE idastuce = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Astuce astuce ) throws SQLException {
		query.set( "libelle",		astuce.getLibelle() );
	}
	
	protected Astuce build( Query query ) throws SQLException {
		var astuce = new Astuce();
		astuce.setId(			query.get( "idastuce", Integer.class ) );
		astuce.setLibelle(			query.get( "libelle", String.class ) );
		return astuce;
	}

	//-------
	// Actions
	//-------

	public void inserer( Astuce astuce )  {
		var query = createQuery( sqlDefault );
		query.insertRow( astuce, this::setData, true );
		astuce.setId( query.get( "idastuce", Integer.class ));
		query.close();
	}

	public void modifier( Astuce astuce)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, astuce.getId() );
		query.updateRow( astuce, this::setData );
	}

	public void supprimer( int idAstuce )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idAstuce );
		query.deleteRow();
	}

	public Astuce retrouver( int idAstuce )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idAstuce );
		return query.getSingleResult( this::build );
	}

	public List<Astuce> listerTout()   {
		var query = createQuery(  "SELECT * FROM Astuce ORDER BY libelle" );
		return query.getResultList( this::build );
	}


	
}
