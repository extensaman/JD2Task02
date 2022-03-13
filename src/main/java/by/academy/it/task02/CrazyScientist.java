package by.academy.it.task02;

import by.academy.it.task02.relation.Dump;
import by.academy.it.task02.relation.Fabric;
import by.academy.it.task02.relation.Servant;
import by.academy.it.task02.utility.PartServant;

import java.util.*;

public class CrazyScientist {
    public static final int COUNT_OF_NIGHT = 100;

    public static void main(String[] args) {
        Dump dump = new Dump();
        Fabric fabric = new Fabric(dump);
        Servant servant1 = new Servant(dump);
        Servant servant2 = new Servant(dump);

        Thread fabricPart = new Thread(fabric);
        Thread crazyScientist_1 = new Thread(servant1);
        Thread crazyScientist_2 = new Thread(servant2);

        List<Thread> threads = new ArrayList<>(Arrays.asList(fabricPart, crazyScientist_1, crazyScientist_2));
        threads.forEach(Thread::start);

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        //определяем победителя
        PartServant.getWinner(servant1, servant2);
    }
}
