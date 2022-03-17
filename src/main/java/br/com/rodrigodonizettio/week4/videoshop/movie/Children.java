package br.com.rodrigodonizettio.week4.videoshop.movie;

public class Children extends Movie {
    public Children(String title) {
        super(title);
    }

    @Override
    public double getAmount(int daysRented) {
        double thisAmount = 1.5;
        if(daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}
