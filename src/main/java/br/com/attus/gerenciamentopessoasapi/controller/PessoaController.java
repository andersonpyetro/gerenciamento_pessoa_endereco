package br.com.attus.gerenciamentopessoasapi.controller;

import br.com.attus.gerenciamentopessoasapi.entities.Endereco;
import br.com.attus.gerenciamentopessoasapi.entities.Pessoa;
import br.com.attus.gerenciamentopessoasapi.service.PessoaService;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.createPessoa(pessoa);
    }

    @GetMapping("/{id}")
    public Pessoa getPessoaById(@PathVariable Long id) {
        return pessoaService.getPessoaById(id);
    }

    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return pessoaService.getAllPessoas();
    }

    @PutMapping("/{id}")
    public Pessoa updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return pessoaService.updatePessoa(id, pessoa);
    }

    @DeleteMapping("/{id}")
    public void deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
    }

    @GetMapping("/endereco-principal")
    public Pessoa getPessoaByEnderecoPrincipal(@RequestParam String logradouro,
                                               @RequestParam Integer cep,
                                               @RequestParam Integer numero,
                                               @RequestParam String cidade,
                                               @RequestParam String estado) {
        return pessoaService.getPessoaByEnderecoPrincipal(logradouro, cep, numero, cidade, estado);
    }
}
