package OxePresentes.sistema.Domain.compra;

import java.util.List;

public record DadosCadastrarCompra(
        List<Long> listaInsumosId,
        double outrosGastos
) {
}
