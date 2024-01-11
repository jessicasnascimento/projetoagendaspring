package br.com.sistema.sistema.dtos;

import br.com.sistema.sistema.entities.Prestador;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrestadorDTO {

    private Integer id;

    private String nome;

    private String servico;

    private String descricao;

    public PrestadorDTO(Prestador entity){
        id = entity.getId();
        nome = entity.getNome();
        servico = entity.getServico();
        descricao = entity.getDescricao();
    }

}
