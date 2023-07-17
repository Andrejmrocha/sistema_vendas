package OxePresentes.sistema.Domain.venda;

import java.sql.Date;

public record DadosDetalharVenda(Long id, String cliente, double valorTotal,
                                 FormaPagamento formaPagamento, Date dataVenda) {
    public DadosDetalharVenda(Venda venda){
        this(venda.getId(), venda.getCliente().getNome(), venda.getValorProdutos(), venda.getFormaPagamento()
        , venda.getDataVenda());
    }
}
