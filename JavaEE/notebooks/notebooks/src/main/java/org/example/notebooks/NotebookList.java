package org.example.notebooks;

import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.example.notebooks.Utils.rootPath;

@Data
public class NotebookList extends Utils.ListItems<Notebook> {
    private List<Notebook> list;
    private String jspName;
    private String[] fieldsNames;
    {
        fieldsNames = new String[]{"id", "Наименование","Описание","Цена, Tgr"};
        jspName = "notebooks";
    }

    public NotebookList() {
        String csvFile = rootPath + "notebooks.csv";

        list = new ArrayList<>();
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
                list.add(new Notebook(id,name,"",photoPath,price));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}