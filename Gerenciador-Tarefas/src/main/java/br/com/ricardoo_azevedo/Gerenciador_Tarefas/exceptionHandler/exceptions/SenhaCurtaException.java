package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class SenhaCurtaException extends RuntimeException{
    
    public SenhaCurtaException(){
        super("Sua senha possui menos de 8 caracteres!");
    }

    public SenhaCurtaException(String message){
        super(message);
    }
}
