package com.adamhedges.utilities.neuralnet;

import com.adamhedges.utilities.linalg.Matrix;
import org.junit.Assert;
import org.junit.Test;

public class TestPerceptron {

    Matrix weights = Matrix.initFrom(new double[][] { new double[] {0.5}, new double[] {0.5} });
    Matrix negatedWeights = Matrix.initFrom(new double[][] { new double[] {-0.5}, new double[] {-0.5} });

    Matrix bothTrue = Matrix.initFrom(new double[][] { new double[] {1, 1}});
    Matrix leftTrue = Matrix.initFrom(new double[][] { new double[] {1, 0}});
    Matrix rightTrue = Matrix.initFrom(new double[][] { new double[] {0, 1}});
    Matrix noneTrue = Matrix.initFrom(new double[][] { new double[] {0, 0}});

    double andBias = -0.8;
    double orBias = -0.3;

    @Test
    public void testPerceptron_boolean_AND() {
        Perceptron and = new Perceptron(weights, andBias);
        Assert.assertEquals(1d, and.activate(bothTrue), 0.);
        Assert.assertEquals(-1d, and.activate(leftTrue), 0.);
        Assert.assertEquals(-1d, and.activate(rightTrue), 0.);
        Assert.assertEquals(-1d, and.activate(noneTrue), 0.);

        Perceptron nand = new Perceptron(negatedWeights, -andBias);
        Assert.assertEquals(-1d, nand.activate(bothTrue), 0.);
        Assert.assertEquals(1d, nand.activate(leftTrue), 0.);
        Assert.assertEquals(1d, nand.activate(rightTrue), 0.);
        Assert.assertEquals(1d, nand.activate(noneTrue), 0.);
    }

    @Test
    public void testPerceptron_boolean_OR() {
        Perceptron or = new Perceptron(weights, orBias);
        Assert.assertEquals(1d, or.activate(bothTrue), 0.);
        Assert.assertEquals(1d, or.activate(leftTrue), 0.);
        Assert.assertEquals(1d, or.activate(rightTrue), 0.);
        Assert.assertEquals(-1d, or.activate(noneTrue), 0.);

        Perceptron nor = new Perceptron(negatedWeights, -orBias);
        Assert.assertEquals(-1d, nor.activate(bothTrue), 0.);
        Assert.assertEquals(-1d, nor.activate(leftTrue), 0.);
        Assert.assertEquals(-1d, nor.activate(rightTrue), 0.);
        Assert.assertEquals(1d, nor.activate(noneTrue), 0.);
    }

    @Test
    public void testPerceptron_boolean_XOR() {
        Perceptron or = new Perceptron(weights, orBias);
        Perceptron nand = new Perceptron(negatedWeights, -andBias);
        Perceptron xor = new Perceptron(weights, andBias);

        Matrix left_only = Matrix.initFrom(new double[][] {
            new double[] { or.activate(leftTrue), nand.activate(leftTrue) }
        });
        Matrix right_only = Matrix.initFrom(new double[][] {
            new double[] { or.activate(rightTrue), nand.activate(rightTrue) }
        });
        Matrix left_right = Matrix.initFrom(new double[][] {
            new double[] { or.activate(bothTrue), nand.activate(bothTrue) }
        });
        Matrix none = Matrix.initFrom(new double[][] {
            new double[] { or.activate(noneTrue), nand.activate(noneTrue) }
        });

        Assert.assertEquals(1d, xor.activate(left_only), 0.);
        Assert.assertEquals(1d, xor.activate(right_only), 0.);
        Assert.assertEquals(-1d, xor.activate(left_right), 0.);
        Assert.assertEquals(-1d, xor.activate(none), 0.);
    }

}
