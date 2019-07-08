package com.github.emilg1101.marketplace.fxclient.loader;

import com.github.emilg1101.marketplace.fxclient.controller.Controller;
import com.github.emilg1101.marketplace.fxclient.controller.MainController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageLoader {

    private static final String FXML_DIR = "/view/fxml/";
    private static final String MAIN_STAGE = "main";

    @Autowired
    private MainController mainController;

    private Parent load(Controller controller, String fxmlName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StageLoader.class.getResource(FXML_DIR + fxmlName + ".fxml"));
        loader.setClassLoader(StageLoader.class.getClassLoader());
        loader.setControllerFactory(param -> controller);
        return loader.load(StageLoader.class.getResourceAsStream(FXML_DIR + fxmlName + ".fxml"));
    }

    public Stage loadMain() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(load(mainController, MAIN_STAGE)));
        stage.setOnHidden(event -> Platform.exit());
        stage.setTitle("Marketplace");
        return stage;
    }

    public Scene loadScene(Controller controller, String fxmlName) throws IOException {
        return new Scene(load(controller, fxmlName));
    }
}
