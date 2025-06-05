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
import javax.swing.Timer;

/**
 *
 * @author Isabela
 */
public class LoginControlador implements ActionListener{
    private VISTA_VALIDACION vista_validacion;
    private Programa programa;
    private Usuario modelo;
    private Boolean LoginEstado;
    
    private int intentosFallidos = 0;
    private final int MAX_INTENTOS = 3;
    private final int TIEMPO_BLOQUEO = 5 * 60 * 1000; 
    
    public LoginControlador (VISTA_VALIDACION vista, Usuario modelo, Programa programa){
        this.vista_validacion=vista;
        this.modelo=modelo;
        this.programa = programa;
        this.vista_validacion.btnLogin.addActionListener(this);
    }
    public void iniciar()
    {
        vista_validacion.setTitle("Validación de usuario");
        vista_validacion.setLocationRelativeTo(null);
        vista_validacion.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        modelo.setNombreUsuario(vista_validacion.txtUsuario.getText());
        modelo.setContraseña(vista_validacion.txtContrasena.getText());
        this.LoginEstado = modelo.ValidarUsuarios();

        if (!this.LoginEstado) {
            intentosFallidos++;

            JOptionPane.showMessageDialog(null, "❌ Usuario o contraseña no válidos. Intento " + intentosFallidos + " de " + MAX_INTENTOS);
            vista_validacion.txtContrasena.setText("");
            vista_validacion.txtUsuario.setText("");
            vista_validacion.txtUsuario.requestFocusInWindow();

            if (intentosFallidos >= MAX_INTENTOS) {
                bloquearLogin();
            }
        } else {
            programa.setVisible(true);
            programa.setLocationRelativeTo(null);
            programa.setResizable(false);
            vista_validacion.dispose();     
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
