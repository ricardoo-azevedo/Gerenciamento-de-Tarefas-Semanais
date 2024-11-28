package br.com.ricardoo_azevedo.Gerenciador_Tarefas.models;

import org.springframework.format.annotation.DateTimeFormat;

public class Notificacao {
    
    private Long id;

    /*reservado pra id_tarefa */

    private DateTimeFormat horario;

    private Status_envio status_envio;

    public enum Status_envio{
        Agendado, Pendente, Concluido
    }

    
}
