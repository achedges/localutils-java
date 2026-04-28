package com.adamhedges.utilities.neuralnet;

import com.adamhedges.utilities.linalg.Matrix;

public class Perceptron {

    private final Matrix weights;
    private final double bias;

    public Perceptron(Matrix initWeights, double bias) {
        weights = initWeights;
        this.bias = bias;
    }

    public double activate(Matrix input) {
        Matrix result = input.dot(weights);
        double value = result.at(0, 0).orElse(0d) + bias;
        return value > 0 ? 1 : -1;
    }

}
