package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import quizzbus.data.Theme;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoTheme extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Theme  WHERE idtheme = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Theme  theme  ) throws SQLException {
		query.set( "nom",		theme.getNom() );
		query.set("description", theme.getDescription());
	}
	
	protected Theme build( Query query ) throws SQLException {
		var theme = new Theme();
		theme.setId(			query.get( "idtheme", Integer.class ) );
		theme.setNom(			query.get( "nom", String.class ) );
		theme.setDescription(			query.get( "description", String.class ) );
		return theme;
	}

	//-------
	// Actions
	//-------

	public void inserer( Theme  theme )  {
		var query = createQuery( sqlDefault );
		query.insertRow( theme, this::setData, true );
		theme.setId( query.get( "idtheme", Integer.class ));
		query.close();
	}

	public void modifier( Theme  theme)  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, theme.getId() );
		query.updateRow( theme, this::setData );
	}

	public void supprimer( int idTheme )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idTheme );
		query.deleteRow();
	}

	public Theme retrouver( int idTheme )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idTheme );
		return query.getSingleResult( this::build );
	}

	public List<Theme> listerTout()   {
		var query = createQuery(  "SELECT * FROM Theme ORDER BY nom" );
		return query.getResultList( this::build );
	}


	
}
