/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos;

import com.mycompany.maqueteo_sistema_gestion_contratos.Controlador.LoginControlador;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.Programa;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.VISTA_VALIDACION;



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
        Programa programa = new Programa();    
        Usuario mod= new Usuario();
        vista1.setVisible(true);
        vista1.setLocationRelativeTo(null);
        LoginControlador ctrl= new LoginControlador(vista1,mod,programa);
        ctrl.iniciar();

    }
    
}
