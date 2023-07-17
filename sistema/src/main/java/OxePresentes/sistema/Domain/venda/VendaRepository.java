package OxePresentes.sistema.Domain.venda;

import OxePresentes.sistema.DTO.IntervaloDatas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("""
            SELECT SUM(v.valorProdutos)
            FROM Venda v
            WHERE YEAR(v.dataVenda) = :ano
            """)
    String faturamentoAno(int ano);

    @Query("""
            SELECT SUM(v.valorProdutos)
            FROM Venda v
            WHERE v.dataVenda >= :dataInicial and
            v.dataVenda <= :dataFinal
            """)
    String faturamentoDatas(Date dataInicial, Date dataFinal);
}
