/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import com.mycompany.maqueteo_sistema_gestion_contratos.Controlador.LoginControlador;
import java.util.Random;



/**
 *
 * @author pc
 */

public class MongoDBModel {

    private final LoginControlador loginControl;
    private final Usuario userModel;
    private final static Random random = new Random();

    public MongoDBModel(LoginControlador loginControl) {
        this.loginControl = loginControl;
        this.userModel = loginControl.getModelo();
    }

    private MongoDatabase connectMongo(String databaseName) {
        MongoClient mongoClient = MongoClients.create(userModel.getMongoURI());
        return mongoClient.getDatabase(databaseName);
    }

    public void buscarUsuario(String nombreUsuario) {
        try {
            MongoDatabase db = connectMongo("DataAdmin");
            MongoCollection<Document> collection = db.getCollection("UserData");
            Document resultado = collection.find(eq("nombreUsuario", nombreUsuario)).first();

            if (resultado != null) {
                System.out.println("Busqueda Terminada ");
                userModel.setNombreUsuario(resultado.getString("nombreUsuario"));
                System.out.println("Busqueda Exitosa: nombre usuario: "+userModel.getNombreUsuario());
                userModel.setPassword(resultado.getString("password"));
            }
            else{
                
                int temp = random.nextInt(10000000);
                String tempS = String.valueOf(temp);
                userModel.setNombreUsuario(tempS);
                userModel.setPassword(tempS+1);
            }

        } catch (Exception e) {
            System.err.println("Error al acceder a MongoDB: " + e.getMessage());
        }
    }
}