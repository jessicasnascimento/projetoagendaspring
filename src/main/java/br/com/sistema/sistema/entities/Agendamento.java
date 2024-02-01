package br.com.sistema.sistema.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "TB_AGENDA")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    private LocalDate data;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
