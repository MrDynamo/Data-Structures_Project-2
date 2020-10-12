package com.github.mrdynamo.Project_2;

public class ReferencePolynomial implements Polynomial {


    @Override
    public int degree() {
        return 0;
    }

    @Override
    public double getCoefficient(int power) throws ExponentOutOfRangeException {
        return 0;
    }

    @Override
    public void setCoefficient(double newCoefficient, int power) throws ExponentOutOfRangeException {

    }

    @Override
    public Polynomial add(Polynomial p) {
        return null;
    }

    @Override
    public Polynomial mult(Polynomial p) throws ExponentOutOfRangeException {
        return null;
    }

    @Override
    public void mult(double scalar) {

    }

    @Override
    public double evaluate(double x) {
        return 0;
    }

    @Override
    public void displayPolynomial() {

    }
}
