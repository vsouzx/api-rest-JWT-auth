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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.vitor.gamesapirest.form.TokenDto;
import com.br.vitor.gamesapirest.form.UsuarioForm;
import com.br.vitor.gamesapirest.security.TokenService;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid UsuarioForm form){

		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
		}
		
	}
}
