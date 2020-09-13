package com.tutorial.crud.security.controller;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.Message;
import com.tutorial.crud.dto.ProductoDto;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.security.dto.JwtDto;
import com.tutorial.crud.security.dto.LoginUsuario;
import com.tutorial.crud.security.dto.NuevoUsuario;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.jwt.JwtProvider;
import com.tutorial.crud.security.service.CompteService;
import com.tutorial.crud.security.service.RolService;
import com.tutorial.crud.security.service.UsuarioService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    CompteService compteService;
    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("les donnéees sont invalides"), HttpStatus.BAD_REQUEST);
        if(compteService.existsByNumCompte(nuevoUsuario.getNumCompte())!=true) {
        	System.err.println(nuevoUsuario.getNumCompte()+"  "+compteService.existsByNumCompte(nuevoUsuario.getNumCompte()));
            return new ResponseEntity(new Message("le numero de compte n'existe pas"), HttpStatus.BAD_REQUEST);}
       
        if(usuarioService.existsByNumCompte(nuevoUsuario.getNumCompte())) {
             	System.err.println(nuevoUsuario.getNumCompte()+"  "+compteService.existsByNumCompte(nuevoUsuario.getNumCompte()));
                 return new ResponseEntity(new Message("le  compte est déja existant"), HttpStatus.BAD_REQUEST);}
        	
        	System.err.println(nuevoUsuario.getNumCompte()+"  "+nuevoUsuario.getPassword());
        	//String pass=getRandomNumberString();
        Usuario usuario =
                new Usuario(nuevoUsuario.getNumCompte(), 
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        usuario.setTelephone(nuevoUsuario.getTelephone());
        System.err.println(nuevoUsuario.getPassword()+"  "+passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuario.setCompte(compteService.findByNumCompte(nuevoUsuario.getNumCompte()));
        
        usuarioService.save(usuario);
        
    NexmoClient client = new NexmoClient.Builder()
        		  .apiKey("f1629011")
        		  .apiSecret("D2SJkWVEjNOPu8pG")
        		  .build();

        		String messageText = "voici votre code d'authentification : "+nuevoUsuario.getPassword();
        		TextMessage message = new TextMessage("APP Finkey", usuario.getTelephone(), messageText);
        		System.err.println("num tel :  "+usuario.getTelephone());
        		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        		for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
        		    System.out.println(responseMessage);
        		}
        return new ResponseEntity(new Message("utilisateur créé"), HttpStatus.CREATED);
        
    }

    @PutMapping("/update/{numCompte}")
    public ResponseEntity<?> update(@PathVariable("numCompte")String numCompte, @RequestBody NuevoUsuario nuevoUsuario){
        if(!usuarioService.existsByNumCompte(numCompte))
            return new ResponseEntity(new Message("l'utilisateur n'existe pas"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(nuevoUsuario.getPassword()))
            return new ResponseEntity(new Message("saisir un password valid"), HttpStatus.BAD_REQUEST);
    	//String pass=getRandomNumberString();
        Usuario usuario = usuarioService.findByNumCompte(nuevoUsuario.getNumCompte()).get();
        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
       // usuario.setTelephone(usuario.getTelephone());
        usuario.setNbsession("modifie1");
        usuario.setModipass(true);
        usuarioService.save(usuario);
        return new ResponseEntity(new Message("mot de passe modifié"), HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("les données incorrects"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNumCompte(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
       
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        Usuario usuario = usuarioService.findByNumCompte(loginUsuario.getNumCompte()).get();
       
       // usuario.setTelephone(usuario.getTelephone());
        usuarioService.save(usuario);
        System.err.println(usuario.getNbsession());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    
    @GetMapping("/infomrations/{numCompte}")
    public ResponseEntity<Usuario> getByNombre(@PathVariable("numCompte") String numCompte){
    	if(!usuarioService.existsByNumCompte(numCompte))
            return new ResponseEntity(new Message("l'utilisateur n'existe pas"), HttpStatus.NOT_FOUND);
        
    	//String pass=getRandomNumberString();
        Usuario usuario = usuarioService.findByNumCompte(numCompte).get();
        // usuario.setTelephone(usuario.getTelephone());
            return new ResponseEntity(usuario, HttpStatus.OK);
    }
    
    
	/*
	 * @PostMapping("/login") public ResponseEntity<JwtDto>
	 * login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult
	 * bindingResult){ if(bindingResult.hasErrors()) return new ResponseEntity(new
	 * Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST); Authentication
	 * authentication = authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
	 * loginUsuario.getPassword()));
	 * SecurityContextHolder.getContext().setAuthentication(authentication); String
	 * jwt = jwtProvider.generateToken(authentication); UserDetails userDetails =
	 * (UserDetails)authentication.getPrincipal(); JwtDto jwtDto = new JwtDto(jwt,
	 * userDetails.getUsername(), userDetails.getAuthorities()); return new
	 * ResponseEntity(jwtDto, HttpStatus.OK); }
	 */
}
