package br.com.ricardoo_azevedo.Gerenciador_Tarefas.models;

import java.sql.Date;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_notificacao")
@Component
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Notificacao {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarefa_id", nullable = false)
    private Tarefa tarefa;

    @Column(name = "horario_notificacao", nullable = false)
    private Date horario;

    @Column(name = "status_envio_notificacao", nullable = false, columnDefinition = "Enum('Agendado', 'Pendente', 'Concluido') DEFAULT 'Agendado' ")
    private Status_envio status_envio;

    public enum Status_envio{
        Agendado, Pendente, Concluido
    }   

    
}
