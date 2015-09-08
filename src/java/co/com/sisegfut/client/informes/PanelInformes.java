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
    private InformeTest informeTest;
    private InformeDeportistaPosicion informeDeportistaPosicion;
    TabItem tabInfoCategoria = new TabItem("Informe por categoría");
    TabItem tabInfoDeportistaPosicion = new TabItem("Informe deportistas posición");
    TabItem tabInfoTest = new TabItem("Informe tests");

    public PanelInformes() {

        setSize(430, 200);
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Informes");
        setLayout(new FillLayout());
        setResizable(false);

        TabPanel panel = new TabPanel();
        panel.setBorders(false);
        panel.setLayoutData(new FitLayout());

        infoCuenta = new InformeCuenta();
        informeDeportista = new InformeDeportista();
        informeTest = new InformeTest();
        informeDeportistaPosicion = new InformeDeportistaPosicion();

        tabInfoDeportistaPosicion.setLayout(new FillLayout());
        tabInfoDeportistaPosicion.addStyleName("pad-text");
        tabInfoDeportistaPosicion.add(informeDeportistaPosicion);
        panel.add(tabInfoDeportistaPosicion);

        tabInfoCategoria.setLayout(new FillLayout());
        tabInfoCategoria.addStyleName("pad-text");
        tabInfoCategoria.add(informeDeportista);
        panel.add(tabInfoCategoria);

        tabInfoTest.setLayout(new FitLayout());
        tabInfoTest.addStyleName("pad-text");
        tabInfoTest.setAutoHeight(true);
        tabInfoTest.add(informeTest);
        panel.add(tabInfoTest);
        add(panel, new MarginData(0));

        setFocusWidget(this.getButtonBar().getItem(0));

    }
}
