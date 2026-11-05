package lemonadeStand.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "order_table")
@Entity
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private double total;

    @OneToMany(mappedBy = "order")
    private List<Lemonade> lemonades = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private LemonadeStand lemonadeStand;

}
