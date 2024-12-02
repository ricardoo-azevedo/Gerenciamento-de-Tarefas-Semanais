package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class UsuarioNaoAchadoException extends RuntimeException{
    
    public UsuarioNaoAchadoException(){
        super("O Usuario não foi achado");
    }

    public UsuarioNaoAchadoException(String message){
        super(message);
    }
}
