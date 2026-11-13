package lemonadeStand.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Lemonade {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private double lemonJuice;

    @Column(nullable = false)
    private double water;

    @Column(nullable = false)
    private double sugar;

    @Column(nullable = false)
    private int iceCubes;

    @Column(nullable = false)
    private double price;

    private boolean deleted;

    @ManyToOne
    @JoinColumn
    private Order order;

}