package br.com.ricardoo_azevedo.Gerenciador_Tarefas.models;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "smn_exibicao_nt_passadas_configuracao", columnDefinition = "Integer DEFAULT 2")
    private int semanas_exibicao_notas_passadas;

    @Enumerated(EnumType.STRING)
    @Column(name = "tema_configuracao", nullable = false, columnDefinition = "Enum('CLARO', 'ESCURO') DEFAULT 'Escuro'", unique = true)
    private Tema tema;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    public enum Tema{
        Claro, Escuro
    }
}
