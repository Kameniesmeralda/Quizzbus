package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jfox.jdbc.DaoAbstract;
import jakarta.inject.Inject;
import jfox.jdbc.Query;
import quizzbus.data.Compte;
import quizzbus.data.Configuration_Poste;

public class DaoElaborer extends DaoAbstract {
	
	//-------
	// Champs
	//-------

	@Inject
	private DaoConfigurationPoste	daoConfigurationPoste;
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Compte compte, Configuration_Poste conf ) throws SQLException {
		query.set( "idcompte",	compte.getId() );
		query.set( "idconf",   conf.getId() );
	}
	
	protected Configuration_Poste build( Query query ) throws SQLException {
		var conf = new Configuration_Poste();
		conf.setId( query.get( "idconf", Integer.class ) );
		return conf;
	}
	
	//-------
	// Actions
	//-------

	public void mettreAJourPourCompte( Compte compte ) {
		var query = createQuery(  "SELECT * FROM elaborer WHERE idcompte = ?" );
		query.setParam( 1, compte.getId() );
		query.updateChildren( compte.getConfigurations(), this::build, (q, item) -> setData( q, compte, item ) );
	}

	public List<Configuration_Poste> listerPourCompte(Compte compte  ) {
		var query = createQuery(  "SELECT c.* FROM elaborer e JOIN Configuration_Poste c ON e.idconf = c.idconf WHERE e.idcompte = ? ORDER BY c.heure" );
		query.setParam( 1, compte.getId() );
		return query.getResultList( daoConfigurationPoste::build );
	}
	
	//
}
