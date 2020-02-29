package com.geekbrains.springwebapp.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Один Заказ может ссылаться на много Айтемов,
    //и здесь укажем каскадные оперции
    /* каскадные оперции - это когда мы сохраняем Order,
     * то он должен ссылаться на какие то ОрдерАйтемы
     * OrderItem, которые уже лежат в БД. А их у нас там
     * нет, мы отдельно их не сохраняем. И мы говорим, что
     * "Смотри Order, когда мы тебя сохраним, то ты по цепочке
     * должен сохранить в том числе и вот эти
     * ордерАйтемы OrderItem items"*/
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;


    /* Уберем внешний ключ на пользователя user
     * И здесь просто говорим, что у нас есть
     * ссылка на имя пользователя
     * без внешнего ключа*/
    //@ManyToOne
    //@JoinColumn(name = "user_id")
    @Column(name = "username")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Order() {
    }

}
