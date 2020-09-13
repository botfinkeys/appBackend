package com.tutorial.crud.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.crud.security.entity.Compte;


public interface CompteRepository extends JpaRepository<Compte, Integer>{
	
	 Compte findByNumCompte(String numCompte);
	 
	    boolean existsByNumCompte(String numCompte);

}
