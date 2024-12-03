package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class ListaVaziaException extends RuntimeException{
    
    public ListaVaziaException(){
        super("Essa Lista se encontra vazia: ");
    }

    public ListaVaziaException(String message){
        super(message);
    }
}
