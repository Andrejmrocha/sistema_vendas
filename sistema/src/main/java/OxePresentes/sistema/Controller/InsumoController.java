package OxePresentes.sistema.Controller;

import OxePresentes.sistema.Domain.insumo.DadosCadastrarInsumo;
import OxePresentes.sistema.Domain.insumo.DadosDetalharInsumo;
import OxePresentes.sistema.Domain.insumo.Insumo;
import OxePresentes.sistema.Domain.insumo.InsumoRepository;
import OxePresentes.sistema.Domain.produto.DadosCadastrarProduto;
import OxePresentes.sistema.Domain.produto.DadosDetalharProduto;
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
@RequestMapping("insumos")
public class InsumoController {

    @Autowired
    private InsumoRepository insumoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastrarInsumo dados,
                                    UriComponentsBuilder uriBuilder){
        var insumo = new Insumo(dados);
        insumoRepository.save(insumo);

        var uri = uriBuilder.path("insumos/{id}").buildAndExpand(insumo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalharInsumo(insumo));

    }
}
