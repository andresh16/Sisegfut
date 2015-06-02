/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.deportista;

import co.com.sisegfut.client.datos.dominio.Deportista;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

/**
 *
 * @author anfeh_000
 */
public class PanelAdminPestDeportista extends LayoutContainer {

    TabItem tabItemInfoGeneral = new TabItem("Información General");
    TabItem tabItemAnteDeportivo = new TabItem("Antecedentes Deportivos");
    TabItem tabItemLogros = new TabItem("Logros Deportivos");
    TabItem tabItemLesion = new TabItem("Lesiones");
    PanelInfoGeneral panelInfoGeneral ;
    PanelAntecedentesDep panelAntecedentesDep= new PanelAntecedentesDep();
    PanelLogrosDep panelLogrosDep = new PanelLogrosDep();
    PanelLesion  panelLesiones = new PanelLesion();
    
    TabPanel tabpanel = new TabPanel();
//    FormPanel panelInfoGeneral= new PanelInfoGeneral();
    TextField<String> txtIngreso = new TextField<String>();
    
     

    public PanelAdminPestDeportista(PanelAdminDeportista panelPadre) {
    
        tabpanel.setLayoutData(new FillLayout());
        tabpanel.setHeight(600);
        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        setScrollMode(Style.Scroll.AUTOY); 
        setLayout(new FillLayout());
        
        panelInfoGeneral = new PanelInfoGeneral(panelPadre);
        
        tabItemInfoGeneral.setLayout(new FillLayout());
        tabItemInfoGeneral.add(panelInfoGeneral);
 
        tabItemAnteDeportivo.setLayout(new FillLayout());
        tabItemAnteDeportivo.add(panelAntecedentesDep);
        
        tabItemLogros.setLayout(new FillLayout());
        tabItemLogros.add(panelLogrosDep);
        
        tabItemLesion.setLayout(new FillLayout());
        tabItemLesion.add(panelLesiones);
//        tabItemAnteDeportivo.setLayout(new FillLayout());
//        tabItemAnteDeportivo.add(panelInfoGeneral);
        
//        tabItemFoto.add(panelCargarFotoDep);

        
        tabpanel.add(tabItemInfoGeneral);
        tabpanel.add(tabItemAnteDeportivo);
        tabpanel.add(tabItemLogros);
        tabpanel.add(tabItemLesion);
        tabpanel.setTabScroll(true);
         
        addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            public void handleEvent(final BoxComponentEvent event) {
                tabpanel.setWidth(event.getWidth());
                tabpanel.setHeight(event.getHeight());
            }
        });
        
        panel.add(tabpanel);
        add(panel);
    }

    public void recibirEntidad(Deportista dep) {
//         mask("Cargando datos");
        
        panelInfoGeneral.setId(dep.getId());
        panelInfoGeneral.cbxTipoDocumento.seleccionar(dep.getTipoDocumento().getId());
        panelInfoGeneral.txtDocumento.setValue(dep.getDocumento());
        panelInfoGeneral.txtNombres.setValue(dep.getNombres());
        panelInfoGeneral.txtApellidos.setValue(dep.getApellidos());
        panelInfoGeneral.DtFecha.setValue(dep.getFechaNacimiento());
        if (dep.getGenero().equalsIgnoreCase("Masculino")) {
            panelInfoGeneral.rdMasculino.setValue(true);
        } else {
            panelInfoGeneral.rdFemenino.setValue(true);
        }
        panelInfoGeneral.txtTelefono.setValue(dep.getTelefono());
        panelInfoGeneral.txtDireccion.setValue(dep.getDireccion());
        panelInfoGeneral.txtCorreo.setValue(dep.getCorreoElectronico());
        panelInfoGeneral.txtBarrio.setValue(dep.getBarrio());
        panelInfoGeneral.txtNombreMadre.setValue(dep.getNombreMadre());
        panelInfoGeneral.txtNumeroMadre.setValue(dep.getNumeroMadre());
        panelInfoGeneral.txtNombrePadre.setValue(dep.getNombrePadre());
        panelInfoGeneral.txtNumeroPadre.setValue(dep.getNumeroPadre());
        panelInfoGeneral.txtNumCamisa.setValue(dep.getNumeroCamisa());
        panelInfoGeneral.cbxCategoria.seleccionar(dep.getCategoria().getId());
        panelInfoGeneral.cbxPosiciones.seleccionar(dep.getPosicion().getId());
        panelInfoGeneral.cbxEps.seleccionar(dep.getEps().getId());
        panelInfoGeneral.cbxEstratos.setSimpleValue(dep.getEstrato()); 
        panelInfoGeneral.cbxInstEducativa.seleccionar(dep.getInstEducativa().getId());
        panelInfoGeneral.cbxNivelEdu.seleccionar(dep.getNivelEducativo().getId());
        panelInfoGeneral.txtPeso.setValue(dep.getPeso());
        panelInfoGeneral.txtEstatura.setValue(dep.getEstatura());
        panelInfoGeneral.txtImc.setValue(dep.getImc());
        panelInfoGeneral.txtGrasa.setValue(dep.getGrasa());
        panelInfoGeneral.calcularFCM();
        panelInfoGeneral.muestraFoto(dep.getId());
        panelInfoGeneral.setNombreCompleto(dep.getNombreCompleto());
        
        
        panelAntecedentesDep.setId(dep.getId());
        panelLogrosDep.setId(dep.getId());
        panelLesiones.setId(dep.getId());
        
        
        unmask();
    }
}
