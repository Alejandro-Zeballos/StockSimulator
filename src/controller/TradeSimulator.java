package controller;

import model.*;
import model.command.BuyShareCommand;
import model.command.Command;
import model.command.invoker.Commander;
import model.iterator.InvestorCollection;
import model.iterator.InvestorCollectionInterface;
import model.iterator.InvestorIteratorInterface;
import model.observer.Observer;
import model.command.receiver.TransactionHelper;
import model.command.receiver.TransactionReceiver;
import model.observer.subject.CompaniesTransactions;
import model.observer.subject.Subject;
import utils.TypeUpdate;

import java.util.ArrayList;
import java.util.List;

public class TradeSimulator implements Observer {

    private List<Company> companyList;
    private List<Investor> investorList;
    private List<Company> companiesWithSalesList;
    private int sharesSoldCounter;
    private int companyNoSharesLeft;
    private int investorNoBudgetLeft;

    private Subject companiesTransactions;
    private boolean thereAreTrades;

    TransactionReceiver tReceiver = new TransactionHelper();
    Commander commander = new Commander();

    //--------------------

    private Subject companiesUpdate;

    public TradeSimulator(List<Company> companies, List<Investor> investors) {

        //proving
        companyNoSharesLeft = 0;
        investorNoBudgetLeft = 0;

        thereAreTrades = true;

        //setting up companies and investors
        this.companyList = companies;
        this.investorList = investors;

        //instantiating subject SINGLETON CompaniesTransaction
        companiesTransactions = CompaniesTransactions.getInstance();
        companiesTransactions.setCompanies(companyList);


        //declaring this observer is gonna observe CompaniesTransactions
        this.setSubject(companiesTransactions);
        //subscribing this observer to the subject
        companiesTransactions.register(this);

        //declaring all the investors that are also Observers, to listen to tradeStatus
        for(Investor investor: investorList) {
            investor.setSubject(companiesTransactions);
            companiesTransactions.register(investor);
        }


        companiesWithSalesList = new ArrayList<>();
        sharesSoldCounter = 0;


    }



    public void simulateTradeDay() {


    //when investors have no budget left or companies have not shares left "thereAreTrades" will become false
        while(thereAreTrades) {
            int investorsBuyingStock = 0;
            Investor investor;

            //Instantiating investors iterator
            InvestorCollectionInterface investors = new InvestorCollection(investorList);
            InvestorIteratorInterface iterator = investors.iterator();

            //Iterating investors that have budget
            boolean investorsHaveBudget = false;

            while(iterator.hasNext()){

                investor = iterator.next();

                if(!thereAreTrades){//if update with no more trades was called during while loop, will break the loop.
                    break;
                }

                //If investor have budget will try to buy a share
                //this method will user a buyshare COMMANDER PATTERN
                buyShare(investor);

                investorsHaveBudget = true;
            }

            //if no investor was found with money set thereAreTrades to false
            if(!investorsHaveBudget) {
                thereAreTrades = false;
            }




        }//while end

        System.out.println("Day has finished");

    }

    //Using a commander pattern for handling commands
    private void buyShare(Investor investor) {
        Command buyShare = new BuyShareCommand(tReceiver, investor);
        commander.setCommand(buyShare);
        commander.executeCommand();

    }



    @Override
    public void update(Subject sub) {
        TypeUpdate type = sub.getUpdate().getTypeUpdate();
        if( type == TypeUpdate.NO_COMPANIES){
            thereAreTrades = false;
        }
    }

    @Override
    public void setSubject(Subject sub) {
        this.companiesTransactions = sub;
    }
}

