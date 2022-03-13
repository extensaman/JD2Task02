package by.academy.it.task02.relation;

import by.academy.it.task02.CrazyScientist;

public class Fabric implements Runnable {

    private final Dump dump;

    public Fabric(Dump dump) {
        this.dump = dump;
    }

    public void run() {
        for (int i = 1; i <= CrazyScientist.COUNT_OF_NIGHT; i++) {
            dump.put();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}