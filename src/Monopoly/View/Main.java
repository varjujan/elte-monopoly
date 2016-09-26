package Monopoly.View;

import Monopoly.Controller.Controller;
import Monopoly.Controller.StartGameController;
import Monopoly.Model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StartGameFrame.fxml"));
        loader.setControllerFactory(aClass -> new StartGameController());

        Parent root = loader.load();
        primaryStage.setTitle("Start Monopoly");
        primaryStage.setMinHeight(556);
        primaryStage.setMinWidth(800);

        Scene scene = new Scene(root, 800, 556);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        //Test comment from zokis

    }


    public static void main(String[] args) {
        launch(args);
    }
}
