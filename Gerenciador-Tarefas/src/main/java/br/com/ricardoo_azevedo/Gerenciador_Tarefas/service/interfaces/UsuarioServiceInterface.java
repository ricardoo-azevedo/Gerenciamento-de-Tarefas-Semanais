package br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos.UsuarioRecordDto;

public interface UsuarioServiceInterface {

    UsuarioRecordDto save(UsuarioRecordDto usuarioRecordDto, MultipartFile arquivo);

    UsuarioRecordDto update(UsuarioRecordDto usuarioRecordDto);

    List<UsuarioRecordDto> findAll();

    UsuarioRecordDto findById(Long id);

    void deleteById(long id);

    boolean existsByApelido(String apelido);

}
