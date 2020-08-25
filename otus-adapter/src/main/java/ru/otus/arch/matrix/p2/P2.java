package ru.otus.arch.matrix.p2;

public class P2 implements I2{
    private int[][] matrix1, matrix2;
    private String targetFile;

    public P2() {
    }

    public P2(String targetFile) {
        this.targetFile = targetFile;
    }

    @Override
    public void generateMatrixAndSaveToFile() {
        System.out.println("Генерация матриц и сохранение их в файл " + this.targetFile);
    }
}
