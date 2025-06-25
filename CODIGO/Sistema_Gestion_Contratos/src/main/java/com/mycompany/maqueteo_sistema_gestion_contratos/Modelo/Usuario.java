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
    private String nombreUsuario;
    private String password;
    private String mongoURI;
            
    public Usuario() {
        this.mongoURI = "mongodb+srv://veromongoad:WNmXdUIJqJgRcbdv@cluster1contratos.fj8u3ak.mongodb.net/?retryWrites=true&w=majority&appName=Cluster1Contratos";
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contraseña) {
        this.password = contraseña;
    }

    public String getMongoURI() {
        return mongoURI;
    }

    public void setMongoURI(String mongoURI) {
        this.mongoURI = mongoURI;
    }
    
    public boolean validarUsuarios(String userTemp, String passTemp){
        System.out.println("Usuario"+this.nombreUsuario+"pssword:"+this.password);
        return this.nombreUsuario.equals(userTemp)&&this.password.equals(passTemp);
    }
    
    public boolean compararPass(String Pass1, String Pass2){
        return Pass1.equals(Pass2);
    }
}
