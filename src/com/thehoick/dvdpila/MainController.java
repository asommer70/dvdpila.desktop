package com.thehoick.dvdpila;


import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
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

    public void initialize() {
        // Load Settings from XML.
        mSettings = Settings.getSettings();
        System.out.println("url: " + mSettings.getProperty("url"));
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

        System.out.printf("mDvds.size: %d %n", mDvds.size());
//        System.out.println(mDvds.get(0).getTitle());

        for (Dvd dvd : mDvds) {
            System.out.println(dvd);
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
