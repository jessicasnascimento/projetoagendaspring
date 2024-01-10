package br.com.sistema.sistema.services;

import br.com.sistema.sistema.dtos.AgendaDTO;
import br.com.sistema.sistema.entities.Agenda;
import br.com.sistema.sistema.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    public List<AgendaDTO> listar() {
        List<Agenda> lista = repository.findAll();
        List<AgendaDTO> listaDto = new ArrayList<>();
        for(Agenda item : lista) {
            listaDto.add(new AgendaDTO(item));
        }
        return listaDto;
    }

    @Transactional
    public AgendaDTO incluir(AgendaDTO dto) {
        Agenda novaAgenda = new Agenda();
        novaAgenda.setDescricao(dto.getDescricao());
        novaAgenda.setDataHora(dto.getDataHora());

        novaAgenda = repository.save(novaAgenda);

        return new AgendaDTO(novaAgenda);
    }
}
