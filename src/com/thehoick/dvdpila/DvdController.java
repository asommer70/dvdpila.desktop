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
    public int mPageIndex;
    public String mDvdId;
    @FXML
    Button backBtn;

    public void initialize() {
        System.out.println("mDvdId: " + mDvdId);

        backBtn.setOnAction(event -> {
            Node source = (Node)event.getTarget();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                Parent parent = (Parent)loader.load();
                MainController controller = loader.getController();
                Scene scene = new Scene(parent, 800, 700);
                Stage stage = (Stage)source.getScene().getWindow();
                stage.setScene(scene);
                controller.setPageIndex(mPageIndex);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void initData(String dvdId, int pageIndex) {
        mPageIndex = pageIndex;
        mDvdId = dvdId;
        System.out.println("dvdId: " + dvdId + " pageIndex: " + pageIndex);
    }

    @FXML
    private void goBack(ActionEvent actionEvent) {

    }
}


