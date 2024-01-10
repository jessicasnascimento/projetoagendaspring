package br.com.sistema.sistema.services;

import br.com.sistema.sistema.dtos.AgendaDTO;
import br.com.sistema.sistema.entities.Agenda;
import br.com.sistema.sistema.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    @Transactional(readOnly = true)
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
        novaAgenda.setCriadoEm(LocalDateTime.now());
        novaAgenda = repository.save(novaAgenda);

        return new AgendaDTO(novaAgenda);
    }

    @Transactional
    public AgendaDTO atualizar(Integer id, AgendaDTO dto){
        Optional<Agenda> agendaOptional = repository.findById(id);
        if(agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            agenda.setDescricao(dto.getDescricao());
            agenda.setDataHora(dto.getDataHora());
            agenda = repository.save(agenda);
            return new AgendaDTO(agenda);
        }
        return new AgendaDTO();
    }

    @Transactional
    public void deletar(Integer id) {
        Optional<Agenda> agendaOptional = repository.findById(id);
        if(agendaOptional.isPresent()){
            Agenda agenda = agendaOptional.get();
            repository.delete(agenda);
        }
    }


}
