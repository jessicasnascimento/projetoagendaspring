package br.com.sistema.sistema.services;

import br.com.sistema.sistema.entities.Cliente;
import br.com.sistema.sistema.dtos.ClienteDTO;
import br.com.sistema.sistema.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        novoCliente.setIdade(dto.getIdade());
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
            cliente.setIdade(dto.getIdade());
            cliente.setProfissao(dto.getProfissao());
            cliente = repository.save(cliente);

            return new ClienteDTO(cliente);
        }
        return new ClienteDTO();
    }

    @Transactional
    public void deletar(Integer id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            repository.delete(cliente);
        }
    }

}
