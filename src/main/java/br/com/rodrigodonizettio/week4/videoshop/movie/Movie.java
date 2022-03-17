package br.com.rodrigodonizettio.week4.videoshop.movie;

public abstract class Movie {
    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = -1;

    private String title;

    public static Movie createMovie(String title, int priceCode) {
        if(priceCode == REGULAR) {
            return new Regular(title);
        }
        if(priceCode == NEW_RELEASE) {
            return new NewRelease(title);
        }
        if(priceCode == CHILDREN) {
            return new Children(title);
        }
        throw new RuntimeException(String.format("The priceCode %d doesn't exist!", priceCode));
    }

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract double getAmount(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
