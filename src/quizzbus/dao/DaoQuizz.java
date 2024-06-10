package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import jakarta.inject.Inject;
import quizzbus.data.Quizz;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoQuizz extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Quiz WHERE idquizz = ?";
	@Inject
	private DaoTheme daoTheme;
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Quizz quizz ) throws SQLException {
	query.set( "description",		quizz.getDescription());
	query.set( "idtheme", quizz.getTheme()==null? null:quizz.getTheme().getId() );
		
	}
	
	protected Quizz build( Query query ) throws SQLException {
		var quizz = new Quizz();
		quizz.setId(			query.get( "idquizz", Integer.class ) );
		quizz.setDescription(   query.get( "description", String.class ) );
		var idTheme = query.get( "idtheme", Integer.class );
		if ( idTheme != null ) {
			quizz.setTheme( daoTheme.retrouver( idTheme ) );
		}
		return quizz;
	}

	//-------
	// Actions
	//-------

	public void inserer(  Quizz quizz  )  {
		var query = createQuery( sqlDefault );
		query.insertRow( quizz, this::setData, true );
		quizz.setId( query.get( "idquizz", Integer.class ));
		query.close();
	}

	public void modifier(  Quizz quizz   )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, quizz.getId() );
		query.updateRow( quizz, this::setData );
	}

	public void supprimer( int idQuizz )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idQuizz );
		query.deleteRow();
	}

	public Quizz retrouver( int idQuizz )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idQuizz );
		return query.getSingleResult( this::build );
	}

	public List<Quizz> listerTout()   {
		var query = createQuery(  "SELECT * FROM Quiz ORDER BY description" );
		return query.getResultList( this::build );
	}

	
	
}
