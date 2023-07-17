package OxePresentes.sistema.Domain.compra;

import OxePresentes.sistema.Domain.insumo.Insumo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "compras")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data;

    @ManyToMany
    @JoinTable(
            name = "compra_insumos",
            joinColumns = @JoinColumn(name = "id_compra"),
            inverseJoinColumns = @JoinColumn(name = "id_insumo")
    )
    private List<Insumo> insumos;

    private double valorInsumos;

    private double outrosGastos;

    public Compra(double outrosGastos){
        this.data = Date.valueOf(LocalDate.now());
        this.valorInsumos = 0.0;
        this.outrosGastos = outrosGastos;
        this.insumos = new ArrayList<>();

    }

    public void adicionarInsumo(Insumo insumo){
        this.insumos.add(insumo);
        this.valorInsumos += insumo.getValor();
    }
}
