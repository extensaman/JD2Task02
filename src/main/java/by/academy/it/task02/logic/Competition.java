package by.academy.it.task02.logic;

import by.academy.it.task02.entity.Robot;
import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.utility.PartFabric;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Competition {

    public static final int INITIAL_PARTS_COUNT = 20;
    private final Queue<Part> partsTrashHeap;
    private final List<Part> robotPrototypeParts = new Robot().getParts();
    private final ReentrantLock lock = new ReentrantLock();
    private final Random random = new Random();

    public Competition() {
        List<Part> initialParts = PartFabric.generateList(INITIAL_PARTS_COUNT);
        this.partsTrashHeap = new LinkedList<>(initialParts);
        System.out.println("Initial 'partsTrashHeap' is " + partsTrashHeap);
    }

    public final class MadScientist {

        private final List<Part> store;
        private final List<Robot> robots;
        private final String name;

        public MadScientist(String name) {
            this.store = new LinkedList<>();
            this.robots = new ArrayList<>();
            this.name = name;
        }

        public List<Part> getStore() {
            return store;
        }

        public List<Robot> getRobots() {
            return robots;
        }

        public void tryAssembleRobot() {
            if (store.containsAll(robotPrototypeParts)) {
                for (Part part : robotPrototypeParts) {
                    store.remove(part);
                }
                robots.add(new Robot());
                System.out.println("!!! Minion of " + this.name + " has assembled robot #" + robots.size());
            }
        }

        public final class Minion implements Callable<Void> {
            private final int partsConsumeBound;

            public Minion(int partsSampleBound) {
                this.partsConsumeBound = partsSampleBound;
            }

            @Override
            public Void call() throws Exception {
                List<Part> minionSample = new ArrayList<>();
                lock.lock();
                for (int i = 0; i < random.nextInt(partsConsumeBound) + 1; i++) {
                    Part part = partsTrashHeap.poll();
                    if (part != null) {
                        minionSample.add(part);
                    }
                }
                MadScientist.this.store.addAll(minionSample);
                System.out.println("--- Minion of " +
                                MadScientist.this.name +
                                    " has taken next part's sample " +
                                        minionSample +
                                            "\nHis store is " +
                                                    store);
                lock.unlock();
                return null;
            }
        }
    }

    public final class TrashDumper implements Callable<Void> {

        private final int partsSupplyBound;

        public TrashDumper(int partsSupplyBound) {
            this.partsSupplyBound = partsSupplyBound;
        }

        @Override
        public Void call() throws Exception {
            List<Part> newPartList = PartFabric.generateList(random.nextInt(partsSupplyBound) + 1);
            lock.lock();
            newPartList.forEach(partsTrashHeap::offer);
            System.out.println(">>> DUMP next parts to Parts-Trash-Heap: " +
                                    newPartList +
                                        "\n*** Parts-Trash-Heap now: " +
                                            partsTrashHeap);
            lock.unlock();
            return null;
        }

    }

}
