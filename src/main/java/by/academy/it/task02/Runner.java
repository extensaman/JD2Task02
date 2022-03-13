package by.academy.it.task02;

import by.academy.it.task02.logic.Simulator;

public class Runner {
    public static void main(String[] args) {

        System.out.println("Pull request from Aleksandr");
        System.out.println("Pull request from Lidia");

        Simulator.getInstance().execute();
    }
}
