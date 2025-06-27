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
          this.formCivil = formciv;
    }
     public void Validacion(){
        if (txtCiudad.getText().isEmpty()|txtFechaContrato.getText().isEmpty()|| txtNombreEmpleador.getText.isEmpty() || 
                txtCedulaEmpleador.getText.isEmpty()||txtCiudadEmpleador.getText.isEmpty()||txtNombreTrabajador.getText.isEmpty() || 
                txtCedulaTrabajador.getText.isEmpty()|| txtCiudadTrabajador.getText.isEmpty()|| txtCargoTrabajador.getText().isEmpty()||
                txtJornadasHoras.getText.isEmpty()||txtDiasTrabajo.getText.isEmpty()||txtFechaInicio.getText.isEmpty()||
                txtMonto.getText.isEmpty()||txtFormaPago.getText.isEmpty()||txtFechaInicio.getText.isEmpty()||
                txtLugarTrabajo.getText.isEmpty()){
            
             btnGuardar.setEnabled(false);
}
    else
        {
            btnGuardar.setEnabled(true);
        }
    }
      public FormularioContratoLaboral getFormLab() {
        return formLab;
    }
     
}
