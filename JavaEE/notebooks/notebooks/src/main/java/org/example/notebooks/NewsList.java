package org.example.notebooks;

import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.example.notebooks.Utils.parseDate;
import static org.example.notebooks.Utils.rootPath;

@Data
public class NewsList extends Utils.ListItems<News> {
    private List<News> list;
    private String[] fieldsNames;
    private String jspName;
    {
        fieldsNames = new String[]{"id", "Дата","Изображение","Заголовок", "Текст"};
        jspName = "news";
    }

    public NewsList() {
        String csvFile = rootPath + "news.csv";

        list = new ArrayList<>();
        File file = new File(csvFile);
        try(InputStreamReader fr = new InputStreamReader(new FileInputStream(file), "UTF-8")){
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(";");
                int id = Integer.parseInt(lineArray[0]);
                String newsDate = lineArray[1];
                String photoPath = "images/news/" + lineArray[2];
                String newsTitle = lineArray[3];
//                    String text = lineArray[4];
                list.add(new News(id, parseDate(newsDate),photoPath,newsTitle,""));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
