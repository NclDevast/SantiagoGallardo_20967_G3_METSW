/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mycompany.maqueteo_sistema_gestion_contratos.Controlador.LoginControlador;


/**
 *
 * @author pc
 */
public class ContratosMongoDB {
    
    private final LoginControlador loginControl;
    private final Usuario userModel;

    public ContratosMongoDB(LoginControlador loginControl) {
        this.loginControl = loginControl;
        this.userModel = loginControl.getModelo();
    }
    
    private void connectMongo(){
        MongoClient mongoClient = MongoClients.create(userModel.getMongoURI());
    }

    
    
}
