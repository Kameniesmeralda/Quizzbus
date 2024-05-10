package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Question;
import quizzbus.data.Quizz;

public class DaoContenir extends DaoAbstract {
	//-------
		// Champs
		//-------

		@Inject
		private DaoQuestion	daoQuestion;
		
		//-------
		// Méthodes auxiliaires
		//-------
		
		protected void setData( Query query, Quizz quizz, Question question ) throws SQLException {
			query.set( "idquizz",	quizz.getId() );
			query.set( "idquestion",question.getId() );
		}
		
		protected Question build( Query query ) throws SQLException {
			var quest = new Question();
			quest.setId( query.get( "idquestion", Integer.class ) );
			return quest;
		}
		
		//-------
		// Actions
		//-------

		public void mettreAJourPourQuizz( Quizz quizz ) {
			var query = createQuery(  "SELECT * FROM contenir WHERE idquizz = ?" );
			query.setParam( 1, quizz.getId() );
			query.updateChildren( quizz.getQuestions(), this::build, (q, item) -> setData( q, quizz, item ) );
		}

		public List<Question> listerPourQuizz(Quizz quizz) {
			var query = createQuery(  "SELECT qe.* FROM contenir c JOIN Question qe ON c.idquestion = qe.idquestion WHERE c.idquizz = ? ORDER BY qe.enonce" );
			query.setParam( 1, quizz.getId() );
			return query.getResultList( daoQuestion::build );
		}
}
