package com.github.emilg1101.marketplace.fxclient;

import com.github.emilg1101.marketplace.fxclient.config.AppConfig;
import com.github.emilg1101.marketplace.fxclient.loader.StageLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StageLoader loader = context.getBean(StageLoader.class);
        loader.loadMain().show();
    }
}
