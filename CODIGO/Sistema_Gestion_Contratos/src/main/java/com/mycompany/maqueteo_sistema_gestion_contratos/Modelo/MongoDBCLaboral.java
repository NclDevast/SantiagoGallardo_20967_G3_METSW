/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoDBCLaboral extends MongoDBContratos {

    public MongoDBCLaboral(Usuario usermodel) {
        super(usermodel);
    }

    /**
     * Inserta el contrato en "ContratosLaboral" y devuelve el ObjectId.
     */
    public ObjectId insertarYObtenerId(String[] datos) {
        MongoCollection<Document> coll = getCollection("ContratosLaboral");

        // Usa el array 'datos' y el array de campos que definiste
        String[] campos = {
            "Ciudad","FechaContrato","NombreEmpleado","CedulaEmpleado",
            "CiudadTrabajador","NombreTrabajador","CedulaTrabajador",
            "CargoTrabajador","JornadasHoras","DiasTrabajo",
            "FechaInicio","Monto","FormaPago","LugarTrabajo"
        };

        Document doc = new Document();
        for (int i = 0; i < campos.length; i++) {
            doc.append(campos[i], datos[i]);
        }

        InsertOneResult res = coll.insertOne(doc);
        ObjectId id = res.getInsertedId().asObjectId().getValue();
        System.out.println("✅ Insertado en MongoDB ContratosLaboral con _id=" + id);
        return id;
    }
    
    //METODOS LEGACY ABSTRACTOS NO IMPLEMENTADOS

}
