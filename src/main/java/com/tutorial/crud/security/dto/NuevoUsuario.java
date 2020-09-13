package com.tutorial.crud.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {
    @NotBlank
    private String numCompte;
   
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();

    @NotNull
    private String telephone;
    
    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
