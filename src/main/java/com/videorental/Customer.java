package com.videorental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
        return setStatementHeader() + getRentalLineReport() + getStatementFooter();
	}

    private String getStatementFooter() {
        return "Amount owed is " + getTotalAmount() + "\n" +
                "You earned " + getFrequentRenterPoints() + " frequent renter pointers";
    }

    private String setStatementHeader() {
        return "Rental Record for " + getName() + "\n";
    }

    private String getRentalLineReport() {
        return rentals.stream()
                .map(rental -> "\t" +  String.valueOf(rental.getMovie().getChargeFor(rental.getDaysRented())) + "(" + rental.getMovie().getTitle() + ")" + "\n")
                .collect(Collectors.joining());
    }

    private int getFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt((rental) -> (rental.getMovie().getFrequentRenterPointsFor(rental.getDaysRented())))
                .sum();
    }

    private double getTotalAmount() {
        return rentals.stream()
                .mapToDouble((rental) -> (rental.getMovie().getChargeFor(rental.getDaysRented())))
                .sum();

    }

}