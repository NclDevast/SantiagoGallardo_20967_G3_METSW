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
import org.bson.types.ObjectId;

public class MongoDBBusqueda {
    private final Usuario userModel;

    public MongoDBBusqueda(Usuario userModel) {
        this.userModel = userModel;
    }

    private MongoDatabase connectMongo(String databaseName) {
        MongoClient mongoClient = MongoClients.create(userModel.getMongoURI());
        return mongoClient.getDatabase(databaseName);
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
                    doc.getString("NombreEmpleado"),
                    doc.getString("CedulaEmpleado"),
                    doc.getString("CiudadEmpleado"),
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
}