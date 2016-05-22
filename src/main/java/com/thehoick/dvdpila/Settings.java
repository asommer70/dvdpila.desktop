package com.thehoick.dvdpila;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

class Settings {
    private Properties mSettings = new Properties();

     Properties getSettings() {
        InputStream input = null;

        try {
            input = Settings.class.getResourceAsStream("/configs/config.properties");
            mSettings.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mSettings;
    }

    static void setSettings(Properties settings) {
        OutputStream output = null;

        try {
            URL resourceUrl = Settings.class.getResource("/configs/config.properties");
            File file = new File(resourceUrl.toURI());
            output = new FileOutputStream(file);
            settings.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                    Settings s = new Settings();
                    s.getSettings();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
