package OxePresentes.sistema.Domain.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    @Query("""
            SELECT SUM(c.valorInsumos)
            FROM Compra c
            WHERE YEAR(c.data) = :ano
            """)
    String faturamentoAno(int ano);

    @Query("""
            SELECT SUM(c.valorInsumos + c.outrosGastos)
            FROM Compra c
            WHERE c.data >= :dataInicial and
            c.data <= :dataFinal
            """)
    String faturamentoDatas(Date dataInicial, Date dataFinal);
}
