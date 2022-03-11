package by.academy.it.task02.utility;

import by.academy.it.task02.entity.part.*;
import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.entity.part.enums.PartTypes;

public class PartCreator {

    public static Part create(PartTypes type) {
        switch (type) {
            case BODY: return new Body();
            case CPU: return new CPU();
            case HDD: return new HDD();
            case HEAD: return new Head();
            case LEFT_HAND: return new LeftHand();
            case LEFT_LEG: return new LeftLeg();
            case RAM: return new RAM();
            case RIGHT_HAND: return new RightHand();
            case RIGHT_LEG: return new RightLeg();
            default: return null;
        }
    }
}
