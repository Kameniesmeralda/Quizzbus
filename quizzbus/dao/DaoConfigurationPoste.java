package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;

import java.time.LocalTime;
import quizzbus.data.Configuration_Poste;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoConfigurationPoste extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Configuration_Poste WHERE idconf = ?";
	@Inject
	private DaoPoste daoPoste;
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Configuration_Poste conf ) throws SQLException {
		query.set( "heure",		conf.getHeure() );
		query.set( "idposte", conf.getPoste()==null? null:conf.getPoste().getId() );
	
	}
	
	protected Configuration_Poste build( Query query ) throws SQLException {
		var conf = new Configuration_Poste();
		conf.setId(			query.get( "idcompte", Integer.class ) );
		conf.setHeure(		query.get( "idconf", LocalTime.class ) );
		var idPoste = query.get( "idposte", Integer.class );
		if ( idPoste != null ) {
		 conf.setPoste( daoPoste.retrouver( idPoste ) );
		}
		return conf;
	}

	//-------
	// Actions
	//-------

	public void inserer( Configuration_Poste conf )  {
		var query = createQuery( sqlDefault );
		query.insertRow( conf, this::setData, true );
		conf.setId( query.get( "idconf", Integer.class ));
		query.close();
	}

	public void modifier(Configuration_Poste conf )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, conf.getId() );
		query.updateRow( conf, this::setData );
	}

	public void supprimer( int idConf )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idConf );
		query.deleteRow();
	}

	public Configuration_Poste retrouver( int idConf )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idConf );
		return query.getSingleResult( this::build );
	}

	public List<Configuration_Poste> listerTout()   {
		var query = createQuery( sqlDefault );
		return query.getResultList( this::build );
	}


	
}
