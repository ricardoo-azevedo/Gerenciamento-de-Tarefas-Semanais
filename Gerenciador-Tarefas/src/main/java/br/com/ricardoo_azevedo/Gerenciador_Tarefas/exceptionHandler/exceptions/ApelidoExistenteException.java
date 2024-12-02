package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class ApelidoExistenteException extends RuntimeException{
    
    public ApelidoExistenteException(){
        super("Apelido ja cadastrado!")
    }

    public ApelidoExistenteException(String message){
        super(message);
    }
}
