package com.github.emilg1101.marketplace.fxclient.controller;

import com.github.emilg1101.marketplace.fxclient.loader.StageLoader;
import com.github.emilg1101.marketplace.fxclient.model.Product;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductDetailsModalStage extends Stage {

    private ActionListener actionListener;

    private Label id;
    private Label title;
    private Label price;
    private Label description;
    private Label image;

    private Button edit;
    private Button delete;

    public ProductDetailsModalStage(StageLoader stageLoader, ActionListener actionListener) {
        this.actionListener = actionListener;
        this.initModality(Modality.WINDOW_MODAL);
        this.centerOnScreen();
        try {
            Scene scene = stageLoader.loadScene("productDetails");
            this.setScene(scene);
            id = (Label) scene.lookup("#id");
            title = (Label) scene.lookup("#title");
            price = (Label) scene.lookup("#price");
            description = (Label) scene.lookup("#description");
            image = (Label) scene.lookup("#image");

            edit = (Button) scene.lookup("#edit");
            delete = (Button) scene.lookup("#delete");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDetails(Product product) {
        id.setText(String.valueOf(product.getId()));
        title.setText(product.getTitle());
        price.setText(product.getPrice() + "$");
        description.setText(product.getDescription());
        image.setText(product.getImageURL());

        edit.setOnMouseClicked(event -> {
            if (actionListener != null) {
                actionListener.onEdit(product.getId(), product);
            }
        });

        delete.setOnMouseClicked(event -> {
            if (actionListener != null) {
                actionListener.onDelete(product.getId());
            }
            hide();
        });

        setTitle("Product details: " + product.getTitle());
        show();
    }

    public interface ActionListener {
        void onEdit(long id, Product product);

        void onDelete(long id);
    }
}
