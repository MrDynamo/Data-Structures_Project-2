package com.github.mrdynamo.Project_2;

public class ReferencePolynomial implements Polynomial {

    /** Class Variables **/

    private Node head, curr, temp, ecurr;
    private int size;

    /** Class Functions **/

    // Default constructor
    public ReferencePolynomial() {
        head = new Node(null, null, null);
        ecurr = head;
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

    // Gets power of this polynomial
    public Node getHead() {
        return head;
    }

    // Gets coefficient of this polynomial
    @Override
    public double getCoefficient(int power) throws ExponentOutOfRangeException {
        if (power > 100)
            throw new ExponentOutOfRangeException("Maximum power cannot exceed 100");

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
        ReferencePolynomial result = new ReferencePolynomial();
        ReferencePolynomial addition = (ReferencePolynomial) p;
        Node pHead = addition.getHead(), pCurr;
        curr = head;
        // Loop through this LinkedList
        while (curr.next != null) {
            curr = curr.next;
            pCurr = pHead;
            // Loop through parameter LinkedList
            while (pCurr.next != null) {
                pCurr = pCurr.next;
                if (curr.power == pCurr.power) {
                    result.setCoefficient(curr.coefficient + p.getCoefficient(curr.power),  curr.power);
                }
                else if (pCurr.power > curr.power) {
                    // TODO: Fix this error
                    if (result.getCoefficient(pCurr.power) != (curr.coefficient + p.getCoefficient(curr.power)) && result.getCoefficient(pCurr.power) != 0.0)
                        result.setCoefficient(pCurr.coefficient, pCurr.power);
                }
            }
        }

        return result;
    }

    // Multiplies this polynomial by Polynomial p, returns a Polynomial result without modifying originals
    @Override
    public Polynomial mult(Polynomial p) throws ExponentOutOfRangeException {
        Polynomial result = new ReferencePolynomial();
        ReferencePolynomial rp = (ReferencePolynomial) p;
        Node pHead = rp.getHead(), pCurr;
        curr = head;
        // Loop through this LinkedList
        while (curr.next != null) {
            curr = curr.next;
            pCurr = pHead;
            // Loop through parameter LinkedList
            while (pCurr.next != null) {
                pCurr = pCurr.next;
                result.setCoefficient(curr.coefficient * pCurr.coefficient, curr.power + pCurr.power);

            }
        }

        // Throw error
        if (result.degree() > 100)
            throw new ExponentOutOfRangeException("Maximum power cannot exceed 100");

        return result;
    }

    // Multiplies this polynomial by a scalar and modifies this
    @Override
    public void mult(double scalar) {
        curr = head;
        Node prev = curr;
        // Loop through LinkedList
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
            }
            else
                prev = curr;
        }

    }

    // Evaluates the polynomial at x recursively
    @Override
    public double evaluate(double x) {
        double result = 0.0;

        // Loop through LinkedList
        if (ecurr.next != null) {
            ecurr = ecurr.next;
            result = ecurr.coefficient * Math.pow(x, ecurr.power);
            return result + evaluate(x);
        }
        else {
            // Reset head for next usage of function
            ecurr = head;
            return result;
        }
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
        size = 0;
        curr = head;
        while (curr.next != null) {
            curr = curr.next;
            size++;
        }
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
