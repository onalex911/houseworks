package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/maxNumber")
public class MaxNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение чисел от пользователя
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int num3 = Integer.parseInt(request.getParameter("num3"));

        // Нахождение максимального числа
        int max = Math.max(Math.max(num1, num2), num3);

        // Формирование JSON ответа
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("maxNumber", max);

        // Настройка и отправка ответа
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
