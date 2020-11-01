package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {

    public void showMessage(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Sudoku");

        GridPane rootNode = new GridPane();
        Scene scene = new Scene(rootNode); // розміри вікна
        primaryStage.setScene(scene);


        IntField[][] intFieldArr = new IntField[9][9];
        SudokuGenerator s = new SudokuGenerator();

        int[][] grid = s.getSudoku();
        for (int i = 0; i<9;i++){
            for (int j = 0; j<9;j++){
                IntField field = new IntField(0,9, grid[i][j]);
                if (grid[i][j] != 0){
                    field.setEditable(false);
                    field.setDisable(true);
                    field.setStyle("-fx-text-inner-color: red;");
                } else {
                    field.setStyle("-fx-text-inner-color: gray;");
                }

                field.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
                field.setPrefWidth(55);
                field.setPrefHeight(50);
                intFieldArr[i][j] = field;

//                field.textProperty().addListener((observable, oldValue, newValue) -> {
//                    System.out.println("textfield changed from " + oldValue + " to " + newValue);
//                });

                // поставить подсветку для выбраной ячейки и для этого ряда и колонки

                rootNode.add(intFieldArr[i][j], i+(int)i/3,j+(int)j/3);

                // Paint vertical and horizontal serparators
                if (i == 3 || i==7 || i == 10){
                    for (int k = 0; k<11;k++){
                        Separator separator = new Separator(Orientation.VERTICAL);
                        separator.setPadding(new Insets(0, 10, 0, 10));
                        rootNode.add(separator, i,k);
                    }
                }
                if (j == 3 || j==7 || j == 10){
                    for (int k = 0; k<11;k++){
                        Separator separator = new Separator(Orientation.HORIZONTAL);
                        separator.setPadding(new Insets(10, 0, 10, 0));
                        rootNode.add(separator, k,j);
                    }
                }

            }
        }

        Button buttonSolve =  new Button("Проверить!");
        buttonSolve.setPadding(new Insets(10, 10, 10, 10));
        buttonSolve.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            int[][] parseGrid = new int[9][9];
            for (int i = 0; i< 9; i++){
                for (int j = 0; j< 9; j++) {
                    parseGrid[i][j] = intFieldArr[i][j].getValue();
                }
            }
            if (SudokuTools.isSolved(parseGrid)) showMessage("Congratulations", "It is solved!!!");
            else showMessage("Error", "Try again!");
            //showMessage("Error", Arrays.deepToString(parseGrid));
        });
        rootNode.add(buttonSolve, 13, 0);

        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
