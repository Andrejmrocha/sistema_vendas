package OxePresentes.sistema.Controller;

import OxePresentes.sistema.Domain.cliente.ClienteRepository;
import OxePresentes.sistema.Domain.produto.DadosListagemProduto;
import OxePresentes.sistema.Domain.produto.Produto;
import OxePresentes.sistema.Domain.produto.ProdutoRepository;
import OxePresentes.sistema.Domain.venda.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("vendas")
public class VendaController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrarVenda dados,
                                    UriComponentsBuilder uriBuilder){
        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var venda = new Venda(dados.valorFrete(), cliente, dados.formaPagamento(), dados.data());


        dados.listaProdutosId().forEach(id -> venda.adicionarProduto(produtoRepository.getReferenceById(id)));

        vendaRepository.save(venda);

        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalharVenda(venda));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemVenda>> listar(@PageableDefault(
            value = 6, sort = {"dataVenda"}, direction = Sort.Direction.DESC) Pageable paginacao){
        var page = vendaRepository.findAll(paginacao).map(DadosListagemVenda::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("lista")
    public ResponseEntity<Page<ListaVendas>> listarVendas(Pageable pageable){
        var page = vendaRepository.listarVendas(pageable);
        return ResponseEntity.ok(page);
    }
    

}
