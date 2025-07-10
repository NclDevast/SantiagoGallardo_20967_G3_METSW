/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;

import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBBusqueda;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.FormularioContratoCivilBusqueda;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.FormularioContratoLaboralBusqueda;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.MenuBusqueda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Isabela
 */
public class BusquedaContratosControlador implements ActionListener{
    
    private final MenuBusqueda menuBusqueda;
    private final Usuario userModel;
    private final MongoDBBusqueda mongoDBbusqueda;
    private final FormularioContratoCivilBusqueda formCivBus;
    private final FormularioContratoLaboralBusqueda formLabBus;

    public BusquedaContratosControlador(Usuario userModel) {
        this.menuBusqueda = new MenuBusqueda();
        this.userModel = userModel;
        this.mongoDBbusqueda = new MongoDBBusqueda(userModel);
        this.formCivBus = new FormularioContratoCivilBusqueda();
        this.formLabBus = new FormularioContratoLaboralBusqueda();
        this.menuBusqueda.BtnBusquedaLab.addActionListener(this);
        this.menuBusqueda.BtnCivilBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menuBusqueda.BtnCivilBuscar) {
            String[] nombresCampos = this.mongoDBbusqueda
                .buscarContrato(this.menuBusqueda.txtRuc.getText(), 0);
            this.cambiarCampos(nombresCampos, 0);
            this.formCivBus.setVisible(true);
            this.formCivBus.setResizable(false);

        }
        if (e.getSource() == this.menuBusqueda.BtnBusquedaLab) {
            String[] nombresCampos = this.mongoDBbusqueda
                .buscarContrato(this.menuBusqueda.txtNumCed.getText(), 1);
            this.cambiarCampos(nombresCampos, 1);
            this.formLabBus.setVisible(true);
            this.formLabBus.setResizable(false);
        }
    }

private void cambiarCampos(String[] campos, int tipo) {
    switch (tipo) {
        case 0: // civil
            if (campos != null && campos.length >= 18) {
                formCivBus.txtNombreArrendataria.setText(campos[0]);
                formCivBus.txtRucArrendataria.setText(campos[1]);
                formCivBus.txtRepresentanteArrendataria.setText(campos[2]);
                formCivBus.txtCargoArrendataria.setText(campos[3]);
                formCivBus.txtNacionalidadArrendataria.setText(campos[4]);
                formCivBus.txtCorreoArrendataria.setText(campos[5]);

                formCivBus.txtNombreArrendador.setText(campos[6]);
                formCivBus.txtRucArrendador.setText(campos[7]);
                formCivBus.txtRepresentanteArrendador.setText(campos[8]);
                formCivBus.txtCargoArrendador.setText(campos[9]);
                formCivBus.txtNacionalidadArrendador.setText(campos[10]);
                formCivBus.txtCorreoArrendador.setText(campos[11]);

                formCivBus.txtAntecedentes.setText(campos[12]);
                formCivBus.txtFechaInicio.setText(campos[13]);
                formCivBus.txtFechaFin.setText(campos[14]);
                formCivBus.txtValorMensual.setText(campos[15]);
                formCivBus.txtFormaPago.setText(campos[16]);
                formCivBus.txtGarantia.setText(campos[17]);
            }
            break;

        case 1: // laboral
            if (campos != null && campos.length >= 15) {
                formLabBus.txtCiudad.setText(campos[0]);
                formLabBus.txtFechaContrato.setText(campos[1]);
                formLabBus.txtNombreEmpleador.setText(campos[2]);
                formLabBus.txtCedulaEmpleador.setText(campos[3]);
                formLabBus.txtCiudadEmpleador.setText(campos[4]);
                formLabBus.txtNombreTrabajador.setText(campos[5]);
                formLabBus.txtCedulaTrabajador.setText(campos[6]);
                formLabBus.txtCiudadTrabajador.setText(campos[7]);
                formLabBus.txtCargoTrabajador.setText(campos[8]);
                formLabBus.txtJornadasHoras.setText(campos[9]);
                formLabBus.txtDiasTrabajo.setText(campos[10]);
                formLabBus.txtFechaInicio.setText(campos[11]);
                formLabBus.txtMonto.setText(campos[12]);
                formLabBus.txtFormaPago.setText(campos[13]);
                formLabBus.txtLugarTrabajo.setText(campos[14]);
            }
            break;
        }
    }
    public void iniciarBusqueda(){
        this.menuBusqueda.setVisible(true);
        this.menuBusqueda.setResizable(false);
        this.menuBusqueda.setLocationRelativeTo(null);
    }
    
}
