package com.kdillo.simple;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class SimpleApp {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        //load app properties from config file.
        try (InputStream configFileStream = SimpleApp.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (configFileStream == null) {
                System.out.println("No props file found");
                return;
            }
            Properties props = new Properties();
            props.load(configFileStream);

            //properties loaded, attempt to read and test from db
            MySQLAccess dbAccess = new MySQLAccess();
            dbAccess.readDataBase(props);

        } catch (IOException ex) {
            //unable to load from properties file..
            System.out.println("Never loaded props file, " + ex.getMessage());
            LOGGER.error(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
