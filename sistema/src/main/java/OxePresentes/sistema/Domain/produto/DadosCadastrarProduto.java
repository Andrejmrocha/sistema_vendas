package OxePresentes.sistema.Domain.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarProduto(
        @NotBlank
        String nome,
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que 0")
        double valor
) {
}
