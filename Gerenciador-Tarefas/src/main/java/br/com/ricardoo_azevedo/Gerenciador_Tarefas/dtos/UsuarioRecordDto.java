package br.com.ricardoo_azevedo.Gerenciador_Tarefas.dtos;

import java.util.Set;

import br.com.ricardoo_azevedo.Gerenciador_Tarefas.models.Configuracao;
import br.com.ricardoo_azevedo.Gerenciador_Tarefas.models.Tarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRecordDto(

    @NotBlank(message = "O apelido é obrigatorio")
    @Size(min = 2, max = 200, message = "O campo deve ter o Minimo de 3 caracteres, ou maximo de 200!")
    String apelido,

    @Size(min = 8, message = "O campo deve ter no minimo 8 digitos!")
    String senha,

    @Size(min = 2, max = 100, message = "O campo deve ser no minimo 2 maximo 100")
    String pergunta_seguranca,

    @Size(min = 2, max = 100, message = "O campo deve ser no minimo 2 maximo 100")
    String resposta_seguranca,
    
    @NotBlank(message = "O Caminho deve ser obrigatorio!")
    String foto_perfil,
    /* TENHO Q VER ESSA SITUAÇÂO DEPOIS

    @NotEmpty(message = "Deve incluir Todas as Tarefas relacionadas a usuario / esta vazio")
    Set<Tarefa> tarefas,

    @NotNull(message = "A configuração deve ser obrigatoria para usuario")
    Configuracao configuracao
    */

) {
}
    
