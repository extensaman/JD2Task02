package by.academy.it.task02.relation;

import by.academy.it.task02.CrazyScientist;
import by.academy.it.task02.entity.part.abstraction.Part;

import java.util.ArrayList;
import java.util.List;


public class Servant implements Runnable {
    Dump dump;
    private final List<Part> listOfServantPart;

    public Servant(Dump dump) {
        this.dump = dump;
        listOfServantPart = new ArrayList<>();
    }

    //прислужник забирает товар
    public void run() {
        for (int i = 1; i <= CrazyScientist.COUNT_OF_NIGHT; i++) {
            listOfServantPart.addAll(dump.get());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Part> getListOfServantPart() {
        return listOfServantPart;
    }

}
