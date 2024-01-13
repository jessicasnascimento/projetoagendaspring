package br.com.sistema.sistema.dtos;

import br.com.sistema.sistema.entities.Agendamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AgendamentoDTO {

    private Integer id;

    private String descricao;

    private LocalDateTime dataHora;

    private PrestadorDTO prestadorDTO;

    public AgendamentoDTO(Agendamento entity) {
        id = entity.getId();
        descricao = entity.getDescricao();
        dataHora = entity.getDataHora();

        prestadorDTO = new PrestadorDTO(entity.getPrestador());
    }

}
