package model.command;

import model.*;
import model.receiver.TransactionReceiver;

public class BuyShareCommand implements Command{
    private TransactionReceiver tReceiver;
    private Investor investor;
    private Company company;

    public BuyShareCommand(TransactionReceiver th, Investor investor, Company company){
        this.tReceiver = th;
        this.investor = investor;
        this.company = company;
    }

    @Override
    public void execute() {
        tReceiver.buyShare(investor, company);
    }
}
