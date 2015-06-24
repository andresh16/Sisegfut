/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aatest.AdvancedChartExample;
import co.com.sisegfut.client.aatest.ejemploBarras;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.sencha.gxt.widget.core.client.FramedPanel;

/**
 *
 * @author fhurtado
 */
public class PanelEstadisticas extends Window {

    private FramedPanel panel = new FramedPanel();
    private AdvancedChartExample advancedChartExample;
//    private ejemploBarras ejemploBarras1 = new ejemploBarras();
    private Long idCompetencia;

    public PanelEstadisticas() {
        AdvancedChartExample advancedChartExample = new AdvancedChartExample();
        setSize(900, 500);
        setPlain(true);
        setModal(true);
        setClosable(true);
        setBlinkModal(true);
        setHeading("Estadistica de la competencia");
        setLayout(new FillLayout());
        
        panel.setLayoutData(new MarginData(10));
        panel.setCollapsible(true);
        panel.setHeadingText("Grouped Bar Chart");
        panel.setPixelSize(620, 500);
        panel.setBodyBorder(true);

//        panel.add(ejemploBarras1);
        add(panel);
        Button btnCancelarBusCompetencia = new Button("Cancelar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });
        addButton(btnCancelarBusCompetencia);
        setFocusWidget(this.getButtonBar().getItem(0));
        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
//                        abrirVentanaAyuda(myConstants.ayudaPanelCompetenciaBuscar());
            }
        }));

    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

}
