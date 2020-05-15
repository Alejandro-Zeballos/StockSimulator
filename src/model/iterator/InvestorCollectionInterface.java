package model.iterator;

import model.*;

public interface InvestorCollectionInterface {
    void addInvestor(Investor investor);
    void removeInvestor(Investor investor);
    InvestorIteratorInterface iterator();
}
