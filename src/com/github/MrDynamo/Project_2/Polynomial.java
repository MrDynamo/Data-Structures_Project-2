// ********************************************************
// Interface PolynomialInterface for the ADT Polynomial.
// By Chi-Cheng Lin
// Date: 09/07/2020
// *********************************************************

package com.github.mrdynamo.Project_2;

public interface Polynomial {
    int degree();
    // Determines the degree of a polynomial.
    // Precondition: None.
    // Postcondition: Returns the degree of a polynomial.
    // Throws: None.

    double getCoefficient(int power)
            throws ExponentOutOfRangeException;
    // Gets the coefficient of the x to the power term.
    // Precondition: power is the power of the term wanted.
    // Postcondition: If power is not out of range, the
    // coefficient of the x to the power term is returned.
    // Throws: ExponentOutOfRangeException if exponent is out of range.

    void setCoefficient(double newCoefficient, int power)
            throws ExponentOutOfRangeException;
    // Replaces the coefficient of the x to the power term with
    // newCoefficient.
    // Precondition: power indicates which term the coefficient
    // should be replaced.
    // Postcondition: If power is not of out range, the coefficient
    // of the x to the power term will be set to newCoefficent.
    // Throws: ExponentOutOfRangeException if exponent is out of range.

    Polynomial add(Polynomial p);
    // Adds polynomial p to this polynomial without modifying this
    // polynomial and returns the result.
    // Precondition: None.
    // Postcondition: The returned polynomial is the sum of this
    // and p.  Both this and p are unchanged.

    Polynomial mult(Polynomial p)
            throws ExponentOutOfRangeException;
    // Multiplies polynomial p to this polynomial without modifying this
    // polynomial and returns the result.
    // Precondition: None.
    // Postcondition: The returned polynomial is the product of this
    // and p.  Both this and p are unchanged.
    // Throws: ExponentOutOfRangeException if exponent is out of range.

    void mult(double scalar);
    // Multiplies a scalar to this polynomial.
    // Precondition: None.
    // Postcondition: All the coefficients in this polynomial is
    // multiplied by scalar.
    // Throws: None.

    double evaluate(double x);
    // Evaluates the polynomial at x.
    // Precondition: None.
    // Postcondition: None.
    // Throws: None.

    void displayPolynomial();
    // Display the polynomial.
    // Precondition: None.
    // Postcondition: None.
    // Throws: None.

}  // end Polynomial