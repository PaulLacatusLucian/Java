package Domain;

import Observers.Observer;

public interface Subject {
    void addObserver(User user);
    void removeObserver(Observer observer);
    void notifyObservers();
}
