package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import quizzbus.data.Question;
import quizzbus.data.Theme;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoQuestion extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Question WHERE idquestion = ?";
	
	@Inject
	private DaoAstuce daoAstuce;
	@Inject
	private DaoEtreAssocier daoEtreAssocier;
	
	
	//-------
	// Méthodes auxiliaires
	//-------

	protected void setData(Query query, Question question) throws SQLException {
	    query.set("enonce", question.getEnonce()); // Utilisez correctement l'objet Query passé en paramètre
	    query.set("idastuce", question.getAstuce() == null ? null : question.getAstuce().getId());
	}
	
	protected Question build( Query query ) throws SQLException {
		var question = new Question();
		question.setId(			query.get( "idquestion", Integer.class ) );
		question.setEnonce(			query.get( "enonce", String.class ) );
		var idAstuce = query.get( "idastuce", Integer.class );
		if ( idAstuce != null ) {
			question.setAstuce( daoAstuce.retrouver( idAstuce ) );
		}
		question.getReponses().setAll(daoEtreAssocier.listerPourQuestion(question));
		return question;
	}

	//-------
	// Actions
	//-------

	
	public void inserer(Question question) throws SQLException {
	    var query = createQuery( sqlDefault );
		query.insertRow( question, this::setData,true );
		question.setId( query.get( "idmemo", Integer.class ));
		query.close();
		daoEtreAssocier.mettreAJourPourQuestion(question);
	}

	public void modifier(  Question question   ) throws SQLException  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, question.getId() );
		query.updateRow( question, this::setData );
		daoEtreAssocier.mettreAJourPourQuestion(question);
	}

	public void supprimer( int idQuestion )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idQuestion );
		query.deleteRow();
	}

	public Question retrouver( int idQuestion )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idQuestion );
		return query.getSingleResult( q -> build( q ));
	}

	public List<Question> listerTout()   {
		var query = createQuery(  "SELECT * FROM Question ORDER BY enonce" );
		return query.getResultList(q -> build( q ) );
	}

	public ObservableList<Question> listerPourQuestion(Theme t)   {
		var query = createQuery(  "SELECT q.* FROM avoir a JOIN question q on  a.idquestion = q.idquestion WHERE a.idtheme = ?  ORDER BY q.enonce" );
		query.setParam(1, t.getId());
		ObservableList<Question> lt = FXCollections.observableArrayList();
		lt.addAll(query.getResultList( this::build ));
		return lt;
	}

	
}
