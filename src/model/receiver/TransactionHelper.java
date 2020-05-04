package model.receiver;

import model.Company;
import model.Investor;

import java.util.ArrayList;
import java.util.List;

public class TransactionHelper implements TransactionReceiver {
    @Override
    public void buyShare(Investor investor, Company company) {
        investor.setBudget(investor.getBudget() - company.getSharePrice());
        investor.addShare();
        company.sellShare();
    }

    @Override
    public void reduceSharesPrice(List<Company> companyList, List<Company> withSalesCompanyList) {

        int companiesAffected = 0;

        for(Company company: companyList){
            if(company.getSharesSold()==0){
                company.reduceSharePrice();
            }
        }
//        for(Company company: companyList){
//            if(!withSalesCompanyList.contains(company)){
//                company.reduceSharePrice();
//                companiesAffected++;
//            }
//        }


    }

}
