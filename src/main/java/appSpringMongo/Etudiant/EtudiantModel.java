package appSpringMongo.Etudiant;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Etudiant") // Nom de la collection (la table) dans MongoDB
public class EtudiantModel {
	
	// @Id
	// @Field(name = "cinEtud")
    private String cin;
	// @Field(name = "nom")
    private String nom;
    private String prenom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    private String telephone;
    private String email;
    
    
	public EtudiantModel(String cin, String nom, String prenom, LocalDate dateNaissance, String telephone,
			String email) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.email = email;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public LocalDate getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
    
    
    
	
    
}
