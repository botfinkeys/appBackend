package com.tutorial.crud.security.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true)
    private String numCompte;
    @OneToOne 
    @JoinColumn( name="idCompte", nullable=false )
    private Compte compte;
    @NotNull
    private String password;
    @NotNull
    private String telephone;
    @NotNull
    private String nbsession="modifie";
    private boolean modipass=false;
       
    
    public boolean isModipass() {
		return modipass;
	}



	public void setModipass(boolean modipass) {
		this.modipass = modipass;
	}



	public String getNbsession() {
		return nbsession;
	}



	public void setNbsession(String nbsession) {
		this.nbsession = nbsession;
	}

	@NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    

    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public Usuario(@NotNull String numCompte, Compte compte, @NotNull String password, @NotNull String telephone,
			@NotNull Set<Rol> roles) {
		super();
		this.numCompte = numCompte;
		this.compte = compte;
		this.password = password;
		this.telephone = telephone;
		this.roles = roles;
	}



	public Usuario(@NotNull String numCompte, @NotNull String password) {
		super();
		this.numCompte = numCompte;
		this.password = password;
	}



	public Usuario(@NotNull String numCompte, Compte compte, @NotNull String password, @NotNull Set<Rol> roles) {
		super();
		this.numCompte = numCompte;
		this.compte = compte;
		this.password = password;
		this.roles = roles;
	}



	public Usuario(int id, @NotNull String numCompte, Compte compte, @NotNull String password,
			@NotNull Set<Rol> roles) {
		super();
		this.id = id;
		this.numCompte = numCompte;
		this.compte = compte;
		this.password = password;
		this.roles = roles;
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



	public Compte getCompte() {
		return compte;
	}



	public void setCompte(Compte compte) {
		this.compte = compte;
	}



	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }



	
    
    
}
