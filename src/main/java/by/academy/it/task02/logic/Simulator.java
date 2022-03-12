package by.academy.it.task02.logic;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulator {

    public static final int POOL_CAPACITY = 3;
    private static final Simulator INSTANCE = new Simulator();
    public static final int PARTS_SUPPLY_BOUND = 4;
    public static final int PARTS_SAMPLE_BOUND = 4;
    public static final int NIGHT_LENGTH = 100;
    public static final int NIGHTS_COUNT = 100;


    private Simulator() {
    }

    public static Simulator getInstance() {
        return INSTANCE;
    }

    public void execute() {

        Competition competition = new Competition();
        Competition.MadScientist madScientistA = competition.new MadScientist("MadScientist A");
        Competition.MadScientist madScientistB = competition.new MadScientist("MadScientist B");


        ExecutorService service = Executors.newFixedThreadPool(POOL_CAPACITY);
        for (int i = 0; i < NIGHTS_COUNT; i++) {
            System.out.println("___ NIGHT #" + (i+1) + " ______________________________________");

            Collection<Callable<Void>> callables =
                    List.of(competition.new TrashDumper(PARTS_SUPPLY_BOUND),
                            madScientistA.new Minion(PARTS_SAMPLE_BOUND),
                            madScientistB.new Minion(PARTS_SAMPLE_BOUND));
            try {
                service.invokeAll(callables);
                Thread.sleep(NIGHT_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            madScientistA.tryAssembleRobot();
            madScientistB.tryAssembleRobot();
        }

        service.shutdown();

        System.out.println("MadScientistA has assembled " + madScientistA.getRobots().size() + " robots");
        System.out.println("MadScientistB has assembled " + madScientistB.getRobots().size() + " robots");
    }

}
