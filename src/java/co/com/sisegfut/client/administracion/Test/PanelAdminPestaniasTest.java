/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Test;

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
 * @author Andres Hurtado
 */
public class PanelAdminPestaniasTest extends LayoutContainer {

    TabItem tabItemEvaIndividual = new TabItem("Evaluación Indvivual");
    TabItem tabItemPrueValorativas = new TabItem("Pruebas Valorativas");

    TabItem tabItemAntropometrico = new TabItem("Antropométrico");
    TabItem tabItemCtlTecnico = new TabItem("Control Técnico");

    TabItem tabItemKarvonen = new TabItem("Test de carga progresiva Karvonen");
    TabItem tabItemCooper = new TabItem("Test de Cooper");
    TextField<String> txtPerBrazoRelajado = new TextField<String>();

    TabPanel tabpanel = new TabPanel();
    TabPanel tabpanelEI = new TabPanel();
    TabPanel tabpanelPV = new TabPanel();
    
    PanelAntropometrico panelAntropometrico = new PanelAntropometrico();
    PanelControlTecnico panelControlTecnico = new PanelControlTecnico();
    PanelCooper panelCooper = new PanelCooper();
    PanelKarvonen panelKarvonen = new PanelKarvonen();

    public PanelAdminPestaniasTest() {

        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        setScrollMode(Style.Scroll.AUTOY);
        setLayout(new FillLayout());
        setHeight("100%");
        setWidth("100");

        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            public void handleEvent(final BoxComponentEvent event) {
                tabItemAntropometrico.setWidth(event.getWidth() - 200);
                tabItemAntropometrico.setHeight(event.getHeight() - 200);
                tabItemCooper.setWidth(event.getWidth() - 200);
                tabItemCooper.setHeight(event.getHeight() - 200);
                tabItemKarvonen.setWidth(event.getWidth() - 200);
                tabItemKarvonen.setHeight(event.getHeight() - 200);

                tabpanelEI.setWidth(event.getWidth());
                tabpanelEI.setHeight(event.getHeight() - 20);
                tabpanelPV.setWidth(event.getWidth());
                tabpanelPV.setHeight(event.getHeight() - 20);
//        .setWidth(500);
//              
            }
        });

        tabpanel.add(tabItemEvaIndividual);
        tabpanel.add(tabItemPrueValorativas);

        tabpanelEI.add(tabItemAntropometrico);
        tabpanelEI.add(tabItemCtlTecnico);
        tabpanelEI.add(tabItemAntropometrico);

        tabItemCtlTecnico.setLayout(new FillLayout());
        tabItemCtlTecnico.add(panelControlTecnico);

        tabItemAntropometrico.setLayout(new FillLayout());
        tabItemAntropometrico.add(panelAntropometrico);

        tabItemCooper.setLayout(new FillLayout());
        tabItemCooper.add(panelCooper);

        tabItemKarvonen.setLayout(new FillLayout());
        tabItemKarvonen.add(panelKarvonen);

//        tabItemAntropometrico.setHeight(400);
//        tabItemAntropometrico.setWidth(500);
        tabItemCtlTecnico.setHeight(400);
        tabItemCtlTecnico.setWidth(500);

        tabpanelPV.add(tabItemKarvonen);
        tabpanelPV.add(tabItemCooper);

        tabItemEvaIndividual.add(tabpanelEI);
        tabItemPrueValorativas.add(tabpanelPV);

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

    public void recibirEntidad(Deportista dep) {
//         mask("Cargando datos");

        String espacios = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"
                + ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"
                + ";&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        espacios = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

        panelAntropometrico.setId(dep.getId());
        panelAntropometrico.cargarfoto(dep.getId());
        panelAntropometrico.lbNombreDep.setText(espacios + "<b>" + dep.getNombreCompleto() + "</b>");

        panelControlTecnico.setId(dep.getId());
        panelControlTecnico.cargarfoto(dep.getId());
        panelControlTecnico.lbNombreDep.setText(espacios + "<b>" + dep.getNombreCompleto() + "</b>");

        panelKarvonen.setId(dep.getId());
        panelKarvonen.cargarfoto(dep.getId());
        panelKarvonen.calcularFCM(dep.getFechaNacimiento());
        panelKarvonen.lbNombreDep.setText(espacios + "<b>" + dep.getNombreCompleto() + "</b>");

        panelCooper.setId(dep.getId());
        panelCooper.cargarfoto(dep.getId());
        panelCooper.setPeso(Integer.parseInt(dep.getPeso()));
        panelCooper.calcularEdad(dep.getFechaNacimiento());
        panelCooper.lbNombreDep.setText(espacios + "<b>" + dep.getNombreCompleto() + "</b>");
        if (dep.getGenero().equalsIgnoreCase("Masculino")) {
            panelCooper.setHombre(true);
        } else {
            panelCooper.setHombre(false);
        }

        unmask();
    }

}
