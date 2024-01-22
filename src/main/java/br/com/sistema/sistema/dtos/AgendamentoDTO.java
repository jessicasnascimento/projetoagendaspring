package br.com.sistema.sistema.dtos;

import br.com.sistema.sistema.entities.Agendamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AgendamentoDTO {

    private Integer id;
    private String descricao;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private PrestadorDTO prestadorDTO;
    private String nomeCliente;
    private String emailCliente;

    public AgendamentoDTO(Agendamento entity) {
        id = entity.getId();
        descricao = entity.getDescricao();
        data = entity.getData();
        horaInicio = entity.getHoraInicio();
        horaFim = entity.getHoraFim();
        nomeCliente = entity.getNomeCliente();
        emailCliente = entity.getEmailCliente();

        prestadorDTO = new PrestadorDTO(entity.getPrestador());
    }

}
