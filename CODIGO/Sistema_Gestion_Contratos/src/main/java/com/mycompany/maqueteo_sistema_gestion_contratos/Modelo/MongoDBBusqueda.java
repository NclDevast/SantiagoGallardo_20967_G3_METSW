/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 *
 * @author pc
 */
public class MongoDBBusqueda {
    private final Usuario userModel;

    public MongoDBBusqueda(Usuario userModel) {
        this.userModel = userModel;
    }
    
    
    
    private MongoDatabase connectMongo(String databaseName) {
        MongoClient mongoClient = MongoClients.create(userModel.getMongoURI());
        return mongoClient.getDatabase(databaseName);
    }
    public void buscarUsuario(String key,int tipo) {
        
            MongoDatabase db = connectMongo("DataContratos");
            switch(tipo){
                case 0: //CIvil
            MongoCollection<Document> collection = db.getCollection("ContratosCivil");
            Document resultado = collection.find(eq("RucArrendataria", key)).first(); //agregar verificacion para RUC Arrendador si no se encuentra
            // extreaer como array de strings todos los datos del contrato
            break;
                case 1: // laboral
                    //aplicar la misma logica pero con CedulaTrabbajador y Cedula Empleador
                    break;
            }
            
            
    }
}
