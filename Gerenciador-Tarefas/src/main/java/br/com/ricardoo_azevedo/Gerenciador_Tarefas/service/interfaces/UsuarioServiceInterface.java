package br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos.UsuarioRecordDto;


public interface UsuarioServiceInterface {

    UsuarioRecordDto save(UsuarioRecordDto usuarioRecordDto, String fileNome);

    UsuarioRecordDto update(UsuarioRecordDto usuarioRecordDto, String apelidoAntigo, String fileNome);

    List<UsuarioRecordDto> findAll();

    UsuarioRecordDto findById(Long id);

    void deleteById(Long id);

    boolean existsByApelido(String apelido);

    UsuarioRecordDto findByApelido(String apelido);

    String saveImage(UsuarioRecordDto usuarioRecordDto, String uploadImadeDir, MultipartFile arquivo);

}
