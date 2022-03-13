package by.academy.it.task02.utility;

import by.academy.it.task02.entity.Robot;
import by.academy.it.task02.entity.part.*;
import by.academy.it.task02.entity.part.abstraction.Part;

import java.util.ArrayList;
import java.util.List;

public class MadScientists {

    private static final List<Part> robotBuildingKit = new ArrayList<>(List.of(new Head(),
            new Body(),
            new LeftHand(),
            new RightHand(),
            new LeftLeg(), new LeftLeg(),
            new RightLeg(),
            new CPU(),
            new RAM(),
            new HDD()));

    private static final List<Part> madScientist1Parts = new ArrayList<>();
    private static final List<Part> madScientist2Parts = new ArrayList<>();

    public static void makeRobots() {
        Thread madScientist1 = new Thread(new Runnable() {
            public void run() {
                try {
                    Servants.getPartsFromJunkyard(madScientist1Parts);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collectParts(madScientist2Parts);
            }
        });
        Thread madScientist2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Servants.getPartsFromJunkyard(madScientist2Parts);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collectParts(madScientist2Parts);
            }
        });
        madScientist1.start();
        madScientist2.start();
    }

    public static void collectParts(List<Part> list) {
        Robot robot = new Robot();
        int count = 0;
        if (list.containsAll(robot.getParts())) {
            for (Part part : robot.getParts()) {
                list.remove(part);
                count++;
            }
        }
        System.out.println("Cписок деталей после удаления комплекта деталей для одного робота\n" + list);
        System.out.println("Сделано роботов безумным ученым " + count);

    }


}
