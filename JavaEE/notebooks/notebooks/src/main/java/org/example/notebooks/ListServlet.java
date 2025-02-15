package org.example.notebooks;

import lombok.var;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.notebooks.Utils.readResourceFile;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String type = request.getParameter("t");
        String jspName = "error", title = "";
        request.setAttribute("nav", readResourceFile("top-menu.html"));
        switch (type) {
            case "prod":
                NotebookList nb = new NotebookList();
                title = "Ноутбуки Novoxel";
                request.setAttribute("title", title);
                request.setAttribute("mainHeading", title);
                request.setAttribute("list", nb.getList());
                jspName = nb.getJspName();
                break;
            case "news":
                var nw = new NewsList();
                title = "Новости микроэлектроники";
                request.setAttribute("title", title);
                request.setAttribute("mainHeading", title);
                request.setAttribute("list", nw.getList());
                jspName = nw.getJspName();
                break;
                default:
        }
        getServletContext().getRequestDispatcher("/" + jspName + ".jsp").forward(request, response);
    }
}
