package quizzbus.dao;

import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import quizzbus.data.Compte;
import jfox.jdbc.DaoAbstract;
import jfox.jdbc.Query;

public class DaoCompte extends DaoAbstract {
	
	//-------
	// Champs
	//-------
	
	private static final String sqlDefault = "SELECT * FROM compte WHERE idcompte = ?";
	
	//-------
	// Méthodes auxiliaires
	//-------
	
	protected void setData( Query query, Compte compte ) throws SQLException {
		query.set( "nom",		compte.getNom() );
		query.set( "prenom",		compte.getPrenom() );
		query.set("date_naissance", compte.getDateNaissance());
		query.set( "motdepasse",	compte.getMotDePasse() );
		query.set( "email",			compte.getEmail() );
		query.set( "flagadmin",		compte.isFlagAdmin() );
		query.set( "ville",		compte.getVille());
		query.set( "categorie",		compte.getCategorie() );
	}
	
	protected Compte build( Query query ) throws SQLException {
		var compte = new Compte();
		compte.setId(			query.get( "idcompte", Integer.class ) );
		compte.setNom(			query.get( "nom", String.class ) );
		compte.setPrenom(		query.get( "prenom", String.class ) );
		compte.setDateNaissance(query.get( "date_naissance", LocalDate.class ) );
		compte.setMotDePasse(	query.get( "motdepasse", String.class ) );
		compte.setEmail(		query.get( "email", String.class ) );
		compte.setFlagAdmin(	query.get( "flagadmin", Boolean.class ) );
		compte.setVille(			query.get( "ville", String.class ) );
		compte.setCategorie(		query.get( "categorie", String.class ) );
		return compte;
	}

	//-------
	// Actions
	//-------

	public void inserer( Compte compte )  {
		var query = createQuery( sqlDefault );
		query.insertRow( compte, this::setData, true );
		compte.setId( query.get( "idcompte", Integer.class ));
		query.close();
	}

	public void modifier( Compte compte )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, compte.getId() );
		query.updateRow( compte, this::setData );
	}

	public void supprimer( int idCompte )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idCompte );
		query.deleteRow();
	}

	public Compte retrouver( int idCompte )  {
		var query = createQuery( sqlDefault );
		query.setParam( 1, idCompte );
		return query.getSingleResult( this::build );
	}

	public List<Compte> listerTout()   {
		var query = createQuery(  "SELECT * FROM compte ORDER BY nom" );
		return query.getResultList( this::build );
	}

	public Compte validerAuthentification( String nom, String motDePasse )  {
		var query = createQuery( "SELECT * FROM compte WHERE nom = ? AND motdepasse = ?" );
		query.setParam( 1, nom );
		query.setParam( 2, motDePasse);
		return query.getSingleResult( this::build );
	}

	public boolean verifierUnicitePseudo( String nom, Integer idCompte )   {
		var query = createQuery(  "SELECT COUNT(*) = 0 FROM compte WHERE nom = ? AND idcompte <> ?" );
		query.setParam( 1, nom );
		query.setParam( 2, idCompte);
		return query.getSingleResult( Boolean.class );
	}
	
}
