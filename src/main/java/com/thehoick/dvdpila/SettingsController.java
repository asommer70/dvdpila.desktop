package com.thehoick.dvdpila;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Properties;

public class SettingsController {
    @FXML
    private TextField mUrlField;
    private Properties mSettings;

    public void initialize() {
        mSettings = Settings.getSettings();
        mUrlField.setText(mSettings.getProperty("url"));
    }

    public TextField getUrlField() {
        return mUrlField;
    }

    public void processResults() {
        Properties settings = Settings.getSettings();
        if (settings == null) {
            settings = new Properties();
        }
        settings.setProperty("url", mUrlField.getText());
        Settings.setSettings(settings);
    }
}
