package OxePresentes.sistema.Domain.compra;

import java.sql.Date;

public record DadosDetalharCompra(Long id, Date data, double valorInsumos, double outrosGastos) {

    public DadosDetalharCompra(Compra compra){
        this(compra.getId(), compra.getData(), compra.getValorInsumos(), compra.getOutrosGastos());
    }
}
