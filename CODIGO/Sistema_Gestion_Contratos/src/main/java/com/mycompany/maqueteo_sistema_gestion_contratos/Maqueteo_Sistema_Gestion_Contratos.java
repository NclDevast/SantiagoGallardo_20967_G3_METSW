/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos;

import com.mycompany.maqueteo_sistema_gestion_contratos.Controlador.*;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.*;



/**
 *
 * @author Isabela
 */
public class Maqueteo_Sistema_Gestion_Contratos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        VISTA_VALIDACION vista1 =new VISTA_VALIDACION();
        Confirmacion confirmacion = new Confirmacion();
        Programa programa = new Programa();    
        Usuario mod= new Usuario();
        FormularioContratoCivil formCivil= new FormularioContratoCivil();
        FormularioContratoLaboral formLab = new FormularioContratoLaboral();
        VistaDatosUsuario datosUsuario = new VistaDatosUsuario();
        UsuarioControlador usuarioctrl = new UsuarioControlador(mod, datosUsuario,confirmacion);
        LoginControlador ctrl= new LoginControlador(vista1,mod,programa,formCivil,formLab,usuarioctrl);
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);

        ctrl.iniciarValidacion();

    }
    
}
