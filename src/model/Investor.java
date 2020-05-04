package model;

import java.util.Random;

public class Investor {
	private int id;
	private int shares;
	private int budget;
	
	public Investor(int id) {
		this.id = id;
		this.budget = generateBudget();
		this.shares = 0;
	}

	
	public int getShares() {
		return this.shares;
	}

	public void addShare(){
		this.shares++;
	}
	
	public int getId() {
		return id;
	}
	
	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget){
		this.budget = budget;
	}
	
	
	@Override
	public String toString() {
		return "\tInvestor ID: "+id+"\n " +
				"\tBudget: "+budget+"\n " +
				"\tShares acquired: "+shares;
	}
	
	private int generateBudget() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(	10000 - 1000 + 1) + 1000;
	}
}
