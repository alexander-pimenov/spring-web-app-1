package com.geekbrains.springwebapp.controllers;

import com.geekbrains.springwebapp.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    //инжектим сюда Корзину
    private ShoppingCart cart;

    @Autowired
    public void setCart(ShoppingCart cart) {
        this.cart = cart;
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
}
