package br.com.ricardoo_azevedo.Gerenciador_Tarefas.models;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_configuracao")
@Component
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Configuracao {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*Reservado pra id_usuario */

    @Column(name = "smn_exibicao_nt_passadas_configuracao", columnDefinition = "Integer DEFAULT 2")
    private int semanas_exibicao_notas_passadas;

    @Enumerated(EnumType.STRING)
    @Column(name = "tema_configuracao", nullable = false, columnDefinition = "Enum('CLARO', 'ESCURO') DEFAULT 'Escuro'", unique = true)
    private Tema tema;

    
    public enum Tema{
        Claro, Escuro
    }
}
