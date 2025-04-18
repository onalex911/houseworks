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

@WebServlet(name = "GetMaximumServlet", value = "/get-max-servlet")
public class GetMaximumServlet extends HttpServlet {
//    @Serial
//    private static final long serialVersionUID = 1L;

    String message;

    public void init() {
        message = "";
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html");
//        message = "It's GET!";
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
//    }

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
            switch (operation){
                case "max" : result.setResVal(getMax(userInputNumbers.getA(),userInputNumbers.getB(),userInputNumbers.getC()));
                break;
                case "min" : result.setResVal(getMin(userInputNumbers.getA(),userInputNumbers.getB(),userInputNumbers.getC()));
                break;
                case "avg" : result.setResVal(getAvg(userInputNumbers.getA(),userInputNumbers.getB(),userInputNumbers.getC()));
                break;
                default: throw new Exception("Invalid operation: " + operation);
            }

        // Установка ответа в формате JSON
        response.setContentType("application/json");

        }catch (NumberFormatException e){
            result.setError("Wrong number format");
        }catch (Exception e){
            result.setError(e.getMessage());
        }finally {

            PrintWriter out = response.getWriter();
            String dataForOut = "{\"error\":\"empty string\"}";
            try {
                dataForOut = gson.toJson(result);
            } catch (Exception e) {
                result.setError(e.getMessage());
            }finally {
                out.print(dataForOut);
                out.flush();
            }
        }
    }



    public void destroy() {
    }

    public static double getMax(double a,double b,double c){
        return Math.max(Math.max(a,b),c);
    }
    public static double getMin(double a,double b,double c){
        return Math.min(Math.min(a,b),c);
    }
    public static String getAvg(double a,double b,double c){
        return new DecimalFormat("#0.00").format((a + b + c)/3);
    }


    static class Result {
        private String resVal;
        private String error;

        public void setResVal(double resVal) {
            this.resVal = String.valueOf(resVal);
        }
        public void setResVal(String resVal) {
            this.resVal = resVal;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
