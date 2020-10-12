// ********************************************************
// ExponentOutOfRangeException class for the ADT Polynomial.
// By Chi-Cheng Lin
// Date: 09/07/2020
// ********************************************************

package com.github.mrdynamo.Project_2;

public class ExponentOutOfRangeException
        extends IndexOutOfBoundsException {
    public ExponentOutOfRangeException(String s) {
        super(s);
    }  // end constructor
}  // end ExponentOutOfRangeException