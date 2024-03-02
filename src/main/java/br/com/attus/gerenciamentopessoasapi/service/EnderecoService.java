package br.com.attus.gerenciamentopessoasapi.service;

import br.com.attus.gerenciamentopessoasapi.entities.Endereco;
import br.com.attus.gerenciamentopessoasapi.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }
    public Endereco createEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
    public Endereco getEnderecoById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado para o id: " + id));
    }
    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }
    public Endereco updateEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
    public void deleteEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }
}
