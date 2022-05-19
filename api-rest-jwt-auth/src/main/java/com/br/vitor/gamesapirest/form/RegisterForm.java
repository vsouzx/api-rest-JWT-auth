package com.br.vitor.gamesapirest.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.vitor.gamesapirest.modelo.Usuario;

public class RegisterForm {

	@NotBlank @NotNull
	private String username;
	
	@NotBlank @NotNull
	private String senha;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario converter() {
		Usuario usuario = new Usuario();
		usuario.setSenha(senha);
		usuario.setUsername(username);
		
		return usuario;
	}
}
