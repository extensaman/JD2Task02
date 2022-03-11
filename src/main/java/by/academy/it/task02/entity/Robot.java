package main.java.by.academy.it.task02.entity;

import main.java.by.academy.it.task02.entity.part.CPU;
import main.java.by.academy.it.task02.entity.part.HDD;
import main.java.by.academy.it.task02.entity.part.Head;
import main.java.by.academy.it.task02.entity.part.LeftHand;
import main.java.by.academy.it.task02.entity.part.LeftLeg;
import main.java.by.academy.it.task02.entity.part.RAM;
import main.java.by.academy.it.task02.entity.part.RightHand;
import main.java.by.academy.it.task02.entity.part.RightLeg;
import main.java.by.academy.it.task02.entity.part.abstraction.Part;
import main.java.by.academy.it.task02.entity.part.Body;

import java.util.List;

public class Robot {
    private final List<Part> parts = List.of(new Head(),
                                        new Body(),
                                        new RightHand(),
                                        new LeftHand(),
                                        new RightLeg(),
                                        new LeftLeg(),
                                        new CPU(),
                                        new RAM(),
                                        new HDD());

    public List<Part> getParts() {
        return parts;
    }
}
