package br.com.sistema.sistema.services;

import br.com.sistema.sistema.entities.Cliente;
import br.com.sistema.sistema.dtos.ClienteDTO;
import br.com.sistema.sistema.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> listar() {
        List<Cliente> lista = repository.findAll();
        List<ClienteDTO> listaDto = new ArrayList<>();
        for(Cliente item : lista) {
            listaDto.add(new ClienteDTO(item));
        }
        return listaDto;
    }

    @Transactional
    public ClienteDTO incluir(ClienteDTO dto) {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(dto.getNome());
        novoCliente.setCpf(dto.getCpf());
        novoCliente.setEmail(dto.getEmail());
        novoCliente.setDataNascimento(dto.getDataNascimento());

        // Calcula a idade e define no cliente
        novoCliente.setIdade(calcularIdade(dto));

        //Validação para impedir o cadastro de CPFs duplicados
        if (repository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        novoCliente.setProfissao(dto.getProfissao());
        novoCliente = repository.save(novoCliente);

        return new ClienteDTO(novoCliente);
    }

    @Transactional
    public ClienteDTO atualizar(Integer id, ClienteDTO dto){
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.setNome(dto.getNome());
            cliente.setEmail(dto.getEmail());
            cliente.setDataNascimento(dto.getDataNascimento());

            //Calcula a idade e atualiza no cliente
            cliente.setIdade(calcularIdade(dto));

            cliente.setProfissao(dto.getProfissao());
            cliente = repository.save(cliente);

            return new ClienteDTO(cliente);
        }else{
            throw new RuntimeException("Não é possível atualizar um Cliente inexistente!");
        }
    }

    @Transactional
    public void deletar(Integer id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            repository.delete(cliente);
        }else{
            throw new RuntimeException("Não é possível excluir um Cliente inexistente!");
        }
    }

    public int calcularIdade(ClienteDTO dto) {
        if (dto != null && dto.getDataNascimento() != null) {
            LocalDate dataNascimento = dto.getDataNascimento();
            LocalDate hoje = LocalDate.now();
            return Period.between(dataNascimento, hoje).getYears();
        } else {
            throw new RuntimeException("Data de nascimento não fornecida.");
        }
    }

}
