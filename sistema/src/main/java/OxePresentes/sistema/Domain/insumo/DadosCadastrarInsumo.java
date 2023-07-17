package OxePresentes.sistema.Domain.insumo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarInsumo(
        @NotBlank
        String nome,
        @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que 0")
        double valor
) {
}
