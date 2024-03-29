package br.com.sistema.sistema.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cpf;

    private String email;

    private LocalDate dataNascimento;

    private Number idade;

    private String profissao;

    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentos;

}
