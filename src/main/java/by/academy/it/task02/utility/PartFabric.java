package by.academy.it.task02.utility;

import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.entity.part.enums.PartTypes;

import java.util.ArrayList;
import java.util.List;

public class PartFabric {
    private static final List<PartTypes> listOfPart = List.of(PartTypes.BODY, PartTypes.CPU,
            PartTypes.HDD, PartTypes.HEAD, PartTypes.LEFT_HAND, PartTypes.LEFT_LEG, PartTypes.RAM,
            PartTypes.RIGHT_HAND, PartTypes.RIGHT_LEG);

    public static List<Part> generateList(int count) {
        int number;
        List<Part> listOfPartFinal = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            number = (int) (Math.random() * listOfPart.size());
            listOfPartFinal.add(PartCreator.create(listOfPart.get(number)));
        }
        return listOfPartFinal;
    }
}
