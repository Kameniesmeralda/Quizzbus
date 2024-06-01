package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Reponse;

public class DaoReponse extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Reponse WHERE idreponse = ?";
	@Inject
	//private DaoQuestion daoQuestion;
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Reponse reponse ) throws SQLException {
		query.set( "libelle ",		reponse.getLibelle());
		//query.set( "vraie",		reponse.isVraie() );

//		query.set( "idquestion", reponse.getQuestion()==null? null:reponse.getQuestion().getId() );

		//query.set( "idquestion", reponse.getQuestion()==null? null:reponse.getQuestion().getId() );

		
	}
	
	protected Reponse build( Query query ) throws SQLException {
		var reponse = new Reponse();
		reponse.setId(			query.get( "idreponse", Integer.class ) );
		reponse.setLibelle(			query.get( "libelle", String.class ) );
		//reponse.setVraie(		query.get( "vraie", Boolean.class ) );

		var idQuestion= query.get( "idquestion", Integer.class );
		if ( idQuestion != null ) {
		//	reponse.setQuestion( daoQuestion.retrouver( idQuestion ) );
		}

		return reponse;
	}

	//-------
	// Actions
	//-------

	public void inserer(  Reponse reponse)  {
		var query = createQuery( sqlDefault );
		query.insertRow( reponse, this::setData, true );
		reponse.setId( query.get( "idreponse", Integer.class ));
		query.close();
	}

	public void modifier( Reponse reponse  )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, reponse.getId() );
		query.updateRow( reponse, this::setData );
	}

	public void supprimer( int idReponse)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idReponse );
		query.deleteRow();
	}

	public Reponse retrouver( int idReponse)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idReponse);
		return query.getSingleResult( this::build );
	}

	public List<Reponse> listerTout()   {
		var query = createQuery(  "SELECT * FROM Reponse ORDER BY libelle" );
		return query.getResultList( this::build );
	}

	
	
}
