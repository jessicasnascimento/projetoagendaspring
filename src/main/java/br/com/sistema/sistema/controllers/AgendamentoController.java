package br.com.sistema.sistema.controllers;

import br.com.sistema.sistema.dtos.AgendamentoDTO;
import br.com.sistema.sistema.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoDTO> listar() {
        return agendamentoService.listar();
    }

    @GetMapping("/cliente/{id}")
    public List<AgendamentoDTO> listarAgendamentosCliente(@PathVariable Integer idCliente) {
        return agendamentoService.listarAgendamentosCliente(idCliente);
    }

    @PostMapping
    public AgendamentoDTO incluir(@RequestBody AgendamentoDTO dto) {
        return agendamentoService.incluir(dto);
    }

    @PutMapping("/{id}")
    public AgendamentoDTO atualizar(@PathVariable Integer id, @RequestBody AgendamentoDTO dto) {
        return agendamentoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        agendamentoService.deletar(id);
    }

}
