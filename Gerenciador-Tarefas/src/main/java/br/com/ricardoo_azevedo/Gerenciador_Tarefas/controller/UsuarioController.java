package br.com.ricardoo_azevedo.Gerenciador_Tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos.UsuarioRecordDto;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.Impl.UsuarioServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioRecordDto> salvar(@ModelAttribute UsuarioRecordDto usuarioRecordDto, @RequestPart("arquivo") MultipartFile arquivo){
        UsuarioRecordDto usuarioRecordDtoSalvo =  usuarioServiceImpl.save(usuarioRecordDto, arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRecordDtoSalvo);    
    }

    @PatchMapping("/editar/{apelido}")
    public ResponseEntity<UsuarioRecordDto> editar(@ModelAttribute UsuarioRecordDto usuarioRecordDto, @PathVariable String apelido, @RequestPart("arquivo") MultipartFile arquivo){
        UsuarioRecordDto usuarioRecordDtoEditado = usuarioServiceImpl.update(usuarioRecordDto, apelido, arquivo);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRecordDtoEditado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioRecordDto>> listar(){
        List<UsuarioRecordDto> usuarios = usuarioServiceImpl.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRecordDto> buscarPorId(@PathVariable Long id){
        UsuarioRecordDto usuarioRecordDto = usuarioServiceImpl.findById(id);
        return ResponseEntity.ok(usuarioRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> verificarDuplicidade(@RequestParam String apelido){
        boolean existe = usuarioServiceImpl.existsByApelido(apelido);
        return ResponseEntity.ok(existe); 
    }

    @GetMapping("/{apelido}")
    public ResponseEntity<UsuarioRecordDto> buscarPorApelido(@PathVariable String apelido){
        UsuarioRecordDto usuarioRecordDto = usuarioServiceImpl.findByApelido(apelido);
        return ResponseEntity.ok(usuarioRecordDto);
    }
     
}
