package com.adamhedges.utilities.linalg;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class Matrix {

    public final int n; // row dimension
    public final int m; // column dimension

    double[][] matrix;

    private Matrix(int numRows, int numColumns) {
        n = numRows;
        m = numColumns;
        matrix = new double[n][m];
    }

    public static Matrix initEmpty(int numRows, int numColumns) {
        return new Matrix(numRows, numColumns);
    }

    public static Matrix initRand(int rows, int cols) {
        Matrix newMatrix = Matrix.initEmpty(rows, cols);

        Random random = new Random();
        IntStream.range(0, newMatrix.n).forEach(r -> newMatrix.matrix[r] = random.doubles(cols).toArray());

        return newMatrix;
    }

    public static Matrix initFrom(double[][] array) {
        if (array.length == 0) {
            return new Matrix(0, 0);
        } else if (array[0].length == 0) {
            return new Matrix(array.length, 0);
        }

        Matrix newMatrix = Matrix.initEmpty(array.length, array[0].length);
        newMatrix.matrix = array;
        return newMatrix;
    }

    public boolean isSquare() {
        return n == m;
    }

    public Optional<Double> at(int row, int column) {
        if (row >= n || column >= m) {
            return Optional.empty();
        }

        return Optional.of(matrix[row][column]);
    }

    public Matrix dot(Matrix other) {
        if (other.n != this.m) {
            throw new RuntimeException(
                String.format("Matrices not aligned - this[%s][%s] -vs- other[%s][%s]", this.n, this.m, other.n, other.m)
            );
        }

        Matrix result = Matrix.initEmpty(this.n, other.m);
        for (int r = 0; r < this.n; r++) {
            for (int c = 0; c < other.m; c++) {
                for (int i = 0; i < this.m; i++) {
                    result.matrix[r][c] += (this.matrix[r][i] * other.matrix[i][c]);
                }
            }
        }

        return result;
    }

    public Matrix transpose() {
        Matrix result = Matrix.initEmpty(m, n);
        for (int r = 0; r < this.n; r++) {
            for (int c = 0; c < this.m; c++) {
                for (int i = 0; i < this.m; i++) {
                    result.matrix[c][r] = matrix[r][c];
                }
            }
        }
        return result;
    }

    public static Optional<Double> determinant(Matrix matrix) {
        if (!matrix.isSquare()) {
            return Optional.empty();
        }

        if (matrix.n == 1) {
            return matrix.at(0, 0);
        }

        if (matrix.n == 2) {
            double a = matrix.at(0, 0).orElse(0.0);
            double b = matrix.at(0, 1).orElse(0.0);
            double c = matrix.at(1, 0).orElse(0.0);
            double d = matrix.at(1, 1).orElse(0.0);
            return Optional.of((a * d) - (b * c));
        }

        double determinant = 0;
        for (int col = 0; col < matrix.m; col++) {
            Matrix subMatrix = Matrix.initEmpty(matrix.n - 1, matrix.m - 1);
            int currentRow = 0;
            int currentCol = 0;
            for (int r = 1; r < matrix.n; r++) {
                for (int c = 0; c < matrix.m; c++) {
                    if (c != col) {
                        subMatrix.matrix[currentRow][currentCol++] = matrix.matrix[r][c];
                    }
                }
                currentRow++;
                currentCol = 0;
            }

            int negation = col % 2 == 0 ? 1 : -1;
            determinant += matrix.matrix[0][col] * Matrix.determinant(subMatrix).orElse(0.0) * negation;
        }

        return Optional.of(determinant);
    }

}
