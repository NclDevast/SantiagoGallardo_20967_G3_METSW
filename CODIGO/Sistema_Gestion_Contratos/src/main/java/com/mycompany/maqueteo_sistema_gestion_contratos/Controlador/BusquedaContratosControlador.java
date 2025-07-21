/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maqueteo_sistema_gestion_contratos.Controlador;

import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.ContratoPdfGeneratoCiv;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.ContratoPdfGeneratoLab;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBBusqueda;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBCCivil;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.MongoDBCLaboral;
import com.mycompany.maqueteo_sistema_gestion_contratos.Modelo.Usuario;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.FormularioContratoCivilBusqueda;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.FormularioContratoLaboralBusqueda;
import com.mycompany.maqueteo_sistema_gestion_contratos.Vista.MenuBusqueda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.types.ObjectId;

public class BusquedaContratosControlador implements ActionListener {

    private final MenuBusqueda menuBusqueda;
    private final Usuario userModel;
    private final MongoDBBusqueda mongoDBbusqueda;
    private final FormularioContratoCivilBusqueda formCivBus;
    private final FormularioContratoLaboralBusqueda formLabBus;

    private ObjectId idCivilBuscado = null;
    private ObjectId idLaboralBuscado = null;

    public BusquedaContratosControlador(Usuario userModel) {
        this.menuBusqueda = new MenuBusqueda();
        this.userModel = userModel;
        this.mongoDBbusqueda = new MongoDBBusqueda(userModel);
        this.formCivBus = new FormularioContratoCivilBusqueda();
        this.formLabBus = new FormularioContratoLaboralBusqueda();

        this.menuBusqueda.BtnBusqueda.addActionListener(this);
        this.menuBusqueda.BtnRadioRUC.addActionListener(this);
        this.menuBusqueda.BtnRadioCedula.addActionListener(this);
        this.formCivBus.btnEliminarCivil.addActionListener(this);
        this.formLabBus.btnEliminarLaboral.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menuBusqueda.BtnBusqueda && this.menuBusqueda.BtnRadioRUC.isSelected()) {
            String clave = this.menuBusqueda.txtBusqueda.getText();
            String[] camposCivil = mongoDBbusqueda.buscarContrato(clave, 0);
            idCivilBuscado = mongoDBbusqueda.obtenerIdContrato(clave, 0);

            if (camposCivil == null || idCivilBuscado == null) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún contrato civil con ese RUC.",
                        "Contrato no registrado", JOptionPane.WARNING_MESSAGE);
            } else {
                cambiarCampos(camposCivil, 0);
                formCivBus.setVisible(true);
                formCivBus.setResizable(false);

                try {
                    MongoDBCCivil servicio = new MongoDBCCivil(userModel);
                    new ContratoPdfGeneratoCiv().generarContratoPDF(servicio, idCivilBuscado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al generar el PDF civil.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == this.menuBusqueda.BtnBusqueda && this.menuBusqueda.BtnRadioCedula.isSelected()) {
            String clave = this.menuBusqueda.txtBusqueda.getText();
            String[] camposLab = mongoDBbusqueda.buscarContrato(clave, 1);
            idLaboralBuscado = mongoDBbusqueda.obtenerIdContrato(clave, 1);

            if (camposLab == null || idLaboralBuscado == null) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún contrato laboral con esa cédula.",
                        "Contrato no registrado", JOptionPane.WARNING_MESSAGE);
            } else {
                cambiarCampos(camposLab, 1);
                formLabBus.setVisible(true);
                formLabBus.setResizable(false);

                try {
                    MongoDBCLaboral servicio = new MongoDBCLaboral(userModel);
                    new ContratoPdfGeneratoLab().generarContratoPDF(servicio, idLaboralBuscado);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al generar el PDF laboral.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }

        if (e.getSource() == formCivBus.btnEliminarCivil) {
            eliminarContrato(idCivilBuscado, 0);
        }

        if (e.getSource() == formLabBus.btnEliminarLaboral) {
            eliminarContrato(idLaboralBuscado, 1);
        }
    }

    private void cambiarCampos(String[] campos, int tipo) {
        switch (tipo) {
            case 0:
                if (campos.length >= 18) {
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

            case 1:
                if (campos.length >= 15) {
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

    public void eliminarContrato(ObjectId idContrato, int tipo) {
        if (idContrato == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el contrato para eliminar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (tipo == 0) {
                MongoDBCCivil servicio = new MongoDBCCivil(userModel);
                servicio.getCollection("ContratosCivil")
                        .deleteOne(new Document("_id", idContrato));
                JOptionPane.showMessageDialog(null, "✅ Contrato civil eliminado correctamente.");
                formCivBus.dispose();
            } else {
                MongoDBCLaboral servicio = new MongoDBCLaboral(userModel);
                servicio.getCollection("ContratosLaboral")
                        .deleteOne(new Document("_id", idContrato));
                JOptionPane.showMessageDialog(null, "✅ Contrato laboral eliminado correctamente.");
                formLabBus.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "❌ Error al eliminar el contrato: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void iniciarBusqueda() {
        this.menuBusqueda.setVisible(true);
        this.menuBusqueda.setResizable(false);
        this.menuBusqueda.setLocationRelativeTo(null);
    }
}