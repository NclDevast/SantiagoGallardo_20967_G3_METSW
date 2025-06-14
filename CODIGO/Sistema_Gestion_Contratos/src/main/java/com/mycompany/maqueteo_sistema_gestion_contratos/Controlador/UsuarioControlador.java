/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBUpdate;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.VistaDatosUsuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.Confirmacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gfa
 */
public class UsuarioControlador implements ActionListener{
    private final Usuario modelo;
    private final VistaDatosUsuario vistadatos;
    private final Confirmacion confirmacion;
    private enum AccionConfirmacion { NINGUNA, CAMBIO_USER, CAMBIO_PASSWORD }
    private AccionConfirmacion accion = AccionConfirmacion.NINGUNA;
    private String nuevaPasswordPendiente = ""; // almacena temporalmente

    public UsuarioControlador(Usuario modelo, VistaDatosUsuario vistadatos, Confirmacion confirmacion) {
        this.modelo = modelo;
        this.vistadatos = vistadatos;
        this.confirmacion = confirmacion;
        this.confirmacion.jButtonSi.addActionListener(this);
        this.confirmacion.jButtonNo.addActionListener(this);
        this.vistadatos.BtnCambioPassword.addActionListener(this);
        this.vistadatos.BtnCambioUser.addActionListener(this);
        this.vistadatos.jButtonRegresar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistadatos.jButtonRegresar) {
            vistadatos.dispose();
        }

        else if (e.getSource() == vistadatos.BtnCambioUser) {
            accion = AccionConfirmacion.CAMBIO_USER;
            iniciarConfirmacion();
        }

        else if (e.getSource() == vistadatos.BtnCambioPassword) {
            String passNueva = vistadatos.jPasswordnew.getText();
            String passConfir = vistadatos.jPasswordConfir.getText();
            String passActual = vistadatos.jPasswordactual.getText();

            boolean passConf = passNueva.equals(passConfir);
            boolean passVer = modelo.getPassword().equals(passActual);

            if (!passVer) {
                JOptionPane.showMessageDialog(null, "La contraseña actual es incorrecta.");
            } else if (!passConf) {
                JOptionPane.showMessageDialog(null, "Las nuevas contraseñas no coinciden.");
            } else {
                // Ambas condiciones se cumplieron
                nuevaPasswordPendiente = passConfir;  // almacenar temporalmente
                accion = AccionConfirmacion.CAMBIO_PASSWORD;
                iniciarConfirmacion();  // abrir ventana de confirmación
            }
        }

        else if (e.getSource() == confirmacion.jButtonSi) {
            if (accion == AccionConfirmacion.CAMBIO_USER) {
                initDB(0,vistadatos.jTextUser.getText());
                modelo.setNombreUsuario(vistadatos.jTextUser.getText());
                System.out.println("Usuario cambiado correctamente a " + modelo.getNombreUsuario());
            } 
            else if (accion == AccionConfirmacion.CAMBIO_PASSWORD) {
                modelo.setPassword(nuevaPasswordPendiente);
                initDB(1,nuevaPasswordPendiente);
                JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente");
                nuevaPasswordPendiente = "";
            }
            confirmacion.dispose();
            accion = AccionConfirmacion.NINGUNA;
        }

        else if (e.getSource() == confirmacion.jButtonNo) {
            nuevaPasswordPendiente = "";
            confirmacion.dispose();
            accion = AccionConfirmacion.NINGUNA;
        }
    }
    public void iniciarVistaDatos(){
        System.out.println("Vista Datos");
        vistadatos.setLocationRelativeTo(null);
        vistadatos.setResizable(false);
        vistadatos.setVisible(true);
        vistadatos.setTitle("Datos de usuario");
        vistadatos.jTextUser.setText(modelo.getNombreUsuario());
    }
    
    public void iniciarConfirmacion(){
        confirmacion.setLocationRelativeTo(null);
        confirmacion.setVisible(true);
        confirmacion.setTitle("Confirmacion");
        confirmacion.setResizable(false);
    }

    public Usuario getModelo() {
        return modelo;
    }
    
    private void initDB(int tipo, String temp){
        MongoDBUpdate mongoDBUpdate = new MongoDBUpdate(this);
        mongoDBUpdate.cambiarDatos(tipo, temp);
    }
}
