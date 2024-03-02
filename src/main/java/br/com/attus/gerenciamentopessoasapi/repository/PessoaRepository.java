package br.com.attus.gerenciamentopessoasapi.repository;

import br.com.attus.gerenciamentopessoasapi.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
