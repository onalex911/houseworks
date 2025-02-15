package ru.onalex.hw1002.model;

public class Test {
    public static void main(String[] args) {
        Person p1 = new Person("Farid", "Abdullayev", 1929);
        Person p2 = new Person("Dima", "Bilan", 1950);
        Person p3 = new Person("Oleq", "Qazmanov", 1974);
        Person p4 = new Person("Olqa", "Buzova", 1940);
        Person p5 = new Person("Alla", "Puqaceva", 1989);

        System.out.println(p5.getFirstName() + ", " + p5.getId());
    }
}
