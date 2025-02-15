interface Toy {
    String getDescription();
    int getPrice(); //цена в центах
}

class Robot implements Toy{
    private String description;
    private int price = 1200;

    public Robot() {
        this.description  = "робот";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
class Warrior implements Toy{
    private final String description;
    private final int price = 1190;

    public Warrior() {
        this.description  = "воин";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

abstract class ToyDecorator implements Toy {
    String description = "Неизвестный атрибут";
    private int price;

    public String getDescription(){
        return description;
    }
    Toy wrappee;
    ToyDecorator(Toy wrappee) {
        this.wrappee = wrappee;
    }
    @Override
    public int getPrice() {
        return price;
    }
}

class Defence extends ToyDecorator{
    int myPrice = 155;
    String description = "защита";
    public Defence(Toy wrappee) {
        super(wrappee);
    }
    public int getPrice() {
        return wrappee.getPrice() + myPrice;
    }
    public String getDescription(){
        return wrappee.getDescription() + ", " + description;
    }
}

class Laser extends ToyDecorator{
    int myPrice = 323;
    String description = "лазер";
    Laser(Toy wrappee) {
        super(wrappee);
    }

    public int getPrice() {
        return wrappee.getPrice() + myPrice;
    }
    public String getDescription(){
        return wrappee.getDescription() + ", " + description;
    }
}

class Bike extends ToyDecorator{
    int myPrice = 1821;
    String description = "байк";
    public Bike(Toy wrappee) {
        super(wrappee);
    }
    public int getPrice() {
        return wrappee.getPrice() + myPrice;
    }
    public String getDescription(){
        return wrappee.getDescription() + ", " + description;
    }
}

class Bazooka extends ToyDecorator{
    int myPrice = 936;
    String description = "базука";
    public Bazooka(Toy wrappee) {
        super(wrappee);
    }
    public int getPrice() {
        return wrappee.getPrice() + myPrice;
    }
    public String getDescription(){
        return wrappee.getDescription() + ", " + description;
    }
}

class NightView extends ToyDecorator{
    int myPrice = 599;
    String description = "прибор ночного видения";
    public NightView(Toy wrappee) {
        super(wrappee);
    }
    public int getPrice() { return wrappee.getPrice() + myPrice; }
    public String getDescription(){ return wrappee.getDescription() + ", " + description; }
}

class ToysDecorator{
public static void main(String[] args) {
    Toy toy1, toy2;

    toy1 = new Robot();
    toy1 = new Defence(toy1);
    toy1 = new Bike(toy1);
    toy1 = new Laser(toy1);

    toy2 = new Warrior();
//    toy2 = new Bike(toy2);
    toy2 = new Bazooka(toy2);
    toy2 = new Defence(toy2);
    toy2 = new NightView(toy2);


    System.out.println(toy1.getDescription() + ": " + String.format("%.2f", (float)toy1.getPrice()/100) + "$");
    System.out.println(toy2.getDescription() + ": " + String.format("%.2f", (float)toy2.getPrice()/100) + "$");
}
}