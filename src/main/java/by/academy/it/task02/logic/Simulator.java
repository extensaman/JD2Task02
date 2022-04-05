package by.academy.it.task02.logic;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulator {

    private static final Simulator INSTANCE = new Simulator();
    public static final int PARTS_SUPPLY_BOUND = 4;
    public static final int PARTS_SAMPLE_BOUND = 4;
    public static final int NIGHT_LENGTH = 100;
    public static final int NIGHTS_COUNT = 100;
    public static final String SCIENTIST_A_NAME = "MadScientist A";
    public static final String SCIENTIST_B_NAME = "MadScientist B";


    private Simulator() {
    }

    public static Simulator getInstance() {
        return INSTANCE;
    }

    public void execute() {

        Competition competition = new Competition();
        Competition.MadScientist madScientistA = competition.new MadScientist(SCIENTIST_A_NAME);
        Competition.MadScientist madScientistB = competition.new MadScientist(SCIENTIST_B_NAME);

        ExecutorService service = Executors.newCachedThreadPool();

        Collection<Callable<Void>> callables =
                List.of(competition.new TrashDumper(PARTS_SUPPLY_BOUND),
                        madScientistA.new Minion(PARTS_SAMPLE_BOUND),
                        madScientistB.new Minion(PARTS_SAMPLE_BOUND));

        for (int i = 0; i < NIGHTS_COUNT; i++) {
            System.out.println("___ NIGHT #" + (i + 1) + " ______________________________________");
            List<Future<Void>> futures = null;
            try {
                futures = service.invokeAll(callables);
                Thread.sleep(NIGHT_LENGTH);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            while (futures.stream().filter(Future::isDone).count() < futures.size()) ;
            madScientistA.tryAssembleRobot();
            madScientistB.tryAssembleRobot();
        }

        service.shutdown();

        System.out.println("MadScientistA has assembled " + madScientistA.getRobots().size() + " robots");
        System.out.println("MadScientistB has assembled " + madScientistB.getRobots().size() + " robots");
    }

}
