/*
 * Para Boos IT
 * Ext GWT 2.2.5 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package co.com.sisegfut.client.base;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.aatest.PanelPrueba;
import co.com.sisegfut.client.administracion.Competencia.PanelAdminCompetencia;
import co.com.sisegfut.client.administracion.ControlAsistencia.PanelAdminControlAsistencia;
import co.com.sisegfut.client.administracion.Rivales.PanelAdminRivales;
import co.com.sisegfut.client.administracion.Test.PanelAdminTests;
import co.com.sisegfut.client.administracion.deportista.PanelAdminDeportista;
import co.com.sisegfut.client.administracion.eps.PanelAdminEps;
import co.com.sisegfut.client.administracion.inicio.PanelInicio;
import co.com.sisegfut.client.administracion.personal.PanelAdminPersonal;
import co.com.sisegfut.client.administracion.torneos.PanelAdminTorneos;
import co.com.sisegfut.client.administracion.usuarios.PanelAdminstracionUsuarios;


import co.com.sisegfut.client.datos.dominio.Usuarios;


import co.com.sisegfut.client.util.PanelInformativo;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import java.util.Date;

/**
 *
 * @author josorio
 */
public class PanelBase extends LayoutContainer {

    private PanelInformativo pnlInformativo;
    private ContentPanel contenedorFotoUsuario;
    private Usuarios usuarioLogueado;
    private VerticalPanel vp;
    private ContentPanel contenMain;
    private CardLayout layoutContenido;
    private PanelPrueba pnlPrueba;
    private PanelAdminstracionUsuarios panelAdminstracionUsuarios;
    private PanelAdminPersonal panelAdminPersonal;
    private PanelAdminEps panelEps;
    private PanelInicio panelInicio;
    private PanelAdminDeportista panelAdminDeportista;
    private PanelAdminTorneos panelAdminTorneos;
    private PanelAdminTests panelAdminTests;
    private PanelAdminControlAsistencia panelAdminControlAsistencia;
    private PanelAdminCompetencia panelAdminCompetencia;
    private PanelAdminRivales panelAdminRivales;


    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    protected Main myConstants;

    public PanelBase(final Usuarios usuario) {
        this.usuarioLogueado = usuario;
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        if (myConstants == null) {
            myConstants = (Main) GWT.create(Main.class);
        }
        setScrollMode(Style.Scroll.AUTOY);

        setLayout(new BorderLayout());

        LayoutContainer vp = new LayoutContainer();
        vp.setLayout(new BorderLayout());
        

        //Creo la zona del head del titulo
        LayoutContainer titulo = construirTitulo();

        //Creo el menu principal a la izquierda
        PanelMenu panelMenuVertical = new PanelMenu(this);
        BorderLayoutData west = new BorderLayoutData(Style.LayoutRegion.WEST, 175, 175, 175);
        west.setCollapsible(true);
        west.setMargins(new Margins(1));
        west.setSplit(true);

        add(panelMenuVertical, west);

        contenedorFotoUsuario = new ContentPanel();
        contenedorFotoUsuario.setHeaderVisible(false);
        contenedorFotoUsuario.setVisible(true);

        // titulo.add(contenedorFotoUsuario);
        //Instancio el contenedor principal de los diferentes paneles del sistema
        layoutContenido = new CardLayout();
        contenMain = new ContentPanel();
        contenMain.setHeaderVisible(false);
        contenMain.setLayout(layoutContenido);

        BorderLayoutData center = new BorderLayoutData(Style.LayoutRegion.CENTER);
        center.setCollapsible(true);
        center.setMargins(new Margins(0));
        BorderLayoutData north = new BorderLayoutData(Style.LayoutRegion.NORTH, 80, 80, 80);
        north.setMargins(new Margins(0));

        add(titulo, north);
        vp.add(contenMain, center);

        add(vp, new BorderLayoutData(Style.LayoutRegion.CENTER));

        //Construye los paneles de contenido.
        construirPanelesContenido();

//        if(usuarioLogueado.getId()!=null){
//        cargarfoto(usuarioLogueado.getId());
//        }else{
//        cargarfotoNoDisponilble();
//        }
        cargarfotoNoDisponilble();
        
        
    }

    /**
     * Construyo el titulo
     *
     * @return
     */
    private LayoutContainer construirTitulo() {
        LayoutContainer titulo = new LayoutContainer();
        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(0));
        layout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.TOP);
        layout.setPack(BoxLayout.BoxLayoutPack.END);
        titulo.setLayout(layout);

        HBoxLayoutData layoutData2 = new HBoxLayoutData(new Margins(20));
        VBoxLayoutData layoutData3 = new VBoxLayoutData(new Margins(10));
        Label usuario = new Label();
        
        
        //titulo.add(contenedorFotoUsuario);
//        Label label = new Label("Bienvenido\n " +usuarioLogueado.getLabel()) ;
//        label.setStyleName("nomUsuario");
        titulo.add(new Html("<font color='#EEC627' size='2px' face='Comic Sans MS, Arial, \n"
                + "MS Sans Serif'>Bienvenido: <big>" + usuarioLogueado.getLabel() + "</big>&nbsp; &nbsp;&nbsp;&nbsp; </font>"
                + "<style type=\"text/css\">"
                + "big:hover{"
                + "        color:#EEC627;\n"
                + "       font-weight: bold;\n"
                + "        text-shadow: 0,0,0,15px #00FF80; /* Sombra */\n"
                + "        -moz-transition: all 1s;\n"
                + "        -webkit-transition: all 1s;\n"
                + "        -o-transition: all 0s;"
                + "        cursor: pointer;"
//                + " padding: 1px 10px 2px 9px;"
                + "}"
                + "</style>"));
//        titulo.add(new Button("Cerrar sesión",Resources.ICONS.iconoSalir(),clickSalir()), layoutData2); 

//        titulo.add(label);
        titulo.setStyleName("panelTituloMain");

        return titulo;
    }
    
    

    /**
     * Cierra la session del usuario activo.
     *
     * @return
     */
    private SelectionListener<ButtonEvent> clickSalir() {
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //MessageBox.alert("Mensaje", "Ejecuto boton " + ce.getButton().getId(), null);
                String url = GWT.getHostPageBaseURL() + "html/logout.html";
                redireccionarA(url);
            }
        };
    }

//    private static native void redireccionarA( String url )/*-{
//        $wnd.location = url;
//    }-*/;    
    private static native void redireccionarA(String url)/*-{ $wnd.location = url; }-*/;

    /**
     * Construye los paneles principales
     */
    private void construirPanelesContenido() {

//        panelInformes = new PanelInformes();
//        if (usuarioLogueado.isAdministradorSistema()) {
//            panelAdminstracionUsuarios = new PanelAdminstracionUsuarios();
//            contenMain.add(panelAdminstracionUsuarios); 
//
//        }
//        contenMain.add(panelAdminstracionUsuarios);        
// 
        if (panelInicio == null) {
            panelInicio = new PanelInicio();
        }
        contenMain.add(panelInicio);
    }

    public void cargarfoto(Long idUsuario) {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        contenedorFotoUsuario.addText("<img height='150' width='150' src='html/foto/descargar/" + idUsuario + "?milis=" + milis + "'/>");

    }

    public void cargarfotoNoDisponilble() {

        contenedorFotoUsuario.addText("<img height='150' width='150' src='imagenes/fotoNoDisponible.jpg'/>");

    }

    public void resetFoto() {
        cargarfotoNoDisponilble();

    }

    protected CardLayout getLayoutContenido() {
        return layoutContenido;
    }

    protected PanelPrueba getPnlPrueba() {
        //return pnlPrueba;
        if (pnlPrueba == null) {
            pnlPrueba = new PanelPrueba();
            contenMain.add(pnlPrueba);
        }
        return pnlPrueba;
    }
    
    protected PanelAdminRivales getAdminRivales() {
        if (panelAdminRivales == null) {
            panelAdminRivales = new PanelAdminRivales();
            contenMain.add(panelAdminRivales);
        }
        return panelAdminRivales;
    }

    protected PanelAdminstracionUsuarios getPanelAdminstracionUsuarios() {
        if (panelAdminstracionUsuarios == null) {
            panelAdminstracionUsuarios = new PanelAdminstracionUsuarios("Administración de usuarios",
                    myConstants.ayudaAdministracionUsuario());
            contenMain.add(panelAdminstracionUsuarios);
        }
        return panelAdminstracionUsuarios;
    }

    protected PanelAdminDeportista getPanelAdminDeportista() {
        if (panelAdminDeportista == null) {
            panelAdminDeportista = new PanelAdminDeportista();
            contenMain.add(panelAdminDeportista);
        }
        return panelAdminDeportista;
    }
    
    protected PanelAdminCompetencia getPanelAdminCompetencia() {
        if (panelAdminCompetencia == null) {
            panelAdminCompetencia = new PanelAdminCompetencia();
            contenMain.add(panelAdminCompetencia);
        }
        return panelAdminCompetencia;
    }

    protected PanelInicio getPanelInicio() {
        if (panelInicio == null) {
            panelInicio = new PanelInicio();
            contenMain.add(panelInicio);
        }
        return panelInicio;
    }


    protected PanelAdminPersonal getPanelAdminPersonal() {
        if (panelAdminPersonal == null) {
            panelAdminPersonal = new PanelAdminPersonal();
            contenMain.add(panelAdminPersonal);
        }
        return panelAdminPersonal;
    }


    protected PanelAdminTorneos getAdminTorneos() {
        if (panelAdminTorneos == null) {
            panelAdminTorneos = new PanelAdminTorneos();
            contenMain.add(panelAdminTorneos);
        }
        return panelAdminTorneos;
    }
    protected PanelAdminTests getAdminTests() {
        if (panelAdminTests == null) {
            panelAdminTests = new PanelAdminTests();
            contenMain.add(panelAdminTests);
        }
        return panelAdminTests;
    }
      protected PanelAdminControlAsistencia getAdminControlAsistencia() {
        if (panelAdminControlAsistencia == null) {
            panelAdminControlAsistencia = new PanelAdminControlAsistencia();
            contenMain.add(panelAdminControlAsistencia);
        }
        return panelAdminControlAsistencia;
    }

    protected PanelAdminEps getPanelEps() {
        if (panelEps == null) {
            panelEps = new PanelAdminEps();
            contenMain.add(panelEps);
        }
        return panelEps;
    }

}
