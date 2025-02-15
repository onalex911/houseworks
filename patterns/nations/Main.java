package AbstractFactory;



public class Main {
    public static void main(String[] args) {
        System.out.println("Выберите народ из списка");
        System.out.println("1 - итальянцы");
        System.out.println("2 - турки");
        System.out.println("3 - японцы");

//        Scanner scanner = new Scanner(System.in);
//
//        int choice = scanner.nextInt();
        NationAbstractFactory nation;
        nation = new ItalianAbstractFactory();
        NationName nn = nation.createNation();
        System.out.println(nn.createNationName());
//        NationFactory nation;
//        switch (choice) {
//            case 1 -> nation = new ItalianFactory();
//            case 2 -> nation = new TurkishFactory();
//            case 3 -> nation = new JapaneeseFactory();
//        }
//        nation;

    }
}
