package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    private Cell[][] grid;
    private Scene scene;
    private Stage stage;


    private Pane makePane(){
        Pane pane = new Pane();
        pane.setPrefSize(50 * 9, 50 * 9);
        this.grid = new Cell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Cell(i, j, Math.random() < .1);
                pane.getChildren().add(grid[i][j]);
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = grid[i][j];
                if (cell.mine)continue;
                cell.num = (int) getNeighbors(cell).stream().filter(e->e.mine).count();

            }
        }

        return pane;
    }
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        scene = new Scene(makePane());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                int x = i + cell.x;
                int y = j + cell.y;
                if (x >= 0 && x < 9 && y >= 0 && y < 9) {
                    neighbors.add(grid[x][y]);
                }
            }
        }
        return neighbors;
    }


    class Cell extends Button {
        int x, y, num;
        boolean mine;
        boolean visible;


        public Cell(int x, int y, boolean mine) {
            this.x = x;
            this.y = y;
            this.mine = mine;


            setFont(Font.font(20));
            setPrefSize(50, 50);
            setStyle("-fx-background-color: #605b5b;-fx-border-color: black");

            setTranslateX(x * 50);
            setTranslateY(y * 50);
            setOnMouseClicked(event -> revealCell());
        }


        private void revealCell() {
            if (visible) {
                return;
            }
            setText(num > 0 ? String.valueOf(num) : "");
            setStyle("-fx-background-color: #b3a6a6;-fx-border-color: black");
            visible = true;
            if (num > 0){
                return;
            }
            if (mine) {
                setText("X");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You lost!");
                alert.showAndWait().ifPresent(response ->{
                    if (response == ButtonType.OK) {
                        stage.close();
                    }
                });
                return;
            }
            getNeighbors(this).forEach(Cell::revealCell);

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
