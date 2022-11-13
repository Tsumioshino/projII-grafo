package com.projII_grafo;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class PrimaryController {
    @FXML
    public Pane telaDesenho;

    @FXML
    public void createNo(){
        String rotulo = JOptionPane.showInputDialog(null, "Digite um nome para o Nó");
        int peso = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do peso;"));

        Label labelRotulo = new Label(rotulo);

        Node no = new Node(40.0, rotulo, peso);
        no.setFill(Color.WHITE);
        no.setStroke(Color.BLACK);
        
        BoxNode layout = new BoxNode();
        layout.getChildren().addAll(no, labelRotulo);
        layout.setLayoutX(30);
        layout.setLayoutY(30);

        addNo(layout);

        App.getNos_existentes().add(no);

        //layout.setOnMouseClicked((MouseEvent event) -> {
        //    System.out.println(event.getSceneX());
        //    System.out.println(event.getScreenX());
        //    System.out.println(event.getX());
        //    System.out.println("mouseClicked");
        //});
        //layout.setOnMousePressed(new EventHandler <MouseEvent>()
        //{
        //    public void handle(MouseEvent event)
        //    {
        //        layout.setMouseTransparent(true);
        //        System.out.println("Event on Source: mouse pressed");
        //        event.setDragDetect(true);
        //    }
        //});
        Delta dragDelta = new Delta();
        layout.setOnMousePressed(event -> {
            dragDelta.x = layout.getLayoutX() - event.getSceneX();
            dragDelta.y = layout.getLayoutY() - event.getSceneY();
            System.out.println("Event on Source: mouse pressed");
        });

        layout.setOnMouseReleased(event -> {
            System.out.println("Event on Source: mouse released");

        });

  
        layout.setOnMouseDragged(event -> 
        {
            
            layout.setLayoutX(event.getSceneX() + dragDelta.x);
            layout.setLayoutY(event.getSceneY() + dragDelta.y);
            
            
            event.setDragDetect(false);
        });
 
        layout.setOnDragDetected(event -> {
            layout.startFullDrag();
            System.out.println("Event on Source: drag detected");

        });
 

    }

    public void addNo(StackPane node){
        this.telaDesenho.getChildren().add(node);
    }

    public Pane getTelaDesenho() {
        return telaDesenho;
    }

    public void setTelaDesenho(Pane telaDesenho) {
        this.telaDesenho = telaDesenho;
    }

}

class Delta { double x, y; }