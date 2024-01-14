package br.com.sistema.sistema.services;

import br.com.sistema.sistema.dtos.AgendamentoDTO;
import br.com.sistema.sistema.entities.Agendamento;
import br.com.sistema.sistema.entities.Prestador;
import br.com.sistema.sistema.repositories.AgendamentoRepository;
import br.com.sistema.sistema.repositories.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;
    @Autowired
    private PrestadorRepository prestadorRepository;

    @Transactional(readOnly = true)
    public List<AgendamentoDTO> listar() {
        List<Agendamento> lista = repository.findAll();
        List<AgendamentoDTO> listaDto = new ArrayList<>();
        for(Agendamento item : lista) {
            listaDto.add(new AgendamentoDTO(item));
        }
        return listaDto;
    }

    @Transactional
    public AgendamentoDTO incluir(AgendamentoDTO dto) {
        Optional<Prestador> prestador = prestadorRepository.findById(dto.getPrestadorDTO().getId());
        if(prestador.isEmpty()){
            throw new RuntimeException("Não é possível incluir um agendamento para um prestador inexistente!");
        }

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setDescricao(dto.getDescricao());
        novoAgendamento.setDataHora(dto.getDataHora());
        novoAgendamento.setCriadoEm(LocalDateTime.now());
        novoAgendamento.setPrestador(prestador.get());
        novoAgendamento = repository.save(novoAgendamento);

        return new AgendamentoDTO(novoAgendamento);
    }

    @Transactional
    public AgendamentoDTO atualizar(Integer id, AgendamentoDTO dto){
        Optional<Agendamento> agendaOptional = repository.findById(id);
        if(agendaOptional.isPresent()){
            Agendamento agendamento = agendaOptional.get();
            agendamento.setDescricao(dto.getDescricao());
            agendamento.setDataHora(dto.getDataHora());
            agendamento = repository.save(agendamento);
            return new AgendamentoDTO(agendamento);
        }else{
            throw new RuntimeException("Não é possível atualizar um agendamento inexistente!");
        }
    }

    @Transactional
    public void deletar(Integer id) {
        Optional<Agendamento> agendaOptional = repository.findById(id);
        if(agendaOptional.isPresent()){
            Agendamento agendamento = agendaOptional.get();
            repository.delete(agendamento);
        }else{
            throw new RuntimeException("Não é possível deletar um agendamento inexistente!");
        }
    }




}
