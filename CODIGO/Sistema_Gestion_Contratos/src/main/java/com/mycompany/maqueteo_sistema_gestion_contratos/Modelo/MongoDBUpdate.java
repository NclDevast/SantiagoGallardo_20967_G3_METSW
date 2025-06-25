/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.mycompany.maqueteo_sistema_gestion_contratos.Controlador.UsuarioControlador;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author pc
 */
public class MongoDBUpdate {
    
    private final UsuarioControlador userController;
    private final Usuario userModel;

    public MongoDBUpdate(UsuarioControlador userController) {
        this.userController = userController;
        this.userModel = userController.getModelo();
    }
     private MongoDatabase connectMongo(String databaseName) {
        MongoClient mongoClient = MongoClients.create(userModel.getMongoURI());
        return mongoClient.getDatabase(databaseName);
    }
     public void cambiarDatos(int tipo, String temp){
         try {
            MongoDatabase db = connectMongo("DataAdmin");
            MongoCollection<Document> collection = db.getCollection("UserData");
            Bson filter = Filters.eq("nombreUsuario", userModel.getNombreUsuario());
            Bson update = null;
            
            switch (tipo) {
            case 0:
                update = Updates.set("nombreUsuario", temp);
                break;
            case 1:
                update = Updates.set("password", temp);
                break;
            default:
                System.err.println("Error Interno");
            }


            if (update != null) {
                System.out.println("Filtro: " + filter.toBsonDocument(Document.class, MongoClientSettings.getDefaultCodecRegistry()));
                System.out.println("Update: " + update.toBsonDocument(Document.class, MongoClientSettings.getDefaultCodecRegistry()));
                UpdateResult result = collection.updateOne(filter, update);
                System.out.println("Documentos modificados: " + result.getModifiedCount());
            } else {
                System.out.println("Tipo de cambio no reconocido.");
            }

         } catch (Exception e) {
            System.err.println("Error al acceder a MongoDB: " + e.getMessage());
        }
     }
}
