package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class ImagemNaoSalvaException extends RuntimeException{
    
    public ImagemNaoSalvaException(){
        super("Não Foi Possivel Salvar a Imagem no Diretorio!");
    }

    public ImagemNaoSalvaException(String message){
        super(message);
    }
}
