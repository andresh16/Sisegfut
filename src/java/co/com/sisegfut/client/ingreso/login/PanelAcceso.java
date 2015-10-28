
package co.com.sisegfut.client.ingreso.login;

import co.com.sisegfut.client.ingreso.recuperacionclave.PanelRecuperacion;
import co.com.sisegfut.client.util.Resources;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
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
        setStyleName("elLogin");
        setScrollMode(Style.Scroll.AUTOY);
        setLayout(new CenterLayout());

        // Construcción del logo
        logoLogin = new Image("imagenes/inicio.png");
        logoLogin.setSize("440", "164");
        formularioLogin = new FormularioLogin();
        panelRecuperacion = new PanelRecuperacion();
        
        ContentPanel panel = new ContentPanel();

        panel.setLayout(new AccordionLayout());
        panel.setWidth(440);
        panel.setAutoHeight(true);
        panel.setBodyBorder(false);
        panel.setBorders(false);
        panel.setScrollMode(Style.Scroll.AUTOY);
        panel.setHeaderVisible(false);

        ContentPanel login = new ContentPanel();
        login.setHeading("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<FONT SIZE=2 FACE='arial' Color='black'><b>INICIAR SESIÓN</b></font></center>");
        login.setAnimCollapse(true);
        login.setHeight(250);
        login.setAutoHeight(true);
        login.setBorders(false);
        login.setIcon(Resources.ICONS.iconoLogin());
        login.add(formularioLogin);
        panel.add(login);

        ContentPanel recuperacion = new ContentPanel();
        recuperacion.setHeading("<FONT SIZE='2' FACE='arial' Color='blue'><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;RECUPERAR CONTRASEÑA</b></font></center>");
        recuperacion.setIcon(Resources.ICONS.iconoClave());
        recuperacion.add(panelRecuperacion);
        panel.add(recuperacion);


        ContentPanel panelContenedor = new ContentPanel();
        panelContenedor.setBodyBorder(false);
        panelContenedor.setHeaderVisible(false);
        panelContenedor.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        panelContenedor.setHeading("<center>Bienvenido</center> ");
        panelContenedor.setFrame(false);
        panelContenedor.add(logoLogin);
//        BorderLayoutData eastData = new BorderLayoutData(LayoutRegion.NORTH, 450);
//        eastData.setSplit(true);
//        eastData.setCollapsible(true);
//        eastData.setMargins(new Margins(0, 0, 0, 0));
        panelContenedor.add(panel);   
        panelContenedor.setWidth(440);
        add(panelContenedor);
    }
}
