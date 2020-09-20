package ru.otus.architect.dz1.util;

import java.io.*;
import java.util.Random;

public class MatrixUtils {
    /**
     * Заполнение матрицы случайными числами.
     *
     * @param matrix Заполняемая матрица.
     */
    public static void randomMatrix(final int[][] matrix) {
        final Random random = new Random();  // Генератор случайных чисел.

        for (int row = 0; row < matrix.length; ++row)           // Цикл по строкам матрицы.
            for (int col = 0; col < matrix[row].length; ++col)  // Цикл по столбцам матрицы.
                matrix[row][col] = random.nextInt(100);         // Случайное число от 0 до 100.
    }

    //

    /**
     * Вывод матрицы в файл.
     * Производится выравнивание значений для лучшего восприятия.
     *
     * @param fileWriter Объект, представляющий собой файл для записи.
     * @param matrix     Выводимая матрица.
     * @throws IOException
     */
    public static void printMatrix(final FileWriter fileWriter, final int[][] matrix) throws IOException {
        boolean hasNegative = false;  // Признак наличия в матрице отрицательных чисел.
        int maxValue = 0;      // Максимальное по модулю число в матрице.

        // Вычисляем максимальное по модулю число в матрице и проверяем на наличие отрицательных чисел.
        for (final int[] row : matrix) {  // Цикл по строкам матрицы.
            for (final int element : row) {  // Цикл по столбцам матрицы.
                int temp = element;
                if (element < 0) {
                    hasNegative = true;
                    temp = -temp;
                }
                if (temp > maxValue)
                    maxValue = temp;
            }
        }

        // Вычисление длины позиции под число.
        int len = Integer.toString(maxValue).length() + 1;  // Одно знакоместо под разделитель (пробел).
        if (hasNegative)
            ++len;  // Если есть отрицательные, добавляем знакоместо под минус.

        // Построение строки формата.
        final String formatString = "%" + len + "d";

        // Вывод элементов матрицы в файл.
        for (final int[] row : matrix) {  // Цикл по строкам матрицы.
            for (final int element : row)  // Цикл по столбцам матрицы.
                fileWriter.write(String.format(formatString, element));

            fileWriter.write("\n");  // Разделяем строки матрицы переводом строки.
        }
    }

    /**
     * Вывод трёх матриц в файл. Файл будет перезаписан.
     *
     * @param fileName     Имя файла для вывода.
     * @param firstMatrix  Первая матрица.
     * @param secondMatrix Вторая матрица.
     * @param resultMatrix Результирующая матрица.
     */
    public static void printAllMatrix(final String fileName, final int[][] firstMatrix, final int[][] secondMatrix, final int[][] resultMatrix) {
        try (final FileWriter fileWriter = new FileWriter(fileName, false)) {
            fileWriter.write("First matrix:\n");
            printMatrix(fileWriter, firstMatrix);

            fileWriter.write("\nSecond matrix:\n");
            printMatrix(fileWriter, secondMatrix);

            fileWriter.write("\nResult matrix:\n");
            printMatrix(fileWriter, resultMatrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readMatrix(FileReader fileReader, int[][] matrix) {

    }

    public static void readAllMatrix(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            String str;
            if ((str = br.readLine()) != null) {

            }

        } finally {
            if (br != null) br.close();
        }
    }
}