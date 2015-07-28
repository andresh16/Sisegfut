/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.inicio;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;

/**
 *
 * @author anfeh_000
 */
public class PanelInicio extends LayoutContainer {

    protected Image img1;
    private ContentPanel cp1;
    protected Image img2;
    private ContentPanel cp2;
    protected Image img3;
    private ContentPanel cp3;
    protected Image img4;
    private ContentPanel cp4;
    protected Image img5;
    private ContentPanel cp5;
    protected Image img6;
    private ContentPanel cp6;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setScrollMode(Style.Scroll.AUTOY);
//        setScrollMode(Style.Scroll.AUTOY);
        setTitle("Categorías");
        setLayout(new FillLayout());
        ContentPanel cp = new ContentPanel();

        // setLayout(new FlowLayout(10));
        // cp.setLayout(new FillLayout(Orientation.VERTICAL));
        cp.setHeading("Categorías del club");

//        VBoxLayout layout = new VBoxLayout();  
//        layout.setPadding(new Padding(5));  
//        layout.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH); 
        //setIcon(Resources.ICONS.iconoCrear());  
        cp.setLayout(new AccordionLayout());

        cp1 = new ContentPanel();
        cp1.setAnimCollapse(false);
//        cp1.setLayout(new FitLayout());
        cp1.setHeading("Primera B");
        cp1.setFrame(true);
        cp1.setWidth("550px");
        cp1.setHeight("540px");
        img1 = new Image("imagenes/example/primera.jpg");
        img1.setWidth("750px");
        img1.setHeight("540px");
        cp1.add(img1);
//        cp1.setLayout(new CenterLayout());
        cp.add(cp1);

        cp2 = new ContentPanel();
        cp2.setLayout(new FitLayout());
        cp2.setFrame(true);
        cp2.setHeading("Primera C");
        cp2.setWidth("750px");
        cp2.setHeight("540px");
        img2 = new Image("imagenes/example/PrimeraC.jpg");
        img2.setWidth("750px");
        img2.setHeight("540px");
        cp2.setLayout(new CenterLayout());
        cp2.add(img2);
        cp.add(cp2);

        cp3 = new ContentPanel();
        cp3.setLayout(new FitLayout());
        cp3.setFrame(true);
        cp3.setHeading("Sub 13");
        cp3.setWidth("750px");
        cp3.setHeight("540px");
        img3 = new Image("imagenes/example/sub13.jpg");
        img3.setWidth("750px");
        cp3.setLayout(new CenterLayout());
        img3.setHeight("540px");
        cp3.add(img3);
        cp.add(cp3);

        cp4 = new ContentPanel();
        cp4.setLayout(new FitLayout());
        cp4.setFrame(true);
        cp4.setHeading("Universitario");
        cp4.setWidth("750px");
        cp4.setHeight("540px");
        img4 = new Image("imagenes/example/primerab.jpg");
        img4.setWidth("750px");
        img4.setHeight("540px");
        cp4.setLayout(new CenterLayout());
        cp4.add(img4);
        cp.add(cp4);
        
        cp5 = new ContentPanel();
        cp5.setLayout(new FitLayout());
        cp5.setFrame(true);
        cp5.setHeading("Pony");
        cp5.setWidth("750px");
        cp5.setHeight("540px");
        img5 = new Image("imagenes/example/pony.jpg");
        img5.setWidth("750px");
        img5.setHeight("540px");
        cp5.setLayout(new CenterLayout());
        cp5.add(img5);
        cp.add(cp5);

        cp6 = new ContentPanel();
        cp6.setAnimCollapse(false);
        cp6.setLayout(new FitLayout());
        cp6.setHeading("Sub 15");
        cp6.setFrame(true);
        cp6.setWidth("750px");
        cp6.setHeight("540px");
        img6 = new Image("imagenes/example/sub15.jpg");
        img6.setWidth("550px");
        img6.setHeight("540px");
        cp6.add(img6);
        cp6.setLayout(new CenterLayout());
        cp.add(cp6);

        

        add(img1);

        //Agrego un listener para escuchar el cambio de tamanio del panel
        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            public void handleEvent(final BoxComponentEvent event) {
                if (event.getHeight() - 160 > 100) {
                    cp1.setHeight(event.getHeight() - 160);
                } else {
                    cp1.setHeight(100);
                }
            }
        });

    }
}
