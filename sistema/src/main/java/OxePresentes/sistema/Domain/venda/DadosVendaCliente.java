package OxePresentes.sistema.Domain.venda;

import java.math.BigDecimal;
import java.sql.Date;

public interface DadosVendaCliente {
    String getNomeP();
    BigDecimal getValor();
    Date getDataVenda();
    String getNomeC();
    Long getId();
    String getTelefone();

}
