package model;

import java.util.Random;

public class Investor {
	private int id;
	private int budget;
	private int shares;
	
	public Investor(int id) {
		this.id = id;
		this.budget = generateBudget();
		this.shares = 0;
	}
	
	@Override
	public String toString() {
		return "Investor id: "+id+" Budget: "+budget+" Shares: "+shares;
	}
	
	private int generateBudget() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(	10000 - 1000 + 1) + 1000;
	}
}
