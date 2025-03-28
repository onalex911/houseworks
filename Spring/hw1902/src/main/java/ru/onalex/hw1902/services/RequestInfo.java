package ru.onalex.hw1902.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class RequestInfo {
    private String ipAddress;
    private Date datetime;

    private static int count = 0;

    public RequestInfo() {
        System.out.println("RequestInfo made times: "+count++);
    }


    public String getClientInfo(HttpServletRequest request) {
        setIpAddress(request.getRemoteAddr());
        setDatetime(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append("IP Address: ").append(ipAddress).append("\n");
        sb.append("Datetime: ").append(datetime).append("\n");
        return sb.toString();
    }
}
