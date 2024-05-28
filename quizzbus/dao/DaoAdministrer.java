package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jfox.jdbc.DaoAbstract;
import jakarta.inject.Inject;
import jfox.jdbc.Query;
import quizzbus.data.Compte;
import quizzbus.data.Poste;

public class DaoAdministrer extends DaoAbstract {
	
	//-------
	// Champs
	//-------

	@Inject
	private DaoPoste	daoPoste;
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Compte compte, Poste poste ) throws SQLException {
		query.set( "idcompte",	compte.getId() );
		query.set( "idposte",   poste.getId() );
	}
	
	protected Poste build( Query query ) throws SQLException {
		var poste = new Poste();
		poste.setId( query.get( "idposte", Integer.class ) );
		return poste;
	}
	
	//-------
	// Actions
	//-------

	public void mettreAJourPourCompte( Compte compte ) {
		var query = createQuery(  "SELECT * FROM administrer WHERE idcompte = ?" );
		query.setParam( 1, compte.getId() );
		query.updateChildren( compte.getAdministrations(), this::build, (q, item) -> setData( q, compte, item ) );
	}

	public List<Poste> listerPourCompte(Compte compte  ) {
		var query = createQuery(  "SELECT p.* FROM administrer a JOIN Poste p ON a.idposte = p.idposte WHERE a.idcompte = ? ORDER BY p.libelle" );
		query.setParam( 1, compte.getId() );
		return query.getResultList( daoPoste::build );
	}
}
