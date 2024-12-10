package br.com.ricardoo_azevedo.Gerenciador_Tarefas.models;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "tarefa", fetch = FetchType.LAZY)
    private Set<Notificacao> notificacoes = new HashSet<>();
    
    public enum Status{
        Agendado, Pendente, Concluido
    }
    
}
