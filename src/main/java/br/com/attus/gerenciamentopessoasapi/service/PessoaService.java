package br.com.attus.gerenciamentopessoasapi.service;

import br.com.attus.gerenciamentopessoasapi.entities.Endereco;
import br.com.attus.gerenciamentopessoasapi.entities.Pessoa;
import br.com.attus.gerenciamentopessoasapi.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {

        Pessoa pessoaExistente = pessoaRepository.findById(pessoa.getId()).orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + pessoa.getId()));
        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setSobrenome(pessoa.getSobrenome());
        pessoaExistente.setDataNascimento(pessoa.getDataNascimento());

        return pessoaRepository.save(pessoaExistente);
    }

    public void deletePessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
    public Pessoa getPessoaByEnderecoPrincipal(String logradouro, Integer cep, Integer numero, String cidade, String estado) {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        for (Pessoa pessoa : pessoas) {
            Endereco enderecoPrincipal = pessoa.getEnderecoPrincipal();
            if (enderecoPrincipal != null && enderecoPrincipal.isPrincipal() &&
                    enderecoPrincipal.getLogradouro().equals(logradouro) &&
                    enderecoPrincipal.getCep().equals(cep) &&
                    enderecoPrincipal.getNumero().equals(numero) &&
                    enderecoPrincipal.getCidade().equals(cidade) &&
                    enderecoPrincipal.getEstado().equals(estado)) {
                return pessoa;
            }
        }

        throw new EntityNotFoundException("Pessoa com endereço principal não encontrado");
    }
}


