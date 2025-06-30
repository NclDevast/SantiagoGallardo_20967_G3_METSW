/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBCCivil;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import javax.swing.JTextField;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * TERMINAR DE IMPLEMENTAR METODOS ABSTRACTOS
 * @author Isabela
 */
public class ContratoCivilControlador implements ActionListener{
    private final FormularioContratoCivil formCivil;
    private final LoginControlador lgnCtrl;
    private final MongoDBCCivil mongoCiv;
    

    public ContratoCivilControlador(FormularioContratoCivil formCivil, LoginControlador lgnCtrl, Usuario userModel) {
        
        this.formCivil = formCivil;
        this.lgnCtrl = lgnCtrl;
        this.mongoCiv = new MongoDBCCivil (this.lgnCtrl.getModelo());
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
                || formCivil.txtCorreoArrendador.getText().isEmpty();

        formCivil.BtnGuardar.setEnabled(!camposVacios);
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

        // También añadimos el JTextArea "txtAntecedentes"
        formCivil.txtAntecedentes.getDocument().addDocumentListener(new DocumentListener() {
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

        public String[] obtenerTextosCamposCivil() {
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
        
    public FormularioContratoCivil getFormCivil() {
        return formCivil;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==formCivil.BtnLimpiar){
            mongoCiv.conectarMongo(obtenerTextosCamposCivil(), 0);
            System.out.println("Boton Guardar");
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}