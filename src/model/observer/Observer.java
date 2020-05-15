package model.observer;

import model.observer.subject.Subject;

public interface Observer {

    void update(Subject sub);

    void setSubject(Subject sub);
}
