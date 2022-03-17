package br.com.rodrigodonizettio.week4.videoshop;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector rentals = new Vector();
    private int frequentRenterPoints = 0;
    private double totalAmount = 0;

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
        totalAmount += rental.getAmount();
        frequentRenterPoints += rental.getFrequentRenterPoints();
    }

    public String getName() {
        return name;
    }

    public String statement() {
        Enumeration rentals = this.rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += "\t" + each.getMovie().getTitle() + "\t"
                    + each.getAmount() + "\n";
        }
        result += "Amount owed is " + getTotalAmount() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints()
                + " frequent renter points";
        return result;
    }

    public int getTotalFrequentRenterPoints() {
        return frequentRenterPoints;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
}
