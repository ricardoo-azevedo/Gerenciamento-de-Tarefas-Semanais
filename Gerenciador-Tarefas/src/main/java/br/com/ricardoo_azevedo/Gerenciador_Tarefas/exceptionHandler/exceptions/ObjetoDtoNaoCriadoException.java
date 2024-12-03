package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions;

public class ObjetoDtoNaoCriadoException extends RuntimeException{
    public ObjetoDtoNaoCriadoException(){
        super("Não foi possivel transferir os dados de entity para dto!");
    }
    public ObjetoDtoNaoCriadoException(String message){
        super(message);
    }
}
