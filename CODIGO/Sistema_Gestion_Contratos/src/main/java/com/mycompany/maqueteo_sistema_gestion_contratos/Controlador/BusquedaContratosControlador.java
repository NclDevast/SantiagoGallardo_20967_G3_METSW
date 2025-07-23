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
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
    private Boolean isEditable = false;
    

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
        this.formLabBus.BtnEditarLab.addActionListener(this);
        this.formCivBus.BtnEditarCivil.addActionListener(this);
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
                //this.mongoDBbusqueda.closeMongoConnection();
            } else {
                cambiarCampos(camposCivil, 0);
                formCivBus.setVisible(true);
                formCivBus.setResizable(false);
                //this.mongoDBbusqueda.closeMongoConnection();
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
                //this.mongoDBbusqueda.closeMongoConnection();
            } else {
                cambiarCampos(camposLab, 1);
                formLabBus.setVisible(true);
                formLabBus.setResizable(false);
                //this.mongoDBbusqueda.closeMongoConnection();
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
            //this.mongoDBbusqueda.closeMongoConnection();
        }

        if (e.getSource() == formLabBus.btnEliminarLaboral) {
            eliminarContrato(idLaboralBuscado, 1);
            //this.mongoDBbusqueda.closeMongoConnection();
        }
if (e.getSource() == formCivBus.BtnEditarCivil) {
    // Validar RUCs antes de editar
    boolean ruc1Valido = esRucValido(formCivBus.txtRucArrendataria.getText());
    boolean ruc2Valido = esRucValido(formCivBus.txtRucArrendador.getText());

    resaltarCampo(formCivBus.txtRucArrendataria, ruc1Valido);
    resaltarCampo(formCivBus.txtRucArrendador, ruc2Valido);

    if (!ruc1Valido || !ruc2Valido) {
        JOptionPane.showMessageDialog(null,
            "RUC inválido. Debe tener 13 dígitos y terminar en 001.",
            "Error de Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Alternar modo de edición
    if (!isEditable) {
        isEditable = setEditable(0, isEditable);
        System.out.println("Botón Editar activado");
    } else {
        mongoDBbusqueda.updateMongoDB(0, idCivilBuscado, obtenerTextosCampos(0));
        isEditable = setEditable(0, isEditable);
        try {
            MongoDBCCivil servicio = new MongoDBCCivil(userModel);
            new ContratoPdfGeneratoCiv().generarContratoPDF(servicio, idCivilBuscado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF civil.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        System.out.println("Botón Editar desactivado");
    }
}

        
if (e.getSource() == formLabBus.BtnEditarLab) {
    // Validar cédulas antes de editar
    boolean ced1Valido = esCedulaValida(formLabBus.txtCedulaEmpleador.getText());
    boolean ced2Valido = esCedulaValida(formLabBus.txtCedulaTrabajador.getText());

    resaltarCampo(formLabBus.txtCedulaEmpleador, ced1Valido);
    resaltarCampo(formLabBus.txtCedulaTrabajador, ced2Valido);

    if (!ced1Valido || !ced2Valido) {
        JOptionPane.showMessageDialog(null,
            "Cédula inválida. Debe tener exactamente 10 dígitos.",
            "Error de Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Alternar modo de edición
    if (!isEditable) {
        isEditable = setEditable(1, isEditable);
    } else {
        mongoDBbusqueda.updateMongoDB(1, idLaboralBuscado, obtenerTextosCampos(1));
        isEditable = setEditable(1, isEditable);
        try {
            MongoDBCLaboral servicio = new MongoDBCLaboral(userModel);
            new ContratoPdfGeneratoLab().generarContratoPDF(servicio, idLaboralBuscado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF laboral.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
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
    
    private boolean setEditable(int tipo, Boolean bool){
        
        switch(tipo){
            case 0: //civil
                formCivBus.txtNombreArrendataria.setEditable(!bool);
                formCivBus.txtRucArrendataria.setEditable(!bool);
                formCivBus.txtRepresentanteArrendataria.setEditable(!bool);
                formCivBus.txtCargoArrendataria.setEditable(!bool);
                formCivBus.txtNacionalidadArrendataria.setEditable(!bool);
                formCivBus.txtCorreoArrendataria.setEditable(!bool);

                formCivBus.txtNombreArrendador.setEditable(!bool);
                formCivBus.txtRucArrendador.setEditable(!bool);
                formCivBus.txtRepresentanteArrendador.setEditable(!bool);
                formCivBus.txtCargoArrendador.setEditable(!bool);
                formCivBus.txtNacionalidadArrendador.setEditable(!bool);
                formCivBus.txtCorreoArrendador.setEditable(!bool);

                formCivBus.txtAntecedentes.setEditable(!bool);
                formCivBus.txtFechaInicio.setEditable(!bool);
                formCivBus.txtFechaFin.setEditable(!bool);
                formCivBus.txtValorMensual.setEditable(!bool);
                formCivBus.txtFormaPago.setEditable(!bool);
                formCivBus.txtGarantia.setEditable(!bool);
                return !bool;
            case 1: //laboral
                    formLabBus.txtCiudad.setEditable(!bool);
                    formLabBus.txtFechaContrato.setEditable(!bool);
                    formLabBus.txtNombreEmpleador.setEditable(!bool);
                    formLabBus.txtCedulaEmpleador.setEditable(!bool);
                    formLabBus.txtCiudadEmpleador.setEditable(!bool);
                    formLabBus.txtNombreTrabajador.setEditable(!bool);
                    formLabBus.txtCedulaTrabajador.setEditable(!bool);
                    formLabBus.txtCiudadTrabajador.setEditable(!bool);
                    formLabBus.txtCargoTrabajador.setEditable(!bool);
                    formLabBus.txtJornadasHoras.setEditable(!bool);
                    formLabBus.txtDiasTrabajo.setEditable(!bool);
                    formLabBus.txtFechaInicio.setEditable(!bool);
                    formLabBus.txtMonto.setEditable(!bool);
                    formLabBus.txtFormaPago.setEditable(!bool);
                    formLabBus.txtLugarTrabajo.setEditable(!bool);
                    return !bool;
        }
        return false;
    }
    public void eliminarContrato(ObjectId idContrato, int tipo) {
    if (idContrato == null) {
        JOptionPane.showMessageDialog(null,
            "No se encontró el contrato para eliminar.",
            "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 🔔 Ventana de confirmación personalizada
    int opcion = JOptionPane.showConfirmDialog(
        null,
        "🗑️ ¿Estás segura que deseas eliminar este contrato?\nEsta acción no se puede deshacer.",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );

    if (opcion != JOptionPane.YES_OPTION) {
        JOptionPane.showMessageDialog(null, "Operación cancelada.");
        return;
    }

    try {
        if (tipo == 0) {
            MongoDBCCivil servicio = new MongoDBCCivil(userModel);
            servicio.getCollection("ContratosCivil")
                    .deleteOne(new Document("_id", idContrato));
            JOptionPane.showMessageDialog(null,
                "✅ Contrato civil eliminado correctamente.");
            formCivBus.dispose();
        } else {
            MongoDBCLaboral servicio = new MongoDBCLaboral(userModel);
            servicio.getCollection("ContratosLaboral")
                    .deleteOne(new Document("_id", idContrato));
            JOptionPane.showMessageDialog(null,
                "✅ Contrato laboral eliminado correctamente.");
            formLabBus.dispose();
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null,
            "❌ Error al eliminar el contrato: " + ex.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
}

    public void iniciarBusqueda() {
        this.menuBusqueda.setVisible(true);
        this.menuBusqueda.setResizable(false);
        this.menuBusqueda.setLocationRelativeTo(null);
    }
    
    public String[] obtenerTextosCampos(int tipo) {
        
        switch(tipo){
            case 0:
                return new String[] {
            formCivBus.txtNombreArrendataria.getText(),
            formCivBus.txtRucArrendataria.getText(),
            formCivBus.txtRepresentanteArrendataria.getText(),
            formCivBus.txtCargoArrendataria.getText(),
            formCivBus.txtNacionalidadArrendataria.getText(),
            formCivBus.txtNombreArrendador.getText(),
            formCivBus.txtRucArrendador.getText(),
            formCivBus.txtRepresentanteArrendador.getText(),
            formCivBus.txtCargoArrendador.getText(),
            formCivBus.txtNacionalidadArrendador.getText(),
            formCivBus.txtAntecedentes.getText(),
            formCivBus.txtFechaInicio.getText(),
            formCivBus.txtFechaFin.getText(),
            formCivBus.txtValorMensual.getText(),
            formCivBus.txtFormaPago.getText(),
            formCivBus.txtGarantia.getText(),
            formCivBus.txtCorreoArrendataria.getText(),
            formCivBus.txtCorreoArrendador.getText()
        };
            case 1:
                return new String[] {
            formLabBus.txtCiudad.getText(),
            formLabBus.txtFechaContrato.getText(),
            formLabBus.txtNombreEmpleador.getText(),
            formLabBus.txtCedulaEmpleador.getText(),
            formLabBus.txtCiudadEmpleador.getText(),
            formLabBus.txtNombreTrabajador.getText(),
            formLabBus.txtCedulaTrabajador.getText(),
            formLabBus.txtCiudadTrabajador.getText(),
            formLabBus.txtCargoTrabajador.getText(),
            formLabBus.txtJornadasHoras.getText(),
            formLabBus.txtDiasTrabajo.getText(),
            formLabBus.txtFechaInicio.getText(),
            formLabBus.txtMonto.getText(),
            formLabBus.txtFormaPago.getText(),
            formLabBus.txtLugarTrabajo.getText()
        };
        }
        return null;
    }
private boolean esRucValido(String ruc) {
    return ruc != null && ruc.matches("^\\d{10}001$");
}

private boolean esCedulaValida(String cedula) {
    return cedula != null && cedula.matches("^\\d{10}$");
}

private void resaltarCampo(JTextField campo, boolean esValido) {
    campo.setBackground(esValido ? Color.WHITE : Color.PINK);
}

}