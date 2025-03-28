package ru.onalex.request1902;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope("singleton")
public class RequestScopedBean {

    private final String requestId;

    public RequestScopedBean() {
        this.requestId = UUID.randomUUID().toString();
    }

    public String getRequestId() {
        return requestId;
    }
}
