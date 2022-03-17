package br.com.rodrigodonizettio.week4.videoshop;

public class Movie {
    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = -1;

    private String title;
    private int priceCode;

    public static Movie createMovie(String title, int priceCode) {
        return new Movie(title, priceCode);
    }

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount(int daysRented) {
        double thisAmount = 0;
        switch (getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if(daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if(daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    public int getFrequentRenterPoints(int daysRented) {
        if((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
            return 2;
        return 1;
    }
}
