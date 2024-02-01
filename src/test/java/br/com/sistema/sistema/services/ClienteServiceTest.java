package br.com.sistema.sistema.services;


import br.com.sistema.sistema.dtos.ClienteDTO;
import br.com.sistema.sistema.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;


public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;


    public void testIncluir() {
        ClienteDTO novoClienteDto = new ClienteDTO();
        novoClienteDto.setNome("Jessica");
        novoClienteDto.setDataNascimento(DataUtil.stringToLocalDate("31/08/1996"));
        novoClienteDto.setEmail("jessica@gmail.com");
        novoClienteDto.setProfissao("Programadora");

        ClienteDTO clienteIncluidoDto = clienteService.incluir(novoClienteDto);
    }

}
