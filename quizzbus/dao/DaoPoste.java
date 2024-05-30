package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import quizzbus.data.Poste;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoPoste extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Poste WHERE idposte = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Poste poste ) throws SQLException {
		query.set( "libelle",		poste.getLibelle() );
	}
	
	protected Poste build( Query query ) throws SQLException {
		var poste = new Poste();
		poste.setId(			query.get( "idposte", Integer.class ) );
		poste.setLibelle(			query.get( "libelle", String.class ) );
		return poste;
	}

	//-------
	// Actions
	//-------

	public void inserer(  Poste poste)  {
		var query = createQuery( sqlDefault );
		query.insertRow( poste, this::setData, true );
		poste.setId( query.get( "idposte", Integer.class ));
		query.close();
	}

	public void modifier( Poste poste)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, poste.getId() );
		query.updateRow( poste, this::setData );
	}

	public void supprimer( int idPoste)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idPoste );
		query.deleteRow();
	}

	public Poste retrouver( int idPoste )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idPoste );
		return query.getSingleResult( this::build );
	}

	public List<Poste> listerTout()   {
		var query = createQuery(  "SELECT * FROM Poste ORDER BY libelle" );
		return query.getResultList( this::build );
	}


	
}
