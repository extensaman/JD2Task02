package by.academy.it.task02.utility;

import by.academy.it.task02.entity.Robot;
import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.relation.Servant;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PartServant {

    //удаление деталей со свалки
    public static List<Part> takePart(List<Part> listOfPart) {
        List<Part> listOfTakenPart = new ArrayList<>();
        int number = (int) (Math.random() * (listOfPart.size() - 1));
        listOfTakenPart.add(listOfPart.remove(number));
        return listOfTakenPart;
    }

    //опрделяем победителя
    public static void getWinner(Servant servant1, Servant servant2) {
        Map<Part, Long> map1 = servant1.getListOfServantPart().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Part, Long> map2 = servant2.getListOfServantPart().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Long robotOfServant1 = map1.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();
        Long robotOfServant2 = map2.entrySet().stream().min(Map.Entry.comparingByValue()).get().getValue();

        if (robotOfServant1 > robotOfServant2 && Robot.getParts().size() == map1.size()) {
            System.out.println("Winner is: CrazyScientist_1\nCrazyScientist_1 - " + robotOfServant1 + " robots");
            if (Robot.getParts().size() == map2.size()) {
                System.out.println("CrazyScientist_2 - " + robotOfServant2 + " robots");
            } else {
                System.out.println("CrazyScientist_2 - 0 robots");
            }
        } else if (robotOfServant2 > robotOfServant1 && Robot.getParts().size() == map2.size()) {
            System.out.println("Winner is: CrazyScientist_2\nCrazyScientist_2 - " + robotOfServant2 + " robots");
            if (Robot.getParts().size() == map1.size()) {
                System.out.println("CrazyScientist_1 - " + robotOfServant1 + " robots");
            } else {
                System.out.println("CrazyScientist_1 - 0 robots");
            }
        } else {
            if (Robot.getParts().size() == map1.size() && Robot.getParts().size() == map2.size()) {
                System.out.println("Everyone tied for first!");
            } else {
                System.out.println("Nobody won");
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("CrazyScientist_1:");
        map1.entrySet().forEach(System.out::println);
        System.out.println();
        System.out.println("CrazyScientist_2:");
        map2.entrySet().forEach(System.out::println);
    }
}

