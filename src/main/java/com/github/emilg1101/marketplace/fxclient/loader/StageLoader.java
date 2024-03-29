package com.github.emilg1101.marketplace.fxclient.loader;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageLoader {

    private static final String FXML_DIR = "/view/fxml/";
    private static final String MAIN_STAGE = "main";

    @Autowired
    private ApplicationContext applicationContext;

    private Parent load(String fxmlName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StageLoader.class.getResource(FXML_DIR + fxmlName + ".fxml"));
        loader.setClassLoader(StageLoader.class.getClassLoader());
        loader.setControllerFactory(applicationContext::getBean);
        return loader.load(StageLoader.class.getResourceAsStream(FXML_DIR + fxmlName + ".fxml"));
    }

    public Stage loadMain() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(load(MAIN_STAGE)));
        stage.setOnHidden(event -> Platform.exit());
        stage.setTitle("Marketplace");
        return stage;
    }

    public Scene loadScene(String fxmlName) throws IOException {
        return new Scene(load(fxmlName));
    }
}
