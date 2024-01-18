package br.com.sistema.sistema.services;

import br.com.sistema.sistema.dtos.AgendamentoDTO;
import br.com.sistema.sistema.entities.Agendamento;
import br.com.sistema.sistema.entities.Prestador;
import br.com.sistema.sistema.repositories.AgendamentoRepository;
import br.com.sistema.sistema.repositories.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
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
        validarAgendamento(dto, prestador);

        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setDescricao(dto.getDescricao());
        novoAgendamento.setData(dto.getData());
        novoAgendamento.setHoraInicio(dto.getHoraInicio());
        novoAgendamento.setHoraFim(dto.getHoraFim());
        novoAgendamento.setPrestador(prestador.get());

        // Lógica para incluir o agendamento após a validação
        Agendamento agendamentoSalvo = repository.save(novoAgendamento);
        return new AgendamentoDTO(agendamentoSalvo);
    }

    @Transactional
    public AgendamentoDTO atualizar(Integer id, AgendamentoDTO dto){
        Optional<Agendamento> agendaOptional = repository.findById(id);
        if(agendaOptional.isPresent()){
            Agendamento agendamento = agendaOptional.get();
            agendamento.setDescricao(dto.getDescricao());
            agendamento.setData(dto.getData());
            agendamento.setHoraInicio(dto.getHoraInicio());
            agendamento.setHoraFim(dto.getHoraFim());
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

    public void  validarAgendamento(AgendamentoDTO dto, Optional<Prestador> prestador) {
        if(prestador.isEmpty()){
            throw new RuntimeException("Não é possível incluir um agendamento para um prestador inexistente!");
        }

        List<Agendamento> agendamentosNoMesmoDia = repository.findByData(dto.getData());

        for (Agendamento existente : agendamentosNoMesmoDia) {
            // Verifica se há sobreposição de horários com intervalo mínimo de 30 minutos
            if (horariosSobrepostosComIntervalo(existente, dto, 30)) {
                throw new RuntimeException("Agendamento inválido. Motivo: Só podem ser feitos agendamentos no intervalo de 30min de um para outro!");
            }
        }
    }

    private boolean horariosSobrepostosComIntervalo(Agendamento existente, AgendamentoDTO dto, long minutosDeIntervalo) {
        // Verifica se há sobreposição de horários
        boolean overlap = dto.getHoraInicio().isBefore(existente.getHoraFim()) &&
                dto.getHoraFim().isAfter(existente.getHoraInicio());

        // Verifica se o intervalo é menor que o mínimo permitido (30 minutos)
        boolean intervaloMenorQuePermitido = ChronoUnit.MINUTES.between(existente.getHoraFim(), dto.getHoraInicio()) < minutosDeIntervalo
                || ChronoUnit.MINUTES.between(dto.getHoraFim(), existente.getHoraInicio()) < minutosDeIntervalo;

        return overlap && intervaloMenorQuePermitido;
    }


}
