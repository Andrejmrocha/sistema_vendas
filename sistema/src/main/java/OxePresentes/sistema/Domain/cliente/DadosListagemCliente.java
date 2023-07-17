package OxePresentes.sistema.Domain.cliente;

public record DadosListagemCliente(Long id, String nome, String endereco, String telefone) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone());
    }
}
