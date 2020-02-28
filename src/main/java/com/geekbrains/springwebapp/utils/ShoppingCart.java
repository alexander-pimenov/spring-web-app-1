package com.geekbrains.springwebapp.utils;

import com.geekbrains.springwebapp.entities.OrderItem;
import com.geekbrains.springwebapp.entities.Product;
import com.geekbrains.springwebapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    //Ссылка на Сервис
    private ProductService productService;

    //добавляем сеттер и аннотацией @Autowired делаем инджекшн
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //Теперь как бы накидываем в Корзину продукты.
    //Корзина не должна в себе хранить продукты, а она должна хранить
    //в себе OrderItem
    private List<OrderItem> items;

    public List<OrderItem> getItems() {
        return items;
    }

    //Когда этот бин создастся, то было бы хорошо, чтоб этот
    //лист products сформировался.
    //Ставим аннотацию @PostConstruct, т.е. означает что этот метод
    //будет выполнен после инициализации бина.
    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    //Нужна возможность добавлять в Корзину товары по
    //id (например).
    public void addProductById(Long id) {
        //получаем наш продукт
        Product product = productService.getProductById(id);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        //И когда говорят что мы хотим какой то продукт добавить,
        //то мы его не добавляем. Т.е. мы его нашли и преобразуем
        //к OrderItem.
        items.add(orderItem);
    }
}
