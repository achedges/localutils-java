package com.adamhedges.utilities;

import com.adamhedges.utilities.linalg.Matrix;
import org.junit.Assert;
import org.junit.Test;

public class TestMatrixMultiplier {

    @Test
    public void testMatrix_initEmpty() {
        Matrix empty = Matrix.initEmpty(2, 2);
        Assert.assertEquals(2, empty.n);
        Assert.assertEquals(2, empty.m);
        for (int r = 0; r < empty.n; r++) {
            for (int c = 0; c < empty.m; c++) {
                Assert.assertEquals(0.0, empty.at(r, c).orElse(-1.0), 0.0);
            }
        }
    }

    @Test
    public void testMatrix_initRand() {
        Matrix rand = Matrix.initRand(2, 2);
        Assert.assertEquals(2, rand.n);
        Assert.assertEquals(2, rand.m);
        for (int r = 0; r < rand.n; r++) {
            for (int c = 0; c < rand.m; c++) {
                Assert.assertNotEquals(0.0, rand.at(r, c).orElse(-1.0), 0.0);
            }
        }
    }

    @Test
    public void testMatrix_multiply() {
        Matrix a = Matrix.initFrom(new double[][] {
            new double[] {1, 5},
            new double[] {2, 3},
            new double[] {1, 7}
        });

        Matrix b = Matrix.initFrom(new double[][] {
            new double[] {1, 2, 3, 7},
            new double[] {5, 2, 8, 1}
        });

        double[][] expected = {
            new double[] {26, 12, 43, 12},
            new double[] {17, 10, 30, 17},
            new double[] {36, 16, 59, 14}
        };

        Matrix result = a.multiply(b);
        Assert.assertEquals(expected.length, result.n);
        Assert.assertEquals(expected[0].length, result.m);
        for (int er = 0; er < expected.length; er++) {
            for (int ec = 0; ec < expected[0].length; ec++) {
                Assert.assertEquals(expected[er][ec], result.at(er, ec).orElse(-1.0), 0.0);
            }
        }
    }

    @Test
    public void testMatric_multiply_vectors() {
        Matrix a = Matrix.initFrom(new double[][] {
            new double[] {1, 2, 3, 4, 5}
        });

        Matrix b = Matrix.initFrom(new double[][] {
            new double[] {6},
            new double[] {7},
            new double[] {8},
            new double[] {9},
            new double[] {10}
        });

        double[][] expected = new double[][] {
            new double[] {130}
        };

        Matrix result = a.multiply(b);
        Assert.assertEquals(expected.length, result.n);
        Assert.assertEquals(expected[0].length, result.m);
        for (int ec = 0; ec < expected[0].length; ec++) {
            Assert.assertEquals(expected[0][ec], result.at(0, ec).orElse(-1.0), 0.0);
        }
    }

    @Test
    public void testMatrix_transpose() {
        Matrix a = Matrix.initFrom(new double[][] {
            new double[] {5, 6},
            new double[] {7, 8},
            new double[] {9, 10}
        });

        double[][] expected = new double[][] {
            new double[] {5, 7, 9},
            new double[] {6, 8, 10}
        };

        Matrix t = a.transpose();
        Assert.assertEquals(2, t.n);
        Assert.assertEquals(3, t.m);
        for (int er = 0; er < expected.length; er++) {
            for (int ec = 0; ec < expected[0].length; ec++) {
                Assert.assertEquals(expected[er][ec], t.at(er, ec).orElse(-1.0), 0.0);
            }
        }
    }

}
