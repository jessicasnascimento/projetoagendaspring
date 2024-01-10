package br.com.sistema.sistema.dtos;

import br.com.sistema.sistema.entities.Agenda;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AgendaDTO {

    private Integer id;

    private String descricao;

    private LocalDateTime dataHora;

    public AgendaDTO(Agenda entity) {
        id = entity.getId();
        descricao = entity.getDescricao();
        dataHora = entity.getDataHora();
    }

}
