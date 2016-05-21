package com.thehoick.dvdpila;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Properties;

public class SettingsController {
    @FXML
    private TextField mUrlField;

    Settings mSettings = new Settings();


    public void initialize() {
        mUrlField.setText(mSettings.getSettings().getProperty("url"));
    }

    public TextField getUrlField() {
        return mUrlField;
    }

    void processResults() {
        Properties settings = mSettings.getSettings();
        if (settings == null) {
            settings = new Properties();
        }
        settings.setProperty("url", mUrlField.getText());
        Settings.setSettings(settings);
    }
}
