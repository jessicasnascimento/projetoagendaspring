package br.com.sistema.sistema.repositories;

import br.com.sistema.sistema.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    List<Agendamento> findByData(LocalDate data);
}
