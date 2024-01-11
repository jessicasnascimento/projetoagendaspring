package br.com.sistema.sistema.services;

import br.com.sistema.sistema.dtos.PrestadorDTO;
import br.com.sistema.sistema.entities.Prestador;
import br.com.sistema.sistema.repositories.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrestadorService {

    @Autowired
    private PrestadorRepository repository;

    @Transactional(readOnly = true)
    public List<PrestadorDTO> listar() {
        List<Prestador> lista = repository.findAll();
        List<PrestadorDTO> listaDTO = new ArrayList<>();
        for (Prestador item : lista) {
            listaDTO.add(new PrestadorDTO(item));
        }
        return listaDTO;
    }

    @Transactional
    public PrestadorDTO incluir(PrestadorDTO dto) {
        Prestador novoPrestador = new Prestador();
        novoPrestador.setNome(dto.getNome());
        novoPrestador.setServico(dto.getServico());
        novoPrestador.setDescricao(dto.getDescricao());
        novoPrestador = repository.save(novoPrestador);

        return new PrestadorDTO(novoPrestador);
    }

    @Transactional
    public PrestadorDTO atualizar(Integer id, PrestadorDTO dto) {
        Optional<Prestador> prestadorOptional = repository.findById(id);

        if (prestadorOptional.isPresent()) {
            Prestador prestador = prestadorOptional.get();
            prestador.setNome(dto.getNome());
            prestador.setServico(dto.getServico());
            prestador.setDescricao(dto.getDescricao());
            prestador = repository.save(prestador);

            return new PrestadorDTO(prestador);
        }else{
            throw new RuntimeException("Não é possível atualizar um Prestador inexistente!");
        }
    }

    @Transactional
    public void deletar(Integer id) {
        Optional<Prestador> prestadorOptional = repository.findById(id);
        if (prestadorOptional.isPresent()) {
            Prestador prestador = prestadorOptional.get();
            repository.delete(prestador);
        }else{
            throw new RuntimeException("Não é possível deletar um Prestador inexistente!");
        }
    }

}
