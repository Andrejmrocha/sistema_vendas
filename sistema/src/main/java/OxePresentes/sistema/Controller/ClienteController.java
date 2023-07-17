package OxePresentes.sistema.Controller;

import OxePresentes.sistema.Domain.cliente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrarCliente dados,
                                    UriComponentsBuilder uriBuilder){
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);

        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalharCliente(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizarCliente dados,
                                    UriComponentsBuilder uriBuilder){
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalharCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listar(Pageable paginacao){
        var page = clienteRepository.findAll(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Page<DadosListagemCliente>> buscarPorNome(Pageable paginacao, @PathVariable String nome){
        var page = clienteRepository.findByNomeContaining(nome, paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }
}
