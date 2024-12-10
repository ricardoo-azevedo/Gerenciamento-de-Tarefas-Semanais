package br.com.ricardoo_azevedo.Gerenciador_Tarefas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import br.com.ricardoo_azevedo.Gerenciador_Tarefas.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    
    boolean existsByApelido(String apelido);

    @Query(value = "SELECT u FROM Usuario u WHERE u.apelido = :apelido")
    Optional<Usuario> findByApelidoNative(@Param("apelido") String apelido);

}
