package ru.otus.architect.dz1.thread;

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
    }

    /**
     * Вычисление значения в одной ячейке.
     *
     * @param row Номер строки ячейки.
     * @param col Номер столбца ячейки.
     */
    private void calcValue(final int row, final int col) {
        int sum = 0;

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
        System.out.println("Поток " + getName() + " запущен. Вычисляется произведение элементов с индексами от " + (firstIndex + 1)  + " до " + lastIndex + " ...");

        for (int index = firstIndex; index < lastIndex; ++index) {
            calcValue(index / colCount, index % colCount);
        }

        System.out.println("Поток " + getName() + " завершен");
    }
}
