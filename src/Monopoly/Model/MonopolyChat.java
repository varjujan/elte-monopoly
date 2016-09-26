package Monopoly.Model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoltankis on 2016. 04. 26..
 */
public class MonopolyChat {
    private Property monopolyChatObservable = new <String>SimpleStringProperty();
    //private List<String> monopolyChat = new ArrayList<>();

    public void writeToChat(String msg) {
        //monopolyChat.add(msg);
        //monopolyChatObservable.set(FXCollections.observableArrayList(monopolyChat));
        Object val = monopolyChatObservable.getValue();
        if(val==null){
            monopolyChatObservable.setValue(msg);
        }
        else{
            monopolyChatObservable.setValue(monopolyChatObservable.getValue() + "\n" + msg);
        }

    }

    public Property getMonopolyChatObservable() {
        return monopolyChatObservable;
    }
}
