package hw2606;

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }
}


public class Main2606 {

    public static void main(String[] args) {
        String status = "";
        int specialVal = 999;
        SinglLinkedList singlLinkedList = new SinglLinkedList();
        singlLinkedList.add(0);
        singlLinkedList.add(10);
        singlLinkedList.add(20);
        singlLinkedList.add(30);
        singlLinkedList.add(specialVal);
        singlLinkedList.add(40);
        singlLinkedList.add(50);
        singlLinkedList.add(specialVal);
        //singlLinkedList.addFirst(100);
        singlLinkedList.AddByIndex(4, 99);

        singlLinkedList.add(60);
        singlLinkedList.add(specialVal);
        singlLinkedList.add(specialVal);
        singlLinkedList.print();
        singlLinkedList.RemoveFirst();
        System.out.println("удален первый");
        singlLinkedList.print();
        status = singlLinkedList.RemoveLast() ? "удален последний" : "не удален";
        System.out.println(status);
        singlLinkedList.print();
        singlLinkedList.add(specialVal);

        int ind = 1;
        status = singlLinkedList.RemoveByIndex(ind) ? "удален по индексу " + ind : "не удален";
        System.out.println(status);
        singlLinkedList.print();
        singlLinkedList.addFirst(specialVal);
        int val = 99;
        status = singlLinkedList.RemoveByValue(val) ? "удален по значению " + val : "не удален";
        System.out.println(status);
        singlLinkedList.print();
        status = singlLinkedList.RemoveByValueAll(specialVal) ? "удалены все по значению " + specialVal : "не удалено";
        System.out.println(status);
        singlLinkedList.print();
        val = 50;
        System.out.println("Список содержит значение " + val + "? " + singlLinkedList.Contains(val));
        ind = 2;
        try {
            System.out.println("Значение по индексу " + ind + " = " + singlLinkedList.get(ind));
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        singlLinkedList.add(70);
        singlLinkedList.add(80);
        singlLinkedList.add(90);
        singlLinkedList.add(100);
        singlLinkedList.AddByIndex(1,20);
        System.out.println("Список до реверса:");
        singlLinkedList.print();
        singlLinkedList.reverse();
        System.out.println("Список после реверса:");
        singlLinkedList.print();
    }
}

class SinglLinkedList {
    private Node head;

    //        + 1) void addFirst
//        + 2) void add
    public void add(int num) {
        if (head == null) {
            head = new Node(num);
        } else {
            Node start = head;
            while (start.next != null) {
                start = start.next;
            }
            start.next = new Node(num);
        }
    }

    public void addFirst(int num) {
        Node node = new Node(num);
        node.next = head;
        head = node;
    }

    //        3) void AddByIndex
    public void AddByIndex(int index, int num) {
        Node node = new Node(num);
        node.next = head;
        if (index == 0)
            head = node;
        else if (index > 0) {
            int count = 0;
            Node start = head;
            while (start.next != null) {
                if (++count == index) {
                    node.next = start.next;
                    start.next = node;
                }
                start = start.next;
            }
        }
    }

    //        4) boolean RemoveFirst()
    public void RemoveFirst() {
        head = head.next;
    }

    //        5) boolean RemoveLast()
    public boolean RemoveLast() {
        Node start = head;
        if (start.next == null)
            return false;

        while (start.next.next != null) {
            start = start.next;
        }
        start.next = null;
        return true;
    }

    //        6) boolean RemoveByIndex(int index)
    public boolean RemoveByIndex(int index) {
        if (index == 0) {
            head = head.next;
            return true;
        } else if (index > 0) {
            int count = 0;
            Node start = head;
            while (start.next != null) {
                if (++count == index) {
                    start.next = start.next.next;
                    return true;
                }
                start = start.next;
            }
        }
        return false;
    }

    //        7) boolean RemoveByValue(int value) remove first find   *   1,2,3,4,3 -> 3 -> 1,2,4,3
    public boolean RemoveByValue(int value) {
        if (head.value == value) {
            head = head.next;
            return true;
        }

        Node start = head;
        while (start.next != null) {
            if (start.next.value == value) {
                start.next = start.next.next;
                return true;
            }
            start = start.next;
        }
        return false;
    }

    //        8) boolean RemoveByValueAll(int value) remove all num   *   1,2,3,4,3 -> 3 -> 1,2,4
    public boolean RemoveByValueAll(int value) {
        int count = 0;
        if (head.value == value) {
            head = head.next;
        }

        Node start = head;
        while (start.next != null) {
            if (start.next.value == value) {
                start.next = start.next.next;
                count++;
            } else
                start = start.next;
        }
        return count > 0;
    }

    //        9) boolean Contains(int value) -> true false
    public boolean Contains(int value) {
        Node start = head;
        while (start.value != value) {
            if (start.next == null)
                return false;
            start = start.next;
        }
        return true;
    }

    //        10)int get(int idnex) -> return by index
    public int get(int index) {
        int count = 0;
        Node start = head;

        while (count++ < index) {
            if (start.next == null)
                throw new IndexOutOfBoundsException("Нет значения с индексом "+index);
            start = start.next;
        }

        return start.value;
    }
//        11)void reverse()  reverse data
    public void reverse(){

        if(head.next != null) { //если список из одного эл-та
            Node tail = head; //A
            Node start = head.next; //B

            if (start.next == null) { //если список из 2-х эл-тов
                head = start;
                tail.next = null;
                head.next = tail;
            }else {
                Node past = head;
                Node cur = head;
                boolean isFirst = true;
                while (cur.next != null) {
                    Node next = cur.next; //C
                    cur.next = isFirst ? null : past;
                    past = cur;
                    cur = next;
                    isFirst = false;

                }
                head = cur;
                head.next = past;
            }
        }
    }

    public void print() {
        Node start = head;
        while (start != null) {
            System.out.print(start.value + " ");
            start = start.next;
        }
        System.out.println();
    }

}
