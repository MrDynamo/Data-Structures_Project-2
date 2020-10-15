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

        // If empty LL, create new node after dummy head
        if (curr.next == null) {
            temp = new Node(newCoefficient, power, null);
            curr.next = temp;
            size++;
        }
        // Else traverse LL and add/modify node, then resetHead - FIX
        else {
            while (curr.next != null) {
                Node prev = curr;
                curr = curr.next;

                // If node with power exists
                if (power == curr.power) {
                    // If 0, delete node
                    if (newCoefficient == 0) {
                        // Delete node, update references
                        prev.next = curr.next;
                        size--;
                    } // Else modify coeff
                    else {
                        curr.coefficient = newCoefficient;
                    }
                    break;
                } // If newPower > current power, add before
                else if (power > curr.power) {
                    if (newCoefficient == 0) {
                        // do nothing
                    }
                    else {
                        temp = new Node(newCoefficient, power, curr);
                        curr = temp;
                        prev.next = curr;
                        break;
                    }
                }
                else {
                    // Do nothing
                }
            }
            // Add to end of list
            if (curr.next == null && newCoefficient != 0) {
                temp = new Node(newCoefficient, power, null);
                curr.next = temp;
            }
        }
    }

    // Implement more
    @Override
    public Polynomial add(Polynomial p) {
        Polynomial result = new ReferencePolynomial();
        curr = head;
        while (curr.next != null) {
            curr = curr.next;
            result.setCoefficient(curr.coefficient + p.getCoefficient(curr.power),  curr.power);
        }

        // Implement

        return result;
    }

    // Implement
    @Override
    public Polynomial mult(Polynomial p) throws ExponentOutOfRangeException {
        Polynomial result = new ReferencePolynomial();
        curr = head;
        while (curr.next != null) {
            curr = curr.next;
            // Implement
            // Multiply, exponents are added together, coefficients are multiplied
            // result.setCoefficient = multiplication
        }

        // Implement

        return result;
    }

    // Multiplies this polynomial by a scalar and modifies this
    @Override
    public void mult(double scalar) {
        curr = head;
        Node prev = curr;
        // Loop
        while (curr.next != null) {
            curr = curr.next;

            try {
                if (prev.coefficient != null && prev.coefficient == 0.0)
                    prev = curr;
            } catch (NullPointerException e) {
                //e.printStackTrace();
            }

            // Multiplication
            curr.coefficient = curr.coefficient * scalar;
            if (curr.coefficient == 0) {
                // Delete node
                prev.next = curr.next;
                size--;
            }
            else
                prev = curr;
        }
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
