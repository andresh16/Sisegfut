/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.informes;

import co.com.sisegfut.client.util.Resources;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;

/**
 *
 * @author fhurtado
 */
public class PanelInformes extends Window {

    private InformeCuenta infoCuenta;
    private InformeDeportista informeDeportista;
    TabItem tabInfoCategoria = new TabItem("Informe por categoria");
    TabItem tabInfoCuenta = new TabItem("Informe por cuenta");

    public PanelInformes() {
        

        setSize(330, 260);
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Informes");
        setLayout(new FillLayout());
        setResizable(false);
        


        TabPanel panel = new TabPanel();
        panel.setBorders(false);
        
        infoCuenta=new InformeCuenta();
        informeDeportista= new InformeDeportista();
        
        tabInfoCuenta.add(infoCuenta);
        tabInfoCuenta.addStyleName("pad-text");
       // panel.add(tabInfoCuenta);

        tabInfoCategoria.setLayout(new FitLayout());
        tabInfoCategoria.addStyleName("pad-text");
        tabInfoCategoria.add(informeDeportista);
        panel.add(tabInfoCategoria);
        add(panel, new MarginData(0));
       

        setFocusWidget(this.getButtonBar().getItem(0));

    }
}
