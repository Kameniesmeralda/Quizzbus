package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Quizz;
import quizzbus.data.Reponse;
import quizzbus.data.Etre_associer;

public class DaoEtreAssocier extends DaoAbstract {
    //-------
    // Champs
    //-------

    @Inject
    private DaoReponse daoReponse;
    @Inject
    private DaoQuizz daoQuizz;

    //-------
    // Méthodes auxiliaires
    //-------

    protected void setData(Query query, Quizz quizz, Reponse reponse, boolean vraie) throws SQLException {
        query.set("idquizz", quizz.getId());
        query.set("idreponse", reponse.getId());
        query.set("vraie", vraie);
    }

    protected Etre_associer build(Query query) throws SQLException {
        var etreAssocier = new Etre_associer();
        etreAssocier.setQuizz(daoQuizz.build(query));
        etreAssocier.setReponse(daoReponse.build(query));
        etreAssocier.setVraie(query.get("vraie", Boolean.class));
        return etreAssocier;
    }

    //-------
    // Actions
    //-------

    public void mettreAJourPourQuizz(Quizz quizz) throws SQLException {
        var query = createQuery("SELECT * FROM etre_associer WHERE idquizz = ?");
        query.setParam(1, quizz.getId());
        List<Etre_associer> etreAssocierList = query.getResultList(this::build);
        for (Etre_associer etreAssocier : etreAssocierList) {
            var updateQuery = createQuery("UPDATE etre_associer SET vraie = ? WHERE idquizz = ? AND idreponse = ?");
            setData(updateQuery, quizz, etreAssocier.getReponse(), etreAssocier.isVraie());
            updateQuery.executeUpdate();
        }
    }

    public List<Etre_associer> listerPourQuizz(Quizz quizz) {
        var query = createQuery("SELECT ea.*, r.*, q.* FROM etre_associer ea JOIN Reponse r ON ea.idreponse = r.idreponse JOIN Quizz q ON ea.idquizz = q.idquizz WHERE ea.idquizz = ? ORDER BY r.libelle");
        query.setParam(1, quizz.getId());
        return query.getResultList(this::build);
    }

    public void ajouterEtreAssocier(Etre_associer etreAssocier) throws SQLException {
        var query = createQuery("INSERT INTO etre_associer (idquizz, idreponse, vraie) VALUES (?, ?, ?)");
        setData(query, etreAssocier.getQuizz(), etreAssocier.getReponse(), etreAssocier.isVraie());
        query.executeUpdate();
    }

    public void supprimerEtreAssocier(Quizz quizz, Reponse reponse) {
        var query = createQuery("DELETE FROM etre_associer WHERE idquizz = ? AND idreponse = ?");
        query.setParam(1, quizz.getId());
        query.setParam(2, reponse.getId());
        query.executeUpdate();
    }
}
