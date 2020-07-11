package ru.otus.architect.dz1.thread;

public class MultiplyMatrix {
    /**
     * Однопоточное умножение матриц (для тестов), чтобы сравнить корректность
     *
     * @param firstMatrix  Первая матрица.
     * @param secondMatrix Вторая матрица.
     * @return Результирующая матрица.
     */
    public int[][] multiplyMatrix(final int[][] firstMatrix, final int[][] secondMatrix) {
        final int rowCount = firstMatrix.length;             // Число строк результирующей матрицы.
        final int colCount = secondMatrix[0].length;         // Число столбцов результирующей матрицы.
        final int sumLength = secondMatrix.length;           // Число членов суммы при вычислении значения ячейки.
        final int[][] result = new int[rowCount][colCount];  // Результирующая матрица.

        for (int row = 0; row < rowCount; ++row) {  // Цикл по строкам матрицы.
            for (int col = 0; col < colCount; ++col) {  // Цикл по столбцам матрицы.
                int sum = 0;
                for (int i = 0; i < sumLength; ++i)
                    sum += firstMatrix[row][i] * secondMatrix[i][col];
                result[row][col] = sum;
            }
        }

        return result;
    }

    /**
     * Многопоточное умножение матриц.
     *
     * @param firstMatrix  Первая (левая) матрица.
     * @param secondMatrix Вторая (правая) матрица.
     * @param threadCount  Число потоков.
     * @return Результирующая матрица.
     */
    public int[][] multiplyMatrixMultiThreading(final int[][] firstMatrix, final int[][] secondMatrix, int threadCount) {
        if (threadCount <= 0) threadCount = 1;

        final int rowCount = firstMatrix.length;             // Число строк результирующей матрицы.
        final int colCount = secondMatrix[0].length;         // Число столбцов результирующей матрицы.
        final int[][] result = new int[rowCount][colCount];  // Результирующая матрица.

        final int cellsForThread = (rowCount * colCount) / threadCount;  // Число вычисляемых ячеек на поток.
        int firstIndex = 0;  // Индекс первой вычисляемой ячейки.
        final MatrixMultiplyThread[] multiplierThreads = new MatrixMultiplyThread[threadCount];  // Массив потоков.

        // Создание и запуск потоков.
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            int lastIndex = firstIndex + cellsForThread;  // Индекс последней вычисляемой ячейки.
            if (threadIndex == (threadCount -1)) {
                /* Один из потоков должен будет вычислить не только свой блок ячеек,
                   но и остаток, если число ячеек не делится нацело на число потоков. */
                lastIndex = rowCount * colCount;
            }
            multiplierThreads[threadIndex] = new MatrixMultiplyThread(firstMatrix, secondMatrix, result, firstIndex, lastIndex);
            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }

        // Ожидание завершения потоков.
        try {
            for (final MatrixMultiplyThread multiplierThread : multiplierThreads)
                multiplierThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
