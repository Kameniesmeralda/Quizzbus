package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;


import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Reponse;
import quizzbus.data.Etre_associer;
import quizzbus.data.Question;

public class DaoEtreAssocier extends DaoAbstract {
    //-------
    // Champs
    //-------

    @Inject
    private DaoReponse daoReponse;
    

    //-------
    // Méthodes auxiliaires
    //-------
    protected void update( Query query, Reponse reponse, Question question ) throws SQLException {
		query.set( "idquestion",	question.getId() );
	}
    protected void setData(Query query,Etre_associer ea) throws SQLException {
        query.set("idquestion", ea.getQuestion().getId());
        query.set("idreponse", ea.getReponse().getId());
        query.set("vraie", ea.isVraie());
    }

    protected Etre_associer build(Query query,Question question ,boolean flagComplet) throws SQLException {
        var etreAssocier = new Etre_associer();
        etreAssocier.setVraie(query.get("vraie", Boolean.class));
        etreAssocier.setQuestion(question);
		
		flagComplet=true;
		if(flagComplet) {
			etreAssocier.setReponse( daoReponse.build( query ) );

		}else {
			etreAssocier.setReponse( new Reponse() );
			etreAssocier.getReponse().setId( query.get( "idreponse", Integer.class ) );
		}
		return etreAssocier;
    }

    //-------
    // Actions
    //-------

   /* public void mettreAJourPourQuestion(Question question) throws SQLException {
    	var query = createQuery( "SELECT  r.*,ea.* FROM etre_associer ea JOIN Reponse r ON ea.idreponse = r.idreponse WHERE idquestion = ?" );
		query.setParam( 1, question.getId() );
		query.updateChildren( question.getReponses(), q -> build( q, question,false) ,this::setData );
    }*/

    public List<Etre_associer> listerPourQuestion(Question question) {
    	var query = createQuery( "SELECT  r.*,ea.* FROM etre_associer ea JOIN Reponse r ON ea.idreponse = r.idreponse WHERE idquestion = ? ORDER BY r.libelle" );
		query.setParam( 1, question.getId() );
		return query.getResultList(q -> build( q, question,true));
    }
	

   

}
