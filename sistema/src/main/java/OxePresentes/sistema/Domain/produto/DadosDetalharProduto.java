package OxePresentes.sistema.Domain.produto;

public record DadosDetalharProduto(Long id, String nome, double valor) {

    public DadosDetalharProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getValor());
    }
}
