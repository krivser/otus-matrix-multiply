package ru.otus.arch.matrix;

import org.junit.jupiter.api.Test;
import ru.otus.arch.matrix.p1.I1;
import ru.otus.arch.matrix.p1.P1;
import ru.otus.arch.matrix.p2.I2;
import ru.otus.arch.matrix.p2.P2;
import ru.otus.arch.matrix.p2.adapter.AdapterP1;

import java.io.IOException;

public class AdapterTest {

    @Test
    public void TestP1() {
        // Программа читает матрицы из файла, суммирует их и сохраняет результат в файл
        I1 p1 = new P1("F0", "F1");
        p1.readMatrixFromFile();
        p1.sumMatrixAndSaveResultToFile();
    }

    @Test
    public void TestP2() {
        // Программа генерирует матрицы и сохраняет их в файл
        I2 p2 = new P2("F2");
        p2.generateMatrixAndSaveToFile();
    }

    @Test
    public void TestAdapter() {
        // Программа, используя АДАПТЕР, читает файл с матрицами, суммирует матрицы и сохраняет результат в файл
        I2 adapterP1 = new AdapterP1(new P1("F2", "F1"));
        adapterP1.generateMatrixAndSaveToFile();
    }
}
