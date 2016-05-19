package com.thehoick.dvdpila;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Properties;

public class DvdController {
    private static int mPageIndex;
    @FXML
    Button backBtn;

    public void initialize() {
        // Load Settings from XML.
        backBtn.setOnAction(event -> {
            Node source = (Node)event.getTarget();

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage)source.getScene().getWindow();
                stage.setScene(scene);

                // TODO:as figure out how to return to the correct Pagination pageIndex.
                MainController.setPageIndex(mPageIndex);

                // TODO:as fix rendering of Dvds layout... not sure why it's all bunched up in the left hand corner.
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void initData(String dvdId, int pageIndex) {
        mPageIndex = pageIndex;
        System.out.println("dvdId: " + dvdId + " pageIndex: " + pageIndex);
    }

    @FXML
    private void goBack(ActionEvent actionEvent) {

    }
}


