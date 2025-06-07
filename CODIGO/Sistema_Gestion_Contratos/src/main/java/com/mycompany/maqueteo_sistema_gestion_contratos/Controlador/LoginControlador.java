/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;

import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Isabela
 */
public class LoginControlador implements ActionListener{
    private final VISTA_VALIDACION vista_validacion;
    private final Programa programa;
    private final Usuario modelo;
    private final FormularioContratoCivil formCivil;
    private final FormularioContratoLaboral formLab;
    private Boolean LoginEstado;
    
    private int intentosFallidos = 0;
    private final int MAX_INTENTOS = 3;
    private final int TIEMPO_BLOQUEO = 5 * 60 * 1000; 
    
    public LoginControlador (VISTA_VALIDACION vista, Usuario modelo, Programa programa, FormularioContratoCivil formciv, FormularioContratoLaboral formlab){
        this.vista_validacion=vista;
        this.modelo=modelo;
        this.programa = programa;
        this.formCivil = formciv;
        this.formLab = formlab;
        this.vista_validacion.btnLogin.addActionListener(this);
        this.programa.BtnContratoCivil.addActionListener(this);
        this.programa.BtnContratoLaboral.addActionListener(this);
        
    }
    public void iniciarValidacion()
    {
        vista_validacion.setTitle("Validación de usuario");
        vista_validacion.setVisible(true);
        vista_validacion.setLocationRelativeTo(null);
        vista_validacion.setResizable(false);
    }
    public void iniciarPrograma()
    {
        programa.setTitle("Gestion de contratos");
        programa.setVisible(true);
        programa.setLocationRelativeTo(null);
        programa.setResizable(false);
    }
    private void iniciarContrato(int Tipo){
        switch (Tipo) {
            case 0:
                formCivil.setTitle("Contrato Civil");
                formCivil.setVisible(true);
                formCivil.setLocationRelativeTo(null);
                formCivil.setResizable(false);
                break;
            case 1:
                formLab.setTitle("Contrato Civil");
                formLab.setVisible(true);
                formLab.setLocationRelativeTo(null);
                formLab.setResizable(false);
                break;
            default:
                System.out.println("Error interno");
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==vista_validacion.btnLogin){
            
            modelo.setNombreUsuario(vista_validacion.txtUsuario.getText());
            modelo.setContraseña(vista_validacion.txtContrasena.getText());
            this.LoginEstado = modelo.validarUsuarios();

        if (!this.LoginEstado) {
            intentosFallidos++;

            JOptionPane.showMessageDialog(null, "❌ Usuario o contraseña no válidos. Intento " + intentosFallidos + " de " + MAX_INTENTOS);
            vista_validacion.txtContrasena.setText("");
            vista_validacion.txtUsuario.setText("");
            vista_validacion.txtUsuario.requestFocusInWindow();

            if (intentosFallidos >= MAX_INTENTOS) {
                bloquearLogin();
            }
            
        }   
        else{
            vista_validacion.dispose();
            iniciarPrograma();
            System.out.println("Acceso Exitoso");
            }
        }
        else if(e.getSource()==programa.BtnContratoCivil){
               System.out.println("Civil");
               iniciarContrato(0);
           }
        else if(e.getSource()==programa.BtnContratoLaboral){
               System.out.println("Laboral");
               iniciarContrato(1);
           }
    }

    
    private void bloquearLogin() {
        vista_validacion.btnLogin.setEnabled(false);
        JOptionPane.showMessageDialog(null, "🚫 Has excedido el número de intentos. El sistema estará bloqueado por 5 minutos.");

        
        Timer timer = new Timer(TIEMPO_BLOQUEO, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                intentosFallidos = 0;
                vista_validacion.btnLogin.setEnabled(true);
                JOptionPane.showMessageDialog(null, "🔓 Ya puedes volver a intentar el login.");
            }
        });

        timer.setRepeats(false); 
        timer.start();
    }
}
