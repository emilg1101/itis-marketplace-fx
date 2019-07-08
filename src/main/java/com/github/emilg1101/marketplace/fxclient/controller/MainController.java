package com.github.emilg1101.marketplace.fxclient.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

@Component
public class MainController extends Controller {

    @FXML
    private Button btn;

    @FXML
    public void onClick() {
        System.out.println("onClick()");
    }
}
