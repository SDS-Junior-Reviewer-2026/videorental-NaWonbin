package com.videorental;

public class ChildrensMovie extends Movie {
    public ChildrensMovie(String title) {
        super(title, Movie.CHILDRENS);
    }

    @Override
    public double getChargeFor(int daysRented) {
        double thisAmount = 1.5;
        if (daysRented > 3)
            thisAmount += (daysRented - 3) * 1.5;
        return thisAmount;
    }
}