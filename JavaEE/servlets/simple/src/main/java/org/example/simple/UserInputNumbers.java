package org.example.simple;

public class UserInputNumbers {
    private double a;  // Поле для "a"
    private double b;  // Поле для "b"
    private double c;  // Поле для "c"
    private String oper;

    {
        a = 0;
        b = 0;
        c = 0;
    }


    public UserInputNumbers() {}

    public double getA() {
        return a;
    }

    public void setA(String a) {
        this.a = Integer.parseInt(a);
    }

    public double getB() {
        return b;
    }

    public void setB(String b) {
        this.b = Integer.parseInt(b);
    }

    public double getC() {
        return c;
    }

    public void setC(String c) {
        this.c = Integer.parseInt(c);
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getOper() {
        return oper;
    }
}