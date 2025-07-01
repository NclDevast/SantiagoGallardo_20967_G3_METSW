/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoDBCCivil extends MongoDBContratos {

    public MongoDBCCivil(Usuario usermodel) {
        super(usermodel);
    }

    @Override
    protected void insertarContrato(String[] datos, MongoCollection<Document> collection) {
        String[] nombresCampos = new String[datos.length];
        nombresCampos[0] = "NombreArrendataria";
        nombresCampos[1] = "RucArrendataria";
        nombresCampos[2] = "RepresentanteArrendataria";
        nombresCampos[3] = "CargoArrendataria";
        nombresCampos[4] = "NacionalidadArrendataria";
        nombresCampos[5] = "NombreArrendador";
        nombresCampos[6] = "RucArrendador";
        nombresCampos[7] = "RepresentanteArrendador";
        nombresCampos[8] = "CargoArrendador";
        nombresCampos[9] = "NacionalidadArrendador";
        nombresCampos[10] = "Antecedentes";
        nombresCampos[11] = "FechaInicio";
        nombresCampos[12] = "FechaFin";
        nombresCampos[13] = "ValorMensual";
        nombresCampos[14] = "FormaPago";
        nombresCampos[15] = "Garantia";
        nombresCampos[16] = "CorreoArrendataria";
        nombresCampos[17] = "CorreoArrendador";

        Document doc = new Document();
        for (int i = 0; i < nombresCampos.length; i++) {
            doc.append(nombresCampos[i], datos[i]);
        }
        collection.insertOne(doc);
    }
}