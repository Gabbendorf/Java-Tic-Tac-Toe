package ticTacToe;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ticTacToe.grid.*;
import ticTacToe.guiApp.*;
import ticTacToe.guiApp.JavaFx.JavaFxGridPane;

public class GuiAppRunner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tic Tac Toe");
        Grid grid = new Grid(3);
        Label label = new Label();
        JavaFxGridPane javaFxGridPane = new JavaFxGridPane(new GridPane());
        GridPanePresenter gridPane = new GridPanePresenter(javaFxGridPane, grid);
        GuiGameFlow flow = new GuiGameFlow(grid, new Lines());
        gridPane.gridPaneSetUp(new ButtonsCreator(new ActionSetter(flow)));
        Scene scene = new Scene(javaFxGridPane.actualGridPane(), 300,300);

        javaFxGridPane.actualGridPane().add(label, 0, 4);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
