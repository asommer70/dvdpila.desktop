package com.thehoick.dvdpila;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static Properties mSettings = new Properties();

    // TODO:as load Settings from XML.
    private Settings() {
    }

    public static Properties getSettings() {
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
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

    public static void setSettings(Properties settings) {
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");
            settings.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // TODO:as save Settings somewhere... maybe SQLite, even better a simple XML file.
}
