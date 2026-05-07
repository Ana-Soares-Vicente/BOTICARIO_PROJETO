package pedidos.boticario.entities;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Pedido")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean pago;
    @ManyToOne
    private Cliente cliente;

    @ManyToMany(mappedBy = "pedido")
    @JsonIgnoreProperties("pedido")
    private List<ItemPedido> itens;

    public Double calcularLucroTotal() {

        return itens.stream()
                .mapToDouble(ItemPedido::calcularLucro)
                .sum();
    }
}
