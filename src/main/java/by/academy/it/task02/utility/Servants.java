package by.academy.it.task02.utility;

import by.academy.it.task02.entity.part.abstraction.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Servants {

    private static final BlockingQueue<List<Part>> partsFromJunkyard = PartFabric.disposeToJunkyard();
    private static final List<Part> partsForMadScientist1 = new ArrayList<>();
    private static final List<Part> partsForMadScientist2 = new ArrayList<>();

    public static List<Part> getPartsFromJunkyard(List<Part> list) throws InterruptedException {
        Thread servant1 = new Thread(new Runnable() {
            public void run() {
                try {
                    consume(partsForMadScientist1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread servant2 = new Thread(new Runnable() {
            public void run() {
                try {
                    consume(partsForMadScientist2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        servant1.start();
        servant2.start();
        return list;
    }

    private static List<Part> consume(List<Part> list) throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(4) == 0) {
                list = partsFromJunkyard.take();
                System.out.println("Со свалки взято: " + list + "; на свалке есть: " + partsFromJunkyard.size() + " деталь(и)");
                return list;

            }
        }
    }
}
