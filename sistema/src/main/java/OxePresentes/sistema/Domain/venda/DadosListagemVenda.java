package OxePresentes.sistema.Domain.venda;


import OxePresentes.sistema.Domain.cliente.Cliente;
import OxePresentes.sistema.Domain.produto.Produto;

import java.sql.Date;
import java.util.List;

public record DadosListagemVenda(double valorProdutos, String cliente, Date data, String local) {

    public DadosListagemVenda(Venda venda){
        this(venda.getValorProdutos(), venda.getCliente().getNome(), venda.getDataVenda(),
                venda.getCliente().getEndereco());
    }
}
