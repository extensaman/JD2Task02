package by.academy.it.task02.utility;

import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.entity.part.enums.PartTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PartFabric {

    public static final List<PartTypes> partsToChooseFrom = List.of(PartTypes.BODY, PartTypes.CPU,
            PartTypes.HDD, PartTypes.HEAD, PartTypes.LEFT_HAND, PartTypes.LEFT_LEG, PartTypes.RAM,
            PartTypes.RIGHT_HAND, PartTypes.RIGHT_LEG);
    private static final List<Part> partsForDisposal = new ArrayList<>();
    private static final BlockingQueue<List<Part>> partsOnJunkyard = new ArrayBlockingQueue<>(4);

    public static List<Part> preparePartsForDisposal(int count) {
        int number;
        for (int i = 0; i < count; i++) {
            number = (int) (Math.random() * partsToChooseFrom.size());
            partsForDisposal.add(PartCreator.create(partsToChooseFrom.get(number)));
        }
        return partsForDisposal;
    }

    public static BlockingQueue<List<Part>> disposeToJunkyard() {
        Thread disposeToJunkyard = new Thread(() -> {
            try {
                dispose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        disposeToJunkyard.start();
        return partsOnJunkyard;
    }

    private static void dispose() throws InterruptedException {
        while (true) {
            partsOnJunkyard.put(preparePartsForDisposal(new Random().nextInt(4)));
        }
    }
}
