package br.com.sistema.sistema.services;


import br.com.sistema.sistema.dtos.ClienteDTO;
import br.com.sistema.sistema.utils.DataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    public ClienteDTO mockCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Jessica");
        clienteDTO.setDataNascimento(DataUtil.stringToLocalDate("31/08/1996"));
        clienteDTO.setEmail("jessica@gmail.com");
        clienteDTO.setProfissao("Programadora");
        clienteDTO.setCpf("999998888");
        return clienteDTO;
    }

    @Test
    public void incluirTest() {
        ClienteDTO novoClienteDto = mockCliente();

        ClienteDTO clienteIncluidoDto = clienteService.incluir(novoClienteDto);
        assertNotNull("O cliente é não pode ser nulo", clienteIncluidoDto);
        assertEquals(novoClienteDto.getNome(),  clienteIncluidoDto.getNome());
        assertEquals(novoClienteDto.getCpf(), clienteIncluidoDto.getCpf());
        assertEquals(novoClienteDto.getProfissao(), clienteIncluidoDto.getProfissao());
    }
}
