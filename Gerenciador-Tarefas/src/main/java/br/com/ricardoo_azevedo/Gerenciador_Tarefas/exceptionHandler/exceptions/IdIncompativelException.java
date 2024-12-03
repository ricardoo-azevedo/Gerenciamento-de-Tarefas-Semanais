package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class IdIncompativelException extends RuntimeException{
    
    public IdIncompativelException(){
        super("O id Inserido é incompativel!");
    }

    public IdIncompativelException(String message){
        super(message);
    }
}
