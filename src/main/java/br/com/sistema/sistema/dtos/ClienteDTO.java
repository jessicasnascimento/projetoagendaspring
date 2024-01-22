package br.com.sistema.sistema.dtos;

import br.com.sistema.sistema.entities.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private Number idade;
    private String profissao;
    private String email;

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        dataNascimento = entity.getDataNascimento();
        idade = entity.getIdade();
        profissao = entity.getProfissao();
        email = entity.getEmail();
    }
}
