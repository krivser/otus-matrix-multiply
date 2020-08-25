package ru.otus.arch.matrix.p1;

public class P1 implements I1{
    private int[][] matrix1, matrix2;
    private String sourceFile, targetFile;

    public P1(String sourceFile, String targetFile) {
        this.sourceFile = sourceFile;
        this.targetFile = targetFile;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    @Override
    public void readMatrixFromFile() {
        System.out.println("Читаю матрицы из файла " + this.sourceFile);
    }

    @Override
    public void sumMatrixAndSaveResultToFile() {
        System.out.println("Вычисляю сумму матриц и записываю результат в файл " + this.targetFile);
    }
}
