package OxePresentes.sistema.Domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String endereco;

    private String telefone;

    public Cliente(DadosCadastrarCliente dados) {
        this.nome = dados.nome();
        this.endereco = dados.endereco();
        this.telefone = dados.telefone();
    }

    public void atualizarInformacoes(DadosAtualizarCliente dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.endereco() != null){
            this.endereco = dados.endereco();
        }

        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
    }
}
