package ru.onalex.hw1902.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.onalex.hw1902.services.OrderService;
import ru.onalex.hw1902.services.RequestInfo;

@RestController
@RequestMapping("/")
public class HomeController {
//    @Autowired
    private OrderService orderService;
//    @Autowired
//    private RequestService requestService;
    private RequestInfo requestInfo;
    private static int count = 0;

//    public HomeController(OrderService orderService) {
//        this.orderService = orderService;
//    }
    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }
    @Autowired
    public void setRequestInfo(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }
// public HomeController(RequestInfo requestInfo) {
//        this.requestInfo = requestInfo;
//    }


    @GetMapping("/createOrder")
    public String createOrder(@RequestParam String product) {
        return orderService.createOrder(product);
    }

    @GetMapping("/orders")
    public String getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/requestInfo")
    public String getRequestInfo(HttpServletRequest request){
        return requestInfo.getClientInfo(request);
    }


}
