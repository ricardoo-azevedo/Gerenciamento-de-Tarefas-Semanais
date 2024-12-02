package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;


public class DiretorioNaoCriadoException extends RuntimeException{
    
    public DiretorioNaoCriadoException(){
        super("Não foi possivel criar o diretorio de imagens!");
    }

    public DiretorioNaoCriadoException(String message){
        super(message);
    }
}
