package Monopoly.Controller;

import Monopoly.Model.Model;
import Monopoly.Model.MonopolyProperty;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final Model model;
    private Random rand;

    private static Image pieceBlueImage =
            new Image(Controller.class.getResourceAsStream("/Images/pieceBlue.png"));
    private static Image pieceGreenImage =
            new Image(Controller.class.getResourceAsStream("/Images/pieceGreen.png"));
    private static Image pieceRedImage =
            new Image(Controller.class.getResourceAsStream("/Images/pieceRed.png"));
    private static Image pieceYellowImage =
            new Image(Controller.class.getResourceAsStream("/Images/pieceYellow.png"));

    private static WritableImage emptyImage = new WritableImage(50, 68);

    private static ObservableList<Image> diceImageList = FXCollections.observableArrayList(
        new Image(Controller.class.getResourceAsStream("/Images/diceOne.png")),
        new Image(Controller.class.getResourceAsStream("/Images/diceTwo.png")),
        new Image(Controller.class.getResourceAsStream("/Images/diceThree.png")),
        new Image(Controller.class.getResourceAsStream("/Images/diceFour.png")),
        new Image(Controller.class.getResourceAsStream("/Images/diceFive.png")),
        new Image(Controller.class.getResourceAsStream("/Images/diceSix.png")));

    @FXML
    private javafx.scene.control.ListView logPropertyList;

    @FXML
    private TextField sendButtonTextField;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private ImageView field5_player2;

    @FXML
    private ImageView field5_player1;

    @FXML
    private ImageView field34_player0;

    @FXML
    private ImageView field5_player3;

    @FXML
    private ImageView field34_player2;

    @FXML
    private ImageView field34_player1;

    @FXML
    private ImageView field5_player0;

    @FXML
    private ImageView field34_player3;

    @FXML
    private ImageView field38_player3;

    @FXML
    private ImageView field38_player2;

    @FXML
    private ImageView field38_player1;

    @FXML
    private ImageView field9_player3;

    @FXML
    private ImageView field9_player1;

    @FXML
    private ImageView field9_player2;

    @FXML
    private ImageView field9_player0;

    @FXML
    private ImageView field38_player0;

    @FXML
    private ImageView field19_player2;

    @FXML
    private ImageView field21_player1;

    @FXML
    private ImageView field19_player3;

    @FXML
    private ImageView field21_player2;

    @FXML
    private ImageView field21_player0;

    @FXML
    private ImageView field19_player0;

    @FXML
    private ImageView field21_player3;

    @FXML
    private Label player2MoneyLabel;

    @FXML
    private javafx.scene.control.ListView player2PropertyList;

    @FXML
    private ImageView field19_player1;

    @FXML
    private Tab player3Tab;

    @FXML
    private ImageView field12_player2;

    @FXML
    private ImageView field28_player1;

    @FXML
    private ImageView field12_player1;

    @FXML
    private ImageView field28_player0;

    @FXML
    private ImageView field28_player3;

    @FXML
    private ImageView field12_player3;

    @FXML
    private ImageView field28_player2;

    @FXML
    private ImageView field12_player0;

    @FXML
    private ImageView field24_player0;

    @FXML
    private SplitPane splitPane1;

    @FXML
    private ImageView field16_player3;

    @FXML
    private ImageView field24_player1;

    @FXML
    private ImageView field16_player2;

    @FXML
    private ImageView field24_player2;

    @FXML
    private ImageView field16_player1;

    @FXML
    private ImageView field24_player3;

    @FXML
    private ImageView field16_player0;

    @FXML
    private ImageView field1_player3;

    @FXML
    private ImageView field1_player2;

    @FXML
    private Label player1MoneyLabel;

    @FXML
    private javafx.scene.control.ListView player1PropertyList;

    @FXML
    private ImageView field1_player1;

    @FXML
    private ImageView field31_player0;

    @FXML
    private ImageView field31_player1;

    @FXML
    private ImageView field31_player2;

    @FXML
    private ImageView field31_player3;

    @FXML
    private Button buyPropertyButton;

    @FXML
    private ImageView field1_player0;

    @FXML
    private ImageView field35_player2;

    @FXML
    private ImageView field35_player3;

    @FXML
    private ImageView field35_player0;

    @FXML
    private ImageView field35_player1;

    @FXML
    private ImageView field20_player2;

    @FXML
    private ImageView field20_player3;

    @FXML
    private ImageView field20_player0;

    @FXML
    private ImageView field20_player1;

    @FXML
    private ImageView field39_player2;

    @FXML
    private ImageView field39_player1;

    @FXML
    private ImageView field39_player0;

    @FXML
    private Label player0MoneyLabel;

    @FXML
    private javafx.scene.control.ListView player0PropertyList;

    @FXML
    private ImageView field18_player0;

    @FXML
    private ImageView field18_player1;

    @FXML
    private ImageView field18_player2;

    @FXML
    private ImageView field6_player1;

    @FXML
    private ImageView field18_player3;

    @FXML
    private ImageView field6_player0;

    @FXML
    private ImageView field6_player3;

    @FXML
    private ImageView field6_player2;

    @FXML
    private ImageView field11_player1;

    @FXML
    private ImageView field27_player0;

    @FXML
    private ImageView field11_player0;

    @FXML
    private ImageView field11_player3;

    @FXML
    private ImageView field11_player2;

    @FXML
    private ImageView field15_player3;

    @FXML
    private ImageView field15_player2;

    @FXML
    private ImageView field2_player3;

    @FXML
    private ImageView field15_player1;

    @FXML
    private ImageView field2_player2;

    @FXML
    private ImageView field15_player0;

    @FXML
    private ImageView field2_player1;

    @FXML
    private ImageView field2_player0;

    @FXML
    private Label player3MoneyLabel;

    @FXML
    private javafx.scene.control.ListView player3PropertyList;

    @FXML
    private ImageView field27_player2;

    @FXML
    private ImageView field27_player1;

    @FXML
    private Tab player0Tab;

    @FXML
    private ImageView field27_player3;

    @FXML
    private ImageView field36_player3;

    @FXML
    private ImageView field39_player3;

    @FXML
    private ImageView field7_player3;

    @FXML
    private ImageView field3_player3;

    @FXML
    private ImageView field32_player0;

    @FXML
    private ImageView field32_player1;

    @FXML
    private ImageView field32_player2;

    @FXML
    private ImageView field32_player3;

    @FXML
    private ImageView field3_player0;

    @FXML
    private ImageView field3_player1;

    @FXML
    private ImageView field3_player2;

    @FXML
    private ImageView field23_player0;

    @FXML
    private ImageView field17_player0;

    @FXML
    private ImageView field17_player1;

    @FXML
    private ImageView field23_player3;

    @FXML
    private ImageView field17_player2;

    @FXML
    private ImageView field23_player2;

    @FXML
    private ImageView field17_player3;

    @FXML
    private ImageView field23_player1;

    @FXML
    private ImageView field7_player1;

    @FXML
    private ImageView field7_player2;

    @FXML
    private ImageView field7_player0;

    @FXML
    private ImageView field36_player1;

    @FXML
    private ImageView field36_player2;

    @FXML
    private ImageView field36_player0;

    @FXML
    private Button sellPropertyButton;

    @FXML
    private ImageView field14_player3;

    @FXML
    private ImageView field26_player1;

    @FXML
    private ImageView field26_player0;

    @FXML
    private ImageView field10_player3;

    @FXML
    private ImageView field10_player0;

    @FXML
    private ImageView field10_player2;

    @FXML
    private ImageView field10_player1;

    @FXML
    private ImageView field14_player1;

    @FXML
    private ImageView field26_player3;

    @FXML
    private Tab player2Tab;

    @FXML
    private ImageView field14_player2;

    @FXML
    private ImageView field26_player2;

    @FXML
    private ImageView field14_player0;

    @FXML
    private ImageView field4_player0;

    @FXML
    private ImageView field4_player1;

    @FXML
    private ImageView field4_player2;

    @FXML
    private ImageView field4_player3;

    @FXML
    private Button mortgageButton;

    @FXML
    private ImageView field37_player2;

    @FXML
    private ImageView field37_player3;

    @FXML
    private ImageView field33_player3;

    @FXML
    private ImageView field33_player2;

    @FXML
    private ImageView field8_player2;

    @FXML
    private ImageView field8_player3;

    @FXML
    private ImageView field33_player1;

    @FXML
    private ImageView field33_player0;

    @FXML
    private Button rollDiceButton;

    @FXML
    private ImageView field8_player0;

    @FXML
    private ImageView field8_player1;

    @FXML
    private ImageView field37_player0;

    @FXML
    private ImageView field37_player1;

    @FXML
    private ImageView field22_player3;

    @FXML
    private ImageView field22_player2;

    @FXML
    private ImageView field22_player1;

    @FXML
    private ImageView field22_player0;

    @FXML
    private Tab player1Tab;

    @FXML
    private ImageView field29_player3;

    @FXML
    private ImageView field29_player1;

    @FXML
    private ImageView field29_player2;

    @FXML
    private ImageView field29_player0;

    @FXML
    private ImageView field25_player0;

    @FXML
    private ImageView field25_player1;

    @FXML
    private ImageView field25_player2;

    @FXML
    private ImageView field0_player1;

    @FXML
    private ImageView field30_player0;

    @FXML
    private ImageView field0_player0;

    @FXML
    private ImageView field25_player3;

    @FXML
    private ImageView field13_player2;

    @FXML
    private ImageView field13_player3;

    @FXML
    private ImageView field30_player3;

    @FXML
    private ImageView field0_player3;

    @FXML
    private ImageView field13_player0;

    @FXML
    private ImageView field30_player2;

    @FXML
    private ImageView field0_player2;

    @FXML
    private ImageView field13_player1;

    @FXML
    private ImageView field30_player1;

    @FXML
    private ImageView firstDiceImage;

    @FXML
    private ImageView secondDiceImage;

    public Controller() {
        model = new Model();
        rand = new Random();
    }

    public Controller(Model model) {
        this.model = model;
        rand = new Random();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player0MoneyLabel.textProperty().bindBidirectional(model.moneyTextProperty(0));
        player1MoneyLabel.textProperty().bindBidirectional(model.moneyTextProperty(1));
        player2MoneyLabel.textProperty().bindBidirectional(model.moneyTextProperty(2));
        player3MoneyLabel.textProperty().bindBidirectional(model.moneyTextProperty(3));

        //Bind player MonopolyProperties
        player0PropertyList.itemsProperty().bindBidirectional(model.getPlayer(0).getMonopolyProperties());
        player1PropertyList.itemsProperty().bindBidirectional(model.getPlayer(1).getMonopolyProperties());
        player2PropertyList.itemsProperty().bindBidirectional(model.getPlayer(2).getMonopolyProperties());
        player3PropertyList.itemsProperty().bindBidirectional(model.getPlayer(3).getMonopolyProperties());

        //Bind with the logger
        logPropertyList.itemsProperty().bindBidirectional(model.getMonopolyLogger().getMonopolyLogObservable());

        //Bind with the logger
        chatTextArea.textProperty().bindBidirectional(model.getMonopolyChat().getMonopolyChatObservable());

        player0Tab.textProperty().bindBidirectional(model.nameProperty(0));
        player1Tab.textProperty().bindBidirectional(model.nameProperty(1));
        player2Tab.textProperty().bindBidirectional(model.nameProperty(2));
        player3Tab.textProperty().bindBidirectional(model.nameProperty(3));

        field0_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(0)).then(pieceBlueImage).otherwise(emptyImage));
        field1_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(1)).then(pieceBlueImage).otherwise(emptyImage));
        field2_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(2)).then(pieceBlueImage).otherwise(emptyImage));
        field3_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(3)).then(pieceBlueImage).otherwise(emptyImage));
        field4_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(4)).then(pieceBlueImage).otherwise(emptyImage));
        field5_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(5)).then(pieceBlueImage).otherwise(emptyImage));
        field6_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(6)).then(pieceBlueImage).otherwise(emptyImage));
        field7_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(7)).then(pieceBlueImage).otherwise(emptyImage));
        field8_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(8)).then(pieceBlueImage).otherwise(emptyImage));
        field9_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(9)).then(pieceBlueImage).otherwise(emptyImage));
        field10_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(10)).then(pieceBlueImage).otherwise(emptyImage));
        field11_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(11)).then(pieceBlueImage).otherwise(emptyImage));
        field12_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(12)).then(pieceBlueImage).otherwise(emptyImage));
        field13_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(13)).then(pieceBlueImage).otherwise(emptyImage));
        field14_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(14)).then(pieceBlueImage).otherwise(emptyImage));
        field15_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(15)).then(pieceBlueImage).otherwise(emptyImage));
        field16_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(16)).then(pieceBlueImage).otherwise(emptyImage));
        field17_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(17)).then(pieceBlueImage).otherwise(emptyImage));
        field18_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(18)).then(pieceBlueImage).otherwise(emptyImage));
        field19_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(19)).then(pieceBlueImage).otherwise(emptyImage));
        field20_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(20)).then(pieceBlueImage).otherwise(emptyImage));
        field21_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(21)).then(pieceBlueImage).otherwise(emptyImage));
        field22_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(22)).then(pieceBlueImage).otherwise(emptyImage));
        field23_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(23)).then(pieceBlueImage).otherwise(emptyImage));
        field24_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(24)).then(pieceBlueImage).otherwise(emptyImage));
        field25_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(25)).then(pieceBlueImage).otherwise(emptyImage));
        field26_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(26)).then(pieceBlueImage).otherwise(emptyImage));
        field27_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(27)).then(pieceBlueImage).otherwise(emptyImage));
        field28_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(28)).then(pieceBlueImage).otherwise(emptyImage));
        field29_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(29)).then(pieceBlueImage).otherwise(emptyImage));
        field30_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(30)).then(pieceBlueImage).otherwise(emptyImage));
        field31_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(31)).then(pieceBlueImage).otherwise(emptyImage));
        field32_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(32)).then(pieceBlueImage).otherwise(emptyImage));
        field33_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(33)).then(pieceBlueImage).otherwise(emptyImage));
        field34_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(34)).then(pieceBlueImage).otherwise(emptyImage));
        field35_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(35)).then(pieceBlueImage).otherwise(emptyImage));
        field36_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(36)).then(pieceBlueImage).otherwise(emptyImage));
        field37_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(37)).then(pieceBlueImage).otherwise(emptyImage));
        field38_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(38)).then(pieceBlueImage).otherwise(emptyImage));
        field39_player0.imageProperty().bind(Bindings.when(model.positionProperty(0).isEqualTo(39)).then(pieceBlueImage).otherwise(emptyImage));

        field0_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(0)).then(pieceGreenImage).otherwise(emptyImage));
        field1_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(1)).then(pieceGreenImage).otherwise(emptyImage));
        field2_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(2)).then(pieceGreenImage).otherwise(emptyImage));
        field3_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(3)).then(pieceGreenImage).otherwise(emptyImage));
        field4_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(4)).then(pieceGreenImage).otherwise(emptyImage));
        field5_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(5)).then(pieceGreenImage).otherwise(emptyImage));
        field6_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(6)).then(pieceGreenImage).otherwise(emptyImage));
        field7_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(7)).then(pieceGreenImage).otherwise(emptyImage));
        field8_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(8)).then(pieceGreenImage).otherwise(emptyImage));
        field9_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(9)).then(pieceGreenImage).otherwise(emptyImage));
        field10_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(10)).then(pieceGreenImage).otherwise(emptyImage));
        field11_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(11)).then(pieceGreenImage).otherwise(emptyImage));
        field12_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(12)).then(pieceGreenImage).otherwise(emptyImage));
        field13_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(13)).then(pieceGreenImage).otherwise(emptyImage));
        field14_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(14)).then(pieceGreenImage).otherwise(emptyImage));
        field15_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(15)).then(pieceGreenImage).otherwise(emptyImage));
        field16_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(16)).then(pieceGreenImage).otherwise(emptyImage));
        field17_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(17)).then(pieceGreenImage).otherwise(emptyImage));
        field18_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(18)).then(pieceGreenImage).otherwise(emptyImage));
        field19_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(19)).then(pieceGreenImage).otherwise(emptyImage));
        field20_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(20)).then(pieceGreenImage).otherwise(emptyImage));
        field21_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(21)).then(pieceGreenImage).otherwise(emptyImage));
        field22_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(22)).then(pieceGreenImage).otherwise(emptyImage));
        field23_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(23)).then(pieceGreenImage).otherwise(emptyImage));
        field24_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(24)).then(pieceGreenImage).otherwise(emptyImage));
        field25_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(25)).then(pieceGreenImage).otherwise(emptyImage));
        field26_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(26)).then(pieceGreenImage).otherwise(emptyImage));
        field27_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(27)).then(pieceGreenImage).otherwise(emptyImage));
        field28_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(28)).then(pieceGreenImage).otherwise(emptyImage));
        field29_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(29)).then(pieceGreenImage).otherwise(emptyImage));
        field30_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(30)).then(pieceGreenImage).otherwise(emptyImage));
        field31_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(31)).then(pieceGreenImage).otherwise(emptyImage));
        field32_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(32)).then(pieceGreenImage).otherwise(emptyImage));
        field33_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(33)).then(pieceGreenImage).otherwise(emptyImage));
        field34_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(34)).then(pieceGreenImage).otherwise(emptyImage));
        field35_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(35)).then(pieceGreenImage).otherwise(emptyImage));
        field36_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(36)).then(pieceGreenImage).otherwise(emptyImage));
        field37_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(37)).then(pieceGreenImage).otherwise(emptyImage));
        field38_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(38)).then(pieceGreenImage).otherwise(emptyImage));
        field39_player1.imageProperty().bind(Bindings.when(model.positionProperty(1).isEqualTo(39)).then(pieceGreenImage).otherwise(emptyImage));

        field1_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(1)).then(pieceRedImage).otherwise(emptyImage));
        field2_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(2)).then(pieceRedImage).otherwise(emptyImage));
        field3_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(3)).then(pieceRedImage).otherwise(emptyImage));
        field0_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(0)).then(pieceRedImage).otherwise(emptyImage));
        field4_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(4)).then(pieceRedImage).otherwise(emptyImage));
        field5_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(5)).then(pieceRedImage).otherwise(emptyImage));
        field6_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(6)).then(pieceRedImage).otherwise(emptyImage));
        field7_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(7)).then(pieceRedImage).otherwise(emptyImage));
        field8_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(8)).then(pieceRedImage).otherwise(emptyImage));
        field9_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(9)).then(pieceRedImage).otherwise(emptyImage));
        field10_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(10)).then(pieceRedImage).otherwise(emptyImage));
        field11_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(11)).then(pieceRedImage).otherwise(emptyImage));
        field12_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(12)).then(pieceRedImage).otherwise(emptyImage));
        field13_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(13)).then(pieceRedImage).otherwise(emptyImage));
        field14_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(14)).then(pieceRedImage).otherwise(emptyImage));
        field15_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(15)).then(pieceRedImage).otherwise(emptyImage));
        field16_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(16)).then(pieceRedImage).otherwise(emptyImage));
        field17_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(17)).then(pieceRedImage).otherwise(emptyImage));
        field18_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(18)).then(pieceRedImage).otherwise(emptyImage));
        field19_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(19)).then(pieceRedImage).otherwise(emptyImage));
        field20_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(20)).then(pieceRedImage).otherwise(emptyImage));
        field21_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(21)).then(pieceRedImage).otherwise(emptyImage));
        field22_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(22)).then(pieceRedImage).otherwise(emptyImage));
        field23_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(23)).then(pieceRedImage).otherwise(emptyImage));
        field24_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(24)).then(pieceRedImage).otherwise(emptyImage));
        field25_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(25)).then(pieceRedImage).otherwise(emptyImage));
        field26_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(26)).then(pieceRedImage).otherwise(emptyImage));
        field27_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(27)).then(pieceRedImage).otherwise(emptyImage));
        field28_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(28)).then(pieceRedImage).otherwise(emptyImage));
        field29_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(29)).then(pieceRedImage).otherwise(emptyImage));
        field30_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(30)).then(pieceRedImage).otherwise(emptyImage));
        field31_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(31)).then(pieceRedImage).otherwise(emptyImage));
        field32_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(32)).then(pieceRedImage).otherwise(emptyImage));
        field33_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(33)).then(pieceRedImage).otherwise(emptyImage));
        field34_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(34)).then(pieceRedImage).otherwise(emptyImage));
        field35_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(35)).then(pieceRedImage).otherwise(emptyImage));
        field36_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(36)).then(pieceRedImage).otherwise(emptyImage));
        field37_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(37)).then(pieceRedImage).otherwise(emptyImage));
        field38_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(38)).then(pieceRedImage).otherwise(emptyImage));
        field39_player2.imageProperty().bind(Bindings.when(model.positionProperty(2).isEqualTo(39)).then(pieceRedImage).otherwise(emptyImage));

        field1_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(1)).then(pieceYellowImage).otherwise(emptyImage));
        field2_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(2)).then(pieceYellowImage).otherwise(emptyImage));
        field3_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(3)).then(pieceYellowImage).otherwise(emptyImage));
        field0_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(0)).then(pieceYellowImage).otherwise(emptyImage));
        field4_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(4)).then(pieceYellowImage).otherwise(emptyImage));
        field5_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(5)).then(pieceYellowImage).otherwise(emptyImage));
        field6_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(6)).then(pieceYellowImage).otherwise(emptyImage));
        field7_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(7)).then(pieceYellowImage).otherwise(emptyImage));
        field8_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(8)).then(pieceYellowImage).otherwise(emptyImage));
        field9_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(9)).then(pieceYellowImage).otherwise(emptyImage));
        field10_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(10)).then(pieceYellowImage).otherwise(emptyImage));
        field11_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(11)).then(pieceYellowImage).otherwise(emptyImage));
        field12_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(12)).then(pieceYellowImage).otherwise(emptyImage));
        field13_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(13)).then(pieceYellowImage).otherwise(emptyImage));
        field14_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(14)).then(pieceYellowImage).otherwise(emptyImage));
        field15_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(15)).then(pieceYellowImage).otherwise(emptyImage));
        field16_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(16)).then(pieceYellowImage).otherwise(emptyImage));
        field17_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(17)).then(pieceYellowImage).otherwise(emptyImage));
        field18_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(18)).then(pieceYellowImage).otherwise(emptyImage));
        field19_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(19)).then(pieceYellowImage).otherwise(emptyImage));
        field20_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(20)).then(pieceYellowImage).otherwise(emptyImage));
        field21_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(21)).then(pieceYellowImage).otherwise(emptyImage));
        field22_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(22)).then(pieceYellowImage).otherwise(emptyImage));
        field23_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(23)).then(pieceYellowImage).otherwise(emptyImage));
        field24_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(24)).then(pieceYellowImage).otherwise(emptyImage));
        field25_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(25)).then(pieceYellowImage).otherwise(emptyImage));
        field26_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(26)).then(pieceYellowImage).otherwise(emptyImage));
        field27_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(27)).then(pieceYellowImage).otherwise(emptyImage));
        field28_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(28)).then(pieceYellowImage).otherwise(emptyImage));
        field29_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(29)).then(pieceYellowImage).otherwise(emptyImage));
        field30_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(30)).then(pieceYellowImage).otherwise(emptyImage));
        field31_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(31)).then(pieceYellowImage).otherwise(emptyImage));
        field32_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(32)).then(pieceYellowImage).otherwise(emptyImage));
        field33_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(33)).then(pieceYellowImage).otherwise(emptyImage));
        field34_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(34)).then(pieceYellowImage).otherwise(emptyImage));
        field35_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(35)).then(pieceYellowImage).otherwise(emptyImage));
        field36_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(36)).then(pieceYellowImage).otherwise(emptyImage));
        field37_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(37)).then(pieceYellowImage).otherwise(emptyImage));
        field38_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(38)).then(pieceYellowImage).otherwise(emptyImage));
        field39_player3.imageProperty().bind(Bindings.when(model.positionProperty(3).isEqualTo(39)).then(pieceYellowImage).otherwise(emptyImage));

        model.getFirstDiceProperty().addListener((obs, oldValue, newValue) -> {
            if((Integer)newValue == 0){
                firstDiceImage.setImage(emptyImage);
            }else{
                firstDiceImage.setImage(diceImageList.get((Integer)newValue - 1));
            }
        });

        model.getSecondDiceProperty().addListener((obs, oldValue, newValue) -> {
            if((Integer)newValue == 0){
                secondDiceImage.setImage(emptyImage);
            }else{
                secondDiceImage.setImage(diceImageList.get((Integer)newValue - 1));
            }
        });
    }


    @FXML
    void newGameMenuItemClicked(ActionEvent event) {

    }

    @FXML
    void saveGameMenuItemClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save game");
        File file = fileChooser.showSaveDialog((Stage) ((Node) rollDiceButton).getScene().getWindow());
        if (file != null) {
            model.saveGame(file);
        }
    }

    @FXML
    void loadGameMenuItemClicked(ActionEvent event) {

    }

    @FXML
    void exitMenuItemClicked(ActionEvent event) {
        Dialog dialog = new Alert(Alert.AlertType.CONFIRMATION, "Save game before exit?", ButtonType.YES, ButtonType.NO);
        dialog.showAndWait();

        if (dialog.getResult() == ButtonType.YES) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save game");
            File file = fileChooser.showSaveDialog((Stage) ((Node) rollDiceButton).getScene().getWindow());
            if (file != null) {
                model.saveGame(file);
            }
        }

        Stage stage = (Stage) rollDiceButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void rulesMenuItemClicked(ActionEvent event) {

    }

    @FXML
    void aboutMenuItemClicked(ActionEvent event) {

    }

    @FXML
    void rollDiceButtonClicked(ActionEvent event) {
        rollDiceButton.setDisable(true);

        int firstDiceValue = rand.nextInt(6) + 1;
        int secondDiceValue = rand.nextInt(6) + 1;

        //TODO: handle cases where (firstDiceValue == secondDiceValue)

        model.setFirstDiceValue(firstDiceValue);
        model.setSecondDiceValue(secondDiceValue);

        int stepValue = firstDiceValue + secondDiceValue;

        //javafx ain't easy...
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < stepValue; i++) {
                    updateMessage(String.valueOf(i));
                    Thread.sleep(250);
                }
                return null;
            }
        };

        task.messageProperty().addListener((obs, oldMessage, newMessage) -> model.step(model.getCurrentPlayer(), 1));
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                model.nextPlayer();
                rollDiceButton.setDisable(false);
            }
        });

        new Thread(task).start();
        if(model.isActFieldProperty()){
            buyPropertyButton.setDisable(false);
        }
        else {
            buyPropertyButton.setDisable(true);
        }
    }


    @FXML
    void buyPropertyButtonClicked(ActionEvent event) {
        model.buyActProperty();
    }

    @FXML
    void sellPropertyButtonClicked(ActionEvent event) {

    }

    @FXML
    void mortgageButtonClicked(ActionEvent event) {

    }

    @FXML
    void sendButtonClicked(ActionEvent event) {
        model.getMonopolyChat().writeToChat("[" +
                model.getPlayer(model.getCurrentPlayer()).getName() +
                "]: " +
                sendButtonTextField.getText());
        sendButtonTextField.setText("");
    }
}
