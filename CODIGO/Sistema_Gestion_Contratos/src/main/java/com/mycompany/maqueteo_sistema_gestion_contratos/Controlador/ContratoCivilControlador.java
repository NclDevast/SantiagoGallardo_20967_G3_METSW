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
          this.formLab = formlab;
    }
          
    
    
    public void Validacion(){
        if (txtNombreArrendataria.getText().isEmpty()||txtRucArrendataria.getText().isEmpty()
                || txtRepresentanteArrendataria.getText.isEmpty() || txtCargoArrendataria.getText.isEmpty()||
                txtNacionalidadArrendataria.getText.isEmpty()||txtNombreArrendador.getText.isEmpty() || 
                txtRucArrendador.getText.isEmpty()|| txtRepresentanteArrendador.getText.isEmpty()|| txtCargoArrendador.getText().isEmpty()||
                txtNacionalidadArrendador.getText.isEmpty()||txtAntecedentes.getText.isEmpty()||txtFechaInicio.getText.isEmpty()||
                txtFechaFin.getText.isEmpty()||txtValorMensual.getText.isEmpty()||txtFormaPago.getText.isEmpty()||
                txtGarantia.getText.isEmpty()||txtCorreoArrendataria.getText.isEmpty()||txtCorreoArrendador.getText.isEmpty()||
                txtAntecedentes.getText.isEmpty()){
            
             btnGuardar.setEnabled(false);
}
    else
        {
            btnGuardar.setEnabled(true);
        }
    }
     public FormularioContratoCivil getFormCivil() {
        return formCivil;
    }
}
