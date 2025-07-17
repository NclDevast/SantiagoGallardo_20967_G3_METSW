/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;

import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.ContratoPdfGeneratoCiv;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBCCivil;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.FormularioContratoCivil;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.bson.types.ObjectId;

public class ContratoCivilControlador implements ActionListener {
    private final FormularioContratoCivil formCivil;
    private final LoginControlador lgnCtrl;
    private final MongoDBCCivil mongoCiv;

    public ContratoCivilControlador(FormularioContratoCivil formCivil, LoginControlador lgnCtrl, Usuario userModel) {
        this.formCivil = formCivil;
        this.lgnCtrl = lgnCtrl;
        this.mongoCiv = new MongoDBCCivil(userModel);
        this.formCivil.BtnGuardar.addActionListener(this);
        agregarValidacionAutomatica();
        Validacion();
    }

public void Validacion() {
    boolean camposVacios =
        formCivil.txtNombreArrendataria.getText().isEmpty()
        || formCivil.txtRucArrendataria.getText().isEmpty()
        || formCivil.txtRepresentanteArrendataria.getText().isEmpty()
        || formCivil.txtCargoArrendataria.getText().isEmpty()
        || formCivil.txtNacionalidadArrendataria.getText().isEmpty()
        || formCivil.txtNombreArrendador.getText().isEmpty()
        || formCivil.txtRucArrendador.getText().isEmpty()
        || formCivil.txtRepresentanteArrendador.getText().isEmpty()
        || formCivil.txtCargoArrendador.getText().isEmpty()
        || formCivil.txtNacionalidadArrendador.getText().isEmpty()
        || formCivil.txtAntecedentes.getText().isEmpty()
        || formCivil.txtFechaInicio.getText().isEmpty()
        || formCivil.txtFechaFin.getText().isEmpty()
        || formCivil.txtValorMensual.getText().isEmpty()
        || formCivil.txtFormaPago.getText().isEmpty()
        || formCivil.txtGarantia.getText().isEmpty()
        || formCivil.txtCorreoArrendataria.getText().isEmpty()
        || formCivil.txtCorreoArrendador.getText().isEmpty()
        || !esRucValido(formCivil.txtRucArrendataria.getText())
        || !esRucValido(formCivil.txtRucArrendador.getText());

    formCivil.BtnGuardar.setEnabled(!camposVacios);
    resaltarCampoRuc(); // Llama al resaltador después de validar
}

    private void agregarValidacionAutomatica() {
        JTextField[] camposTexto = {
            formCivil.txtNombreArrendataria,
            formCivil.txtRucArrendataria,
            formCivil.txtRepresentanteArrendataria,
            formCivil.txtCargoArrendataria,
            formCivil.txtNacionalidadArrendataria,
            formCivil.txtNombreArrendador,
            formCivil.txtRucArrendador,
            formCivil.txtRepresentanteArrendador,
            formCivil.txtCargoArrendador,
            formCivil.txtNacionalidadArrendador,
            formCivil.txtFechaInicio,
            formCivil.txtFechaFin,
            formCivil.txtValorMensual,
            formCivil.txtFormaPago,
            formCivil.txtGarantia,
            formCivil.txtCorreoArrendataria,
            formCivil.txtCorreoArrendador
        };

        for (JTextField campo : camposTexto) {
            campo.getDocument().addDocumentListener(new DocumentListener() {
                public void insertUpdate(DocumentEvent e) { 
                    Validacion(); 
                }
                public void removeUpdate(DocumentEvent e) { 
                    Validacion(); 
                }
                public void changedUpdate(DocumentEvent e) { 
                    Validacion(); 
                }
            });
        }


        formCivil.txtAntecedentes.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { 
                Validacion(); }

            public void removeUpdate(DocumentEvent e) { Validacion(); 
            }

            public void changedUpdate(DocumentEvent e) { 
                Validacion(); 
            }
        });
    }

    public String[] obtenerTextosCampos() {
        return new String[] {
            formCivil.txtNombreArrendataria.getText(),
            formCivil.txtRucArrendataria.getText(),
            formCivil.txtRepresentanteArrendataria.getText(),
            formCivil.txtCargoArrendataria.getText(),
            formCivil.txtNacionalidadArrendataria.getText(),
            formCivil.txtNombreArrendador.getText(),
            formCivil.txtRucArrendador.getText(),
            formCivil.txtRepresentanteArrendador.getText(),
            formCivil.txtCargoArrendador.getText(),
            formCivil.txtNacionalidadArrendador.getText(),
            formCivil.txtAntecedentes.getText(),
            formCivil.txtFechaInicio.getText(),
            formCivil.txtFechaFin.getText(),
            formCivil.txtValorMensual.getText(),
            formCivil.txtFormaPago.getText(),
            formCivil.txtGarantia.getText(),
            formCivil.txtCorreoArrendataria.getText(),
            formCivil.txtCorreoArrendador.getText()
        };
    }

    public void iniciarContrato() {
        formCivil.setTitle("Contrato Civil");
        formCivil.setVisible(true);
        formCivil.setLocationRelativeTo(null);
        formCivil.setResizable(false);
        Validacion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formCivil.BtnGuardar) {
            mongoCiv.conectarMongo(obtenerTextosCampos(), 0); 
            System.out.println("Contrato Civil guardado en MongoDB");
            ObjectId idNuevo = mongoCiv.insertarYObtenerId(obtenerTextosCampos());
            System.out.println("Documento insertado con _id=" + idNuevo);

            try {
            ContratoPdfGeneratoCiv pdfGen = new ContratoPdfGeneratoCiv();
            pdfGen.generarContratoPDF(mongoCiv, idNuevo);
            System.out.println("Contrato PDF generado correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    }
    private boolean esRucValido(String ruc) {
    return ruc.matches("^\\d{10}001$");
}
    private void resaltarCampoRuc() {
    if (!esRucValido(formCivil.txtRucArrendataria.getText())) {
        formCivil.txtRucArrendataria.setBackground(Color.PINK);
    } else {
        formCivil.txtRucArrendataria.setBackground(Color.WHITE);
    }

    if (!esRucValido(formCivil.txtRucArrendador.getText())) {
        formCivil.txtRucArrendador.setBackground(Color.PINK);
    } else {
        formCivil.txtRucArrendador.setBackground(Color.WHITE);
    }
}
}