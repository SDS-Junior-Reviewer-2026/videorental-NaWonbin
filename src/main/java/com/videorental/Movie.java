package com.videorental;

public abstract class Movie {
	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	
	private String title;
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int arg) {
		priceCode = arg;
	}

	public String getTitle() {
		return title;
	}

    public abstract double getChargeFor(int daysRented);
//    {
//        Movie movie;
//        if (getPriceCode() == REGULAR) {
//            movie = new RegularMovie(title);
//        } else if (getPriceCode() == NEW_RELEASE) {
//            movie = new NewReleaseMovie(title);
//        } else if (getPriceCode() == CHILDRENS) {
//            movie = new ChildrensMovie(title);
//        } else {
//            return 0;
//        }
//        return movie.getChargeFor(daysRented);
//
//    }

    public int getFrequentRenterPointsFor(int daysRented) {
        // add bonus for a two day new release rental
        if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) return 2;
        return 1;
    }
}