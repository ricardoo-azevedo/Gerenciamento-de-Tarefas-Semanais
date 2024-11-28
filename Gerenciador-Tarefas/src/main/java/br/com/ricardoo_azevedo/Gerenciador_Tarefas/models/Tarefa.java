package br.com.ricardoo_azevedo.Gerenciador_Tarefas.models;

import java.security.Timestamp;
import java.sql.Time;
import java.util.Date;

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
@Table(name = "tb_Tarefa")
@Component
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Tarefa {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*reservado pra id-usuario */

    @Column(name = "titulo_tarefa", length = 50, unique = true, nullable = false)
    private String titulo;

    @Column(name = "descricao_tarefa", length = 300, nullable = true)
    private String descricao;

    @Column(name = "data_tarefa", nullable = false)
    private Date data;

    @Column(name = "hora_tarefa", nullable = true)
    private Time hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_tarefa", nullable = false, columnDefinition = "Enum('Agendado', 'Pendente', 'Concluido')")
    private Status status;

    @Column(name = "data_criacao_tarefa", columnDefinition = "DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp data_criacao;
    

    public enum Status{
        Agendado, Pendente, Concluido
    }
    
}
