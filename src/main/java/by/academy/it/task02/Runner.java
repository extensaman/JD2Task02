package by.academy.it.task02;

import by.academy.it.task02.entity.Robot;
import by.academy.it.task02.entity.part.Body;
import by.academy.it.task02.entity.part.CPU;
import by.academy.it.task02.entity.part.HDD;
import by.academy.it.task02.entity.part.Head;
import by.academy.it.task02.entity.part.LeftHand;
import by.academy.it.task02.entity.part.LeftLeg;
import by.academy.it.task02.entity.part.RAM;
import by.academy.it.task02.entity.part.RightHand;
import by.academy.it.task02.entity.part.RightLeg;
import by.academy.it.task02.entity.part.abstraction.Part;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Pull-request from Aleksandr");
        List<Part> list = new ArrayList<>(List.of(new Head(), new Head(),
                new Body(), new Body(),
                new LeftHand(),
                new RightHand(),
                new LeftLeg(), new LeftLeg(),
                new RightLeg(),
                new CPU(),
                new RAM(),
                new HDD()));

        System.out.println("Первоначальный список деталей\n" + list);
        Robot robot = new Robot();
        if (list.containsAll(robot.getParts())) {
            for (Part part : robot.getParts()) {
                list.remove(part);
            }
        }
        System.out.println("Cписок деталей после удаления комплекта деталей для одного робота\n" + list);
    }
}
