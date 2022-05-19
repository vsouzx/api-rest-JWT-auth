package com.br.vitor.gamesapirest.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.vitor.gamesapirest.form.TokenDto;
import com.br.vitor.gamesapirest.form.UsuarioForm;
import com.br.vitor.gamesapirest.security.TokenService;

public class LoginResource {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("")
	public ResponseEntity<TokenDto> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm){
		UsernamePasswordAuthenticationToken dadosLogin = usuarioForm.converter();
		
		try {
			
			Authentication auth = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(auth);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		}
			catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
		}
	}
}
