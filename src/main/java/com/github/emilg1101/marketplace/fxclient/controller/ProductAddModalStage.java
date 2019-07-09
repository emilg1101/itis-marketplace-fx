package com.github.emilg1101.marketplace.fxclient.controller;

import com.github.emilg1101.marketplace.fxclient.loader.StageLoader;
import com.github.emilg1101.marketplace.fxclient.model.Product;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductAddModalStage extends Stage {

    private ActionListener actionListener;

    private TextField title;
    private TextField price;
    private TextArea description;
    private TextField image;

    private Button add;

    public ProductAddModalStage(StageLoader stageLoader, ActionListener actionListener) {
        this.actionListener = actionListener;
        this.initModality(Modality.WINDOW_MODAL);
        this.centerOnScreen();
        try {
            Scene scene = stageLoader.loadScene("productAdd");
            this.setScene(scene);
            title = (TextField) scene.lookup("#title");
            price = (TextField) scene.lookup("#price");
            description = (TextArea) scene.lookup("#description");
            image = (TextField) scene.lookup("#image");

            add = (Button) scene.lookup("#add");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDetails() {
        add.setOnMouseClicked(event -> {
            if (actionListener != null) {
                Product newProduct = Product.builder()
                        .title(title.getText())
                        .price(Double.valueOf(price.getText()))
                        .description(description.getText())
                        .imageURL(image.getText())
                        .build();
                actionListener.onAdd(newProduct);
            }
            hide();
        });

        setTitle("Add product");
        show();
    }

    public interface ActionListener {
        void onAdd(Product product);
    }
}
