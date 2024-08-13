import java.util.*;
class Bubbles{
    int radius;
    String color;
    boolean alive;

    public Bubbles(int radius, String color, boolean alive) {
        this.radius = radius;
        this.color = color;
        this.alive = alive;
    }
}
public class Main {


//    enum Season {
//        WINTER(0),
//        SPRING(1),
//        SUMMER(2),
//        AUTUMN(3);
//        int power;
//
//        Season(int power) {
//            this.power = power;
//        }
//
//        @Override
//        public String toString() {
//            return "Season{" +
//                    "power=" + power +
//                    '}';
//        }
//        public String getName(int power){
//            return
//        }
//    }

    //public static void pr

    public static void main(String[] args) {
//        enum Colors{
//            RED, GREEN, BLUE
//        }
        Random rnd = new Random();
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = rnd.nextInt(50);
//            System.out.print(arr[i] + " ");
//        }
//
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] < arr[j])
//                    arr[j] = (arr[i] + arr[j]) - (arr[i] = arr[j]);
//            }
//        }
//        System.out.println("------------------------------");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }

//        int colorIndex = 2; // Номер цвета, который хотите получить
//
//        Colors[] colors = Colors.values();
//
//        if(colorIndex >= 0 && colorIndex < colors.length){
//            Colors color = colors[colorIndex];
//            System.out.println(color);
//        } else {
//            System.out.println("Цвет с номером " + colorIndex + " не существует");
//        }
        int maxVal = 100;
        //ArrayList<Integer> test1 = new ArrayList<Integer>();
        //LinkedList<Integer> test = new LinkedList<Integer>();
//        HashSet<Integer> test = new HashSet<>();
//        int val;
//        for (int i = 0; i < 10; i++) {
//            val = rnd.nextInt(maxVal);
//            test.add(val);
//        }
//        HashSet<Bubbles> test = new HashSet<>();
//        test.add(new Bubbles(1,"red",true));
//        test.add(new Bubbles(2,"green",true));
//        test.add(new Bubbles(1,"red",true));

//        HashSet<String> test = new HashSet<>();
//        test.add("red");
//        test.add("green");
//        test.add("blue");

        //Collections.sort(test);
//        test.sort();
        //System.out.println(test);
        //test.add(1,999);

        //создаем компаратор для обработки очереди в порядке убывания эл-тов
        Comparator<Integer> comparator = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                if( o1 > o2 ) //if first element is greater
                { //we return negative value
                    return -1; //to get descending sort order
                }
                if( o1 < o2 ) //if first element is less
                { //we return positive value
                    return 1; //to get descending sort order
                }
                return 0; //if the elements are equal
            }
        };

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(comparator);
        pq.add(4);
        pq.add(3);
        pq.add(10);
        pq.offer(9);
        pq.offer(1);

        System.out.println("Collection:"+pq);
        System.out.println("Peeked: "+pq.peek());
        System.out.println("------------------------------");

        Comparator<String> comparatorStr = new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                return o1.compareTo(o2);
                /*if( o1.compareTo(o2) > 0 ) //if first element is greater
                { //we return negative value
                    return -1; //to get descending sort order
                }
                if( o1.compareTo(o2) < 0 ) //if first element is less
                { //we return positive value
                    return 1; //to get descending sort order
                }
                return 0; //if the elements are equal*/
            }
        };

        PriorityQueue<String> pqStr = new PriorityQueue<String>(comparatorStr);
//        pqStr.add("А");
//        pqStr.offer("Azora");
//        pqStr.add("roza");
//        pqStr.add("upala");
//        pqStr.offer("na");
//        pqStr.offer("lapu");
//        pqStr.add("А");
        pqStr.add("А");
        pqStr.offer("B");
        pqStr.add("Z");
        pqStr.add("X");
        pqStr.offer("O");
        pqStr.offer("R");
        pqStr.add("C");

        Iterator<String> iter = pqStr.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next()+" ");
        }

        //System.out.println("Collection:"+pqStr);
        //System.out.println("Peeked: "+pqStr.peek());

//        System.out.println(test);
        System.out.println("------------------------------");
//        for (Integer t : test)
//            System.out.print(t + " ");

//        ListIterator<Integer> it_beg = test.listIterator();
//        System.out.println("Loop forward");
//        while (it_beg.hasNext()) {
//            System.out.print(it_beg.next() + " ");
//        }
//        System.out.println("\n------------------------------");
//
//        System.out.println("Loop back");
//        while (it_beg.hasPrevious())
//            System.out.print(it_beg.previous() + " ");
//        System.out.println("\n------------------------------");
//
//        ListIterator<Integer> it_ind = test.listIterator(3);
//        System.out.println("Loop from index, back");
//        while (it_ind.hasNext())
//            System.out.print(it_ind.next() + " ");

//        Collections.sort(test);
//        for(Integer i:test)
//            System.out.print(i+" ");
//        System.out.println("\n------------------------------");
//        System.out.println(test);

//        HashMap<String, Integer> hm =
//                new HashMap<String,Integer>();
//
//        hm.put("Argentina",1);
//        hm.put("Norway",12);
//        hm.put("Canada",10);
//        hm.put("USA",5);
//
//        LinkedHashMap<String, Integer> hm2 =
//                new LinkedHashMap<String,Integer>();
//
//        hm2.put("Argentina",1);
//        hm2.put("Norway",12);
//        hm2.put("Canada",10);
//        hm2.put("USA",5);
//
//        TreeMap<String, Integer> hm3 =
//                new TreeMap<String, Integer>();
//
//        hm3.put("Argentina",1);
//        hm3.put("Norway",12);
//        hm3.put("Canada",10);
//        hm3.put("USA",5);
//        hm3.put("Russia",666);
//
//        WeakHashMap<String, Integer> hm4 =
//                new WeakHashMap<String, Integer>();
//
//        hm4.put("Argentina",1);
//        hm4.put("Norway",12);
//        hm4.put("Canada",10);
//        hm4.put("USA",5);
//        hm4.put("Russia",666);
//        for(Map.Entry m: hm.entrySet()){
//            System.out.println(m.getKey()+" "+m.getValue());
//        }
        ;
//        System.out.println(hm);     //HashMap
//        System.out.println(hm2);    //LinkedHashMap
//        System.out.println(hm3);    //TreeMap
//        System.out.println(hm4);    //WeakHashMap
    }
}
