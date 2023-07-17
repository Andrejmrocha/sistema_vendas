package OxePresentes.sistema.Domain.cliente;

import OxePresentes.sistema.Domain.cliente.Cliente;

public record DadosDetalharCliente(Long id, String nome, String endereco, String telefone) {

    public DadosDetalharCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone());
    }

}
