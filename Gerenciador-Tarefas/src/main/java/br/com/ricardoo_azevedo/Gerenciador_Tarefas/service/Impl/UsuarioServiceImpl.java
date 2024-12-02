package br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos.UsuarioRecordDto;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.models.Usuario;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.repository.UsuarioRepository;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.interfaces.UsuarioServiceInterface;



@Service
public class UsuarioServiceImpl implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${image.upload.dir}")
    private String uploadImagemDir;

    @Override
    public UsuarioRecordDto save(UsuarioRecordDto usuarioRecordDto, MultipartFile arquivo) {
        /* faze as validação mais tarde */
        Path uploadImagemPath = Paths.get(uploadImagemDir);
        if (!Files.exists(uploadImagemPath)) {
            try {
                Files.createDirectories(uploadImagemPath);
            } catch (IOException e) {
                throw new RuntimeException("[Erro ao criar diretório para salvar imagens: "+e.getMessage()+"]");
            }
        }
        String fileNome = usuarioRecordDto.apelido() + "_" + System.currentTimeMillis() + ".png";
        Path filePath = uploadImagemPath.resolve(fileNome);
        try {
            arquivo.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException("[Erro ao salvar a imagem no servidor: "+e.getMessage()+"]");
        }
        Usuario usuario = new Usuario();
        usuario.setApelido(usuarioRecordDto.apelido());
        usuario.setSenha(usuarioRecordDto.senha());
        usuario.setPergunta_seguranca(usuarioRecordDto.pergunta_seguranca());
        usuario.setResposta_seguranca(usuarioRecordDto.resposta_seguranca());
        usuario.setFoto_perfil(fileNome);
        Usuario usuarioSalvo = usuarioRepository.save(usuario); 
        return new UsuarioRecordDto(
            usuarioSalvo.getApelido(),
            usuarioSalvo.getSenha(),
            usuarioSalvo.getPergunta_seguranca(),
            usuarioSalvo.getResposta_seguranca(),
            usuarioSalvo.getFoto_perfil()
        );
    }

    

    @Override
    public UsuarioRecordDto update(UsuarioRecordDto usuarioRecordDto) {
        /* validacoes futuras */

        //preciso arrumar um jeito de pegar o id pra usar no update aqui
        // findById(usuarioRecordDto.)
    }

    @Override
    public List<UsuarioRecordDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public UsuarioRecordDto findById(Long id) {
        /*validacoes futuras pra eu não esquecer */

        return usuarioRepository.findById(id)
        .map(usuario -> new UsuarioRecordDto(usuario.getApelido(), 
        usuario.getSenha(), 
        usuario.getPergunta_seguranca(), 
        usuario.getResposta_seguranca(), 
        usuario.getFoto_perfil()
        )).orElseThrow(); //adiciona a validação aqui mais tarde
    }

    @Override
    public void deleteById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public boolean existsByApelido(String apelido) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByApelido'");
    }

}
