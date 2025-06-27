/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.*;
/**
 *
 * @author Isabela
 */
public class ContratoLaboralControlador {
    private final FormularioContratoLaboral formLab;
    
     public ContratoLaboralControlador(FormularioContratoLaboral formlab){
          this.formLab = formlab;
    }
    public void Validacion() {
        if (formLab.txtCiudad.getText().isEmpty()
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
                || formLab.txtLugarTrabajo.getText().isEmpty()) {

            formLab.btnGuardar.setEnabled(false);
        } else {
            formLab.btnGuardar.setEnabled(true);
        }
    }

      public FormularioContratoLaboral getFormLab() {
        return formLab;
    }
     
}
