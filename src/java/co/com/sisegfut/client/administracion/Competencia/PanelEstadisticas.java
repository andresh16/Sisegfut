/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aatest.AdvancedChartExample;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.sencha.gxt.widget.core.client.FramedPanel;

/**
 *
 * @author fhurtado
 */
public class PanelEstadisticas extends Window {

    private FramedPanel panel;
    private AdvancedChartExample advancedChartExample;
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

        add(advancedChartExample);
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
