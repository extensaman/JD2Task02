package by.academy.it.task02.logic;

import by.academy.it.task02.entity.Robot;
import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.utility.PartFabric;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Competition {

    public static final int INITIAL_PARTS_COUNT = 20;
    private final ConcurrentLinkedQueue<Part> partQueue;
    private final List<Part> robotPropotypeParts = new Robot().getParts();
    private final Random random = new Random();

    public Competition() {
        List<Part> initialParts = PartFabric.generateList(INITIAL_PARTS_COUNT);
        this.partQueue = new ConcurrentLinkedQueue<>(initialParts);
        System.out.println("Initial partQueue is " + partQueue);
    }

    public final class MadScientist {

        private final List<Part> store;
        private final List<Robot> robots;

        public MadScientist() {
            this.store = new LinkedList<>();
            this.robots = new ArrayList<>();
        }

        public List<Part> getStore() {
            return store;
        }

        public List<Robot> getRobots() {
            return robots;
        }

        public void tryAssembleRobot() {
            if (store.containsAll(robotPropotypeParts)) {
                for (Part part : robotPropotypeParts) {
                    store.remove(part);
                }
                robots.add(new Robot());
            }
        }

        public final class Minion implements Runnable {
            private final int partsConsumeBound;

            public Minion(int partsSampleBound) {
                this.partsConsumeBound = partsSampleBound;
            }

            @Override
            public void run() {
                List<Part> minionSample = Stream.generate(partQueue::poll)
                        .filter(Objects::nonNull)
                        .limit(random.nextInt(partsConsumeBound) + 1)
                        .collect(Collectors.toList());
                System.out.println("Minion \'" + Thread.currentThread().getName() + "\' has taken next part's sample " + minionSample);
                MadScientist.this.store.addAll(minionSample);
            }
        }
    }

    public final class Junkyard implements Runnable {

        private final int partsSupplyBound;

        public Junkyard(int partsSupplyBound) {
            this.partsSupplyBound = partsSupplyBound;
        }

        @Override
        public void run() {
            List<Part> newPartList = PartFabric.generateList(random.nextInt(partsSupplyBound) + 1);
            System.out.println(">>> PUT to Junkyard " + newPartList);
            newPartList.forEach(partQueue::offer);
            System.out.println("*** Junkyard *** is " + partQueue);
        }

    }

}
