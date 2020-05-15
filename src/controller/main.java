package controller;

import model.Company;
import model.Investor;
import view.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class main {



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new main();
    }


    public UserInterface userInterface;
    private List<Company> companyList;
    private List<Investor> investorList;

    public main(){
        userInterface = new UserInterface();
        userInterface.printMessage("Starting Simulation...");
        userInterface.delaySeg(2);

        //Generating companies
        generateCompanies(100);

        userInterface.printMessage("100 Companies joined to the simulation...");
        userInterface.delaySeg(2);

        //Generating investors
        generateInvestors(100);

        userInterface.printMessage("100 Inversors joined to the simulation...");
        userInterface.delaySeg(2);

        userInterface.printMessage("Simulating trading day...");
        userInterface.delaySeg(3);

        TradeSimulator tradeSimulator = new TradeSimulator(companyList, investorList);


        tradeSimulator.simulateTradeDay();
        //After simulation companyList and investorList will be modified and will be ready to query

        userInterface.printMessage("Trading day finished..");
        userInterface.delaySeg(2);
        userInterface.printMessage("Results ready to query..");

        userInterface.delaySeg(2);
        int userOption;
        do{
            userInterface.listOptions();
            userOption = userInterface.getOption();
            displayResult(userOption);//
            userInterface.delaySeg(4);

        }while(userOption!= 0);

    }



    private void generateCompanies(int number){
        companyList = new ArrayList<>();
        for(int k = 1; k <= number; k++){
            Company newCompany = new Company(k);
            companyList.add(newCompany);
        }
    }

    private void generateInvestors(int number){
        investorList = new ArrayList<>();
        //creating investors that are observers also
        for(int k = 1; k <= number; k++){
            Investor newInvestor = new Investor(k, companyList);
            investorList.add(newInvestor);
        }
    }

    private void displayResult(int userOption) {
        List<Company> companies;
        List<Investor> investors;

        switch (userOption){
            case 1:
                companies = getCompanyWithHighestCapital();
                userInterface.printMessage("Companies with the highest capital: "+companies.size()+" companies\n");
                for(Company company: companies){
                    userInterface.printMessage(company.toString());
                }
                break;
            case 2:
                companies = getCompanyWithLeastCapital();
                userInterface.printMessage("Companies with the lowest capital: "+companies.size()+" companies\n");
                for(Company company: companies){
                    userInterface.printMessage(company.toString());
                }
                break;
            case 3:
                investors = getInvestorWithMoreShares();
                userInterface.printMessage("Investors with the most shares: "+investors.size()+" investors\n");
                for(Investor investor: investors){
                    userInterface.printMessage(investor.toString());
                }
                break;
            case 4:
                investors = getInvestorWithLessShares();
                userInterface.printMessage("Investors with the least shares: "+investors.size()+" investors\n");
                for(Investor investor: investors){
                    userInterface.printMessage(investor.toString());
                }
                break;
            default:
                userInterface.close();
        }
    }

    private List<Company> getCompanyWithLeastCapital() {

        Company poorerCompany = companyList.get(0);
        int leastCapital = poorerCompany.getCapital();

        for(Company company: companyList){
            if(company.getCapital()<leastCapital){
                poorerCompany = company;
                leastCapital = poorerCompany.getCapital();
            }

        }

        return getCompaniesWithSameCapital(poorerCompany);

    }

    private List<Company> getCompaniesWithSameCapital(Company company){
        List<Company> companies = new ArrayList<>();

        for(Company c: companyList){
            if(c.getCapital() == company.getCapital()){
                companies.add(c);
            }
        }
        return companies;
    }

    private List<Company> getCompanyWithHighestCapital() {

        Company richestCompany = companyList.get(0);
        int bigestCapital = richestCompany.getCapital();

        for(Company company: companyList){
            if(company.getCapital()>bigestCapital){
                richestCompany = company;
                bigestCapital = richestCompany.getCapital();
            }

        }
        return getCompaniesWithSameCapital(richestCompany);
    }




    private List<Investor> getInvestorWithLessShares() {
        Investor poorerInvestor = investorList.get(0);
        int leastShares = poorerInvestor.getShares();

        for(Investor investor: investorList){
            if(investor.getShares()<leastShares){
                poorerInvestor = investor;
                leastShares = poorerInvestor.getShares();
            }

        }
        return getInvestorWithIqualShares(poorerInvestor);
    }

    private List<Investor> getInvestorWithIqualShares(Investor investor){
        List<Investor> investors = new ArrayList<>();
        for(Investor i: investorList){
            if(i.getShares() == investor.getShares()){
                investors.add(i);
            }
        }

        return investors;
    }


    private List<Investor> getInvestorWithMoreShares() {
        Investor richestInvestor = investorList.get(0);
        int mostShares = richestInvestor.getShares();

        for(Investor investor: investorList){
            if(investor.getShares()>mostShares){
                richestInvestor = investor;
                mostShares = richestInvestor.getShares();
            }

        }
        return getInvestorWithIqualShares(richestInvestor);
    }
}
