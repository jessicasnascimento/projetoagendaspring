package br.com.sistema.sistema.dtos;

import br.com.sistema.sistema.entities.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Integer id;

    private String nome;

    private Number idade;

    private String profissao;

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        idade = entity.getIdade();
        profissao = entity.getProfissao();
    }
}
