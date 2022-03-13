package by.academy.it.task02.entity;

import by.academy.it.task02.entity.part.CPU;
import by.academy.it.task02.entity.part.HDD;
import by.academy.it.task02.entity.part.Head;
import by.academy.it.task02.entity.part.LeftHand;
import by.academy.it.task02.entity.part.LeftLeg;
import by.academy.it.task02.entity.part.RAM;
import by.academy.it.task02.entity.part.RightHand;
import by.academy.it.task02.entity.part.RightLeg;
import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.entity.part.Body;

import java.util.List;

public class Robot {
    private static final List<Part> parts = List.of(new Head(),
                                        new Body(),
                                        new RightHand(),
                                        new LeftHand(),
                                        new RightLeg(),
                                        new LeftLeg(),
                                        new CPU(),
                                        new RAM(),
                                        new HDD());

    public static List<Part> getParts() {
        return parts;
    }
}
