package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import jakarta.inject.Inject;
import quizzbus.data.Joueur;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoJoueur extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Joueur WHERE idjoueur = ?";
	@Inject
	private DaoPoste daoPoste;
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Joueur joueur ) throws SQLException {
		query.set( "ville ",		joueur.getVille());
		query.set( "categorie",		joueur.getCategorie() );
		query.set( "idposte", joueur.getPoste()==null? null:joueur.getPoste().getId() );
		
	}
	
	protected Joueur build( Query query ) throws SQLException {
		var joueur = new Joueur();
		joueur.setId(			query.get( "idjoueur", Integer.class ) );
		joueur.setVille(			query.get( "ville", String.class ) );
		joueur.setCategorie(		query.get( "categorie", String.class ) );
		var idPoste = query.get( "idposte", Integer.class );
		if ( idPoste != null ) {
		 joueur.setPoste( daoPoste.retrouver( idPoste ) );
		}
		return joueur;
	}

	//-------
	// Actions
	//-------

	public void inserer( Joueur joueur )  {
		var query = createQuery( sqlDefault );
		query.insertRow( joueur, this::setData, true );
		joueur.setId( query.get( "idjoueur", Integer.class ));
		query.close();
	}

	public void modifier( Joueur joueur )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, joueur.getId() );
		query.updateRow( joueur, this::setData );
	}

	public void supprimer( int idCompte )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idCompte );
		query.deleteRow();
	}

	public Joueur retrouver( int idJoueur )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idJoueur );
		return query.getSingleResult( this::build );
	}

	public List<Joueur> listerTout()   {
		var query = createQuery(  "SELECT * FROM Joueur ORDER BY ville" );
		return query.getResultList( this::build );
	}

	
	
}
