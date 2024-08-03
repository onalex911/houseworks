package hw3107;


import java.io.File;
import java.io.FileReader;

class MyThread1 extends Thread{
    private char[] text;
    private long count;

    public MyThread1(char[] text) {
        this.text = text;
        count = 0;
    }
    @Override
    public void run() {
//        super.run();
        System.out.println("Start t1");
        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < Main3107.glas.length; j++) {
                if(text[i] == Main3107.glas[j]) {
                    count++;
                    break;
                }
            }
        }

        System.out.println("End t1");
    }

    public long getCount() {
        return count;
    }
}class MyThread2 extends Thread{
    private char[] text;
    private long count;

    public MyThread2(char[] text) {
        this.text = text;
        count = 0;
    }
    @Override
    public void run() {
//        super.run();
        System.out.println("Start t2");
        for (int i = 0; i < text.length; i++) {
            if(Main3107.isLetter(text[i])){
                boolean flag = true;
                for (int j = 0; j < Main3107.glas.length; j++) {
                    if (text[i] == Main3107.glas[j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    count++;
            }
        }
        System.out.println("End t2");
    }

    public long getCount() {
        return count;
    }
}

//Создать программу которая будет считать из файла который содержит N-количество слов ( сказку)
//1) Количество глассных символов
//2) Количество согласных символов
//3) Количество чисел символов
//4) Количество символов
//5) Количество указанного символа
//6) Количество указанного слово
//
//Выполнить данное задание используя потоки
public class Main3107 {
    public static char[] glas = {'a','e','i','o','u'};

    public static boolean isNumber(char ch){
        return ch >= 48 && (int) ch <= 57;
    }public static boolean isLetter(char ch){
        return ch >= 65 && (int) ch <= 90 || (int) ch >= 97 && (int) ch <= 122;
    }
    public static void main(String[] args) {

        long startTime =  System.nanoTime();
        File file = new File("Main3107/lorem1.txt");
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                //System.out.println(file.length());
                char[] buffer = new char[(int)file.length()];
                int countChars = fr.read(buffer);
                if(countChars > 0) {
                    Thread MyThread3 = new Thread((buffer)->{
            System.out.println("Start MyThread3");

            System.out.println("End MyThread3");
        });
                    MyThread1 t1 = new MyThread1(buffer);
                    t1.start();

                    MyThread2 t2 = new MyThread2(buffer);
                    t2.start();

                    t1.join();
                    t2.join();

                    //System.out.println("File length = "+file.length()+", countChars = "+countChars);
                    System.out.println("glas = " + t1.getCount() + ", soglas = " + t2.getCount());
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Total time: "+(endTime-startTime)/1_000_000);
    }
}
