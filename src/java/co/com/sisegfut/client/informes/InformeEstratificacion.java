/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.informes;

import co.com.sisegfut.client.aatest.BasicChartExample;
import co.com.sisegfut.client.aatest.PieExample;
import com.extjs.gxt.charts.client.model.charts.PieChart;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.sencha.gxt.widget.core.client.FramedPanel;

/**
 *
 * @author fhurtado
 */
public class InformeEstratificacion extends Window{
    
    private BasicChartExample basicChartExample;
    private PieExample pieChart;
    private FramedPanel panel = new FramedPanel();

    public InformeEstratificacion() {
        
//        setSize(410, 410);
        setSize(620, 500);
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Reporte de estratificación");
        setLayout(new FillLayout());
        setResizable(false);
        
        panel.setLayoutData(new MarginData(0));
        panel.setCollapsible(false);
        panel.setHeadingText("Reporte de estratificación");
        panel.setHeaderVisible(false);
        panel.setPixelSize(620, 500);
        panel.setBodyBorder(true);
         pieChart=new PieExample();
        panel.add(pieChart);
        
        basicChartExample= new BasicChartExample();
        
        add(panel, new MarginData(0));

        setFocusWidget(this.getButtonBar().getItem(0));
    }
    
    
}
