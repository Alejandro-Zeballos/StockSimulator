package model;

import utils.Utils;

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
		this.shares = Utils.generateRandomNumber(500, 1000);
		this.sharePrice = Utils.generateRandomNumber(10, 100);
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
				"\tShares sold: " +sharesSold + "\n";
	}

	public void sellShare() {
		shares--;
		sharesSold++;
		sellingShares++;
		this.capital = this.capital + this.sharePrice;
	}
	
	public void reduceSharePrice() {

		this.sharePrice = (int)  (sharePrice*0.98);
		if(this.sharePrice<1){
			//min share price = 1
			this.sharePrice = 1;
		}

	}

}
