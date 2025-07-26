/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class MongoDBBusqueda {
    private final Usuario userModel;
    private MongoClient mongoClient = null;

    public MongoDBBusqueda(Usuario userModel) {
        this.userModel = userModel;
    }

    private MongoDatabase connectMongo(String databaseName) {
        try {
            if (mongoClient == null) {
                mongoClient = MongoClients.create(userModel.getMongoURI());
            }
            return mongoClient.getDatabase(databaseName);
        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
            return null;
        }
    }

    public void closeMongoConnection() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar conexión MongoDB: " + e.getMessage());
            }
        }
    }
    /**
     * Busca un contrato por clave y tipo (0: civil, 1: laboral)
     * y retorna sus campos como arreglo de Strings.
     */
    public String[] buscarContrato(String key, int tipo) {
        MongoDatabase db = connectMongo("DataContratos");

        switch (tipo) {
            case 0: { // Civil
                MongoCollection<Document> col = db.getCollection("ContratosCivil");
                Document doc = col.find(eq("RucArrendataria", key)).first();
                if (doc == null) {
                    doc = col.find(eq("RucArrendador", key)).first();
                }
                if (doc == null) {
                    return null;
                }

                return new String[] {
                    doc.getString("NombreArrendataria"),
                    doc.getString("RucArrendataria"),
                    doc.getString("RepresentanteArrendataria"),
                    doc.getString("CargoArrendataria"),
                    doc.getString("NacionalidadArrendataria"),
                    doc.getString("CorreoArrendataria"),
                    doc.getString("NombreArrendador"),
                    doc.getString("RucArrendador"),
                    doc.getString("RepresentanteArrendador"),
                    doc.getString("CargoArrendador"),
                    doc.getString("NacionalidadArrendador"),
                    doc.getString("CorreoArrendador"),
                    doc.getString("Antecedentes"),
                    doc.getString("FechaInicio"),
                    doc.getString("FechaFin"),
                    doc.getString("ValorMensual"),
                    doc.getString("FormaPago"),
                    doc.getString("Garantia")
                };
            }

            case 1: { // Laboral
                MongoCollection<Document> col = db.getCollection("ContratosLaboral");
                Document doc = col.find(eq("CedulaTrabajador", key)).first();
                if (doc == null) {
                    doc = col.find(eq("CedulaEmpleado", key)).first();
                }
                if (doc == null) {
                    return null;
                }

                return new String[] {
                    doc.getString("Ciudad"),
                    doc.getString("FechaContrato"),
                    doc.getString("CiudadEmpleado"),
                    doc.getString("NombreEmpleado"),
                    doc.getString("CedulaEmpleado"),
                    doc.getString("CiudadTrabajador"),
                    doc.getString("NombreTrabajador"),
                    doc.getString("CedulaTrabajador"),
                    doc.getString("CargoTrabajador"),
                    doc.getString("JornadasHoras"),
                    doc.getString("DiasTrabajo"),
                    doc.getString("FechaInicio"),
                    doc.getString("Monto"),
                    doc.getString("FormaPago"),
                    doc.getString("LugarTrabajo")
                };
            }

            default:
                //closeMongoConnection();
                return null;
                
        }
    }

    /**
     * Busca el ObjectId del contrato según la clave y tipo.
     * Requerido para generar el PDF original desde MongoDB.
     */
    public ObjectId obtenerIdContrato(String key, int tipo) {
        MongoDatabase db = connectMongo("DataContratos");

        switch (tipo) {
            case 0: { // Civil
                MongoCollection<Document> col = db.getCollection("ContratosCivil");
                Document doc = col.find(eq("RucArrendataria", key)).first();
                if (doc == null) {
                    doc = col.find(eq("RucArrendador", key)).first();
                }
                return (doc != null) ? doc.getObjectId("_id") : null;
            }

            case 1: { // Laboral
                MongoCollection<Document> col = db.getCollection("ContratosLaboral");
                Document doc = col.find(eq("CedulaTrabajador", key)).first();
                if (doc == null) {
                    doc = col.find(eq("CedulaEmpleado", key)).first();
                }
                return (doc != null) ? doc.getObjectId("_id") : null;
            }

            default:
                return null;
        }
    }
    
    public void updateMongoDB(int tipo, ObjectId id, String [] datos){
        
        MongoDatabase db = connectMongo("DataContratos");
        Bson filter = Filters.eq("_id", id);
        switch(tipo){
            case 0: //civil
                String[] campos = {
            "NombreArrendataria","RucArrendataria","RepresentanteArrendataria",
            "CargoArrendataria","NacionalidadArrendataria","NombreArrendador",
            "RucArrendador","RepresentanteArrendador","CargoArrendador",
            "NacionalidadArrendador","Antecedentes","FechaInicio","FechaFin",
            "ValorMensual","FormaPago","Garantia","CorreoArrendataria",
            "CorreoArrendador"
        };
               MongoCollection<Document> col = db.getCollection("ContratosCivil");
               Document updates = new Document();
                for (int i = 0; i < campos.length; i++) {
                    updates.append(campos[i], datos[i]);
                }
                
                UpdateResult result = col.updateOne(
                    filter, 
                    new Document("$set", updates)
                );
                
                if (result.getModifiedCount() == 0) {
                    System.err.println("No se encontró el documento o no hubo cambios");
                }
                //closeMongoConnection();
                break;
            case 1:
                String[] camposLab = {
                "Ciudad", "FechaContrato", "NombreEmpleado", "CedulaEmpleado", "CiudadEmpleado",
                "NombreTrabajador", "CedulaTrabajador", "CiudadTrabajador",  // Fixed order here
                "CargoTrabajador", "JornadasHoras", "DiasTrabajo",
                "FechaInicio", "Monto", "FormaPago", "LugarTrabajo"
                };
               MongoCollection<Document> coll = db.getCollection("ContratosLaboral");
               Document updatesLab = new Document();
                for (int i = 0; i < camposLab.length; i++) {
                    updatesLab.append(camposLab[i], datos[i]);
                }
                
                UpdateResult resultLab = coll.updateOne(
                    filter, 
                    new Document("$set", updatesLab)
                );
                
                if (resultLab.getModifiedCount() == 0) {
                    System.err.println("No se encontró el documento o no hubo cambios");
                }
                //closeMongoConnection();
                break;
                
        }
    }
}