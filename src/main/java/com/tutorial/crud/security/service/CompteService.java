package com.tutorial.crud.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.security.entity.Compte;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.repository.CompteRepository;

@Service
@Transactional
public class CompteService {
	
	@Autowired
	private CompteRepository compteRepository;
	
	

    public  Compte findByNumCompte(String numCompte){
        return compteRepository.findByNumCompte(numCompte);
    }
    
    public  Compte getOne(String numCompte){
        return compteRepository.findByNumCompte(numCompte);
    }


    public   boolean existsByNumCompte(String numCompte){
        return compteRepository.existsByNumCompte(numCompte);
    }

}
