package OxePresentes.sistema.Domain.venda;

import OxePresentes.sistema.Domain.produto.Produto;
import OxePresentes.sistema.Domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> listaProdutos;

    private double valorFrete;

    private double valorProdutos;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private Date dataVenda;

    public Venda(double valorFrete, Cliente cliente, FormaPagamento formaPagamento) {
        this.valorFrete = valorFrete;
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.dataVenda = Date.valueOf(LocalDate.now());
        this.valorProdutos = 0.0;
        this.listaProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto){
        this.listaProdutos.add(produto);
        this.valorProdutos += produto.getValor();

    }

}
