package OxePresentes.sistema.Domain.insumo;

import OxePresentes.sistema.Domain.produto.Produto;

public record DadosDetalharInsumo(Long id, String nome, double valor) {

    public DadosDetalharInsumo(Insumo insumo){
        this(insumo.getId(), insumo.getNome(), insumo.getValor());
    }
}
