package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jfox.jdbc.DaoAbstract;
import jakarta.inject.Inject;
import jfox.jdbc.Query;
import quizzbus.data.Compte;
import quizzbus.data.Statistique;

public class DaoConsulter extends DaoAbstract {
	
	//-------
	// Champs
	//-------

	@Inject
	private DaoStatistique	daoStatistique;
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Compte compte, Statistique statistique ) throws SQLException {
		query.set( "idcompte",	compte.getId() );
		query.set( "idstat",   statistique.getId() );
	}
	
	protected Statistique build( Query query ) throws SQLException {
		var statistique = new Statistique();
		statistique.setId( query.get( "idstat", Integer.class ) );
		return statistique;
	}
	
	//-------
	// Actions
	//-------

	public void mettreAJourPourCompte( Compte compte ) {
		var query = createQuery(  "SELECT * FROM consulter WHERE idcompte = ?" );
		query.setParam( 1, compte.getId() );
		query.updateChildren( compte.getStatistiques(), this::build, (q, item) -> setData( q,  compte,item ) );
	}

	public List<Statistique> listerPourMemo(Compte compte  ) {
		var query = createQuery(  "SELECT s.* FROM consulter c JOIN Statistique s ON c.idstat = s.idstat WHERE c.idcompte = ? ORDER BY s.libelle" );
		query.setParam( 1, compte.getId() );
		return query.getResultList( daoStatistique::build );
	}
}
