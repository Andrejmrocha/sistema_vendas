package OxePresentes.sistema.Domain.venda;

import java.math.BigDecimal;
import java.sql.Date;

public interface ListaVendas {
    Long getId();
    Date getData();
    String getPg();
    BigDecimal getVp();
    String getNome();
}
