package com.github.emilg1101.marketplace.fxclient;

import com.github.emilg1101.marketplace.fxclient.loader.StageLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        StageLoader.loadMain().show();
    }
}
