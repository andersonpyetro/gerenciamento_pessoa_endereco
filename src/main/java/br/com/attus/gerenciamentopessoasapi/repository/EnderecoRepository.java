package br.com.attus.gerenciamentopessoasapi.repository;

import br.com.attus.gerenciamentopessoasapi.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
