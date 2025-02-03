package FactoryMethod;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<NationFactory> nations = new ArrayList<>();
        nations.add(new IndianFactory());
        nations.add(new EskimoFactory());
        nations.add(new SlavicFactory());

        for(NationFactory n : nations) {
            Nation nation = n.createNation();
            System.out.println("Наименование: " + nation.getNationName());
            System.out.println("Название жилища: " + nation.getHouseName());
            System.out.println("Распространенная еда: " + nation.getFoodName());
            System.out.println("----------------------------------------------");
        }
    }
}
