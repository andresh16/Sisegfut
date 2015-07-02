/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.personal;

import co.com.sisegfut.client.datos.dominio.Personal;
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
public class PanelInfoPersonal extends LayoutContainer {

    TabItem tabItemInfoGeneral = new TabItem("Información General");
    TabItem tabItemExperiencia = new TabItem("Experiencia");
    TabItem tabItemEstudios = new TabItem("Estudios Realizados");
    PanelInfoGeneral panelInfoGeneral ;
    PanelEstudiosRealizados panelEstudiosRealizados= new PanelEstudiosRealizados();
    PanelExperiencia panelExperiencia = new PanelExperiencia();
    
    TabPanel tabpanel = new TabPanel();
//    FormPanel panelInfoGeneral= new PanelInfoGeneral();
    TextField<String> txtIngreso = new TextField<String>();
    
     

    public PanelInfoPersonal(PanelAdminPersonal panelPadre) {
    
        tabpanel.setLayoutData(new FillLayout());
        tabpanel.setHeight(600);
        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        setScrollMode(Style.Scroll.AUTOY); 
        setLayout(new FillLayout());
        
        panelInfoGeneral = new PanelInfoGeneral(panelPadre);
        
        tabItemInfoGeneral.setLayout(new FillLayout());
        tabItemInfoGeneral.add(panelInfoGeneral);
 
        tabItemEstudios.setLayout(new FillLayout());
        tabItemEstudios.add(panelEstudiosRealizados);
        
        tabItemExperiencia.setLayout(new FillLayout());
        tabItemExperiencia.add(panelExperiencia);
       
       //        tabItemAnteDeportivo.setLayout(new FillLayout());
//        tabItemAnteDeportivo.add(panelInfoGeneral);
        
//        tabItemFoto.add(panelCargarFotoDep);

        
        tabpanel.add(tabItemInfoGeneral);
        tabpanel.add(tabItemEstudios);
        tabpanel.add(tabItemExperiencia);
        tabpanel.setTabScroll(true);
         
        addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            public void handleEvent(final BoxComponentEvent event) {
                tabpanel.setWidth(event.getWidth());
                tabpanel.setHeight(event.getHeight());
                
//                if (event.getHeight() - 160 > 100) {
//                    cp.setHeight(event.getHeight()-10);
//                    cp2.setHeight(event.getHeight()-10);
//                } else {
//                    cp.setHeight(100);
//                }
            }
        });
        
        panel.add(tabpanel);
        add(panel);
    }

    public void recibirEntidad(Personal per) {
//         mask("Cargando datos");
        
        panelInfoGeneral.setId(per.getId());
        panelInfoGeneral.cbxTipoDocumento.seleccionar(per.getTipoDocumento().getId());
        panelInfoGeneral.txtDocumento.setValue(per.getDocumento());
        panelInfoGeneral.txtNombres.setValue(per.getNombres());
        panelInfoGeneral.txtApellidos.setValue(per.getApellidos());
        panelInfoGeneral.DtFecha.setValue(per.getFechaNacimiento());
        if (per.getGenero().equalsIgnoreCase("Masculino")) {
            panelInfoGeneral.rdMasculino.setValue(true);
        } else {
            panelInfoGeneral.rdFemenino.setValue(true);
        }
        panelInfoGeneral.txtTelefono.setValue(per.getTelefono());
        panelInfoGeneral.txtCelular.setValue(per.getCelular());
        panelInfoGeneral.txtDireccion.setValue(per.getDireccion());
        panelInfoGeneral.txtCorreo.setValue(per.getCorreoElectronico());
        panelInfoGeneral.txtBarrio.setValue(per.getBarrio());
        panelInfoGeneral.cbxCargo.seleccionar(per.getCargo().getId());
        panelInfoGeneral.cbxNivelEdu.seleccionar(per.getNivelEducativo().getId());        
        panelInfoGeneral.chLunes.setValue(per.getLunes());
        panelInfoGeneral.chMartes.setValue(per.getMartes());
        panelInfoGeneral.chMiercoles.setValue(per.getMiercoles());
        panelInfoGeneral.chJueves.setValue(per.getJueves());
        panelInfoGeneral.chViernes.setValue(per.getViernes());
        panelInfoGeneral.chSabado.setValue(per.getSabado());
        panelInfoGeneral.tmHoraInicio.setDateValue(per.getHoraInicio());
        panelInfoGeneral.tmHoraFin.setDateValue(per.getHoraFin());
        panelInfoGeneral.muestraFoto(per.getId());
        panelInfoGeneral.setNombreCompleto(per.getNombreCompleto());
        
        panelEstudiosRealizados.setId(per.getId());
        panelExperiencia.setId(per.getId());
        
        
        unmask();
    }
}
