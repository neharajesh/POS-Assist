/*
package com.example.posassist.entities;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customerName;

    @ManyToOne
    private User staffName;

    @OneToMany
    @JoinTable(name = "order",
            joinColumns = @JoinColumn(name = "order_id"))
    private Order order;

    @OneToMany
    private List<OrderItems> itemsList = new ArrayList<OrderItems>();

    private Boolean checkOut;
}

*/
