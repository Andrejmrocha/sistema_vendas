package OxePresentes.sistema.Domain.produto;

public record DadosListagemProduto(Long id, String nome, double valor) {

    public DadosListagemProduto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getValor());
    }
}
