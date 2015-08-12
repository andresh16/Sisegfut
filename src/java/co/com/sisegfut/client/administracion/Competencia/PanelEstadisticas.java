/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.aatest.AdvancedChartExample;
import co.com.sisegfut.client.aatest.ejemploBarras;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.widget.core.client.FramedPanel;

/**
 *
 * @author fhurtado
 */
public class PanelEstadisticas extends Window {

    @Override
    public void show() {
        ejemploBarras1.buscarSituacionesxCompetencia(idCompetencia);
        super.show(); //To change body of generated methods, choose Tools | Templates.
    }

    
    private FramedPanel panel = new FramedPanel();
    private AdvancedChartExample advancedChartExample;
//    private ejemploBarras ejemploBarras1 = new ejemploBarras();
    private ejemploBarras ejemploBarras1;
    private Long idCompetencia;
    private Main myConstants;

    public PanelEstadisticas() {
        setSize(900, 600);
        setPlain(true);
        setModal(true);
        setClosable(true);
        setBlinkModal(true);
        setHeading("Estadistica de la competencia");
        setLayout(new FillLayout());
        myConstants = (Main) GWT.create(Main.class);

        panel.setLayoutData(new MarginData(0));
        panel.setCollapsible(false);
        panel.setHeadingText("Estadisticas");
        panel.setHeaderVisible(false);
        panel.setPixelSize(620, 500);
        panel.setBodyBorder(true);
        ejemploBarras1 = new ejemploBarras();
        panel.add(ejemploBarras1);
//        panel.add(advancedChartExample);
        add(panel);
        Button btnCancelarBusCompetencia = new Button("Cancelar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ejemploBarras1.limpiargrafica();
                hide();
            }
        });
        addButton(btnCancelarBusCompetencia);
        setFocusWidget(this.getButtonBar().getItem(0));
        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentanaAyuda(myConstants.ayudaPanelCompetenciaEstadistica());
            }
        }));

    }

    public void setIdCompetencia(Long idCompetencia) {
//        this.idCompetencia = idCompetencia;
        ejemploBarras1.setIdCompetencia(idCompetencia);
    }

    /**
     * Abre ventana de ayuda.
     */
    private void abrirVentanaAyuda(String texto) {
        final Dialog simple = new Dialog();
        simple.setHeading("Ayuda");
        simple.setButtons(Dialog.OK);
        simple.setBodyStyleName("pad-text");
        simple.addText(texto);
        simple.getItem(0).getFocusSupport().setIgnore(true);
        simple.setScrollMode(Style.Scroll.AUTO);
        simple.setHideOnButtonClick(true);
        simple.setWidth(550);
        //simple.setSize(550, 255);

        simple.show();
    }

}
