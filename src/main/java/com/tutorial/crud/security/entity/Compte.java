package com.tutorial.crud.security.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Compte implements Serializable {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    @NotNull
	    @Column(unique = true)
	    private String numCompte;
	    @NotNull
	    private String nom;
	    @NotNull
	    private String prenom;
	    @NotNull
	    private String address;
	    @NotNull
	    private String telephone;
	    
	    public Compte() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Compte(@NotNull String numCompte, @NotNull String nom, @NotNull String prenom, @NotNull String address,
				@NotNull String telephone) {
			super();
			this.numCompte = numCompte;
			this.nom = nom;
			this.prenom = prenom;
			this.address = address;
			this.telephone = telephone;
		}
		public Compte(int id, @NotNull String numCompte, @NotNull String nom, @NotNull String prenom,
				@NotNull String address, @NotNull String telephone) {
			super();
			this.id = id;
			this.numCompte = numCompte;
			this.nom = nom;
			this.prenom = prenom;
			this.address = address;
			this.telephone = telephone;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNumCompte() {
			return numCompte;
		}
		public void setNumCompte(String numCompte) {
			this.numCompte = numCompte;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
	    
	    
	    
}
