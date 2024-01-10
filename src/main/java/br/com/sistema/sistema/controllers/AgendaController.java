package br.com.sistema.sistema.controllers;

import br.com.sistema.sistema.dtos.AgendaDTO;
import br.com.sistema.sistema.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public List<AgendaDTO> listar() {
        return agendaService.listar();
    }

    @PostMapping
    public AgendaDTO incluir(@RequestBody AgendaDTO dto) {
        return agendaService.incluir(dto);
    }

}
