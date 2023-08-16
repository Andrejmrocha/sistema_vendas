package OxePresentes.sistema.Domain.venda;

import java.util.List;

public record DadosCadastrarVenda(
        List<Long> listaProdutosId,
        double valorFrete,
        Long idCliente,
        FormaPagamento formaPagamento,
        String data
) {
}
