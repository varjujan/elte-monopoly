package Monopoly.Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoltankis on 2016. 04. 25..
 */
public class MonopolyLogger {
    private ListProperty<String> monopolyLogObservable = new <String>SimpleListProperty();
    private List<String> monopolyLog = new ArrayList<>();

    public void writeToLogger(String msg) {
        monopolyLog.add(msg);
        monopolyLogObservable.set(FXCollections.observableArrayList(monopolyLog));
    }

    public ListProperty<String> getMonopolyLogObservable() {
        return monopolyLogObservable;
    }
}
