package com.br.vitor.gamesapirest.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.vitor.gamesapirest.form.RegisterForm;
import com.br.vitor.gamesapirest.modelo.Usuario;
import com.br.vitor.gamesapirest.repository.UserRepository;

@RestController
@RequestMapping(value = "registrar")
public class LoginResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid RegisterForm registerForm, BindingResult result){
		
		Usuario user = registerForm.converter();
	
		String senhaNaoCriptografada = user.getSenha();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(senhaNaoCriptografada);
		user.setSenha(encodedPassword);
		Usuario novo = userRepository.save(user);
		
		return ResponseEntity.ok(novo);
		
	}
}
