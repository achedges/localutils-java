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

    public Optional<Double> at(int row, int column) {
        if (row >= n || column >= m) {
            return Optional.empty();
        }

        return Optional.of(matrix[row][column]);
    }

    public Matrix multiply(Matrix other) {
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

}
