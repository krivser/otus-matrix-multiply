package ru.otus.arch.matrix.p2.adapter;

import ru.otus.arch.matrix.p1.I1;
import ru.otus.arch.matrix.p1.P1;
import ru.otus.arch.matrix.p2.I2;
import ru.otus.arch.matrix.p2.P2;

import java.io.IOException;

public class AdapterP1 extends P2 {
    private I1 adaptee;

    public AdapterP1(P1 adaptee) {
        super(adaptee.getSourceFile());
        this.adaptee = adaptee;
    }

    @Override
    public void generateMatrixAndSaveToFile() {
        super.generateMatrixAndSaveToFile();
        adaptee.readMatrixFromFile();
        adaptee.sumMatrixAndSaveResultToFile();
    }
}
