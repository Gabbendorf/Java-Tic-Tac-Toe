package ticTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ticTacToe.guiApp.GuiGridOnPane;

public class GuiAppRunner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        GuiGridOnPane gridOnPane = new GuiGridOnPane(new GridPane(), 3);
        gridOnPane.gridPaneSetUp();
        gridOnPane.addButtonsToPane();

        Scene scene = new Scene(gridOnPane.actualGridPane(), 200,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
