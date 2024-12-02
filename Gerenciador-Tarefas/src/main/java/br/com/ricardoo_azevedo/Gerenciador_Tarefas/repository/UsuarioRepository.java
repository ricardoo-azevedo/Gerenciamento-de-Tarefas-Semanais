package br.com.ricardoo_azevedo.Gerenciador_Tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    boolean existsByApelido(String apelido);
}
