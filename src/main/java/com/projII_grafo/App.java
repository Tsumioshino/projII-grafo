package com.projII_grafo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private static PrimaryController controller;
    private static List<Node> nos_existentes = new ArrayList<>();

    

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary" + ".fxml"));
        Parent loader = fxmlLoader.load();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.setResizable(false);
        App.setController(fxmlLoader.getController());
        stage.show();
    }

    static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (IOException e) {
            System.out.println("Algum erro aconteceu");
            e.printStackTrace();
        }
    }

    static void setRoot(Parent parent) {
        scene.setRoot(parent);
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        App.scene = scene;
    }

    public static PrimaryController getController() {
        return controller;
    }

    public static void setController(PrimaryController controller) {
        App.controller = controller;
    }

    public static List<Node> getNos_existentes() {
        return nos_existentes;
    }

    public static void setNos_existentes(List<Node> nos_existentes) {
        App.nos_existentes = nos_existentes;
    }

}