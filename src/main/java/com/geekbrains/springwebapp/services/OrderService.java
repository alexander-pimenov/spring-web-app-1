package com.geekbrains.springwebapp.services;

import com.geekbrains.springwebapp.entities.Order;
import com.geekbrains.springwebapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //метод ссохраняющий Заказ в БД
    public Order saveOrder(Order order){
        //когда мы Заказ сохраняем, то он нам возвращается
        return orderRepository.save(order);
    }
}
