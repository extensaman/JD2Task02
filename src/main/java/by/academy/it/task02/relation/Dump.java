package by.academy.it.task02.relation;

import by.academy.it.task02.entity.part.abstraction.Part;
import by.academy.it.task02.utility.PartFabric;
import by.academy.it.task02.utility.PartServant;

import java.util.ArrayList;
import java.util.List;

public class Dump {
    private static final int MAX_PRODUCED = 4;
    private static final int MAX_TAKEN = 4;
    private static final int FIRST_NIGHT_DETAIL = 20;
    private final List<Part> listOfPart;
    private volatile int amount;

    public Dump() {
        //список деталей на свалке в 1ую ночь
        listOfPart = new ArrayList<>(PartFabric.generateList(FIRST_NIGHT_DETAIL));
        amount = listOfPart.size();
    }

    //поступление деталей на свалку с фабрики
    public synchronized void put() {
        while (amount > 2000000) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int countOfDetail = (int) (Math.random() * MAX_PRODUCED + 1);
        listOfPart.addAll(PartFabric.generateList(countOfDetail));
        System.out.println("Dump.put listOfPart.size - " + listOfPart.size());
        notify();
    }

    //удаление деталей со свалки
    public synchronized List<Part> get() {
        while (amount <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int countOfTakenDetail = 0;
        List<Part> listOfServantPart = new ArrayList<>();
        while (countOfTakenDetail < MAX_TAKEN) {
            if (listOfPart.size() != 0) {
                listOfServantPart.addAll(PartServant.takePart(listOfPart)); //удаляем деталь со свалки и записываем в лист прислужника
                countOfTakenDetail++;
                System.out.println("Dump.remove - " + listOfPart.size() + " " + Thread.currentThread().getName());
            } else {
                countOfTakenDetail = MAX_TAKEN;
            }
        }
        notify();
        return listOfServantPart;

    }

}
