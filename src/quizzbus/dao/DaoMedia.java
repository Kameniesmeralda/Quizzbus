package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;

import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;
import quizzbus.data.Media;

public class DaoMedia extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM Media WHERE idmedia = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Media media ) throws SQLException {
		query.set( "titre",		media.getTitre() );
		query.set("description", media.getDescription());
	}
	
	protected Media build( Query query ) throws SQLException {
		var media = new Media();
		media.setId(			query.get( "idmedia", Integer.class ) );
		media.setTitre(			query.get( "titre", String.class ) );
		media.setDescription(			query.get( "description", String.class ) );
		return media;
	}

	//-------
	// Actions
	//-------

	public void inserer(  Media media  )  {
		var query = createQuery( sqlDefault );
		query.insertRow( media, this::setData, true );
		media.setId( query.get( "idmedia", Integer.class ));
		query.close();
	}

	public void modifier( Media media )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, media.getId() );
		query.updateRow( media, this::setData );
	}

	public void supprimer( int idMedia )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idMedia );
		query.deleteRow();
	}

	public Media retrouver( int idMedia )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idMedia );
		return query.getSingleResult( this::build );
	}

	public List<Media> listerTout()   {
		var query = createQuery(  "SELECT * FROM Media ORDER BY titre" );
		return query.getResultList( this::build );
	}
//	
//	public List<Media> getImagesFromDatabase() {
//	    List<Media> images = new ArrayList<>();
//	    Connection connection = null;
//	    PreparedStatement statement = null;
//	    ResultSet resultSet = null;
//
//	    try {
//
//	        // Requête SQL pour récupérer les images
//	        String query = "SELECT * FROM Media WHERE type = 'image'";
//	        statement = connection.prepareStatement(query);
//
//	        // Exécution de la requête
//	        resultSet = statement.executeQuery();
//
//	        // Parcours des résultats et ajout des images à la liste
//	        while (resultSet.next()) {
//	            Media image = build((Query) resultSet);
//	            images.add(image);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        // Fermeture des ressources
//	        closeResources(connection, statement, resultSet);
//	    }
//
//	    return images;
//	}
//
//

	
}
