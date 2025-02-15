import java.util.Scanner;
//необязательная обертка
interface ToyPart{
    String partName = "";
}

//абстрактные продукты
interface ToyFace extends ToyPart{
    String partName = "face";
    String getFace();
}
interface ToyBody{
    String partName = "body";
    String getToyBody();
}
interface ToyHands{
    String partName = "hands";
    String getToyHands();
}
interface ToyLegs{
    String partName = "legs";
    String getToyLegs();
}

//конкретные продукты
class BoyFace implements ToyFace{
    public String getFace() {
        return partName + " is serious";
    }
}
class BoyHands implements ToyHands{
    public String getToyHands() {
        return partName + " are strong";
    }
}
class BoyLegs implements ToyLegs{
    public String getToyLegs() {
        return partName + " are jumping";
    }
}
class BoyBody implements ToyBody{
    public String getToyBody() {
        return partName + " is flexible";
    }
}
class GirlFace implements ToyFace{
    public String getFace() {
        return partName + " is laughing";
    }
}
class GirlBody implements ToyBody{
    public String getToyBody() {
        return partName + " is delicate";
    }
}

class GirlHands implements ToyHands{
    public String getToyHands() {
        return partName + " are pretty";
    }
}
class GirlLegs implements ToyLegs{
    public String getToyLegs() {
        return partName + " are dancing";
    }
}class RobotFace implements ToyFace{
    public String getFace() {
        return partName + " is flat";
    }
}
class RobotBody implements ToyBody{
    public String getToyBody() {
        return partName + " is iron";
    }
}

class RobotHands implements ToyHands{
    public String getToyHands() {
        return partName + " are precision";
    }
}
class RobotLegs implements ToyLegs{
    public String getToyLegs() {
        return partName + " are stable";
    }
}

//абстрактная фабрика
interface ToyFactory{
    String name = "";
    String getName();
      ToyFace getToyFace();
      ToyBody getToyBody();
      ToyHands getToyHands();
      ToyLegs getToyLegs();
}

//конкретные фабрики продуктов
class BoyFactory implements ToyFactory{
    private String name = "Boy";
    public String getName() {return name;}

    public ToyFace getToyFace() {
        return new BoyFace();
    }
    public ToyBody getToyBody() {
        return new BoyBody();
    }
    public ToyHands getToyHands() {
        return new BoyHands();
    }
    public ToyLegs getToyLegs() {
        return new BoyLegs();
    }
}

class GirlFactory implements ToyFactory{
    private String name = "Girl";
    public String getName() {return name;}

    public ToyFace getToyFace() {
        return new GirlFace();
    }
    public ToyBody getToyBody() {
        return new GirlBody();
    }
    public ToyHands getToyHands() {
        return new GirlHands();
    }
    public ToyLegs getToyLegs() {
        return new GirlLegs();
    }
}
class RobotFactory implements ToyFactory{
    private String name = "Robot";
    public String getName() {return name;}

    public ToyFace getToyFace() {
        return new RobotFace();
    }
    public ToyBody getToyBody() {
        return new RobotBody();
    }
    public ToyHands getToyHands() {
        return new RobotHands();
    }
    public ToyLegs getToyLegs() {
        return new RobotLegs();
    }
}

public class AFTest {
    public static String preposition = " of the ";
    public static void main(String[] args) {
        ToyFactory factory = null;
        System.out.println("\n1 - boy");
        System.out.println("2 - girl");
        System.out.println("3 - robot");
        System.out.print("Select a toy: ");
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();
        switch (type) {
            case 1 -> factory = new BoyFactory();
            case 2 -> factory = new GirlFactory();
            case 3 -> factory = new RobotFactory();
            default -> System.out.println("Invalid choice");
        }

        String toyName = factory.getName();
        ToyFace hd = factory.getToyFace();
        ToyBody bd = factory.getToyBody();
        ToyHands hnd = factory.getToyHands();
        ToyLegs lg = factory.getToyLegs();
        System.out.println(toyName + "'s " + hd.getFace() + ", " + hnd.getToyHands() + ", " + bd.getToyBody() + ", " + lg.getToyLegs());
    }
}
