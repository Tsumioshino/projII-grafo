package com.projII_grafo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projII_grafo.model.Node;

@RestController
public class Controller {
    
    @GetMapping("/")
    public Node teste(){
        int a[] = {1,2,3,4};
        Node node = new Node("alfredo", a);
        //ObjectMapper objectMapper = new ObjectMapper();
        //ObjectNode objectNode = objectMapper.createObjectNode();
        //ArrayNode arrayNode = objectMapper.createArrayNode();
        //arrayNode.put("Aaaa", a);
        return node; 
    }
}
