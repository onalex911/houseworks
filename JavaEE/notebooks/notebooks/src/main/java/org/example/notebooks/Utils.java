package org.example.notebooks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

public class Utils {

    abstract static class ListItems<T>{
        List<T> list;
        String[] fieldsNames;
        String jspName;
    }

    public static String rootPath = "C:\\_TOP\\houseworks\\JavaEE\\notebooks\\notebooks\\src\\main\\resources\\";
    public static String readResourceFile(String fileName) throws IOException {

        File file = new File(rootPath + fileName);
        InputStreamReader fr = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }



    public static Date parseDate(String dateText){
        String[] datePerts = dateText.split("\\.");
        int year = Integer.parseInt(datePerts[2]) - 1900;
        int month = Integer.parseInt(datePerts[1]) - 1;
        int day = Integer.parseInt(datePerts[0]);
        return new Date(year,month,day);
    }
}
