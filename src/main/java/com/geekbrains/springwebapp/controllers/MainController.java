package com.geekbrains.springwebapp.controllers;

import com.geekbrains.springwebapp.entities.Product;
import com.geekbrains.springwebapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8189/app - это корень нашего приложения
    // http://localhost:8189/app/index - это значит покажи начальную страницу

    @GetMapping("/index")
    public String homePage() {
        return "index";
    }

    //Проверка пользователя по Форме Логин
    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/shop")
    public String shopPage(Model model) {
        //Отобразим все товары на странице магазина
        //Стучимся в Сервис и получаем из него список всех продуктов.
        List<Product> allProducts = productService.getAllProducts();

        //прокидываем их на страничку shop под именем "products"
        model.addAttribute("products", allProducts);

        return "shop";
    }

    //используем метод Сервиса = getProductById
    @GetMapping("/details/{id}")
    public String detailsPage(Model model, @PathVariable("id") Long id) {
        Product selectedProduct = productService.getProductById(id);
        model.addAttribute("selectedProduct", selectedProduct);
        return "details";
    }

    //используем метод Сервиса = getProductByTitle
    @GetMapping("/find_by_title")
    public String detailsPageByTitle(Model model, @RequestParam("title") String title) {
        //находим продукт по названию
        Product selectedProduct = productService.getProductByTitle(title);
        //пуляем этот объект на страничку с деталями
        model.addAttribute("selectedProduct", selectedProduct);
        return "details";
    }

    //удаление продукта
    @GetMapping("/products/delete/{id}")
    //нужно выцепить из url-ки наш id поэтому пишем: @PathVariable("id") Long id
    public String deleteProductById(@PathVariable("id") Long id) {
        //удаляем элемент (товар)
        productService.deleteProductById(id);

        /* и возвращаем пользователя на главную странцу shop.
           Запись "redirect:/shop" говорит, что как будет
           выполнено действие в методе, будет удален продукт,
           то выполнится метод shopPage, т.е. покажем страницу shop.*/
        return "redirect:/shop";
    }

    /*  Метод может отвечать не только страницей, но и чистым текстом.
        Указываем аннотацию @ResponseBode и на запрос /"data" будет являться
        обычная строка.*/
    @GetMapping("/data")
    @ResponseBody
    //Если мы укажем required=false, то это будет значит,
    //что параметр не является обязательным, т.е. он может быть, а может и не быть.
    public String dataExample(@RequestParam(value = "serial", required = false) Long serial, @RequestParam("number") Long number) {
        return "S/N: " + serial + " / " + number;
    }
}
