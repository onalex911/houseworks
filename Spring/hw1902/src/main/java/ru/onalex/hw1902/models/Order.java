package ru.onalex.hw1902.models;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.onalex.hw1902.controllers.HomeController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component()
@Scope("prototype")
@Data
public class Order {
    private String uuid;
    private Date date;
    private String productName;


//    @Autowired
public Order(){
    System.out.println("Order w/o args created");
}

    public Order(String name) {
        setOrder(name);
    }

//    public void setOrder(@Value("${default.name}")String name) {
    public Order setOrder(String name) {
        this.date = new Date();
        this.productName = name;
        this.uuid = UUID.randomUUID().toString();
        System.out.println("Order created: " + this.uuid);
        return this;
    }



    public String getOrderDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(this.uuid).append("\n");
        sb.append("Date: ").append(this.date).append("\n");
        sb.append("Product: ").append(this.productName).append("\n");
        return sb.toString();
    }
}
