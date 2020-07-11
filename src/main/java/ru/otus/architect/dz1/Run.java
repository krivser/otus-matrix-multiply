package ru.otus.architect.dz1;

import ru.otus.architect.dz1.thread.MultiplyMatrix;
import ru.otus.architect.dz1.util.MatrixUtils;

/**
 * Произведение матриц в несколько потоков
 * Кол-во элементов разбивается пропорционально кол-ву ядер процессора
 * Таким образом параллельно будут запущены сразу несколько потоков,
 * где каждый поток будет вычислять произведение только своих элементов матрицы
 */
public class Run {
    public static void main(String[] args) {
        // Число строк матрицы
        final int N_ROWS = 100;
        // Число столбцов матрицы
        final int N_COLS = 100;

        final int[][] firstMatrix = new int[N_ROWS][N_COLS];    // Первая (левая) матрица.
        final int[][] secondMatrix = new int[N_ROWS][N_COLS];   // Вторая (правая) матрица.

        // Заполняем рандомно матрицы
        MatrixUtils.randomMatrix(firstMatrix);
        MatrixUtils.randomMatrix(secondMatrix);

        // Вычисляем произведение в несколько потоков
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix();
        final int[][] resultMatrixMT = multiplyMatrix.multiplyMatrixMultiThreading(firstMatrix, secondMatrix, Runtime.getRuntime().availableProcessors());

        // Результат сохраняем в файл
        MatrixUtils.printAllMatrix("Matrix.txt", firstMatrix, secondMatrix, resultMatrixMT);
    }
}