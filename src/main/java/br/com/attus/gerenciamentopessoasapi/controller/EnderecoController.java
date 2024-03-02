package br.com.attus.gerenciamentopessoasapi.controller;

import br.com.attus.gerenciamentopessoasapi.entities.Endereco;
import br.com.attus.gerenciamentopessoasapi.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public Endereco createEndereco(@RequestBody Endereco endereco) {
        return enderecoService.createEndereco(endereco);
    }

    @GetMapping("/{id}")
    public Endereco getEnderecoById(@PathVariable Long id) {
        return enderecoService.getEnderecoById(id);
    }

    @GetMapping
    public List<Endereco> getAllEnderecos() {
        return enderecoService.getAllEnderecos();
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        return enderecoService.updateEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    public void deleteEndereco(@PathVariable Long id) {
        enderecoService.deleteEndereco(id);
    }
}
