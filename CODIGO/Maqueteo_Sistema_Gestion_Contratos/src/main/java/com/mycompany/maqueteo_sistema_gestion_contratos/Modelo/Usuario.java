/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

/**
 *
 * @author Isabela
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contraseña;

    public Usuario(){
        
    }
 
    public Usuario(int id, String nombreUsuario, String contraseña) {
        this.id = id;
        this.nombreUsuario = "Veronica";
        this.contraseña = "1234";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public boolean ValidarUsuarios(){
       if(this.nombreUsuario.equals("Veronica")&&this.contraseña.equals("1234")){
           return true;
       }else 
           return false;
    }
}
