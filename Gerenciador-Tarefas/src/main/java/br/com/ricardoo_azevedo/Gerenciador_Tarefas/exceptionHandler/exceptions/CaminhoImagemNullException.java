package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class CaminhoImagemNullException extends RuntimeException{
    
    public CaminhoImagemNullException(){
        super("O caminho da Imagem Se encontra Nulo!");
    }

    public CaminhoImagemNullException(String message){
        super(message);
    }
}
