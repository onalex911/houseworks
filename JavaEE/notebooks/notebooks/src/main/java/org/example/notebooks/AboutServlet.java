package org.example.notebooks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.example.notebooks.Utils.readResourceFile;

@WebServlet("/about")
public class AboutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        String[] users = new String[]{"Tom", "Bob", "Sam"};
        String title = "Welcome to Novoxel official website!";
        String mainHeading = "Солидные ноутбуки в солидные руки!";
//        String content = "<div class=\"center\">" + readResourceFile("about.html") + "</div>";
        String content = readResourceFile("about.html");
        request.setAttribute("title", title);
        request.setAttribute("nav", readResourceFile("top-menu.html"));
        request.setAttribute("mainHeading", mainHeading);
        request.setAttribute("content", content);
        getServletContext().getRequestDispatcher("/article.jsp").forward(request, response);
    }
}
