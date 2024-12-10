package br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos.UsuarioRecordDto;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ApelidoExistenteException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ApelidoNullException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.CaminhoImagemNullException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.DiretorioNaoCriadoException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.IdIncompativelException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ImagemNaoSalvaException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.ListaVaziaException;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.SenhaCurtaException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.exceptionHandler.exceptions.UsuarioNaoAchadoException;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.models.Usuario;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.repository.UsuarioRepository;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.interfaces.UsuarioServiceInterface;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioServiceInterface {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
 

    @Override
    public String saveImage(UsuarioRecordDto usuarioRecordDto, String uploadImadeDir, MultipartFile arquivo) {
        Path uploadImagePath = Paths.get(uploadImadeDir);
        if(uploadImadeDir == null || uploadImadeDir.isBlank()){
            throw new CaminhoImagemNullException();
        }
        if(!Files.exists(uploadImagePath)){
            try {
                Files.createDirectories(uploadImagePath);
            } catch (IOException e) {
                throw new DiretorioNaoCriadoException();
            }
        }
        String originalFileName = arquivo.getOriginalFilename();
        String extensao = originalFileName.substring(originalFileName.lastIndexOf("."));
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyy");
        String timestamp = LocalDateTime.now().format(formato);
        String fileNome = usuarioRecordDto.getApelido() + "_" + timestamp + extensao;
        Path filePath = uploadImagePath.resolve(fileNome);
        try {
            arquivo.transferTo(filePath);
            return fileNome;
        } catch (IOException e) {
            throw new ImagemNaoSalvaException(e.getMessage());
            
        }

    }

    @Override
    public UsuarioRecordDto save(UsuarioRecordDto usuarioRecordDto, String fileNome) {
        Usuario usuario = new Usuario();
        if (usuarioRepository.existsByApelido(usuarioRecordDto.getApelido())) {
            throw new ApelidoExistenteException();
        }
        if (usuarioRecordDto.getApelido().isEmpty() || usuarioRecordDto.getApelido().isBlank() || usuarioRecordDto.getApelido() == null) {
            throw new ApelidoNullException();
        }
        if (usuarioRecordDto.getSenha().length() < 8) {
            throw new SenhaCurtaException();
        }
        usuario.setApelido(usuarioRecordDto.getApelido());
        usuario.setSenha(usuarioRecordDto.getSenha());
        usuario.setPergunta_seguranca(usuarioRecordDto.getPergunta_seguranca());
        usuario.setResposta_seguranca(usuarioRecordDto.getResposta_seguranca());
        usuario.setFoto_perfil(fileNome);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioRecordDto(
                usuarioSalvo.getApelido(),
                usuarioSalvo.getSenha(),
                usuarioSalvo.getPergunta_seguranca(),
                usuarioSalvo.getResposta_seguranca(),
                usuarioSalvo.getFoto_perfil());
    }

    @Override
    @Transactional
    public UsuarioRecordDto update(UsuarioRecordDto usuarioRecordDto, String apelidoAntigo, String fileNome) {
        
        Usuario usuario = usuarioRepository.findByApelidoNative(apelidoAntigo)
                .orElseThrow(() -> new UsuarioNaoAchadoException());
        if (usuarioRepository.existsByApelido(usuarioRecordDto.getApelido())) {
            throw new ApelidoExistenteException();
        }
        if (usuarioRecordDto.getApelido().isEmpty() || usuarioRecordDto.getApelido().isBlank() || usuarioRecordDto.getApelido() == null) {
            throw new ApelidoNullException();
        }
        if (usuarioRecordDto.getSenha().length() < 8) {
            throw new SenhaCurtaException();
        }
        usuario.setApelido(usuarioRecordDto.getApelido());
        usuario.setSenha(usuarioRecordDto.getSenha());
        usuario.setPergunta_seguranca(usuarioRecordDto.getPergunta_seguranca());
        usuario.setResposta_seguranca(usuarioRecordDto.getResposta_seguranca());
        usuario.setFoto_perfil(fileNome);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioRecordDto(
                usuarioSalvo.getApelido(),
                usuarioSalvo.getSenha(),
                usuarioSalvo.getPergunta_seguranca(),
                usuarioSalvo.getResposta_seguranca(),
                usuarioSalvo.getFoto_perfil());
    }

    @Override
    public List<UsuarioRecordDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new ListaVaziaException();
        }
        return usuarios.stream()
                .map(usuario -> new UsuarioRecordDto(
                        usuario.getApelido(),
                        usuario.getSenha(),
                        usuario.getPergunta_seguranca(),
                        usuario.getResposta_seguranca(),
                        usuario.getFoto_perfil()))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioRecordDto findById(Long id) {
        if (id == null) {
            throw new IdIncompativelException("O id esta Nulo!");
        }
        if (!(id instanceof Long)) {
            throw new IdIncompativelException("O tipo do Id não esta como esperado!");
        }
        return usuarioRepository.findById(id)
                .map(usuario -> new UsuarioRecordDto(
                        usuario.getApelido(),
                        usuario.getSenha(),
                        usuario.getPergunta_seguranca(),
                        usuario.getResposta_seguranca(),
                        usuario.getFoto_perfil()))
                .orElseThrow(() -> new UsuarioNaoAchadoException());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IdIncompativelException("O id Esta Nulo!");
        }
        if (!(id instanceof Long)) {
            throw new IdIncompativelException("O tipo do Id não esta como Esperado!");
        }
        if(usuarioRepository.existsById(id) == false){
            throw new UsuarioNaoAchadoException();
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByApelido(String apelido) {
        if (apelido == null) {
            throw new ApelidoNullException();
        }
        return usuarioRepository.existsByApelido(apelido);
    }

    @Override
    public UsuarioRecordDto findByApelido(String apelido) {
        if (apelido == null) {
            throw new ApelidoNullException();
        }
        return usuarioRepository.findByApelidoNative(apelido)
                .map(usuario -> new UsuarioRecordDto(
                        apelido,
                        usuario.getSenha(),
                        usuario.getPergunta_seguranca(),
                        usuario.getResposta_seguranca(),
                        usuario.getFoto_perfil()))
                .orElseThrow(() -> new UsuarioNaoAchadoException());

    }


}
