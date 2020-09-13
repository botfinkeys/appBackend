package com.tutorial.crud.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.crud.security.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNumCompte(String numCompte);
    boolean existsByNumCompte(String numCompte);
    public Usuario getByNumCompte(String numCompte);

}
