package hw2406;

import java.sql.Timestamp;
import java.util.Scanner;
import java.util.Timer;
import java.util.stream.IntStream;

public class Main2406 {
    public static void main(String[] args) {

        String[] users = {"Арбузов Артамон", "Клубникин Ксенофонт", "Земляникин Зиновий", "Виноградов Винедикт"};
        String[] emails = {"u1@domain.ru", "u2@domain.ru", "u3@domain.ru", "u4@domain.ru"}; //, "u5@domain.ru"

       /* Scanner scn = new Scanner(System.in);
        try {
            DataProcessor dp = new DataProcessor(emails, users); //инициализируем "базу данных"
            System.out.print("Введите ваш логин (в формате ящика электронной почты): ");
            String login = scn.nextLine();
            UserInputHandler check = new UserInputHandler();
            check.validateInput(login); //проверка на соответствие введенного имени формату эл.почты
            System.out.println("Ваше имя: " + dp.findData(login)); //ищем введенный логин в "базе данных"

        }catch (RuntimeException r){
            System.out.println(r.getMessage());

        }catch (InvalidUserInputException e) {
            System.out.println(e.getMessage() + ": " + e.getMsg());

        }catch (DataNotFoundException d){
            System.out.println(d.getMessage());
        }*/
        String num = "+7 (912) 123-58-57 ";
        long startTime = System.nanoTime();
        System.out.println(String.format("%015d", strToLong(num)));
        //strToLong(num);
        long interTime = System.nanoTime();
        strToLongOld(num);
        long endTime = System.nanoTime();
        System.out.println("1: "+(interTime-startTime));
        System.out.println("2: "+(endTime-interTime));
    }

    static long strToLong(String str){
        IntStream streamFromString = str.chars();
        String txtResult = streamFromString
                .filter(Character::isDigit)
//                .filter(s -> s >= 48 && s <= 57)
                .mapToObj(c -> String.valueOf((char) c))
                .reduce("",String::concat);//forEach(x-> (char)x);
        //System.out.println(txtResult);
        return Long.parseLong(txtResult);

    }
    static long strToLongOld(String str){
        char[] chars = str.toCharArray();
        String txtResult = "";
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] >= 48 && chars[i] <= 57){
                txtResult += chars[i];
            }
        }
        return Long.parseLong(txtResult);
    }
}
