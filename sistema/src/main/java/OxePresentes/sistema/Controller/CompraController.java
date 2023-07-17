package OxePresentes.sistema.Controller;

import OxePresentes.sistema.Domain.compra.Compra;
import OxePresentes.sistema.Domain.compra.CompraRepository;
import OxePresentes.sistema.Domain.compra.DadosCadastrarCompra;
import OxePresentes.sistema.Domain.compra.DadosDetalharCompra;
import OxePresentes.sistema.Domain.insumo.InsumoRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("compras")
public class CompraController {

    @Autowired
    private InsumoRepository insumoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrarCompra dados,
                                    UriComponentsBuilder uriBuilder){
        var compra = new Compra(dados.outrosGastos());

        dados.listaInsumosId().forEach(id -> compra.adicionarInsumo(insumoRepository.getReferenceById(id)));

        compraRepository.save(compra);

        var uri = uriBuilder.path("compras/{id}").buildAndExpand(compra.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalharCompra(compra));

    }
}
