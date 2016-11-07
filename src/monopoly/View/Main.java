package monopoly.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import monopoly.controller.StartGameController;
import monopoly.model.Model;
import monopoly.model.board.StandardBoard;
import monopoly.model.deck.CommunityCardDeck;
import monopoly.model.deck.ChanceCardDeck;
import monopoly.model.deck.card.*;
import monopoly.model.dice.StandardDice;
import monopoly.model.dice.TwoStandardDices;
import monopoly.model.player.Player;
import monopoly.model.player.changer.SequentialPlayerChanger;
import monopoly.util.logger.InGameLogger;
import monopoly.util.random.JavaRandom;
import monopoly.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        StandardBoard board = new StandardBoard();
        TwoStandardDices dices = new TwoStandardDices(new StandardDice(new JavaRandom()),
                new StandardDice(new JavaRandom()));
        SequentialPlayerChanger playerChanger = new SequentialPlayerChanger(Arrays.asList(new Player("", 1500),
                new Player("", 1500), new Player("", 1500), new Player("", 1500)));
        InGameLogger logger = new InGameLogger();

        List<Card> communityCards = new ArrayList<>();
        List<Card> chanceCards = new ArrayList<>();
        for (int i=1; i<=17; i++) {
            chanceCards.add(new ChanceCard(i, playerChanger, board));
            if (i<17) {
                communityCards.add(new CommunityCard(i, playerChanger, board));
            }
        }
        ChanceCardDeck chanceCardDeck = new ChanceCardDeck(chanceCards);
        CommunityCardDeck communityCardDeck = new CommunityCardDeck(communityCards);

        Model model = new Model(board, dices, playerChanger, logger, chanceCardDeck, communityCardDeck);

        ViewModel viewModel = new ViewModel(model);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("StartGameFrame.fxml"));
        loader.setControllerFactory(aClass -> new StartGameController(viewModel));

        Parent root = loader.load();
        primaryStage.setTitle("Start monopoly");
        primaryStage.setMinHeight(556);
        primaryStage.setMinWidth(800);

        Scene scene = new Scene(root, 800, 556);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
