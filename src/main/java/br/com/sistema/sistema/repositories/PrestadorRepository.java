package br.com.sistema.sistema.repositories;


import br.com.sistema.sistema.entities.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

}
