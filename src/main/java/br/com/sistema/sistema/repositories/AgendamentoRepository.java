package br.com.sistema.sistema.repositories;

import br.com.sistema.sistema.entities.Agendamento;
import br.com.sistema.sistema.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    List<Agendamento> findByData(LocalDate data);

    @Query("select ag from Agendamento ag where ag.cliente.id = :idCliente")
    List<Agendamento> findByClienteId(@Param("idCliente") Integer idCliente);

}
