package br.com.sistema.sistema.controllers;

import br.com.sistema.sistema.dtos.PrestadorDTO;
import br.com.sistema.sistema.services.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestador")

public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @GetMapping
    public List<PrestadorDTO> listar() {
        return prestadorService.listar();
    }

    @PostMapping
    public PrestadorDTO incluir (@RequestBody PrestadorDTO dto){
        return prestadorService.incluir(dto);
    }

    @PutMapping("/{id}")
    public PrestadorDTO atualizar(@PathVariable Integer id, @RequestBody PrestadorDTO dto){
        return prestadorService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        prestadorService.deletar(id);
    }
}
