package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jakarta.inject.Inject;
import java.time.LocalTime;
import quizzbus.data.Configuration_Poste;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoConfigurationPoste extends DaoAbstract {

    //-------
    // Champs
    //-------

    private static final String sqlDefault = "SELECT * FROM Configuration_Poste WHERE idconf = ?";
    private static final String sqlListAll = "SELECT * FROM Configuration_Poste";
    @Inject
    private DaoTheme daoTheme;
    @Inject
    private DaoParcours daoParcours;

    //-------
    // Méthodes auxiliaires
    //-------

    protected void setData(Query query, Configuration_Poste conf) throws SQLException {
        query.set("heure", LocalTime.now());
        query.set("idtheme", conf.getTheme() == null ? null : conf.getTheme().getId());
        query.set("idparcours", conf.getParcours() == null ? null : conf.getParcours().getId());
        query.set("score", conf.getScore());
        query.set("question", conf.getQuestion());
    }

    protected Configuration_Poste build(Query query) throws SQLException {
        var conf = new Configuration_Poste();
        conf.setId(query.get("idconf", Integer.class));
        conf.setHeure(query.get("heure", LocalTime.class));
        conf.setQuestion(query.get("question", Integer.class)); 
        conf.setQuestion(query.get("score", Integer.class)); 
        var theme = query.get("idtheme", Integer.class);
        var parcours = query.get("idparcours", Integer.class);
        if (theme != null) {
            conf.setTheme(daoTheme.retrouver(theme));
        }
        if (parcours != null) {
            conf.setParcours(daoParcours.retrouver(parcours));
        }
        return conf;
    }

    //-------
    // Actions
    //-------

    public void inserer(Configuration_Poste conf) {
        var query = createQuery(sqlDefault);
        query.insertRow(conf, this::setData, true);
        conf.setId(query.get("idconf", Integer.class));
        query.close();
    }

    public void modifier(Configuration_Poste conf) {
        var query = createQuery(sqlDefault);
        query.setParam(1, conf.getId());
        query.updateRow(conf, this::setData);
    }

    public void supprimer(int idConf) {
        var query = createQuery(sqlDefault);
        query.setParam(1, idConf);
        query.deleteRow();
    }

    public Configuration_Poste retrouver(int idConf) {
        var query = createQuery(sqlDefault);
        query.setParam(1, idConf);
        return query.getSingleResult(this::build);
    }

    public List<Configuration_Poste> listerTout() {
        var query = createQuery(sqlListAll);
        return query.getResultList(this::build);
    }
}
