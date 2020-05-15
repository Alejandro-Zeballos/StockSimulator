package model.command;

import model.*;
import model.command.receiver.TransactionReceiver;

public class BuyShareCommand implements Command{
    private TransactionReceiver tReceiver;
    private Investor investor;

    public BuyShareCommand(TransactionReceiver th, Investor investor){
        this.tReceiver = th;
        this.investor = investor;
    }

    @Override
    public void execute() {
        tReceiver.buyShare(investor);
    }
}
