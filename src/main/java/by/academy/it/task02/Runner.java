package by.academy.it.task02;

import by.academy.it.task02.logic.Simulator;

/**
 * Two mad scientists had a competition to see who could assemble
 * more robots in 100 nights. For this every night each of
 * them sends his minion to the junkyard of the robot factory for
 * details. To assemble one robot they need: Head, Body,
 * Left hand, Right hand, Left foot, Right foot, CPU, RAM, HDD.
 * On the first night, there are 20 random parts in the junkyard. Every
 * night the factory throws 1 to 4 random parts into the landfill.
 * At the same time, the minions of both scientists are sent to
 * junkyard, and each collects 1 to 4 random parts. If on
 * there are no parts in the junkyard - the minions leaves with nothing.
 * Then they return home and give the details to the scientists.
 * Scientists are trying to assemble as many robots as possible from parts
 * which they received.
 *
 * Write a program that simulates this process. For simulation
 * accept that every night comes through 100 ms.
 * The factory and the two minions operate at the same time.
 * After 100 nights (about 10 seconds) determine the winner
 * competitions.
 *
 * @author Yusikau Aliaksandr
 * @author LidiaZh
 * @author AleksandrMikhalevich
 * @version 1.0
 */

public class Runner {
    public static void main(String[] args) {

        System.out.println("Pull request from Aleksandr");
        System.out.println("Pull request from Lidia");

        Simulator.getInstance().execute();
    }
}
