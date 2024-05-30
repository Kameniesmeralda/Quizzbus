package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import jakarta.inject.Inject;
import quizzbus.data.Question;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoQuestion extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Question WHERE idquestion = ?";
	@Inject
	private DaoAstuce daoAstuce;
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Question question ) throws SQLException {
		query.set( "enonce ",		question.getEnonce());
	query.set( "idastuce", question.getAstuce()==null? null:question.getAstuce().getId() );
		
	}
	
	protected Question build( Query query ) throws SQLException {
		var question = new Question();
		question.setId(			query.get( "idquestion", Integer.class ) );
		question.setEnonce(			query.get( "enonce", String.class ) );
		var idAstuce = query.get( "idastuce", Integer.class );
		if ( idAstuce != null ) {
			question.setAstuce( daoAstuce.retrouver( idAstuce ) );
		}
		return question;
	}

	//-------
	// Actions
	//-------

	public void inserer(  Question question  )  {
		var query = createQuery( sqlDefault );
		query.insertRow( question, this::setData, true );
		question.setId( query.get( "idquestion", Integer.class ));
		query.close();
	}

	public void modifier(  Question question   )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, question.getId() );
		query.updateRow( question, this::setData );
	}

	public void supprimer( int idQuestion )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idQuestion );
		query.deleteRow();
	}

	public Question retrouver( int idQuestion )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idQuestion );
		return query.getSingleResult( this::build );
	}

	public List<Question> listerTout()   {
		var query = createQuery(  "SELECT * FROM Question ORDER BY enonce" );
		return query.getResultList( this::build );
	}

	
	
}
