package br.com.ricardoo_azevedo.Gerenciador_Tarefas.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_usuario")
@Component
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Usuario {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "apelido_usuario", nullable = false, unique = true, length = 200)
    private String apelido;

    @Column(name = "senha_usuario", nullable = true, unique = true, length = 30)
    private String senha;

    @Column(name = "pergunta_seguranca_usuario", nullable = true, unique = true, length = 100)
    private String pergunta_seguranca;

    @Column(name = "resposta_seguranca_usuario", nullable = true, unique = true, length = 100)
    private String resposta_seguranca;

    @Column(name = "caminho_foto_perfil_usuario", nullable = true, unique = true, length = 150)
    private String foto_perfil;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Tarefa> tarefas = new HashSet<>();

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Configuracao configuracao;

   
}
