/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import com.mycompany.maqueteo_sistema_gestion_contratos.Controlador.LoginControlador;
import com.mycompany.maqueteo_sistema_gestion_contratos.Controlador.UsuarioControlador;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.FormularioContratoCivil;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.FormularioContratoLaboral;

/**
 *
 * @author pc
 */
public class ContratosMongoDB {
    
    private final LoginControlador loginControl;
    private final FormularioContratoCivil formCivil;
    private final FormularioContratoLaboral formLab;
    private final Usuario modelUser;

    public ContratosMongoDB(UsuarioControlador loginControl, FormularioContratoCivil formCivil, FormularioContratoLaboral formLab, Usuario modelUser) {
        this.loginControl = loginControl;
        this.formCivil = formCivil;
        this.formLab = formLab;
        this.modelUser = modelUser;
    }
    
    
    
}
