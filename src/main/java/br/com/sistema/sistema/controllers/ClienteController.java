package br.com.sistema.sistema.controllers;

import br.com.sistema.sistema.dtos.ClienteDTO;
import br.com.sistema.sistema.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @PostMapping
    public ClienteDTO incluir (@RequestBody ClienteDTO dto){
        return clienteService.incluir(dto);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Integer id, @RequestBody ClienteDTO dto){
        return clienteService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        clienteService.deletar(id);
    }

}
