package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class ApelidoNullException extends RuntimeException{
    
    public ApelidoNullException(){
        super("O Apelido esta Nulo!");
    }

    public ApelidoNullException(String message){
        super(message);
    }
}
