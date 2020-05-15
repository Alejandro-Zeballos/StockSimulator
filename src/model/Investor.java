package model;

import model.observer.Observer;
import model.observer.subject.Subject;
import utils.TypeUpdate;
import utils.Utils;

import java.util.List;


public class Investor implements Observer {
	private int id;
	private int shares;
	private int budget;
	private List<Company> companiesAvailable;
	private Subject companiesState;
	
	public Investor(int id, List<Company> companies) {
		this.id = id;
		this.budget = Utils.generateRandomNumber(1000, 10000);
		this.shares = 0;
		this.companiesAvailable = companies;
	}

	public List<Company> getCompaniesAvailable(){
		return  this.companiesAvailable;
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
				"\tShares acquired: "+shares+"\n";
	}


	//this update method will update the local list of companies
	@Override
	public void update(Subject sub) {
		TypeUpdate updateType =  sub.getUpdate().getTypeUpdate();
		Company updatedCompany = (Company) sub.getUpdate().getObj();

		switch (updateType){
			case SHARE_PRICE_CHANGED:
				//if the update type is price change, will update company price
				for(Company company: companiesAvailable){
					if(company.getId() == updatedCompany.getId()){
						company.setSharePrice(updatedCompany.getSharePrice());
					}
				}
				break;
			case NO_SHARES:
				//if the case is no_shares, it will remove this company from the pool of companies
				companiesAvailable.removeIf(company -> company.getId() == updatedCompany.getId());
			break;
		}
	}

	@Override
	public void setSubject(Subject sub) {
		this.companiesState = sub;
	}
}
