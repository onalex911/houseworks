package GetQuotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Quotations{
    private static final String QUOTATIONS_PATH = "data/quotations.txt";
    private final ArrayList<String> qArray;

    {
        qArray = new ArrayList<>();
    }
    public Quotations() throws IOException {
        try {
            File file = new File(QUOTATIONS_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String str;

                while((str = reader.readLine()) != null ){
                    if(!str.isEmpty()){
                        qArray.add(str);
                    }
                }

            } else {
                    throw new IOException("Не найден файл цитат.");
            }
        } catch (IOException ioex) {
            System.out.println("Ошибка ввода-вывода: " + ioex.getMessage());
        }

    }

    public String getQuotation(int n){
        return qArray.get(n - 1);
    }

    public String getRandomQuotation(){
        Random rnd = new Random();

        return qArray.get(rnd.nextInt(qArray.size()) - 1);
    }

    public int getQArraySize(){
        return qArray.size();
    }
    
}
