package model;

import java.util.Random;

public class Company {
	private int id;
	private int shares;
	private int sharePrice;
	private int capital;
	private int sharesSold;
	private int sellingShares;
	
	public Company(int id) {
		this.id = id;
		this.shares = generateShares();
		this.sharePrice = generateSharePrice();
		this.sharesSold = 0;
		this.capital = 0;
		this.sellingShares = 0;
	}
	
	
	
	
	
	public int getSharesSold() {
		return sharesSold;
	}

	public int getSellingShares() {
		return this.sellingShares;
	}

	public void resetSellingShares() {
		this.sellingShares = 0;
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
		return "\tCompany ID: "+id+"\n " +
				"\tCapital: "+capital+"\n " +
				"\tShares available: "+shares + "\n " +
				"\tShare price: "+sharePrice + "\n " +
				"\tShares sold: " +sharesSold;
	}

	public void sellShare() {
		shares--;
		sharesSold++;
		sellingShares++;
		this.capital = this.capital + this.sharePrice;
		
		if(sellingShares==10) {

			this.sharePrice = sharePrice*2;
			this.sellingShares=0;
		}
	}
	
	public void reduceSharePrice() {

		this.sharePrice = (int)  (sharePrice*0.98);
		if(this.sharePrice==0){
			//min share price = 1
			this.sharePrice = 1;
		}

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
