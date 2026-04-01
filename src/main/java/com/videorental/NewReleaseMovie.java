package com.videorental;

public class NewReleaseMovie extends Movie {
    public NewReleaseMovie(String title) {
        super(title, Movie.NEW_RELEASE);
    }

    @Override
    public double getChargeFor(int daysRented) {
        return daysRented * 3;
    }
}