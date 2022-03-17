package br.com.rodrigodonizettio.week4.videoshop;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
    private String name;
    private Vector<Rental> rentals = new Vector<>();
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
        Enumeration<Rental> currentRentals = this.rentals.elements();
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ").append(getName()).append("\n");
        while (currentRentals.hasMoreElements()) {
            Rental each = currentRentals.nextElement();
            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(each.getAmount()).append("\n");
        }
        result.append("Amount owed is ").append(getTotalAmount()).append("\n").append("You earned ")
                .append(getTotalFrequentRenterPoints()).append(" frequent renter points");
        return result.toString();
    }

    public int getTotalFrequentRenterPoints() {
        return frequentRenterPoints;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
}
