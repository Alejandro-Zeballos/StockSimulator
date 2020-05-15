package model.iterator;

import model.Investor;

import java.util.*;

public class InvestorCollection implements InvestorCollectionInterface{

    private List<Investor> investorList;

    public InvestorCollection(){
        investorList = new ArrayList<>();
    }

    public InvestorCollection(List<Investor> investorList){
        this.investorList = investorList;
    }

    @Override
    public void addInvestor(Investor investor) {
        this.investorList.add(investor);
    }

    @Override
    public void removeInvestor(Investor investor) {
        this.investorList.remove(investor);
    }

    @Override
    public InvestorIteratorInterface iterator() {
        return new InvestorIterator(investorList);
    }



    //Inner class investorIterator
    private class InvestorIterator implements InvestorIteratorInterface{

        private List<Investor> investorList;
        private int position;

        public InvestorIterator(List<Investor> investorList){
            this.investorList = investorList;
        }

        @Override
        public boolean hasNext() {

            Investor i;

            while (position < investorList.size()) {
                i = investorList.get(position);
                //returning true if the investor have budget
                if (i.getBudget()>0) {
                    return true;
                } else {
                    position++;
                }
            }
            return false;
        }

        @Override
        public Investor next() {
            Investor i = investorList.get(position);
            position++;
            return i;
        }
    }
}
