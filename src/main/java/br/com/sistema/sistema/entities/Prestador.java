package br.com.sistema.sistema.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_PRESTADOR")
public class Prestador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String servico;

    private String descricao;

    @OneToMany(mappedBy = "prestador")
    private List<Agendamento> agendamentos;

}
