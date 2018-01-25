package ticTacToe;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ticTacToe.grid.*;
import ticTacToe.guiApp.*;

public class GuiAppRunner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        Grid grid = new Grid(3);
        ButtonsCreator buttonsCreator = new ButtonsCreator(new ActionSetter(grid));
        JavaFxGridPane javaFxGridPane = new JavaFxGridPane(new GridPane());
        Label label = new Label();
        GuiGridOnPane gridOnPane = new GuiGridOnPane(javaFxGridPane, grid);

        gridOnPane.gridPaneSetUp();
        gridOnPane.addAll(buttonsCreator.createButtons(grid));
        javaFxGridPane.actualGridPane().add(label, 0, 4);

        Scene scene = new Scene(javaFxGridPane.actualGridPane(), 250,250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
