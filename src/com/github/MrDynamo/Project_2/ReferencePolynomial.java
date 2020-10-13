package com.github.mrdynamo.Project_2;

public class ReferencePolynomial implements Polynomial {

    /** Class Variables **/

    private Node dummy = new Node(null, null, null);
    private Node head;
    private int size = 0;

    /** Class Functions **/

    // Default constructor
    public ReferencePolynomial() {
        dummy.next = head;
    }

    @Override
    public int degree() {
        // Return first node, should be highest degree
        if (head != null) {
            return head.power;
        }

        // Else return 0 power
        return 0;
    }

    @Override
    public double getCoefficient(int power) throws ExponentOutOfRangeException {
        if (head != null) {
            do {
                if (head.power == power)
                    return head.coefficient;
                else {
                    try {
                        head = head.next;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            } while (head != null);
        }

        resetHead();
        return 0.0;
    }

    @Override
    public void setCoefficient(double newCoefficient, int power) throws ExponentOutOfRangeException {
        if (power > 100)
            throw new ExponentOutOfRangeException("Maximum power cannot exceed 100");

        // If empty LL, create new node after dummy head
        if (head == null) {
            head = new Node(newCoefficient, power, null);
            dummy.next = head;
            size++;
        }
        // Else traverse LL and add/modify node, then resetHead - FIX
        else {
            do {
                // If newPower > next node power, add new node before
                if (power > head.power) {
                    Node tmp = new Node(newCoefficient, power, head);
                    head = tmp;
                } // If node with power exists, modify coefficient
                else if (power == head.power) {
                        head.coefficient = newCoefficient;
                } // Else move head to next, continue do-while loop
                else {
                    try {
                        head = head.next;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            } while (head != null);
        }
        resetHead();
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
        // Declare and initialize display strings
        String display = "", tmp = "";

        // Implement display -- Traverse LL and add each coefficient & power to display
        while (head != null) {
            // If nothing has been added to display yet, don't add the + to the end of display
            if (display.isBlank()) {
                if (head.power == 0)
                    display = Double.toString(head.coefficient);
                else
                    display = head.coefficient + "x^" + head.power;
            } else {
                tmp = head.coefficient + "x^" + head.power + " + ";
                display = tmp + display;
            }
            // Set head to next node in LL
            try {
                head = head.next;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }

        // Display result if not blank
        if (!display.isBlank())
            System.out.println(display);
            // Else display 0.0
        else
            System.out.println("0.0");

        resetHead();
    }
    
    private void resetHead() {
        head = dummy.next;
    }

    private class Node {

        /** Class Variables **/
        Double coefficient;
        Integer power;
        Node next;

        /** Class Methods **/

        // Default constructor
        public Node(Double coeff, Integer pow, Node nxt) {
            coefficient = coeff;
            power = pow;
            next = nxt;
        }

        //

    }

}
