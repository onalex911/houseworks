package FactoryMethod;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<NationFactory> nations = new ArrayList<>();
        nations.add(new IndianFactory()); //добавление фабрик для создания продуктов (каждая фабрика возвращает ссылку на определенный продукт)
        nations.add(new EskimoFactory());
        nations.add(new SlavicFactory());

        for(NationFactory n : nations) {
            Nation nation = n.createNation(); //фабрика возвращает ссылку на свой продукт, всё равно, какой
            System.out.println("Наименование: " + nation.getNationName()); //получаем свойства конкретного продукта
            System.out.println("Название жилища: " + nation.getHouseName());
            System.out.println("Распространенная еда: " + nation.getFoodName());
            System.out.println("----------------------------------------------");
        }
    }
}
