package ru.otus.architect.dz1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.architect.dz1.thread.MultiplyMatrix;
import ru.otus.architect.dz1.util.MatrixUtils;

class MatrixTest {

    /** Число строк матрицы. */
    final static int N_ROWS  = 10;
    /** Число столбцов матрицы. */
    final static int N_COLS  = 10;

    final int[][] firstMatrix  = new int[N_ROWS][N_COLS];    // Первая (левая) матрица.
    final int[][] secondMatrix = new int[N_ROWS][N_COLS];    // Вторая (правая) матрица.

    @BeforeEach
    void setUp() {
        MatrixUtils.randomMatrix(firstMatrix);
        MatrixUtils.randomMatrix(secondMatrix);
    }

    @Test
    void multiplyMatrixOneThread() {
        testMultiplyMatrix(1);
    }

    @Test
    void multiplyMatrixTwoThread() {
        testMultiplyMatrix(2);
    }

    @Test
    void multiplyMatrixFiveThread() {
        testMultiplyMatrix(5);
    }

    @Test
    void multiplyMatrixZeroThread() {
        testMultiplyMatrix(0);
    }

    private void testMultiplyMatrix(int threadCount) {
        MultiplyMatrix multiplyMatrix = new MultiplyMatrix();
        final int[][] resultMatrixMT = multiplyMatrix.multiplyMatrixMultiThreading(firstMatrix, secondMatrix, threadCount);
        final int[][] resultMatrix = multiplyMatrix.multiplyMatrix(firstMatrix, secondMatrix);

        // Проверка многопоточных вычислений
        boolean isOk = true;
        for (int row = 0; row < N_ROWS; ++row) {
            for (int col = 0; col < N_COLS; ++col) {
                if (resultMatrixMT[row][col] != resultMatrix[row][col]) {
                    isOk = false;
                    return;
                }
            }
        }

        assert isOk;
    }
}