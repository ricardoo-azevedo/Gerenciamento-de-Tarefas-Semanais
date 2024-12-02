package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.formater;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class RestErrorMessage {
    
    private HttpStatus httpStatus;
    private String message;
    
}
