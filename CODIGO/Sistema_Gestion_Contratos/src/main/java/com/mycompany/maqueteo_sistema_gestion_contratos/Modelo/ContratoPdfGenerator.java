/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;
import com.itextpdf.text.pdf.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoCollection;

import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Objects;

public class ContratoPdfGenerator {

    private static final String TEMPLATE = "src/main/resources/CONTRATO CIVIL.pdf";

    /**
     * Genera el PDF usando el registro con id = insertedId.
     */
    public void generarContratoPDF(MongoDBCCivil service, ObjectId insertedId) throws Exception {
        Objects.requireNonNull(service, "El servicio MongoDB no puede ser nulo");
        Objects.requireNonNull(insertedId, "El ID del documento no puede ser nulo");

        MongoCollection<Document> coll = service.getCollection("ContratosCivil");
        Document doc = coll.find(new Document("_id", insertedId)).first();
        
        if (doc == null) {
            throw new IllegalStateException("No se encontró el documento con ID: " + insertedId);
        }

        // Extraer datos del documento
        String nombreArrendataria = doc.getString("NombreArrendataria");
        System.out.println(nombreArrendataria);
        String rucArrendataria = doc.getString("RucArrendataria");
        String repArrendataria = doc.getString("RepresentanteArrendataria");
        String cargoArrendataria = doc.getString("CargoArrendataria");
        String nacArrendataria = doc.getString("NacionalidadArrendataria");
        String nombreArrendador = doc.getString("NombreArrendador");
        String rucArrendador = doc.getString("RucArrendador");
        String repArrendador = doc.getString("RepresentanteArrendador");
        String cargoArrendador = doc.getString("CargoArrendador");
        String nacArrendador = doc.getString("NacionalidadArrendador");
        String antecedentes = doc.getString("Antecedentes");
        String fechaInicio = doc.getString("FechaInicio");
        String fechaFin = doc.getString("FechaFin");
        String valorMensual = doc.getString("ValorMensual");
        String garantia = doc.getString("Garantia");
        String correoArrendataria = doc.getString("CorreoArrendataria");
        String correoArrendador = doc.getString("CorreoArrendador");

        // Preparar ruta de salida
        String home = System.getProperty("user.home");
        String output = Paths.get(home, "Desktop", "Contrato_" + nombreArrendataria + ".pdf").toString();

        PdfReader reader = null;
        PdfStamper stamper = null;
        
        try {
            reader = new PdfReader(TEMPLATE);
            stamper = new PdfStamper(reader, new FileOutputStream(output));
            
            AcroFields form = stamper.getAcroFields();
            System.out.println("Campos detectados en el PDF:");
            for (String campo : form.getFields().keySet()) {
            System.out.println("si " + campo);
            }
            
            stamper.setFormFlattening(false);
            // Rellenar campos del arrendatario
            form.setField("NOMBREARRENDATARIA", nombreArrendataria);
            form.setField("RUCARRENDATARIA", rucArrendataria);
            form.setField("REPRESENTANTEARRENDATARIA", repArrendataria);
            form.setField("CARGOARRENDATARIA", cargoArrendataria);
            form.setField("NACIONALIDADARRENDATARIA", nacArrendataria);

            // Rellenar campos del arrendador
            form.setField("NOMBREARRENDADOR", nombreArrendador);
            form.setField("RUCARRENDADOR", rucArrendador);
            form.setField("REPRESENTANTEARRENDADOR", repArrendador);
            form.setField("CARGOARRENDADOR", cargoArrendador);
            form.setField("NACIONALIDADARRENDADOR", nacArrendador);

            // Rellenar campos generales
            form.setField("ANTECEDENTES", antecedentes);
            form.setField("FECHAINICIO", fechaInicio);
            form.setField("FECHAFIN", fechaFin);
            form.setField("VALORMENSUAL", valorMensual);
            form.setField("GARANTIA", garantia);

            // Rellenar información de contacto
            form.setField("CORREOARRENDATARIA", correoArrendataria);
            form.setField("CORREOARRENDADOR", correoArrendador);
            
            

            // Aplanar campos para hacer el PDF no editable
            //stamper.setFormFlattening(true);
            stamper.close();

            System.out.println("✅ Contrato generado exitosamente en: " + output);
            
        } catch (Exception e) {
            System.err.println("❌ Error al generar el contrato: " + e.getMessage());
            throw e;
        } finally {
            // Cerrar recursos manualmente
            if (stamper != null) {
                try {
                    stamper.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar PdfStamper: " + e.getMessage());
                }
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
}