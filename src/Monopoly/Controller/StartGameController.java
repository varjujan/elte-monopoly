package Monopoly.Controller;

import Monopoly.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartGameController implements Initializable {

    public StartGameController() { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private Label errorLabel;

    @FXML
    private TextField player2NameLabel;

    @FXML
    private Label errorMsgLabel1;

    @FXML
    private Label errorMsgLabel2;

    @FXML
    private Button loadGameButton;

    @FXML
    private TextField player3NameLabel;

    @FXML
    private TextField player1NameLabel;

    @FXML
    private TextField player4NameLabel;

    @FXML
    private Label errorMsgLabel3;

    @FXML
    private Button newGameButton;

    @FXML
    private Label errorMsgLabel4;

    @FXML
    void newGameButtonClicked(ActionEvent event) throws IOException {
        boolean error = false;

        if(player1NameLabel.getText().isEmpty()){
            errorMsgLabel1.setText("Required field");
            error = true;
        }else{
            errorMsgLabel1.setText("");
        }

        if(player2NameLabel.getText().isEmpty()){
            errorMsgLabel2.setText("Required field");
            error = true;
        }else{
            errorMsgLabel2.setText("");
        }

        if(player3NameLabel.getText().isEmpty()){
            errorMsgLabel3.setText("Required field");
            error = true;
        }else{
            errorMsgLabel3.setText("");
        }

        if(player4NameLabel.getText().isEmpty()){
            errorMsgLabel4.setText("Required field");
            error = true;
        }else{
            errorMsgLabel4.setText("");
        }

        if(!error){
            if(player1NameLabel.getText().equals(player2NameLabel.getText()) ||
                    player1NameLabel.getText().equals(player3NameLabel.getText()) ||
                    player1NameLabel.getText().equals(player4NameLabel.getText()) ||
                    player2NameLabel.getText().equals(player3NameLabel.getText()) ||
                    player2NameLabel.getText().equals(player4NameLabel.getText()) ||
                    player3NameLabel.getText().equals(player4NameLabel.getText())){
                errorLabel.setText("Names must be different");
                error = true;
            }else{
                errorLabel.setText("");
            }
        }else{
            errorLabel.setText("");
        }

        if(error) return;

        final Model model = new Model(player1NameLabel.getText(), player2NameLabel.getText(), player3NameLabel.getText(), player4NameLabel.getText());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Monopoly/View/MainFrame.fxml"));
        loader.setControllerFactory(aClass -> new Controller(model));

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();

        primaryStage.setTitle("Awesome Monopoly");
        primaryStage.setMinHeight(768);
        primaryStage.setMinWidth(1366);
        primaryStage.setResizable(true);

        Scene scene = new Scene(root, 1366, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void loadGameButtonClicked(ActionEvent event) {

    }

}

