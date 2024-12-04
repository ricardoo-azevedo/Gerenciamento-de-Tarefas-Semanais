package br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.treatment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ApelidoExistenteException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ApelidoNullException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.CaminhoImagemNullException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.DiretorioNaoCriadoException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.IdIncompativelException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ImagemNaoSalvaException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ListaVaziaException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ObjetoDtoNaoCriadoException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.SenhaCurtaException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.UsuarioNaoAchadoException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.formater.RestErrorMessage;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(CaminhoImagemNullException.class)
     private ResponseEntity<RestErrorMessage> CaminhoImagemNullException(CaminhoImagemNullException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
     }

     @ExceptionHandler(ApelidoExistenteException.class)
     private ResponseEntity<RestErrorMessage> ApelidoExistenteException(ApelidoExistenteException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
     }

     @ExceptionHandler(DiretorioNaoCriadoException.class)
     private ResponseEntity<RestErrorMessage> DiretorioNaoCriadoException(DiretorioNaoCriadoException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_IMPLEMENTED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
     }

     @ExceptionHandler(ImagemNaoSalvaException.class)
     private ResponseEntity<RestErrorMessage> ImagemNaoSalvaException(ImagemNaoSalvaException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
     }

     @ExceptionHandler(SenhaCurtaException.class)
     private ResponseEntity<RestErrorMessage> SenhaCurtaException(SenhaCurtaException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
     }

     @ExceptionHandler(ApelidoNullException.class)
     private ResponseEntity<RestErrorMessage> ApelidoNullException(ApelidoNullException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
     }

     @ExceptionHandler(UsuarioNaoAchadoException.class)
     private ResponseEntity<RestErrorMessage> UsuarioNaoAchadoException(UsuarioNaoAchadoException exception){
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
     }

     @ExceptionHandler(ListaVaziaException.class)
     private ResponseEntity<RestErrorMessage> ListaVaziaException(ListaVaziaException exception){
         RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
     }

     @ExceptionHandler(IdIncompativelException.class)
     private ResponseEntity<RestErrorMessage> IdIncompativelException(IdIncompativelException exception){
         RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
         return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
     }

     @ExceptionHandler(ObjetoDtoNaoCriadoException.class)
     private ResponseEntity<RestErrorMessage> ObjetoDtoNaoCriadoException(ObjetoDtoNaoCriadoException exception){
         RestErrorMessage response = new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
         return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
     }
}
