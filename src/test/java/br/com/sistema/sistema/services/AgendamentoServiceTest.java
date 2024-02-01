package br.com.sistema.sistema.services;

import br.com.sistema.sistema.dtos.AgendamentoDTO;
import br.com.sistema.sistema.dtos.ClienteDTO;
import br.com.sistema.sistema.dtos.PrestadorDTO;
import br.com.sistema.sistema.entities.Agendamento;
import br.com.sistema.sistema.entities.Cliente;
import br.com.sistema.sistema.entities.Prestador;
import br.com.sistema.sistema.repositories.AgendamentoRepository;
import br.com.sistema.sistema.repositories.ClienteRepository;
import br.com.sistema.sistema.repositories.PrestadorRepository;
import br.com.sistema.sistema.utils.DataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
public class AgendamentoServiceTest {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public ClienteDTO mockCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Jessica Cliente");
        cliente.setDataNascimento(DataUtil.stringToLocalDate("31/08/1996"));
        cliente.setEmail("jessicacliente@gmail.com");
        cliente.setProfissao("Programadora Cliente");
        cliente.setCpf("999995555");

        clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    private PrestadorDTO mockPrestador() {
        Prestador prestador = new Prestador();
        prestador.setNome("Rogerio");
        prestador.setDescricao("Motorista de aplicativo");
        prestador.setServico("Motorista");

        prestador = prestadorRepository.save(prestador);

        PrestadorDTO prestadorDTO = new PrestadorDTO();
        BeanUtils.copyProperties(prestador, prestadorDTO);

        return prestadorDTO;
    }

    @Test
    public void incluirTest() {
        ClienteDTO clienteDTO = mockCliente();
        PrestadorDTO prestadorDTO = mockPrestador();

        AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
        agendamentoDTO.setClienteDTO(clienteDTO);
        agendamentoDTO.setPrestadorDTO(prestadorDTO);
        agendamentoDTO.setDescricao("Transporte dentro da cidade");
        agendamentoDTO.setData(LocalDate.now().plusDays(1));
        agendamentoDTO.setHoraInicio(LocalTime.now().plusHours(4));
        agendamentoDTO.setHoraFim(LocalTime.now().plusHours(4).plusMinutes(30));

        AgendamentoDTO agendamentoIncluidoDto = agendamentoService.incluir(agendamentoDTO);
        assertNotNull("O agendamento não foi incluido", agendamentoIncluidoDto);
    }

}
