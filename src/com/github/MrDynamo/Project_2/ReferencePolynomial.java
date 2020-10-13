package com.github.mrdynamo.Project_2;

public class ReferencePolynomial implements Polynomial {

    /** Class Variables **/

    private Node dummy, head;

    /** Class Functions **/

    // Default constructor
    public ReferencePolynomial() {
        // Dummy head
        dummy = new Node(null, null, null);
    }

    @Override
    public int degree() {
        // Return first node, should be highest degree
        if (head.next != null) {
            return head.next.power;
        }

        // Else return dummy head power (0)
        return head.power;
    }

    @Override
    public double getCoefficient(int power) throws ExponentOutOfRangeException {
        if (head.next == null) {
            return head.coefficient;
        }
        else {
            do {
                head = head.next;
                if (head.power == power)
                    return head.coefficient;
            } while(head.next != null);

            /*
            while (head.power != power) {
                head = head.next;
                if (head.power == power)
                    return head.coefficient;
                if (head.next == null)
                    return 0.0;
            }
             */
        }

        resetHead();
        return head.coefficient;
        //throw new ExponentOutOfRangeException("No coefficient exists for that power");
    }

    @Override
    public void setCoefficient(double newCoefficient, int newPower) throws ExponentOutOfRangeException {
        if (newPower > 100)
            throw new ExponentOutOfRangeException("Maximum power cannot exceed 100");

        // If empty LL, create new node after and set dummy head next
        if (head.next == null) {
            dummy.next = new Node(newCoefficient, newPower, null);
            head = dummy.next;
        }
        // Else traverse LL and add/modify node, then resetHead
        else {
            do {
                // If node with power exists, modify coefficient
                if (head.next.power == newPower) {
                    head.next.coefficient = newCoefficient;
                } // Else continue
                else {
                    // If newPower > next node power, add new node before
                    if (newPower > head.next.power) {
                        Node tmp = new Node(newCoefficient, newPower, head.next);
                        head.next = tmp;
                    }
                }
                // Move head to next, continue do-while loop
                head = head.next;
            } while (head.next != null);
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

        System.out.println("Debug 1");
        // Implement display -- Traverse LL and add each coefficient & power to display
        while (head.next != null) {
            System.out.println("Debug 2");
            // If nothing has been added to display yet, don't add the + to the end of display
            if (display.isBlank()) {
                if (head.next.power == 0)
                    display = Double.toString(head.next.coefficient);
                else
                    display += head.next.coefficient + "x^" + head.next.power;
            } else {
                tmp = head.next.coefficient + "x^" + head.next.power + " + ";
                display = tmp + display;
            }
            // Set head to next node in LL
            head = head.next;
        }

        // Display result if not blank
        if (!display.isBlank())
            System.out.println(display);
            // Else display 0.0
        else
            System.out.println("Debug 3");
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
