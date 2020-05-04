package controller;

import model.*;
import model.command.BuyShareCommand;
import model.command.Command;
import model.command.ReducePriceCommand;
import model.invoker.Commander;
import model.receiver.TransactionHelper;
import model.receiver.TransactionReceiver;
import view.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TradeSimulator {

    private UserInterface userInterface;
    private List<Company> companyList;
    private List<Investor> investorList;
    private List<Investor> investorNoBudgetList;
    private List<Company> companyNoSharesList;
    private List<Company> companiesWithSalesList;
    private int sharesSoldCounter;

    //probando---------------

    private int companyNoSharesLeft;
    private int investorNoBudgetLeft;
    private List<Investor> cleaningInvestorList;
    private List<Company> cleaningCompanyList;
    TransactionReceiver tReceiver = new TransactionHelper();
    Commander commander = new Commander();

    //--------------------

    public TradeSimulator() {

        //proving
        companyNoSharesLeft = 0;
        investorNoBudgetLeft = 0;

        userInterface = new UserInterface();

        companyList = new ArrayList<>();
        investorList = new ArrayList<>();
        companyNoSharesList = new ArrayList<>();
        investorNoBudgetList = new ArrayList<>();
        cleaningInvestorList = new ArrayList<>();
        cleaningCompanyList = new ArrayList<>();
        companiesWithSalesList = new ArrayList<>();
        sharesSoldCounter = 0;

        userInterface.printMessage("Simulating trade day...");
        userInterface.delaySeg(3);


        //Generating companies
        for(int k = 1; k <= 100; k++) {
            Company newCompany = new Company(k);
            companyList.add(newCompany);
        }

        //Generating investors
        for(int k = 1; k <= 100; k++) {
            Investor newInvestor = new Investor(k);
            investorList.add(newInvestor);
        }

        System.out.println();



        simulateTradeDay(companyList, investorList);

        userInterface.printMessage("Trading day finished");
        int userOption=0;
        do{
            userInterface.listOptions();
            userOption = userInterface.getOption();
            userInterface.printMessage(generateResult(userOption));//
            userInterface.delaySeg(4);

        }while(userOption!= 0);

    }

    private String generateResult(int userOption) {
        Company company;
        Investor investor;
        String message = "";
        switch (userOption){
            case 1:
                company = getCompanyWithHighestCapital();
                message = "Company with the highest capital:\n"+company.toString();
                break;
            case 2:
                company = getCompanyWithLeastCapital();
                message = "Company with the lowest capital:\n"+ company.toString();
                break;
            case 3:
                investor = getInvestorWithMoreShares();
                message = "Investor with the most shares:\n"+ investor.toString();
                break;
            case 4:
                investor = getInvestorWithLessShares();
                message = "Investor with the least shares:\n"+ investor.toString();
                break;
            default:
                userInterface.close();
        }
        return message;
    }

    private Company getCompanyWithLeastCapital() {

        int leastCapital = 10000;
        Company poorerCompany = null;

        for(Company company: companyList) {
            if (company.getCapital()<leastCapital) {
                poorerCompany = company;
                leastCapital = company.getCapital();
            }
        }
        for(Company company: companyNoSharesList) {
            if (company.getCapital()<leastCapital) {
                poorerCompany = company;
                leastCapital = company.getCapital();
            }
        }

        return poorerCompany;
    }

    private Company getCompanyWithHighestCapital() {

        int higestCapital = 0;
        Company richestCompany = null;

        for(Company company: companyList) {
            if (company.getCapital()>higestCapital) {
                richestCompany = company;
                higestCapital = company.getCapital();
            }
        }
        for(Company company: companyNoSharesList) {
            if (company.getCapital()>higestCapital) {
                richestCompany = company;
                higestCapital = company.getCapital();
            }
        }

        return richestCompany;
    }




    private Investor getInvestorWithLessShares() {
        int leastShares = 10000;
        Investor poorerInvestor = null;

        for(Investor investor: investorList) {
            if (investor.getShares()<leastShares) {
                poorerInvestor = investor;
                leastShares = investor.getShares();
            }
        }
        for(Investor investor: investorNoBudgetList) {
            if (investor.getShares()<leastShares) {
                poorerInvestor = investor;
                leastShares = investor.getShares();
            }
        }

        return poorerInvestor;
    }


    private Investor getInvestorWithMoreShares() {
        int mostShares = 0;
        Investor richestInvestor = null;

        for(Investor investor: investorList) {
            if (investor.getShares()>mostShares) {
                richestInvestor = investor;
                mostShares = investor.getShares();
            }
        }
        for(Investor investor: investorNoBudgetList) {
            if (investor.getShares()>mostShares) {
                richestInvestor = investor;
                mostShares = investor.getShares();
            }
        }

        return richestInvestor;
    }


    private void simulateTradeDay(List<Company> companyList, List<Investor> investorList) {



        while(thereIsShares(companyList) & investorsHaveMoney(investorList)) {
            int investorsBuyingStock = 0;

            //for each investor available we make a trade
            for(Investor investor: investorList) {
                if(companyList.size()==0) {
                    return;
                }

                //grabing a random trading company with shares price less or equal
                //to investor budget
                Company tradingCompany = getRandomCompany(companyList, investor.getBudget());

                if(tradingCompany == null) {
                    continue;	//Checking if system didnt find a suitable company to buy
                }


                //From here There is a investor with enough money to buy a share from tradingCompany

                //Buying Share //Using commander pattern
                buyShare(investor, tradingCompany);
                //If company that did the sell have sold 10 shares so far, will duplicate its share price internally


                //Documenting seller company
                companiesWithSalesList.add(tradingCompany);
                sharesSoldCounter ++;
                investorsBuyingStock++;

                //if we sold 10 shares reduce price of shares of companies that didn't sell
                if(sharesSoldCounter == 10) {
                    reduceSharePrice(companyList, companiesWithSalesList);
                    companiesWithSalesList = new ArrayList<>();
                    sharesSoldCounter = 0;
                }

                //checking if the company that made the share still have shares
                if(tradingCompany.getShares() == 0) {
                    companyNoSharesLeft++;

                    companyNoSharesList.add(tradingCompany);
                    cleaningCompanyList.add(tradingCompany);
                }

                //checking if investor have money after trading
                if(!investorHaveMoney(investor)) {
                    investorNoBudgetLeft++;

                    investorNoBudgetList.add(investor);
                    cleaningInvestorList.add(investor);
                }


            }//foreach end


            if(investorsBuyingStock==0){

                reduceAllSharesPrice(companyList);
            }
            //cleaning List of investors with no money left
            for(Investor investorToClean: cleaningInvestorList) {
                investorList.remove(investorToClean);

            }
            cleaningInvestorList= new ArrayList<>();

            //cleaning List of Companies with no shares left
            for(Company companyToClean: cleaningCompanyList) {
                companyList.remove(companyToClean);
            }

            cleaningCompanyList= new ArrayList<>();



        }//while end

    }

    private void reduceAllSharesPrice(List<Company> companyList) {
        for(Company company: companyList){
            company.reduceSharePrice();
        }
    }

    private void buyShare(Investor investor, Company company) {
        Command buyShare = new BuyShareCommand(tReceiver, investor, company);
        commander.setCommand(buyShare);
        commander.executeCommand();

    }

    private void reduceSharePrice(List<Company> companyList, List<Company> companiesWithSalesList) {
        Command reduceSharePrice = new ReducePriceCommand(tReceiver, companyList, companiesWithSalesList);
        commander.setCommand(reduceSharePrice);
        commander.executeCommand();

    }


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
        int companyIndex = generateRandomNumber(companiesCandidate.size());
        return companiesCandidate.get(companyIndex);
    }


    private int generateRandomNumber(int top) {
        int randomNumber;
        ThreadLocalRandom threadRandom = ThreadLocalRandom.current();
        randomNumber = threadRandom.nextInt(0, top);

        return randomNumber;
    }

    private boolean thereIsShares(List<Company> companyList) {
        for(Company company: companyList) {
            if(company.getShares()!=0) {
                return true;
            }
        }

        return false;
    }

    private boolean investorsHaveMoney(List<Investor> investorList) {
        for(Investor investor: investorList) {
            if(investor.getBudget()>1) {
                return true;
            }
        }

        return false;
    }

    private boolean investorHaveMoney(Investor investor) {
        return investor.getBudget() > 1;
    }



}

