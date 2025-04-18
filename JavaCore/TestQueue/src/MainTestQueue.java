import java.util.*;

public class MainTestQueue {
    public static void main(String[] args) {

        //LIFO - last in first out
//        Deque<String> stack = new ArrayDeque<>();
//        stack.add("first");
//        stack.add("second");
//        stack.add("third");
//        stack.add("fourth");
//        Queue<String> stack2 = new LinkedList<>();
//        stack2.add("first");
//        stack2.add("second");
//        stack2.add("third");
//        stack2.add("fourth");
//
//        System.out.println(stack.poll());
//        System.out.println(stack.element());
//        System.out.println(stack.peek());
//        System.out.println("----------------------");
//        System.out.println(stack2.poll());
//        System.out.println(stack2.element());
//        System.out.println(stack2.peek());

//        HashSet<String> stack3 = new HashSet<>();
//        stack3.add("first");
//        stack3.add("second");
//        stack3.add("third");
//        stack3.add("fourth");
//
//        HashSet<String> stack4 = new HashSet<>();
//        stack4.add("third");
//        stack4.add("fourth");
//
//        stack3.retainAll(stack4);
        ArrayList<Integer> stack5 = new ArrayList<>();
//        Set<Integer> stack5 = new HashSet<>();
        stack5.add(100000);
        stack5.add(10000);
        stack5.add(1);
        stack5.add(10);
        stack5.add(100);
        stack5.add(1000);


        for (Integer s:stack5){
            System.out.println(s);
        }
    }
}
