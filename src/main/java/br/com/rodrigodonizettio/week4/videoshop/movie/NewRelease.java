package br.com.rodrigodonizettio.week4.videoshop.movie;

public class NewRelease extends Movie {
    public NewRelease(String title) {
        super(title);
    }

    @Override
    public double getAmount(int daysRented) {
        return daysRented * 3D;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        if(daysRented > 1) {
            return 2;
        }
        return 1;
    }
}
