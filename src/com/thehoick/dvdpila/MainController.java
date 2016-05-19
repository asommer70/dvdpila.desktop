package com.thehoick.dvdpila;


import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class MainController {
    List<Dvd> mDvds;
    Properties mSettings;

    @FXML
    private BorderPane mMainPane;
    @FXML
    private GridPane mMainGrid;

    public void initialize() {
        // Load Settings from XML.
        mSettings = Settings.getSettings();
        getDvds();
    }

    @FXML
    public void getDvds() {
        DvdsService dvdsService = DvdsService.retrofit.create(DvdsService.class);
        Call<List<Dvd>> call = dvdsService.getDvds();

        try {
            mDvds = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int rowCount = 0;
        int colCount = 0;
        for (Dvd dvd : mDvds) {
            Image image = new Image(dvd.getImageUrl());
            ImageView dvdImage = new ImageView();
            dvdImage.setImage(image);
            dvdImage.setFitWidth(130);
            dvdImage.setPreserveRatio(true);
            dvdImage.setSmooth(true);
            dvdImage.setCache(true);
            Label dvdTitle = new Label();
            dvdTitle.setText(dvd.getTitle());

            VBox dvdBox = new VBox();
            dvdBox.setPadding(new Insets(10, 10, 10, 10));
            dvdBox.setSpacing(20);
            dvdBox.setAlignment(Pos.CENTER);
            dvdBox.getChildren().addAll(dvdImage, dvdTitle);

            mMainGrid.add(dvdBox, colCount, rowCount);
            colCount++;
            if (colCount >= 5) {
                rowCount++;
                colCount = 0;
            }
        }
    }

    @FXML
    public void showSettingsDialog() throws IOException {
        Dialog<ButtonType> settingsDialog = new Dialog<>();
        settingsDialog.initOwner(mMainPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/settings.fxml"));

        settingsDialog.getDialogPane().setContent(fxmlLoader.load());

        settingsDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        settingsDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = settingsDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("OK pressed...");
            SettingsController settingsController = fxmlLoader.getController();
            settingsController.processResults();
        }
    }

}
