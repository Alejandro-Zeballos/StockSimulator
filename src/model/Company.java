package model;

import java.util.Random;

public class Company {
	private int id;
	private int shares;
	private int sharePrice;
	private int capital;
	
	public Company(int id) {
		this.id = id;
		this.shares = generateShares();
		this.sharePrice = generateSharePrice();
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getShares() {
		return shares;
	}



	public void setShares(int shares) {
		this.shares = shares;
	}



	public int getSharePrice() {
		return sharePrice;
	}



	public void setSharePrice(int sharePrice) {
		this.sharePrice = sharePrice;
	}



	public int getCapital() {
		return capital;
	}



	public void setCapital(int capital) {
		this.capital = capital;
	}

	@Override
	public String toString() {
		return "Company id: "+id+" Capital: "+capital+" Shares: "+shares + " Share price: "+sharePrice;
	}


	private int generateSharePrice() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(	100 - 10 + 1) + 10;
	}
	private int generateShares() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(	1000 - 500 + 1) + 500;
	}
}
