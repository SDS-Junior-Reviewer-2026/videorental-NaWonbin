package com.videorental;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CustomerTest {

    public static final String NAME = "NAME_NOT_IMPORTANT";
    public static final String TITLE = "TITLE_NOT_IMPORTANT";
    Customer customer = new Customer(NAME);

    private static Rental createRentalFor(int priceCode, int daysRented) {
        Movie movie = getMovie(priceCode);
        Rental rental = new Rental(movie, daysRented);
        return rental;
    }

    private static Movie getMovie(int priceCode) {
        switch (priceCode) {
            case Movie.REGULAR:
                return new RegularMovie(TITLE);
            case Movie.NEW_RELEASE:
                return new NewReleaseMovie(TITLE);
            case Movie.CHILDRENS:
                return new ChildrensMovie(TITLE);
            default:
                return null;
        }
    }

    private static Movie setMovie(Movie movie) {
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                return new RegularMovie(TITLE);
            case Movie.NEW_RELEASE:
                return new NewReleaseMovie(TITLE);
            case Movie.CHILDRENS:
                return new ChildrensMovie(TITLE);
            default:
                return null;
        }
    }

    @Test
    void returnNewCustomer() {
        assertThat(customer).isNotNull();

    }

    @Test
    void statementForNoRental() {
        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter pointers");
    }

    @Test
    void statementForRegularMovieRentalForLessThan3Days() {
        // arange
        customer.addRental(createRentalFor(Movie.REGULAR, 2));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t2.0(" + TITLE + ")\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter pointers");
    }

    @Test
    void statementForRegularMovieRentalForMoreThan2Days() {
        // arange
        customer.addRental(createRentalFor(Movie.REGULAR, 3));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t3.5(" + TITLE + ")\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter pointers");
    }

    @Test
    void statementForNewReleaseMovie() {
        // arange
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 1));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t3.0(" + TITLE + ")\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers");
    }

    @Test
    void statementForChildrensMovieRentalMoreThan3Days() {
        // arange
        customer.addRental(createRentalFor(Movie.CHILDRENS, 4));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t3.0(" + TITLE + ")\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter pointers");
    }

    @Test
    void statementForChildrensMovieRentalMoreThan4Days() {
        // arange
        customer.addRental(createRentalFor(Movie.CHILDRENS, 3));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t1.5(" + TITLE + ")\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter pointers");
    }

    @Test
    void statementForNewReleaseMovieRentalMoreThan1Day() {
        // arange
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 2));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t6.0(" + TITLE + ")\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter pointers");
    }

    @Test
    void statementForFewMovieRental() {
        // arange
        customer.addRental(createRentalFor(Movie.REGULAR, 1));
        customer.addRental(createRentalFor(Movie.NEW_RELEASE, 4));
        customer.addRental(createRentalFor(Movie.CHILDRENS, 4));

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t2.0(" + TITLE + ")\n" +
                "\t12.0(" + TITLE + ")\n" +
                "\t3.0(" + TITLE + ")\n" +
                "Amount owed is 17.0\n" +
                "You earned 4 frequent renter pointers");
    }

    @Test
    void useSetPriceCode() {
        // arange
        Movie movie = getMovie(Movie.REGULAR);
        movie.setPriceCode(Movie.NEW_RELEASE);
        movie = setMovie(movie);
        Rental rental = new Rental(movie, 10);
        customer.addRental(rental);

        // assert
        assertThat(customer.statement()).isEqualTo("Rental Record for " + NAME + "\n" +
                "\t30.0(" + TITLE + ")\n" +
                "Amount owed is 30.0\n" +
                "You earned 2 frequent renter pointers");
    }


}
