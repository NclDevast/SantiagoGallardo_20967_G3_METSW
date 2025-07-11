/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author pc
 */
public class MongoDBCLaboral extends MongoDBContratos{

    public MongoDBCLaboral(Usuario usermodel) {
        super(usermodel);
    }
            public ObjectId insertarYObtenerId(String[] datos) {
        MongoCollection<Document> coll = getCollection("ContratosCivil");

        String[] campos = {
            "NombreArrendataria","RucArrendataria","RepresentanteArrendataria",
            "CargoArrendataria","NacionalidadArrendataria","NombreArrendador",
            "RucArrendador","RepresentanteArrendador","CargoArrendador",
            "NacionalidadArrendador","Antecedentes","FechaInicio","FechaFin",
            "ValorMensual","FormaPago","Garantia","CorreoArrendataria",
            "CorreoArrendador"
        };

        Document doc = new Document();
        for (int i = 0; i < campos.length; i++) {
            doc.append(campos[i], datos[i]);
        }

        InsertOneResult result = coll.insertOne(doc);
        return result.getInsertedId().asObjectId().getValue();
    }
    
}
