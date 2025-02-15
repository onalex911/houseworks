package org.example.notebooks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.notebooks.Utils.readResourceFile;
import static org.example.notebooks.Utils.rootPath;

@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String type = request.getParameter("t");
        int id = Integer.parseInt(request.getParameter("id"));
        String csvFile = rootPath + (type.equals("prod") ? "notebooks.csv" : "news.csv");
        String title = "";

        List<Notebook> nbList = new ArrayList<>();
        File file = new File(csvFile);
        try(InputStreamReader fr = new InputStreamReader(new FileInputStream(file), "UTF-8")){
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            request.setAttribute("nav", readResourceFile("top-menu.html"));
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(";");
                if(Integer.parseInt(lineArray[0]) == id) {
                    if(type.equals("prod")) {
                        title = lineArray[1];
                        request.setAttribute("title", title);
                        request.setAttribute("mainHeading", title);
                        request.setAttribute("description", lineArray[2]);
                        request.setAttribute("photoPath", "images/production/" + lineArray[3]);
                        request.setAttribute("price", Integer.parseInt(lineArray[4]));
                        getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                    }else{
                        title = lineArray[3];
                        request.setAttribute("title", title);
                        request.setAttribute("mainHeading", title);
                        request.setAttribute("date", lineArray[1]);
                        request.setAttribute("photoPath", "images/news/" + lineArray[2]);
                        request.setAttribute("text", lineArray[4]);
                        getServletContext().getRequestDispatcher("/news-item.jsp").forward(request, response);
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
