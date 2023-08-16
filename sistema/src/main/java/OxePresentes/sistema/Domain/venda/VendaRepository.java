package OxePresentes.sistema.Domain.venda;

import OxePresentes.sistema.DTO.IntervaloDatas;
import OxePresentes.sistema.Domain.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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

    Venda getReferenceById(Long id);

    @Query("""
            SELECT DATE_FORMAT(v.dataVenda, '%Y-%m') AS mesAno, SUM(v.valorProdutos) AS valorTotal
            FROM Venda v
            WHERE v.dataVenda >= :startDate
            GROUP BY DATE_FORMAT(v.dataVenda, '%Y-%m')
            ORDER BY DATE_FORMAT(v.dataVenda, '%Y-%m')
            """)
    Page<Dados12meses> getReceitasUltimos12meses(@Param("startDate")Date startDate, Pageable pageable);

    @Query("""
            SELECT v.id as id, v.dataVenda as data, v.formaPagamento as pg, v.valorProdutos as vp, c.nome as nome
            FROM Venda v
            inner join Cliente c
            on c.id = v.cliente.id
            """)
    Page<ListaVendas> listarVendas(Pageable pageable);

}
