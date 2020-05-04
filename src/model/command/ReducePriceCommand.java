package model.command;

import model.Company;
import model.Investor;
import model.receiver.TransactionReceiver;

import java.util.List;

public class ReducePriceCommand implements Command{
    private TransactionReceiver transactionReceiver;
    private List<Company> companyList;
    private List<Company> withSalesCompanyList;


    public ReducePriceCommand(TransactionReceiver th, List<Company> companyList, List<Company> withSalesCompanyList ){
        this.transactionReceiver = th;
        this.companyList = companyList;
        this.withSalesCompanyList = withSalesCompanyList;

    }

    @Override
    public void execute() {
        transactionReceiver.reduceSharesPrice(companyList, withSalesCompanyList);
    }
}
