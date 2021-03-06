package com.geekbrains.springwebapp.entities;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Создали класс OrderItem
 * Он является сущностью @Entity и хранится в таблице
 * order_items @Table(name="order_items")
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    //private static final long serialVersionUID = -1587797388552932937L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItem() {
    }
}
