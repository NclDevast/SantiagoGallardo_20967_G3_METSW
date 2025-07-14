/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoCollection;

import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Objects;

public class ContratoPdfGeneratoLab {

    private static final String TEMPLATE = "src/main/resources/CONTRATO LABORAL.pdf";

    /**
     * Genera el PDF usando el registro con id = insertedId.
     * Usa MongoDBCLaboral como servicio y la colección "ContratosLaboral".
     */
    public void generarContratoPDF(MongoDBCLaboral service, ObjectId insertedId) throws Exception {
        Objects.requireNonNull(service, "El servicio MongoDB no puede ser nulo");
        Objects.requireNonNull(insertedId, "El ID del documento no puede ser nulo");

        // 1) Obtener colección y documento
        MongoCollection<Document> coll = service.getCollection("ContratosLaboral");
        Document doc = coll.find(new Document("_id", insertedId)).first();
        if (doc == null) {
            throw new IllegalStateException("No se encontró el documento con ID: " + insertedId);
        }

        // 2) Extraer datos del BSON
        String ciudad             = doc.getString("Ciudad");
        String fechaContrato      = doc.getString("FechaContrato");
        String nombreEmpleado     = doc.getString("NombreEmpleado");
        String cedulaEmpleado     = doc.getString("CedulaEmpleado");
        String ciudadEmpleado     = doc.getString("CiudadEmpleado");
        String nombreTrabajador   = doc.getString("NombreTrabajador");
        String cedulaTrabajador   = doc.getString("CedulaTrabajador");
        String ciudadTrabajador   = doc.getString("CiudadTrabajador");
        String cargoTrabajador    = doc.getString("CargoTrabajador");
        String jornadasHoras      = doc.getString("JornadasHoras");
        String diasTrabajo        = doc.getString("DiasTrabajo");
        String fechaInicio        = doc.getString("FechaInicio");
        String monto              = doc.getString("Monto");
        String formaPago          = doc.getString("FormaPago");
        String lugarTrabajo       = doc.getString("LugarTrabajo");

        // 3) Preparar ruta de salida en el Escritorio
        String home   = System.getProperty("user.home");
        String output = Paths.get(home, "Desktop",
                                  "ContratoLaboral_" + nombreEmpleado + ".pdf")
                             .toString();

        PdfReader reader = null;
        PdfStamper stamper = null;

        try {
            // 4) Leer plantilla y crear stamper
            reader  = new PdfReader(TEMPLATE);
            stamper = new PdfStamper(reader, new FileOutputStream(output));
            AcroFields form = stamper.getAcroFields();

            // 5) (Opcional) Depurar campos disponibles
            System.out.println("Campos detectados en el PDF:");
            for (String campo : form.getFields().keySet()) {
                System.out.println(" → " + campo);
            }

            // 6) Rellenar los controles (los nombres en mayúsculas)
            form.setField("CIUDAD",            ciudad);
            form.setField("FECHACONTRATO",     fechaContrato);
            form.setField("NOMBREEMPLEADO",    nombreEmpleado);
            form.setField("CEDULAEMPLEADO",    cedulaEmpleado);
            form.setField("CIUDADEMPLEADO",    ciudadEmpleado);
            form.setField("CIUDADTRABAJADOR",  ciudadTrabajador);
            form.setField("NOMBRETRABAJADOR",  nombreTrabajador);
            form.setField("CEDULATRABAJADOR",  cedulaTrabajador);
            form.setField("CIUDADTRABAJADOR",  ciudadTrabajador);
            form.setField("CARGOTRABAJADOR",   cargoTrabajador);
            form.setField("JORNADASHORAS",     jornadasHoras);
            form.setField("DIASTRABAJO",       diasTrabajo);
            form.setField("FECHAINICIO",       fechaInicio);
            form.setField("MONTO",             monto);
            form.setField("FORMAPAGO",         formaPago);
            form.setField("LUGARTRABAJO",      lugarTrabajo);

            // 7) Mantener el PDF editable (sin aplanar) para confirmar valores
            stamper.setFormFlattening(false);
            stamper.close();
            reader.close();

            System.out.println("✅ Contrato generado exitosamente en: " + output);

        } catch (Exception e) {
            System.err.println("❌ Error al generar el contrato: " + e.getMessage());
            throw e;
        } finally {
            // Cierre seguro de recursos
            if (stamper != null) {
                try { stamper.close(); }
                catch (Exception ignored) {}
            }
            if (reader != null) {
                reader.close();
            }
        }
    }
}