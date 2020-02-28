package com.geekbrains.springwebapp.entities;

import javax.persistence.*;

/*
 * Класс для описания товара в магазине.
 * В будущем это будет храниться в БД.
 */
@Entity
@Table(name = "products")
public class Product {

    /*
     * @Id - указываем, что это идентификатор.
     * @GeneratedValue(strategy=GenerationType.IDENTITY) -
     * Здесь мы запрашиваем генерацию id у БД.
     * @Column(name="id") - столбец (поле) id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // идентификационный номер товара

    /*title лежит в столбце "title"*/
    @Column(name = "title")
    private String title; // название товара

    /*price лежит в столбце "price"*/
    @Column(name = "price")
    private int price; // стоимость товара

    //Наличие дефолтного конструктора обязательно!!!
    public Product() {
    }

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //Для удобного вывода информации в консоль, если понадобится.
    @Override
    public String toString() {
        return String.format("Product: [id = %d, title = %s, price = %d]",
                id, title, price);
    }
}
