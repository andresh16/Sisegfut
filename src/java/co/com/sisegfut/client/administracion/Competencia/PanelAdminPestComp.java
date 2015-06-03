/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

/**
 *
 * @author Andres Hurtado
 */
public class PanelAdminPestComp extends LayoutContainer {

    TabItem tabItemConvocados = new TabItem("Convocados");
    TabItem tabItemSituaciones = new TabItem("Situaciones Juego");
    TabItem tabItemControlJuego = new TabItem("Control Juego");
    PanelAdminConvocados panelAdminConvocados;
    PanelAdminSituaciones panelAdminSituaciones = new PanelAdminSituaciones();
    PanelAdminControlJuego panelAdminControlDisciplinario = new PanelAdminControlJuego();

    TabPanel tabpanel = new TabPanel();

    public TabPanel getTabpanel() {
        return tabpanel;
    }

    public void setTabpanel(TabPanel tabpanel) {
        this.tabpanel = tabpanel;
    }

    public PanelAdminPestComp() {
        panelAdminConvocados = new PanelAdminConvocados(this);
        tabpanel.setLayoutData(new FillLayout());
        tabpanel.setHeight(600);
        tabpanel.disable();
        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);
        setScrollMode(Style.Scroll.AUTOY);
        setLayout(new FillLayout());

        tabItemConvocados.setLayout(new FillLayout());
        tabItemConvocados.add(panelAdminConvocados);

        tabItemSituaciones.setLayout(new FillLayout());
        tabItemSituaciones.setEnabled(false);
        tabItemSituaciones.add(panelAdminSituaciones);

        tabItemControlJuego.setLayout(new FillLayout());
        tabItemControlJuego.setEnabled(false);
        tabItemControlJuego.add(panelAdminControlDisciplinario);

        tabpanel.add(tabItemConvocados);
        tabpanel.add(tabItemSituaciones);
        tabpanel.add(tabItemControlJuego);
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

    public void cargarJugadores(Long idCategoria) {
        panelAdminConvocados.cargarJugadoresXCategoria(idCategoria);
    }
    

//    public void habilitarTabs(Long idCompetencia) {
////        panelAdminControlDisciplinario.setIdCompetencia(idCompetencia);
//        tabItemSituaciones.setEnabled(true);
//        tabItemControlJuego.setEnabled(true);
//        tabItemControlJuego.show();
//    }
}
