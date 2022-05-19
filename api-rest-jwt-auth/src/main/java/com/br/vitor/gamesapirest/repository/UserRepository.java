package com.br.vitor.gamesapirest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.vitor.gamesapirest.modelo.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUsername(String username);

}
