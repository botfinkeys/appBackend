package com.tutorial.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tutorial.crud.security.entity.Compte;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.repository.CompteRepository;
import com.tutorial.crud.security.service.RolService;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CrudApplication.class, args);
		ApplicationContext ctx= SpringApplication.run(CrudApplication.class, args);
		
		
		/*
		 * RolService rolService=ctx.getBean(RolService.class); CompteRepository
		 * compteService=ctx.getBean(CompteRepository.class);
		 * 
		 * 
		 * 
		 * 
		 * compteService.save(new Compte("111222", "ilyass", "attachi",
		 * "nador","+212636774154")); compteService.save(new Compte("333444", "user2",
		 * "user2", "nador","+212636774154"));
		 * 
		 * 
		 * 
		 * compteService.save(new Compte("11223344", "user1", "user1", "nador",
		 * "+212636774154")); compteService.save(new Compte("123456789", "user1",
		 * "user1", "nador", "+212636774154"));
		 * 
		 * Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN); Rol rolUser = new
		 * Rol(RolNombre.ROLE_USER); rolService.save(rolAdmin);
		 * rolService.save(rolUser);
		 * 
		 */
		 
		
	}

}
