package br.com.ricardoo_azevedo.Gerenciador_Tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos.UsuarioRecordDto;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.Impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioRecordDto> salvar(@ModelAttribute UsuarioRecordDto usuarioRecordDto,
            @RequestPart("arquivo") MultipartFile arquivo) {
        UsuarioRecordDto usuarioRecordDtoSalvo = usuarioServiceImpl.save(usuarioRecordDto, arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRecordDtoSalvo);
    }

    @PostMapping("/editar/{apelidoAntigo}")
    public ResponseEntity<UsuarioRecordDto> editar(@PathVariable String apelidoAntigo, @ModelAttribute UsuarioRecordDto usuarioRecordDto, @RequestParam MultipartFile arquivo) { // Recebe o arquivo separadamente
        UsuarioRecordDto usuarioRecordDtoEditado = usuarioServiceImpl.update(usuarioRecordDto, apelidoAntigo, arquivo);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRecordDtoEditado);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuarioRecordDto>> listar() {
        List<UsuarioRecordDto> usuarios = usuarioServiceImpl.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<UsuarioRecordDto> buscarPorId(@PathVariable Long id) {
        UsuarioRecordDto usuarioRecordDto = usuarioServiceImpl.findById(id);
        return ResponseEntity.ok(usuarioRecordDto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> verificarDuplicidade(@RequestParam String apelido) {
        boolean existe = usuarioServiceImpl.existsByApelido(apelido);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/buscar-apelido/{apelido}")
    public ResponseEntity<UsuarioRecordDto> buscarPorApelido(@PathVariable String apelido) {
        UsuarioRecordDto usuarioRecordDto = usuarioServiceImpl.findByApelido(apelido);
        return ResponseEntity.ok(usuarioRecordDto);
    }

}
