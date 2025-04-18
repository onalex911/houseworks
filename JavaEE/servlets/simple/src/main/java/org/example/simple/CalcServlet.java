package org.example.simple;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

@WebServlet(name = "CalcServlet", value = "/calc-servlet")
public class CalcServlet extends HttpServlet {
    public static int MAX_FLOAT_DIGITS = 10;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Безопасное чтение JSON из тела запроса
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        Result result = new Result();
        Gson gson = new Gson();
        try {
            UserInputNumbers userInputNumbers = gson.fromJson(sb.toString(), UserInputNumbers.class);
            String operation = userInputNumbers.getOper();
            switch (operation) {
                case "add":
                    result.setResVal(doAdd(userInputNumbers.getA(), userInputNumbers.getB()));
                    break;
                case "sub":
                    result.setResVal(doSub(userInputNumbers.getA(), userInputNumbers.getB()));
                    break;
                case "mult":
                    result.setResVal(doMult(userInputNumbers.getA(), userInputNumbers.getB()));
                    break;
                case "divd":
                    result.setResVal(doDivide(userInputNumbers.getA(), userInputNumbers.getB()));
                    break;
                case "perc":
                    result.setResVal(doPerc(userInputNumbers.getA(), userInputNumbers.getB()));
                    break;
                case "pow":
                    result.setResVal(doPow(userInputNumbers.getA(), userInputNumbers.getB()));
                    break;
                default:
                    throw new Exception("Invalid operation: " + operation);
            }

            // Установка ответа в формате JSON
            response.setContentType("application/json");

        } catch (NumberFormatException e) {
            result.setError("Wrong number format");
        } catch (Exception e) {
            result.setError(e.getMessage());
        } finally {

            PrintWriter out = response.getWriter();
            String dataForOut = "{\"error\":\"empty string\"}";
            try {
                dataForOut = gson.toJson(result);
            } catch (Exception e) {
                result.setError(e.getMessage());
            } finally {
                out.print(dataForOut);
                out.flush();
            }
        }
    }

    static class Result {
        private String resVal;
        private String error;

        public void setResVal(double resVal) {
            String result = String.valueOf(resVal);
            String[] resultParts = result.split(".");
            if(resultParts.length == 2 && resultParts[1].length() > MAX_FLOAT_DIGITS) {
                result = resultParts[0] + "." + resultParts[1].substring(0, MAX_FLOAT_DIGITS);
                this.resVal = new DecimalFormat("#0.000").format(result);
            }else{
                this.resVal = result;
            }
        }

        public void setError(String error) {
            this.error = error;
        }
    }

    public static double doAdd(double a, double b) {
        return a + b;
    }
    public static double doSub(double a, double b) {
        return a - b;
    }
    public static double doMult(double a, double b) {
        return a * b;
    }
    public static double doDivide(double a, double b) throws Exception {
        if(b == 0) throw new Exception("You can't divide by zero!");
        return a / b;

    }
    public static double doPerc(double a, double b) {
        return a * b / 100;
    }
    public static double doPow(double a, double b) {
        return Math.pow(a,b);
    }
}
