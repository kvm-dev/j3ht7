package ru.geekbrains.j3.ht7;

public class Main {

    public static void main(String[] args) {
        Class t = Tester.class;
        try {
            TestClass.start(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}