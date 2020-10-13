package com.github.mrdynamo.Project_2;

public class ReferencePolynomial implements Polynomial {

    /** Class Variables **/

    private Node head, curr, temp;
    private int size = 0;

    /** Class Functions **/

    // Default constructor
    public ReferencePolynomial() {
        head = new Node(null, null, null);
    }

    // Displays degree of this polynomial
    @Override
    public int degree() {
        // Return first node, should be highest degree
        if (head.next != null)
            return head.next.power;

        // Else return 0 power
        return 0;
    }

    // Gets coefficient of this polynomial
    @Override
    public double getCoefficient(int power) throws ExponentOutOfRangeException {
        curr = head;
        while (curr.next != null) {
            curr = curr.next;
            if (curr.power == power)
                return curr.coefficient;
        }

        return 0.0;
    }

    // Sets coefficient of this polynomial
    @Override
    public void setCoefficient(double newCoefficient, int power) throws ExponentOutOfRangeException {
        curr = head;

        // Throw exception
        if (power > 100)
            throw new ExponentOutOfRangeException("Maximum power cannot exceed 100");

        // Dont add new node if coefficient is 0
        if (newCoefficient == 0.0)
            return;

        // If empty LL, create new node after dummy head
        if (curr.next == null) {
            curr = new Node(newCoefficient, power, null);
            head.next = curr;
            size++;
        }
        // Else traverse LL and add/modify node, then resetHead - FIX
        else {
            while (curr.next != null) {
                curr = curr.next;
                // If newPower > next node power, add new node before
                if (power > curr.power) {
                    temp = new Node(newCoefficient, power, curr);
                    curr = temp;
                    head.next = curr;
                    size++;
                    break;
                } // If node with power exists, modify coefficient
                else if (power == curr.power) {
                    curr.coefficient = newCoefficient;
                    size++;
                    break;
                } // If newPower < power, add node after
                else if (power < curr.power) {
                    temp = new Node(newCoefficient, power, curr.next);
                    curr.next = temp;
                    size++;
                    break;
                }
                else
                    // Finish implementation
                    curr = curr.next;
            }
        }
    }

    // Implement
    @Override
    public Polynomial add(Polynomial p) {
        return null;
    }

    // Implement
    @Override
    public Polynomial mult(Polynomial p) throws ExponentOutOfRangeException {
        return null;
    }

    // Implement
    @Override
    public void mult(double scalar) {

    }

    // Implement recursively
    @Override
    public double evaluate(double x) {
        return 0;
    }

    // Displays polynomial information
    @Override
    public void displayPolynomial() {
        // Declare and initialize display strings and variables
        String display = "", tmp = "";
        curr = head;

        // Implement display -- Traverse LL and add each coefficient & power to display
        while (curr != null) {
            // If nothing has been added to display yet, don't add the + to the end of display
            if (display.isBlank()) {
                if (curr.power == null) {

                }
                else {
                    if (curr.power == 0)
                        display = "" + curr.coefficient;
                    else
                        if (curr.coefficient == 0.0)
                            display = "x^" + curr.power;
                        else
                            display = curr.coefficient + "x^" + curr.power;
                }
            } else {
                if (curr.power == 0)
                    tmp = " + " + curr.coefficient;
                else {
                    if (curr.coefficient == 0.0)
                        tmp = " + x^" + curr.power;
                    else
                        tmp = " + " + curr.coefficient + "x^" + curr.power;
                }

                display += tmp;
            }
            // Set head to next node in LL
            try {
                curr = curr.next;
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
    }

    // Gets size of LinkedList
    public int getSize() {
        return size;
    }

    // Node class for LinkedList
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
    }

}
