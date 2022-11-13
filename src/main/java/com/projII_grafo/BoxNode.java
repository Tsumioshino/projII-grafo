package com.projII_grafo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class BoxNode extends StackPane implements Initializable {
    private double startDragX;
    private double startDragY;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.setOnMousePressed(e -> {
            System.out.println("AAAAAAAAAAAaa");
            startDragX = e.getSceneX();
            startDragY = e.getSceneY();
        });

        this.setOnMouseDragged(e -> {
            this.setTranslateX(e.getSceneX() - startDragX);
            this.setTranslateY(e.getSceneY() - startDragY);
        });
    }
}
