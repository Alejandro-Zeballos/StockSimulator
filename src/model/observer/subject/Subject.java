package model.observer.subject;

import model.Company;
import model.observer.Observer;
import model.observer.subject.model.Update;

import java.util.List;


public interface Subject {
    void register(Observer obj);
    void unregister(Observer obj);
    //method to notify observers of change
    void notifyObservers();

    //method to get updates from subject
    Update getUpdate();
    void setCompanies(List<Company> companyList);
}
