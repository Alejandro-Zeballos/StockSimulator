package controller;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Investor;
import view.UserInterface;

public class StockTradeSimulator {

	private UserInterface userInterface;
	private List<Company> companyList;
	private List<Investor> investorList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StockTradeSimulator();
	}
	
	
	public StockTradeSimulator() {
		userInterface = new UserInterface();
	
		companyList = new ArrayList<Company>();
		investorList = new ArrayList<Investor>();
		
		//Generating companies
		for(int k = 1; k <= 100; k++) {
			companyList.add(new Company(k));
		}
		
		//Generating investors
		for(int k = 1; k <= 100; k++) {
			investorList.add(new Investor(k));
		}
		

		
		simulateTradeDay();
		
	}
	
	private void simulateTradeDay() {
		userInterface.printMessage(companyList.get(80).toString());
		userInterface.printMessage(investorList.get(60).toString());
	}
	
	

}
