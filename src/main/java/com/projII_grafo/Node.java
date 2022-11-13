package com.projII_grafo;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Circle;

public class Node extends Circle {

    private String rotulo;
    private int peso;
    private List<Node> nos = new ArrayList<>();
    
    
    public Node(double radius, String rotulo, int peso) {
        super(radius);
        this.rotulo = rotulo;
        this.peso = peso;
    }

    public Node(String rotulo, int peso) {
        this.rotulo = rotulo;
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setNos(List<Node> nos) {
        this.nos = nos;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public void addNo(Node no) {
        this.nos.add(no);
    }

    public void removeNo(Node no) {
        this.nos.remove(no);
    }

    public List<Node> getNos() {
        return nos;
    }
}
