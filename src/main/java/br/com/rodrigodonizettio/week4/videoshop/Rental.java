package br.com.rodrigodonizettio.week4.videoshop;

import br.com.rodrigodonizettio.week4.videoshop.movie.Movie;

public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }

    public double getAmount() {
        return movie.getAmount(daysRented);
    }
}
