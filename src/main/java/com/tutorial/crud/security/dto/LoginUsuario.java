package com.tutorial.crud.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
    @NotBlank
    private String numCompte;
    @NotBlank
    private String password;

  

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
}
