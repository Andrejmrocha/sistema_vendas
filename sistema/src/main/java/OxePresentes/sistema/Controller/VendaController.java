package OxePresentes.sistema.Controller;

import OxePresentes.sistema.Domain.cliente.ClienteRepository;
import OxePresentes.sistema.Domain.produto.Produto;
import OxePresentes.sistema.Domain.produto.ProdutoRepository;
import OxePresentes.sistema.Domain.venda.DadosCadastrarVenda;
import OxePresentes.sistema.Domain.venda.DadosDetalharVenda;
import OxePresentes.sistema.Domain.venda.Venda;
import OxePresentes.sistema.Domain.venda.VendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        var venda = new Venda(dados.valorFrete(), cliente, dados.formaPagamento());


        dados.listaProdutosId().forEach(id -> venda.adicionarProduto(produtoRepository.getReferenceById(id)));

        vendaRepository.save(venda);

        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalharVenda(venda));
    }

}
