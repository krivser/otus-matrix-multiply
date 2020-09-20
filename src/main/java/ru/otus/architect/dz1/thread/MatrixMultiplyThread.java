package ru.otus.architect.dz1.thread;

import ru.otus.architect.dz1.logger.Logger;

/** Поток-вычислитель группы ячеек матрицы. */
public class MatrixMultiplyThread extends Thread {
    // Первая (левая) матрица
    private final int[][] firstMatrix;
    // Вторая (правая) матрица
    private final int[][] secondMatrix;
    // Результирующая матрица
    private final int[][] resultMatrix;
    // Начальный индекс элементов для расчета
    private final int firstIndex;
    // Конечный индекс элементов для расчета
    private final int lastIndex;
    // Число строк и столбцов
    private final int rowCount, colCount;

    Logger log;
    /**
     * @param firstMatrix  Первая (левая) матрица.
     * @param secondMatrix Вторая (правая) матрица.
     * @param resultMatrix Результирующая матрица.
     * @param firstIndex   Начальный индекс (ячейка с этим индексом вычисляется).
     * @param lastIndex    Конечный индекс (ячейка с этим индексом не вычисляется).
     */
    public MatrixMultiplyThread(final int[][] firstMatrix, final int[][] secondMatrix, final int[][] resultMatrix, final int firstIndex, final int lastIndex) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;

        rowCount = secondMatrix.length;
        colCount = secondMatrix[0].length;

        log = Logger.getInstance();
    }

    /**
     * Вычисление значения в одной ячейке.
     *
     * @param row Номер строки ячейки.
     * @param col Номер столбца ячейки.
     */
    private void calcValue(final int row, final int col) {
        int sum = 0;

        Logger.info("Поток " + getName() + ". Вычисляю элемент [" + (row+1) + "][" + (col+1) + "]\n");
        for (int i = 0; i < rowCount; ++i) {
            sum += firstMatrix[row][i] * secondMatrix[i][col];
        }

        resultMatrix[row][col] = sum;
    }

    /**
     * Рабочая функция потока.
     */
    @Override
    public void run() {
        Logger.info("Поток " + getName() + " запущен. Вычисляется произведение элементов с индексами от " + (firstIndex + 1)  + " до " + lastIndex + " ...\n");
        //System.out.println("Поток " + getName() + " запущен. Вычисляется произведение элементов с индексами от " + (firstIndex + 1)  + " до " + lastIndex + " ...");

        for (int index = firstIndex; index < lastIndex; ++index) {
            calcValue(index / colCount, index % colCount);
        }

        //System.out.println("Поток " + getName() + " завершен");
        Logger.info("Поток " + getName() + " завершен\n");
    }
}
