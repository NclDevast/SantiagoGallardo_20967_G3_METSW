/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author pc
 */
public class MongoDBCLaboral extends MongoDBContratos{

    public MongoDBCLaboral(Usuario usermodel) {
        super(usermodel);
    }
            protected void insertarContrato(String [] datos, MongoCollection<Document> collection){
        
            String[]nombresCampos = new String[datos.length];
            nombresCampos[0] = "Ciudad";
            nombresCampos[1] = "FechaContrato";
            nombresCampos[2] = "NombreEmpleado";
            nombresCampos[3] = "CedulaEmpleador";
            nombresCampos[4] = "CiudadEmpleador";
            nombresCampos[5] = "NombreTrabajador";
            nombresCampos[6] = "CedulaTrabajador";
            nombresCampos[7] = "CiudadTrabajador";
            nombresCampos[8] = "CargoTrabajador";
            nombresCampos[9] = "JornadasHoras";
            nombresCampos[10] = "DiasTrabajo";
            nombresCampos[11] = "FechaInicio";
            nombresCampos[12] = "Monto";
            nombresCampos[13] = "FormaPago";
            nombresCampos[14] = "LugarTrabajo";
            
            Document doc = new Document();
            
            for(int i=0;i<nombresCampos.length;i++){
                doc.append(nombresCampos[i], datos[i]);
    }
            collection.insertOne(doc);
            
            
            
            
            
    }
    
}
