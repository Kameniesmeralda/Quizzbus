package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import quizzbus.data.Parcours;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoParcours extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Parcours WHERE idparcours = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Parcours parcours ) throws SQLException {
		query.set( "mode",		parcours.getMode() );
	}
	
	protected Parcours build( Query query ) throws SQLException {
		var parcours = new Parcours();
		parcours.setId(			query.get( "idparcours", Integer.class ) );
		parcours.setMode(			query.get( "mode", String.class ) );
		return parcours;
	}

	//-------
	// Actions
	//-------

	public void inserer( Parcours parcours )  {
		var query = createQuery( sqlDefault );
		query.insertRow( parcours, this::setData, true );
		parcours.setId( query.get( "idparcours", Integer.class ));
		query.close();
	}

	public void modifier(Parcours parcours)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, parcours.getId() );
		query.updateRow( parcours, this::setData );
	}

	public void supprimer( int idParcours )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idParcours );
		query.deleteRow();
	}

	public Parcours retrouver( int idParcours )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idParcours );
		return query.getSingleResult( this::build );
	}

	public List<Parcours> listerTout()   {
		var query = createQuery(  "SELECT * FROM Parcours ORDER BY mode" );
		return query.getResultList( this::build );
	}


	
}
