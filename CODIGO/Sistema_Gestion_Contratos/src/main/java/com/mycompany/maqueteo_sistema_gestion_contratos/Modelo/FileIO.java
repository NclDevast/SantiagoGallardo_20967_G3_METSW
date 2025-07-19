/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Modelo;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author gfa
 */
public class FileIO {
    public static BufferedImage readImageFile(Object requestor, String fileName){
        BufferedImage image = null;
        try{
            InputStream input = requestor.getClass().getResourceAsStream(fileName);
            image = ImageIO.read(input);
        }
        catch(Exception e){
            String message = "El archivo "+ fileName + " No se pudo abrir";
            JOptionPane.showMessageDialog(null, message);
        }
        return image;
    }
}
