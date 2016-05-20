package com.thehoick.dvdpila;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = (Parent)loader.load();
        MainController controller = loader.getController();
        controller.setPageIndex(0);
        primaryStage.setTitle("DVD Pila!");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
