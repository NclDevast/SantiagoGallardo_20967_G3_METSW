/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.*;
/**
 *
 * @author Isabela
 */
public class ContratoCivilControlador {
    private final FormularioContratoCivil formCivil;
    
    public ContratoCivilControlador(FormularioContratoCivil formCivil){
          this.formCivil = formCivil;
    }

    public void Validacion() {
        if (formCivil.txtNombreArrendataria.getText().isEmpty()
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
                || formCivil.txtCorreoArrendador.getText().isEmpty()) {

            formCivil.btnGuardar1.setEnabled(false);
        } else {
            formCivil.btnGuardar1.setEnabled(true);
        }
    }

    public FormularioContratoCivil getFormCivil() {
        return formCivil;
    }
}