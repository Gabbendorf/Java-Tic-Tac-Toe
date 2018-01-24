package ticTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ticTacToe.game.Mark;
import ticTacToe.grid.Grid;
import ticTacToe.guiApp.ActionSetter;
import ticTacToe.guiApp.GuiGridOnPane;
import ticTacToe.guiApp.JavaFxButton;

public class GuiAppRunner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        Grid grid = new Grid(3);
        GuiGridOnPane gridOnPane = new GuiGridOnPane(new GridPane(), grid);
        gridOnPane.gridPaneSetUp();
        gridOnPane.addButtonsToPane();
        ActionSetter actionSetter = new ActionSetter(grid);
        for (JavaFxButton button : gridOnPane.getAllButtons()) {
            actionSetter.addClickHandler(button, Mark.NOUGHT, button.actualButton().getText());
        }

        Scene scene = new Scene(gridOnPane.actualGridPane(), 200,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
