/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public abstract class MongoDBContratos {

    private Usuario usermodel;
    private String MongoURI;
    private MongoClient mongoClient;

    public MongoDBContratos(Usuario usermodel) {
        this.usermodel   = usermodel;
        this.MongoURI    = usermodel.getMongoURI();
        this.mongoClient = MongoClients.create(this.MongoURI);
    }

    public void conectarMongo(String[] Datos, int tipo) {
        // ... implementación actual ...
    }

    protected void insertarContrato(String[] datos, MongoCollection<Document> collection){
        
    }

    /**
     * Permite obtener la base de datos "DataContratos".
     */
    protected MongoDatabase getDatabase() {
        return mongoClient.getDatabase("DataContratos");
    }

    /**
     * Expone cualquier colección de DataContratos por nombre.
     */
    public MongoCollection<Document> getCollection(String nombreColeccion) {
        return getDatabase().getCollection(nombreColeccion);
    }
}