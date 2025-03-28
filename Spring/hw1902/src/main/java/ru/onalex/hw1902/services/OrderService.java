package ru.onalex.hw1902.services;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.onalex.hw1902.models.Order;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class OrderService {

    @Autowired
    private ObjectFactory<Order> orders;
    List<Order> ordersList = new ArrayList<>();
    private static int count = 0;

    public OrderService() {
        System.out.println("OrderService : " + ++count);
    }

    public String createOrder(String product) {
        if(product.isEmpty()){
            return "Order is NOT created!";
        }else {
//            orders.getObject().setOrder(product); //непонятно, как создавать заказ с параметрами и запоминать его в списке
            Order order = orders.getObject().setOrder(product);
//            так, оказывается, работает. НО! Если обращаться с другого хоста, то заказы будут складываться в одну "корзину"
            // получается, нужно список объектов тоже привязать к какой-то области видимости (session?)
            ordersList.add(order);
            return "Order of " + product + " is created successfully!";
        }
    }

    public String getAllOrders(){
        String output = "";
        for(Order order : ordersList){
            output += order.getOrderDetails();            ;
            output += "----------------------\n";
        }
        return output;
    }
}
