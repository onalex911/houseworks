package ru.onalex.request1902.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
//@Scope("prototype")
public class ClientService {

    private final String requestId;

    public ClientService() {
        this.requestId = "Request-" + System.currentTimeMillis();
    }

    public String getRequestId() {
        return requestId;
    }
}

