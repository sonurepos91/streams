package com.streams8.straeamsapp;

interface Calculator{
    void switchOn();
}

public class TestRunnable {
    public static void main (String[] args) {

        Calculator calculator= () -> System.out.println("Hii");
        calculator.switchOn();

    }
}
