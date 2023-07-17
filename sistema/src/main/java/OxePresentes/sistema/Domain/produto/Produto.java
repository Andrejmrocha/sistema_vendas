package OxePresentes.sistema.Domain.produto;

import OxePresentes.sistema.Domain.venda.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private double valor;

    @ManyToMany(mappedBy = "listaProdutos")
    private List<Venda> vendas = new ArrayList<>();

    public Produto(DadosCadastrarProduto dados) {
        this.nome = dados.nome();
        this.valor = dados.valor();
    }

    public void atualizarInformacoes(DadosAtualizarProduto dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.valor() != 0.0) {
            this.valor = dados.valor();
        }
    }
}
