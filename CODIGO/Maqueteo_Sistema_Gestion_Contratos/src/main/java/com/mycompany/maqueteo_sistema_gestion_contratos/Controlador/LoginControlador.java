/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;

import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.Programa;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.VISTA_VALIDACION;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Isabela
 */
public class LoginControlador implements ActionListener{
    private VISTA_VALIDACION vista_validacion;
    private Programa programa;
    private Usuario modelo;
    private Boolean LoginEstado;
    
    public LoginControlador (VISTA_VALIDACION vista, Usuario modelo, Programa programa){
        this.vista_validacion=vista;
        this.modelo=modelo;
        this.vista_validacion.btnLogin.addActionListener(this);
        this.programa = programa;
    }
    public void iniciar()
    {
        vista_validacion.setTitle("Validación de usuario");
        vista_validacion.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e){
        modelo.setNombreUsuario(vista_validacion.txtUsuario.getText());
        modelo.setContraseña(vista_validacion.txtContrasena.getText());
        this.LoginEstado = modelo.ValidarUsuarios();
        if(!this.LoginEstado){
        JOptionPane.showMessageDialog(null,"usuario o contraseña no valido");
        vista_validacion.txtContrasena.setText("");
        vista_validacion.txtUsuario.setText("");
        vista_validacion.txtUsuario.requestFocusInWindow();
        }
        else {
        programa.setVisible(true);
        programa.setLocationRelativeTo(null);
        }
        
    }
    
}
