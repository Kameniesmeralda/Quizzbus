package quizzbus.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import quizzbus.data.Question;
import quizzbus.data.Quizz;
import quizzbus.data.Theme;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoQuestion extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Question WHERE idquestion = ?";
	
	@Inject
	private DaoQuizz daoQuizz;
//	@Inject
//	private DaoEtreAssocier daoEtreAssocier;
	
	
	//-------
	// Méthodes auxiliaires
	//-------

	protected void setData(Query query, Question question) throws SQLException {
	    query.set("enonce", question.getEnonce());
	    query.set("reponse", question.getReponse());
	    query.set("reponse1", question.getReponse1());
	    query.set("reponse2", question.getReponse2());
	    query.set("reponse3", question.getReponse3());
	    query.set("reponse4", question.getReponse4());
		query.set( "idquizz", question.getQuizz()==null? null:question.getQuizz().getId() );
	}
	
	protected Question build( Query query ) throws SQLException {
		var question = new Question();
		question.setId(query.get( "idquestion", Integer.class));
		question.setEnonce(query.get("enonce", String.class ));
		question.setReponse(query.get("reponse", Integer.class ));
		question.setReponse1(query.get("reponse1", String.class ));
		question.setReponse2(query.get("reponse2", String.class ));
		question.setReponse3(query.get("reponse3", String.class ));
		question.setReponse4(query.get("reponse4", String.class ));
		question.setAstuce(query.get("astuce", String.class));
		
		var idquizz = query.get( "idquizz", Integer.class );
		if ( idquizz != null ) {
			question.setQuizz( daoQuizz.retrouver( idquizz ) );
		}
		return question;
	}

	//-------
	// Actions
	//-------

	
	public void inserer(Question question) throws SQLException {
	    var query = createQuery( sqlDefault );
		query.insertRow( question, this::setData,true );
//		question.setId( query.get( "idmemo", Integer.class ));
//		question.setEnonce(sqlDefault);
		query.close();
//		daoEtreAssocier.mettreAJourPourQuestion(question);
	}

	public void modifier(  Question question   ) throws SQLException  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, question.getId() );
		query.updateRow( question, this::setData );
//		daoEtreAssocier.mettreAJourPourQuestion(question);
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

	public List<Question> listerParQuizz(Quizz quizz)   {
		var query = createQuery(  "SELECT * FROM Question WHERE quizz = ? ORDER BY enonce" );
		query.setParam(1, quizz);
		return query.getResultList(q -> build( q ) );
	}

	public ObservableList<Question> listerPourQuestion(Theme t)   {
		var query = createQuery(  "SELECT q.* FROM avoir a JOIN question q on  a.idquestion = q.idquestion WHERE a.idtheme = ?  ORDER BY q.enonce" );
		query.setParam(1, t.getId());
		ObservableList<Question> lt = FXCollections.observableArrayList();
		lt.addAll(query.getResultList( this::build ));
		return lt;
	}

	public ObservableList<Question> listerQuestionsParTheme(Theme theme, int limit) throws SQLException {
        var query = createQuery("SELECT q.* FROM Question q JOIN Avoir a ON q.idquestion = a.idquestion WHERE a.idtheme = ? LIMIT ?");
        query.setParam(1, theme.getId());
        query.setParam(2, limit);
        List<Question> questions = query.getResultList(this::build);
        return FXCollections.observableArrayList(questions);
    }
	
	 public ObservableList<Integer> initialiserReponses(int taille) {
	        List<Integer> reponses = new ArrayList<>();
	        for (int i = 0; i < taille; i++) {
	            // Initialiser chaque élément de la liste de réponses à 1
	            reponses.add(1);
	        }
	        return FXCollections.observableArrayList(reponses);
	    }
	
}
