package OxePresentes.sistema.Controller;

import OxePresentes.sistema.Domain.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("produtos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrarProduto dados,
                                    UriComponentsBuilder uriBuilder){
        var produto = new Produto(dados);
        produtoRepository.save(produto);

        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalharProduto(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizarProduto dados,
                                    UriComponentsBuilder uriBuilder){
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalharProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(Pageable paginacao){
        var page = produtoRepository.findAll(paginacao).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity detalhar(@PathVariable Long id){
//        var produto = produtoRepository.getReferenceById(id);
//        return ResponseEntity.ok(new DadosDetalharProduto(produto));
//    }

    @GetMapping("/{nome}")
    public ResponseEntity<Page<DadosListagemProduto>> buscarPorNome(Pageable paginacao, @PathVariable String nome){
        var page = produtoRepository.findByNomeContaining(nome, paginacao).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }
}
