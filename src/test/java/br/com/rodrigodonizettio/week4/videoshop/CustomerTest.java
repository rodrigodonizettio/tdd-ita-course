package br.com.rodrigodonizettio.week4.videoshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerTest {
    Customer client;

    @BeforeEach
    void beforeEachTest() {
        client = new Customer("John");
    }

    @Test
    public void nameCreationTest() {
        String result = client.statement();

        assertTrue(result.contains("Rental Record for John"));
    }

    @Test
    public void oneRegularOneDayTest() {
        rentMovie("Indiana Jones", Movie.REGULAR, 1);
        String result = client.statement();

        assertTrue(result.contains("Amount owed is 2.0"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void oneRegularThreeDaysTest() {
        rentMovie("Indiana Jones", Movie.REGULAR, 3);
        String result = client.statement();

        assertTrue(result.contains("Amount owed is 3.5"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void oneChildrenOneDayTest() {
        rentMovie("Finding Nemo", Movie.CHILDREN, 1);
        String result = client.statement();

        assertTrue(result.contains("Amount owed is 1.5"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void oneChildrenFiveDaysTest() {
        rentMovie("Finding Nemo", Movie.CHILDREN, 5);
        String result = client.statement();

        assertTrue(result.contains("Amount owed is 4.5"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void oneNewReleaseOneDayTest() {
        rentMovie("The Batman", Movie.NEW_RELEASE, 1);
        String result = client.statement();

        assertTrue(result.contains("Amount owed is 3.0"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void oneNewReleaseThreeDaysTest() {
        rentMovie("The Batman", Movie.NEW_RELEASE, 3);
        String result = client.statement();

        assertTrue(result.contains("Amount owed is 9.0"));
        assertTrue(result.contains("You earned 2 frequent renter points"));
    }

    @Test
    public void manyRentsTest() {
        rentMovie("The Batman", Movie.NEW_RELEASE, 2);
        rentMovie("Top Gun: Maverick", Movie.NEW_RELEASE, 3);
        rentMovie("Finding Nemo", Movie.CHILDREN, 3);
        rentMovie("Indiana Jones", Movie.REGULAR, 2);
        rentMovie("Despicable Me", Movie.CHILDREN, 4);
        rentMovie("The Lord Of The Rings", Movie.REGULAR, 3);
        String result = client.statement();

        assertTrue(result.contains("Amount owed is 25.0"));
        assertTrue(result.contains("You earned 8 frequent renter points"));
    }

    private void rentMovie(String title, int type, int days) {
        Movie movie = new Movie(title, type);
        Rental rental = new Rental(movie, days);

        client.addRental(rental);
    }
}
