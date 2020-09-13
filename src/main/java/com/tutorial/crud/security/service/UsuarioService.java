package com.tutorial.crud.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.repository.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByNumCompte(String numCompte){
        return usuarioRepository.findByNumCompte(numCompte);
    }
    

    public boolean existsByNumCompte(String numCompte){
        return usuarioRepository.existsByNumCompte(numCompte);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }


	

	
}
