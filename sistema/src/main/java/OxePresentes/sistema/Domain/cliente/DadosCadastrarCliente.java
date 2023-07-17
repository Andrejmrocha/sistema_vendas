package OxePresentes.sistema.Domain.cliente;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarCliente(
        @NotBlank
        String nome,
        @NotBlank
        String endereco,
        @NotBlank
        String telefone
) {
}
