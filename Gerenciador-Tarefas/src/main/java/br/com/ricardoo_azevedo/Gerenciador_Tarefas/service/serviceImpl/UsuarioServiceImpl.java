package br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos.UsuarioRecordDto;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.models.Usuario;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.repository.UsuarioRepository;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${image.upload.dir}")
    private String uploadImageDir;

    @Override
    public UsuarioRecordDto save(UsuarioRecordDto usuarioRecordDto, MultipartFile arquivo) {
        /* futuras validações */
        Path uploadImagePath = Paths.get(uploadImageDir);
        if (!Files.exists(uploadImagePath)) {
            try {
                Files.createDirectories(uploadImagePath);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar diretório para salvar imagens.", e);
            }
        }

       
        String fileName = usuarioRecordDto.apelido() + "_" + System.currentTimeMillis() + ".png";
        Path filePath = uploadImagePath.resolve(fileName);

        try {
            arquivo.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a imagem no servidor.", e);
        }

        
        Usuario usuario = new Usuario();
        usuario.setApelido(usuarioRecordDto.apelido());
        usuario.setSenha(usuarioRecordDto.senha());
        usuario.setPergunta_seguranca(usuarioRecordDto.pergunta_seguranca());
        usuario.setResposta_seguranca(usuarioRecordDto.resposta_seguranca());
        usuario.setFoto_perfil(fileName);
        
       Usuario usuarioSalvo = usuarioRepository.save(usuario);

        
        return new UsuarioRecordDto(
            
            usuarioSalvo.getApelido(),
            usuarioSalvo.getSenha(),
            usuarioSalvo.getPergunta_seguranca(),
            usuarioSalvo.getResposta_seguranca(),
            usuarioSalvo.getFoto_perfil()
        );
    }

    }

    @Override
    public UsuarioRecordDto update(UsuarioRecordDto usuarioRecordDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<UsuarioRecordDto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public UsuarioRecordDto findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
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
