/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_gestion_contratos.informes.Controlador;

import java.awt.event.ActionListener;
import sistema_gestion_contratos.informes.Modelo.Usuario;
import sistema_gestion_contratos.informes.Vista.VISTA_VALIDACION;

/**
 *
 * @author Isabela
 */
public class LoginControlador implements ActionListener{
    private VISTA_VALIDACION vista;
    private Usuario modelo;
    
    public LoginControlador (VISTA_VALIDACION vista, Usuario modelo){
        this.vista=vista;
        this.modelo=modelo;
        this.vista.btnLogin.addActionListener(this);
    }
}
