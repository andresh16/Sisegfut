/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.informes;

import co.com.sisegfut.client.aatest.BasicChartExample;
import co.com.sisegfut.client.aatest.DeportistaTipoDeportista;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.sencha.gxt.widget.core.client.FramedPanel;

/**
 *
 * @author ManuelAlejandro
 */
public class InformeDeportistasTipoDeportista extends Window{
    
    private BasicChartExample basicChartExample;
    private DeportistaTipoDeportista pieChart;
    private FramedPanel panel = new FramedPanel();

    public InformeDeportistasTipoDeportista() {
        
        //        setSize(410, 410);
        setSize(420, 300);
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Reporte de deportistas por tipo de deportista");
        setLayout(new FillLayout());
        setResizable(false);
        
        pieChart=new DeportistaTipoDeportista();
        
        add(pieChart, new MarginData(0));

        setFocusWidget(this.getButtonBar().getItem(0));
    }
}
