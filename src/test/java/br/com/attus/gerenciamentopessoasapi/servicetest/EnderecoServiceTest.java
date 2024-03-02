package br.com.attus.gerenciamentopessoasapi.servicetest;

import br.com.attus.gerenciamentopessoasapi.entities.Endereco;
import br.com.attus.gerenciamentopessoasapi.repository.EnderecoRepository;
import br.com.attus.gerenciamentopessoasapi.service.EnderecoService;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    public void testCreateEndereco() {

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Teste");
        endereco.setNumero("123");

        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        Endereco savedEndereco = enderecoService.createEndereco(endereco);

        assertNotNull(savedEndereco);
        assertEquals(endereco.getLogradouro(), savedEndereco.getLogradouro());
        assertEquals(endereco.getNumero(), savedEndereco.getNumero());

    }

    @Test
    public void testGetEnderecoById() {

        Endereco endereco = new Endereco();

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        Endereco foundEndereco = enderecoService.getEnderecoById(1L);

        assertNotNull(foundEndereco);
        assertEquals(endereco.getLogradouro(), foundEndereco.getLogradouro());
        assertEquals(endereco.getNumero(), foundEndereco.getNumero());

    }

    @Test
    public void testGetAllEnderecos() {

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco(1L, "Rua Teste 1", "123"));
        enderecos.add(new Endereco(2L, "Rua Teste 2", "456"));

        when(enderecoRepository.findAll()).thenReturn(enderecos);

        List<Endereco> foundEnderecos = enderecoService.getAllEnderecos();

        assertNotNull(foundEnderecos);
        assertEquals(2, foundEnderecos.size());

    }

    @Test
    public void testUpdateEndereco() {

        Endereco endereco = new Endereco(1L, "Rua Teste", "123");

        lenient().when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        Endereco updatedEndereco = enderecoService.updateEndereco(endereco);

        assertNotNull(updatedEndereco);
        assertEquals(endereco.getId(), updatedEndereco.getId());
        assertEquals("Rua Teste", updatedEndereco.getLogradouro());
        assertEquals("123", updatedEndereco.getNumero());
    }

    @Test
    public void testDeleteEndereco() {

        doNothing().when(enderecoRepository).deleteById(1L);
        enderecoService.deleteEndereco(1L);

        verify(enderecoRepository).deleteById(1L);
    }

}

