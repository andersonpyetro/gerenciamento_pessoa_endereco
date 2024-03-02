package br.com.attus.gerenciamentopessoasapi.servicetest;



import br.com.attus.gerenciamentopessoasapi.entities.Pessoa;
import br.com.attus.gerenciamentopessoasapi.repository.PessoaRepository;
import br.com.attus.gerenciamentopessoasapi.service.PessoaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;
    @InjectMocks
    private PessoaService pessoaService;
    @Test
    public void testCreatePessoa() {

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        pessoa.setSobrenome("Souza");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        Mockito.when(pessoaService.createPessoa(pessoa)).thenReturn(pessoa);

        Pessoa savedPessoa = pessoaService.createPessoa(pessoa);

        assertNotNull(savedPessoa);
        assertEquals("João Silva", savedPessoa.getNome());
        assertEquals("Souza", savedPessoa.getSobrenome());
        assertEquals(LocalDate.of(1990, 1, 1), savedPessoa.getDataNascimento());
    }
    @Test
    public void testUpdatePessoa() {

        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(1L);
        pessoaExistente.setNome("João Silva");
        pessoaExistente.setSobrenome("Souza");
        pessoaExistente.setDataNascimento(LocalDate.of(1990, 1, 1));

        Pessoa pessoaAtualizada = new Pessoa();
        pessoaAtualizada.setId(1L);
        pessoaAtualizada.setNome("Maria Oliveira");
        pessoaAtualizada.setSobrenome("Santos");
        pessoaAtualizada.setDataNascimento(LocalDate.of(1991, 1, 1));

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoaExistente));

        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenAnswer(invocation -> {
            Pessoa pessoaSalva = invocation.getArgument(0);

            pessoaExistente.setNome(pessoaSalva.getNome());
            pessoaExistente.setSobrenome(pessoaSalva.getSobrenome());
            pessoaExistente.setDataNascimento(pessoaSalva.getDataNascimento());
            return pessoaExistente;
        });

        Pessoa savedPessoa = pessoaService.updatePessoa(1L, pessoaAtualizada);

        assertNotNull(savedPessoa);
        assertEquals("Maria Oliveira", savedPessoa.getNome());
        assertEquals("Santos", savedPessoa.getSobrenome());
        assertEquals(LocalDate.of(1991, 1, 1), savedPessoa.getDataNascimento());
    }

    @Test
    public void testDeletePessoa() {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João Silva");
        pessoa.setSobrenome("Souza");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        Mockito.lenient().when(pessoaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        pessoaService.deletePessoa(1L);

        Mockito.verify(pessoaRepository).deleteById(1L);
    }

    @Test
    public void testGetPessoaById() {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João Silva");
        pessoa.setSobrenome("Souza");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Pessoa foundPessoa = pessoaService.getPessoaById(1L);

        assertNotNull(foundPessoa);
        assertEquals("João Silva", foundPessoa.getNome());
        assertEquals("Souza", foundPessoa.getSobrenome());
        assertEquals(LocalDate.of(1990, 1, 1), foundPessoa.getDataNascimento());
    }

    @Test
    public void testFindAllPessoas() {

        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa(1L, "João Silva", "Souza", LocalDate.of(1990, 1, 1)),
                new Pessoa(2L, "Maria Oliveira", "Santos", LocalDate.of(1995, 2, 2)),
                new Pessoa(3L, "Pedro Souza", "Costa", LocalDate.of(2000, 3, 3))
        );

        Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<Pessoa> foundPessoas = pessoaService.getAllPessoas();

        assertNotNull(foundPessoas);
        assertEquals(3, foundPessoas.size());

        for (Pessoa pessoa : foundPessoas) {
            if (pessoa.getId() == 1L) {
                assertEquals("João Silva", pessoa.getNome());
                assertEquals("Souza", pessoa.getSobrenome());
                assertEquals(LocalDate.of(1990, 1, 1), pessoa.getDataNascimento());
            } else if (pessoa.getId() == 2L) {
                assertEquals("Maria Oliveira", pessoa.getNome());
                assertEquals("Santos", pessoa.getSobrenome());
                assertEquals(LocalDate.of(1995, 2, 2), pessoa.getDataNascimento());
            } else if (pessoa.getId() == 3L) {
                assertEquals("Pedro Souza", pessoa.getNome());
                assertEquals("Costa", pessoa.getSobrenome());
                assertEquals(LocalDate.of(2000, 3, 3), pessoa.getDataNascimento());
            }
        }
    }
}
