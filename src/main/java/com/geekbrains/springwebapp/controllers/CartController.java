package com.geekbrains.springwebapp.controllers;

import com.geekbrains.springwebapp.entities.Order;
import com.geekbrains.springwebapp.services.OrderService;
import com.geekbrains.springwebapp.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("/cart")
public class CartController {

    //инжектим сюда Корзину
    private ShoppingCart cart;

    //индектим сюда Сервис
    private OrderService orderService;

    @Autowired
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    //метод добавляющий продукт в Корзину.
    @GetMapping("/add/{id}")
    public String addProductToCart(Model model, @PathVariable("id") Long id) {
        cart.addProductById(id);
        return "redirect:/shop";
    }

    //метод показывающий Корзину, т.е. показывает товар в Корзине
    //вернее items
    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("items", cart.getItems());
        return "cart";
    }

    //метод сохраняющий заказ в БД
    //через Principal узнаем username Пользователя
    @GetMapping("/create_order")
    public String createOrder(Principal principal) {
        Order order = new Order();
        order.setItems(new ArrayList<>());
        //скажем Заказу кто его Пользователь
        order.setUsername(principal.getName());
        cart.getItems().stream().forEach(i -> {
            //в ордер закидываем айтемы
            order.getItems().add(i);
            //показываем к какому заказу привязан этот айтем
            i.setOrder(order);
        });
        cart.getItems().clear();
        orderService.saveOrder(order);
        return "redirect:/shop";
    }
}
