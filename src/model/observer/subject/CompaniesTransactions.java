package model.observer.subject;

import model.Company;
import model.observer.Observer;
import model.observer.subject.model.Update;
import utils.TypeUpdate;

import java.util.ArrayList;
import java.util.List;

public class CompaniesTransactions implements Subject {

    private List<Company> companyList;
    private List<Company> noSharesCompanyList;
    private List<Observer> observers;
    private boolean existChanges;
    private Update<Company> update;



    private static CompaniesTransactions single_instance = null;

    private CompaniesTransactions(){
        this.companyList = new ArrayList<>();
        observers = new ArrayList<>();
        existChanges = false;
        noSharesCompanyList = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Company to update: "+ update + " nro observers: " + observers.size();
    }

    public static CompaniesTransactions getInstance()
    {
        if (single_instance == null)
            single_instance = new CompaniesTransactions();


        return single_instance;
    }

    @Override
    public void setCompanies(List<Company> companyList) {
        this.companyList = companyList;
    }

    //This method will reduce price of the companies that are not in the received list and will notify so to Observers
    public void reducePrice(List<Company> withSalesCompanies){

        //looping all the companies that are left (with shares to sell)
        for(Company company: companyList) {
            if(!withSalesCompanies.contains(company)){
                //if a company is not in the list "withSalesCompanies" price will be reduced by 2%
                company.reduceSharePrice();
                updateChanges(company, TypeUpdate.SHARE_PRICE_CHANGED);
            }

        }

    }
    /*  This method will trigger method sellShare inside the company object and will check and update observers if
        this company have no shares left or if has duplicated its shares price.
        It will also update the local companyList
    */
    public void sellShare(Company company){

        for(Company c: companyList){
            if(c.getId() == company.getId()){
                c.sellShare();
                if(c.getShares()==0){

                    //moving company with no shares to a new List
                    noSharesCompanyList.add(c);
                    companyList.remove(c);
                    if(companyList.size()==0){
                        //if company list == 0 means all the shares have been left and therefor tradeDay must be stopped

                        tradeDayHasFinished();
                        updateChanges(company, TypeUpdate.NO_COMPANIES);

                    }else{
                        //updating changes and notifying observers
                        updateChanges(company, TypeUpdate.NO_SHARES);
                    }
                }else{
                    //if 10 shares was sold by the company, double up the share price and report it.
                    if(c.getSellingShares()==10){
                        c.setSharePrice(c.getSharePrice()*2);
                        c.resetSellingShares();
                        updateChanges(company, TypeUpdate.SHARE_PRICE_CHANGED);
                    }
                }

                return;
            }
        }
    }

    @Override
    public void register(Observer obj) {
        observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {

        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        if(!existChanges){
            return;
        }
        this.existChanges = false;
        for(Observer observer: this.observers){
            observer.update(this);
        }
    }

    @Override
    public Update getUpdate() {
        return update;
    }

    public void tradeDayHasFinished(){
        companyList.addAll(noSharesCompanyList);
    }



    private void updateChanges(Company company, TypeUpdate type){
        update = new Update<>(company, type);
        existChanges = true;
        notifyObservers();
    }
}
