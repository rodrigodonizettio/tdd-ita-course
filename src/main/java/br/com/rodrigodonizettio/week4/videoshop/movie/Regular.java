package br.com.rodrigodonizettio.week4.videoshop.movie;

public class Regular extends Movie {
    public Regular(String title) {
        super(title);
    }

    @Override
    public double getAmount(int daysRented) {
        double thisAmount = 2;
        if(daysRented > 2) {
            thisAmount += (daysRented - 2) * 1.5;
        }
        return thisAmount;
    }
}
