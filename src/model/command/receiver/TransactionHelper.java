package model.command.receiver;

import model.Company;
import model.Investor;
import model.observer.subject.CompaniesTransactions;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TransactionHelper implements TransactionReceiver {

    private CompaniesTransactions companiesTransactions;
    private int sharesSold;
    private List<Company> companiesSeller;

    public TransactionHelper(){
        this.companiesTransactions = CompaniesTransactions.getInstance();
        companiesSeller = new ArrayList<>();
        sharesSold = 0;
    }

    //This method will be in charge of the selling - buying logic
    @Override
    public void buyShare(Investor investor) {
        //retrieving companies available to sell
        List<Company> companiesAvailable = investor.getCompaniesAvailable();
        Company companySeller = getRandomCompany(companiesAvailable, investor.getBudget());
        //will return if havent found suitable company to buy from (budget wise)
        if(companySeller == null){
            return;
        }
        //Updating budget after buying a share
        investor.setBudget(investor.getBudget() - companySeller.getSharePrice());
        investor.addShare();
        companiesTransactions.sellShare(companySeller);
        companiesSeller.add(companySeller);
        //keeping track of investor budget
        if(investor.getBudget() < 1){
            companiesTransactions.unregister(investor);
        }

        //keeping track of shares Sold for reducing prices of companies that didnt sell
        sharesSold++;
        if(sharesSold == 10){
            companiesTransactions.reducePrice(companiesSeller);
            companiesSeller = new ArrayList<>();
            sharesSold = 0;
        }
    }


    //This method will return a random company but that suits investor budget

    private Company getRandomCompany(List<Company> companyList, int investorBudget) {

        List<Company> companiesCandidate = new ArrayList<>();
        for(Company company: companyList) {
            if(company.getSharePrice()<=investorBudget) {
                companiesCandidate.add(company);
            }
        }
        if(companiesCandidate.size()==0) {
            return null;
        }
        int companyIndex = Utils.generateRandomNumber(0,companiesCandidate.size());
        return companiesCandidate.get(companyIndex);
    }




}
