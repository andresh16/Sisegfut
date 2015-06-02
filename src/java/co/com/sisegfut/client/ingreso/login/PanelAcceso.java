/*
 * Para Boos IT
 * Ext GWT 2.2.5 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package co.com.sisegfut.client.ingreso.login;

import co.com.sisegfut.client.ingreso.recuperacionclave.PanelRecuperacion;
import co.com.sisegfut.client.util.Resources;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;

/**
 *
 * @author josorio
 */
public class PanelAcceso extends LayoutContainer {

    /**
     * Formulario de login
     */
    private FormularioLogin formularioLogin;
    /**
     * Panel con los formularios de recuperacion de clave.
     */
    private PanelRecuperacion panelRecuperacion;
    /**
     * Logo de la empresa
     */
    protected Image logoLogin;

    /**
     * Subclasses must override and ensure setElement is called for lazy
     * rendered components.
     *
     * @param target the target element
     * @param index the insert location
     */
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setLayout(new CenterLayout());
        setStyleName("elLogin"); 
        setScrollMode(Style.Scroll.AUTOY); 

        // Construcción del logo
//        logoLogin = new Image("imagenes/misfinanzas.jpg");
//        logoLogin.setSize("440", "164");

        formularioLogin = new FormularioLogin();
        panelRecuperacion = new PanelRecuperacion();

        TabPanel panel = new TabPanel();
        panel.setPlain(true);
        panel.setWidth(440); 
        panel.setSize(440, 240);
        panel.setAutoHeight(true);

        TabItem login = new TabItem("INICIAR SESIÓN");
        login.setBorders(false);
        login.setIcon(Resources.ICONS.iconoLogin());
        login.add(formularioLogin); 
        panel.add(login);
        
        TabItem recuperacion = new TabItem("RECUPERAR CONTRASEÑA");
        recuperacion.setIcon(Resources.ICONS.iconoClave());
        recuperacion.add(panelRecuperacion); 
        panel.add(recuperacion);

        ContentPanel panelContenedor = new ContentPanel();
        panelContenedor.setBodyBorder(false);
        panelContenedor.setHeaderVisible(false);
        panelContenedor.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        panelContenedor.setHeading("<center>Bienvenido</center> ");
        panelContenedor.setFrame(true); 

        //panelContenedor.add(logoLogin);
        panelContenedor.add(panel);
        panelContenedor.setWidth(443); 

        add(panelContenedor);
    }
}
