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

/**
 *
 * @author pc
 */
public abstract class MongoDBContratos {
    
    private Usuario usermodel;
    private String MongoURI;
    private MongoClient mongoClient;

    public MongoDBContratos(Usuario usermodel) {
        this.usermodel = usermodel;
        this.MongoURI = usermodel.getMongoURI();
        this.mongoClient = MongoClients.create(this.MongoURI);
    }

    public void conectarMongo (String [] Datos, int tipo){
        
        try {
            MongoDatabase database = mongoClient.getDatabase("DataContratos");
            MongoCollection<Document> collection;
            
            switch(tipo) {
                case 0:
                    collection = database.getCollection("ContratosCivil");
                    insertarContrato(Datos, collection);
                    break;
                case 1:
                    collection = database.getCollection("ContratosLaboral");
                    insertarContrato(Datos, collection);
                    break;
                default:
                    System.err.println("Error interno: Tipo de contrato no válido");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con MongoDB: " + e.getMessage());
        }
    }
    
    protected void insertarContrato(String [] datos, MongoCollection<Document> collection){
        
    }
}
