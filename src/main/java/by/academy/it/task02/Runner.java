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
import by.academy.it.task02.logic.Simulator;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {

        System.out.println("Pull request from Aleksandr");
        System.out.println("Pull request from Lidia");

        Simulator.getInstance().execute();
    }
}
