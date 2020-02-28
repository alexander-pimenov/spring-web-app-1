package com.geekbrains.springwebapp.services;


import com.geekbrains.springwebapp.entities.Product;
import com.geekbrains.springwebapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    //сервису нужен доступ к репозиторию
    //поэтому инжектим сюда ссылку на репозиторий
    private ProductRepository productRepository;

    //ставим сеттер и аннотацию @Autowired
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //метод, который найдет продукт по id
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    //метод для получения списка продуктов
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //метод для получения продукта по названию
    public Product getProductByTitle(String title){
        return productRepository.findOneByTitle(title);
    }

    //метод для удаления продукта по его id
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


}
