package org.example.notebooks;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static org.example.notebooks.Utils.readResourceFile;
import static org.example.notebooks.Utils.rootPath;

@WebServlet("/production")
public class NotebooksServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String csvFile = rootPath + "notebooks.csv";

        List<Notebook> nbList = new ArrayList<>();
        File file = new File(csvFile);
        try(InputStreamReader fr = new InputStreamReader(new FileInputStream(file), "UTF-8")){
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(";");
                int id = Integer.parseInt(lineArray[0]);
                String name = lineArray[1];
//                String description = lineArray[2];
                String photoPath = "images/production/" + lineArray[3];
                int price = Integer.parseInt(lineArray[4]);
                nbList.add(new Notebook(id,name,"",photoPath,price));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String title = "Ноутбуки Novoxel";
        request.setAttribute("title", title);
            request.setAttribute("nav", readResourceFile("top-menu.html"));
            request.setAttribute("mainHeading", title);
                request.setAttribute("list", nbList);
                getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
    }
}