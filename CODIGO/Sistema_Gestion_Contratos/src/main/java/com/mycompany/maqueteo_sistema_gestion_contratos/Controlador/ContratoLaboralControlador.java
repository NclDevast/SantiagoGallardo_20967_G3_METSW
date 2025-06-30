/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;
import com.mongodb.client.MongoCollection;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBContratos;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.bson.Document;
/**
 *TERMINAR DE IMPLEMENTAR METODOS ABSTRACTOS
 * @author Isabela
 */
public class ContratoLaboralControlador extends MongoDBContratos implements ActionListener{
    private final FormularioContratoLaboral formLab;
    private final LoginControlador lgnCtrl;

    public ContratoLaboralControlador(FormularioContratoLaboral formLab, LoginControlador lgnCtrl, Usuario userModel) {
        super(userModel);
        this.formLab = formLab;
        this.lgnCtrl = lgnCtrl;
        agregarValidacionAutomatica();
        Validacion(); // Verifica al iniciar (los campos estarán vacíos)
    }

    public void Validacion() {
        boolean camposVacios =
                formLab.txtCiudad.getText().isEmpty()
                || formLab.txtFechaContrato.getText().isEmpty()
                || formLab.txtNombreEmpleador.getText().isEmpty()
                || formLab.txtCedulaEmpleador.getText().isEmpty()
                || formLab.txtCiudadEmpleador.getText().isEmpty()
                || formLab.txtNombreTrabajador.getText().isEmpty()
                || formLab.txtCedulaTrabajador.getText().isEmpty()
                || formLab.txtCiudadTrabajador.getText().isEmpty()
                || formLab.txtCargoTrabajador.getText().isEmpty()
                || formLab.txtJornadasHoras.getText().isEmpty()
                || formLab.txtDiasTrabajo.getText().isEmpty()
                || formLab.txtFechaInicio.getText().isEmpty()
                || formLab.txtMonto.getText().isEmpty()
                || formLab.txtFormaPago.getText().isEmpty()
                || formLab.txtLugarTrabajo.getText().isEmpty();

        formLab.btnGuardar.setEnabled(!camposVacios);
    }

    private void agregarValidacionAutomatica() {
        JTextField[] camposTexto = {
            formLab.txtCiudad,
            formLab.txtFechaContrato,
            formLab.txtNombreEmpleador,
            formLab.txtCedulaEmpleador,
            formLab.txtCiudadEmpleador,
            formLab.txtNombreTrabajador,
            formLab.txtCedulaTrabajador,
            formLab.txtCiudadTrabajador,
            formLab.txtCargoTrabajador,
            formLab.txtJornadasHoras,
            formLab.txtDiasTrabajo,
            formLab.txtFechaInicio,
            formLab.txtMonto,
            formLab.txtFormaPago,
            formLab.txtLugarTrabajo
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
    }

    public FormularioContratoLaboral getFormLab() {
        return formLab;
    }
    
        public String[] obtenerTextosCampos() {
        return new String[] {
            formLab.txtCiudad.getText(),
            formLab.txtFechaContrato.getText(),
            formLab.txtNombreEmpleador.getText(),
            formLab.txtCedulaEmpleador.getText(),
            formLab.txtCiudadEmpleador.getText(),
            formLab.txtNombreTrabajador.getText(),
            formLab.txtCedulaTrabajador.getText(),
            formLab.txtCiudadTrabajador.getText(),
            formLab.txtCargoTrabajador.getText(),
            formLab.txtJornadasHoras.getText(),
            formLab.txtDiasTrabajo.getText(),
            formLab.txtFechaInicio.getText(),
            formLab.txtMonto.getText(),
            formLab.txtFormaPago.getText(),
            formLab.txtLugarTrabajo.getText()
        };
    }
        private void insertarContrato(String [] datos, MongoCollection<Document> collection){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}