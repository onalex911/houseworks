package ru.onalex.request1902;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private RequestScopedBean requestScopedBean;

    @GetMapping("/greet")
    public String greet() {
        return "Request ID: " + requestScopedBean.getRequestId();
    }
}
