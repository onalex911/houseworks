package org.example.notebooks;
import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String rootPath = "C:\\_TOP\\houseworks\\JavaEE\\notebooks\\notebooks\\src\\main\\resources\\";
    public static String readResourceFile(String fileName) throws IOException {
//        File file = new File(Paths.get("").toAbsolutePath().toString() + "\\" + fileName);

        File file = new File(rootPath + fileName);
        InputStreamReader fr = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    public static void printDir(String dir){
        File file = new File(dir);
        File[] files = file.listFiles();
        assert files != null;
        System.out.println("files:" + files.length);
        for(File f : files){
            if(f.isDirectory()){
                printDir(f.getAbsolutePath());
            }
            else{
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {

        // Получаем текущую рабочую директорию с использованием Paths
//        String currentDirectory = Paths.get("").toAbsolutePath().toString();
//        System.out.println("Текущая директория: " + currentDirectory);
//        printDir(currentDirectory + "\\src\\main\\resources");
        String csvFile = rootPath + "notebooks.csv"; // Укажите путь к вашему CSV-файлу
        System.out.println(csvFile);
        List<Notebook> nbList = new ArrayList<>();
        File file = new File(csvFile);
        try(InputStreamReader fr = new InputStreamReader(new FileInputStream(file), "UTF-8")){
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(";");
                int id = Integer.parseInt(lineArray[0]);
                String name = lineArray[1];
                String description = lineArray[2];
                String photoPath = lineArray[3];
                int price = Integer.parseInt(lineArray[4]);
                nbList.add(new Notebook(id,name,description,photoPath,price));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //;
//        try (CSVReader reader = new CSVReader()) {
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                int id = Integer.parseInt(line[1]);
//                String name = line[2];
//                String description = line[3];
//                String photoPath = line[4];
//                int price = Integer.parseInt(line[5]);
//                nbList.add(new Notebook(id,name,description,photoPath,price));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // Выводим список людей, считанных из CSV
        for (Notebook person : nbList) {
            System.out.println(person);
        }

        // Выводим текущую директорию

    }
}
