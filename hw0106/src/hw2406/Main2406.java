package hw2406;

import java.util.Scanner;

public class Main2406 {
    public static void main(String[] args) {

        String[] users = {"Арбузов Артамон", "Клубникин Ксенофонт", "Земляникин Зиновий", "Виноградов Винедикт"};
        String[] emails = {"u1@domain.ru", "u2@domain.ru", "u3@domain.ru", "u4@domain.ru"}; //, "u5@domain.ru"

        Scanner scn = new Scanner(System.in);
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
        }
    }
}
