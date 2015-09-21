/*
 * PanelMenu
 *
 */
package co.com.sisegfut.client.base;

import co.com.sisegfut.client.aaI18N.Main;

import co.com.sisegfut.client.adminCRUDgral.VentanaModificar;
import co.com.sisegfut.client.administracion.Rivales.PanelAdminRivales;
import co.com.sisegfut.client.administracion.cargos.PanelAdminCargos;
import co.com.sisegfut.client.administracion.categoria.PanelAdminCategoria;
import co.com.sisegfut.client.administracion.eps.PanelAdminEps;
import co.com.sisegfut.client.administracion.instEducativa.PanelAdminInsEducativa;
import co.com.sisegfut.client.administracion.nivelEducativo.PanelAdminNivelEducativo;
import co.com.sisegfut.client.administracion.posiciones.PanelAdminPosiciones;
import co.com.sisegfut.client.administracion.tipodocumento.PanelAdminTipoDocumento;
import co.com.sisegfut.client.administracion.torneos.PanelAdminTorneos;
import co.com.sisegfut.client.administracion.usuarios.FormularioUsuarios;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.informes.InformeDeportistasPosicion;
import co.com.sisegfut.client.informes.InformeEstratificacion;
import co.com.sisegfut.client.informes.PanelInformes;

import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.Dialogo;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuariosAsync;
import co.com.sisegfut.client.util.rpc.RPCLogin;
import co.com.sisegfut.client.util.rpc.RPCLoginAsync;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.*;

/**
 *
 * @author 
 */
public class PanelMenu extends LayoutContainer {

    private PanelBase panelBase;
    private Usuarios usuarioLogueado;
    /**
     * Logo de la empresa
     */
    protected Image logo;
    protected Image logoSisegfut;
    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    private Main myConstants;
    /**
     * Comando para salir de la aplicación
     */
    private Command cmdCerrarSesion;
    private Command cmdInicio;
    private Command cmdInformes;
    private Command cmdMovimientos;
    private Command cmdDatosUsuario;
    private Command cmdAdmUsuario;
    private Command cmdAdmCuenta;

    private Command cmdAdmEps;
    private Command cmdAdmCargos;
    private Command cmdAdmPosiciones;
    private Command cmdAdmNivelEducativo;
    private Command cmdAdmCategorias;
    private Command cmdAdmInstEducativa;
    private Command cmdAdmPersonal;
    private Command cmdAdmDeportista;
    private Command cmdAdmSituacionesJuego;
//    private Command cmdAdmEstadoAsistencia;

    private Command cmdAdmCompetencia;
    private Command cmdAdmControlAsistencia;

    private Command cmdAdmMedAntropometricas;
    private Command cmdAdmTestTecnico;
    private Command cmdAdmTestCargaProgresiva;
    private Command cmdAdmTest1Kilometro;
    private Command cmdAdmFrecuenciaCardiaca;
    private Command cmdAdmBosco;
    private Command cmdAdmTipoDocumento;
    private Command cmdAdmTorneos;
    private Command cmdAdmTests;
    private Command cmdAdminRivales;
    private Command cmdInfoEstratificacion;
    private Command cmdInfoDeportistasPosicion;

    /**
     * Menú de la aplicación
     */
    private MenuBar menuBar;

    /**
     * Constructor
     *
     * @param panelBase
     */
    public PanelMenu(PanelBase panelBase) {
        setStyleName("panelIzqMain");
        this.panelBase = panelBase;
        myConstants = (Main) GWT.create(Main.class);

        setWidth("170px");
//        setScrollMode(Style.Scroll.AUTOY);

        usuarioLogueado = BeansLocales.getUsuario();

        final ContentPanel cpMenu = new ContentPanel();
        //cpMenu.setHeading("           ::Menú::        ");
        cpMenu.setHeaderVisible(false);
        cpMenu.setBodyBorder(false);
        //cpMenu.setFrame(true);

        cpMenu.setWidth(165);

        Timer timer;
        timer = new Timer() {
            public void run() {
                actualizarSesion();
            }
        };
        timer.scheduleRepeating(180000);
//        timer.scheduleRepeating(1800000);

        crearBarraMenu();

        cpMenu.add(menuBar);

        // Construcción del logo
        logo = new Image("imagenes/menu.png");
        logo.setWidth("150px");
        logo.setHeight("80px");

        logoSisegfut = new Image("imagenes/acercade.png");
        logoSisegfut.setStyleName("fotoAcercade");
        logoSisegfut.setWidth("120px");
        logoSisegfut.setHeight("100px");
        logoSisegfut.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                abrirCreditos();
            }
        });

        final LayoutContainer c = new LayoutContainer();
        VBoxLayout layout = new VBoxLayout();
        layout.setPadding(new Padding(0));
        layout.setVBoxLayoutAlign(VBoxLayout.VBoxLayoutAlign.CENTER);
        layout.setPack(BoxLayout.BoxLayoutPack.END);
        c.setLayout(layout);

        VBoxLayoutData layoutData = new VBoxLayoutData(new Margins(50, 0, 0, 0));
        //  c.add(logoSisegfut, layoutData);

        add(logo, new FlowData(10));
        add(cpMenu, new FlowData(5));

        final FlowData flowd = new FlowData(05, 20, 20, 20);
        add(logoSisegfut, flowd);

//        //Escucho las redimenciones 
//        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
//
//            public void handleEvent(final BoxComponentEvent event) {
//                if (event.getHeight() - 400 > 100) {
//                    flowd.setMargins(new Margins(90, 20, 20, 20));
//                } else {
//                    flowd.setMargins(new Margins(10, 20, 20, 20));
//                }
//            }
//        });
        // Hago que la interface se refresque sola cada 40 segundos
//        Timer timer;
//        timer = new Timer() {
//
//            public void run() {
//                
//            }
//        };
//        timer.scheduleRepeating(30000);
    }

    /**
     *
     */
    private void abrirCreditos() {
        final Dialog simple = new Dialog();
        simple.setHeading("Acerca de..");
        simple.setButtons(Dialog.OK);
        simple.setBodyStyleName("pad-text");
        simple.setButtonAlign(Style.HorizontalAlignment.CENTER);
        simple.setStyleName("ventanaAcercade");
        simple.addText(textoCreditos());
        simple.getItem(0).getFocusSupport().setIgnore(true);
        simple.setScrollMode(Style.Scroll.AUTO);
        simple.setHideOnButtonClick(true);
        simple.setSize(540, 242);

        simple.show();
    }

    /**
     * String con el texto de los creditos del proyecto.
     *
     * @return
     */
    private String textoCreditos() {
        String baseImg = GWT.getModuleBaseURL() + "../imagenes/creditos.png";
        StringBuilder sb = new StringBuilder();
        sb.append("<table style='color:#0A3A18; background:#EEC627 ;margin:0px; width:525px; height:160px' >");
        sb.append("<tr>");
        sb.append("<td>");
        sb.append("<img src='").append(baseImg).append("' alt='logo Sisegfut'/>");
        sb.append("</td>");
        sb.append("<td>");
        sb.append(myConstants.creditoNombreAplicacion() + "<br>");
        sb.append(myConstants.creditoVersionAplicacion() + "<br>");
        sb.append(myConstants.creditoNumeroRevision() + "<br>");
        sb.append(myConstants.creditoFechaDeployment() + "<br>");
        sb.append(myConstants.creditoDatosEmpresa() + "");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</table>");

        return sb.toString();
    }

    /**
     * Método que crea el menú
     */
    private void crearBarraMenu() {
        menuBar = new MenuBar(true);
        menuBar.addStyleName("menuBase");
        menuBar.setWidth("165");

        crearComandos();

        menuBar.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Inicio"
                + "</img>",
                true,
                cmdInicio);
        // Si el usuario administra el sistema
        if (usuarioLogueado.isAdministradorSistema()) {
            menuBar.addItem(
                    "<img src='imagenes/iconos/icono2.ico'> "
                    + "Administración"
                    + "</img>",
                    true,
                    crearMenuAdministradorSistema());
        }

        menuBar.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Competencias"
                + "</img>",
                true,
                cmdAdmCompetencia);
        menuBar.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Entrenamientos"
                + "</img>",
                true,
                crearMenuEntrenamientos());
        menuBar.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Deportista"
                + "</img>",
                true,
                cmdAdmDeportista);

        // Si el usuario administra el sistema
//        if (usuarioLogueado.isAdministradorSistema()) {
//        menuBar.addItem(
//                "<img src='imagenes/iconos/vinetaMenu.gif'> "
//                + "Movimientos"
//                + "</img>",
//                true,
//                cmdMovimientos);
//        }
        if (usuarioLogueado.isAdminClub() || usuarioLogueado.isAdministradorSistema()) {
            menuBar.addItem(
                    "<img src='imagenes/iconos/icono2.ico'> "
                    + "Personal"
                    + "</img>",
                    true,
                    cmdAdmPersonal);
        }

        menuBar.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Informes "
                + "</img>",
                true,
                crearMenuInformes());
        menuBar.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Mis datos"
                + "</img>",
                true,
                cmdDatosUsuario);
//        menuBar.addItem(
//                "<img src='imagenes/iconos/icono2.ico'> "
//                + "Estratificación"
//                + "</img>",
//                true,
//                cmdInfoEstratificacion);
//        menuBar.addItem(
//                "<img src='imagenes/iconos/icono2.ico'> "
//                + "Deportistas Por Posición"
//                + "</img>",
//                true,
//                cmdInfoDeportistasPosicion);

        //------------
        menuBar.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Cerrar sesión"
                + "</img>",
                true,
                cmdCerrarSesion);
    }

    /**
     * Submenu de administracion
     *
     * @return
     */
    private MenuBar crearMenuAdministradorSistema() {
        MenuBar retorno = new MenuBar(true);
        //Los comandos fuerom previamente creados

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Usuarios"
                + "</img>",
                true,
                cmdAdmUsuario);

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Eps"
                + "</img>",
                true,
                cmdAdmEps);
        
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Rivales"
                + "</img>",
                true,
                cmdAdminRivales);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Nivel Educativo"
                + "</img>",
                true,
                cmdAdmNivelEducativo);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Cargos"
                + "</img>",
                true,
                cmdAdmCargos);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Categorías"
                + "</img>",
                true,
                cmdAdmCategorias);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Institución Educativa"
                + "</img>",
                true,
                cmdAdmInstEducativa);
//        retorno.addItem(
//                "<img src='imagenes/iconos/icono2.ico'> "
//                + "Situaciones de Juego"
//                + "</img>",
//                true,
//                cmdAdmSituacionesJuego);

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Posiciones"
                + "</img>",
                true,
                cmdAdmPosiciones);

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Tipo Documento"
                + "</img>",
                true,
                cmdAdmTipoDocumento);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Torneos"
                + "</img>",
                true,
                cmdAdmTorneos);

        return retorno;
    }

    private MenuBar crearMenuTestIndividual() {
        MenuBar retorno = new MenuBar(true);
        //Los comandos fuerom previamente creados

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Medidas Antropométricas"
                + "</img>",
                true,
                cmdAdmMedAntropometricas);

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Test Técnico"
                + "</img>",
                true,
                cmdAdmTestTecnico);

        return retorno;
    }

    private MenuBar crearMenuEntrenamientos() {
        MenuBar retorno = new MenuBar(true);
        //Los comandos fuerom previamente creados

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Control de Asistencia"
                + "</img>",
                true,
                cmdAdmControlAsistencia);

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Pruebas Individuales y Valorativas "
                + "</img>",
                true, cmdAdmTests);

        return retorno;
    }

    private MenuBar crearMenuInformes() {
        MenuBar retorno = new MenuBar(true);
        //Los comandos fuerom previamente creados
         
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Gráficas"
                + "</img>",
                true,
                crearMenuInformesGraficas());
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "PDF"
                + "</img>",
                true,
                cmdInformes);

        return retorno;
    }
    
    private MenuBar crearMenuInformesGraficas() {
        MenuBar retorno = new MenuBar(true);
        //Los comandos fuerom previamente creados
         
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Estratificación"
                + "</img>",
                true,
                cmdInfoEstratificacion);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Deportistas Por Posición"
                + "</img>",
                true,
                cmdInfoDeportistasPosicion); 

        return retorno;
    }    
        
    private MenuBar crearMenuPruebasValorativas() {
        MenuBar retorno = new MenuBar(true);
        //Los comandos fuerom previamente creados

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Test Carga Progresiva Karvonen"
                + "</img>",
                true,
                cmdAdmTestCargaProgresiva);

        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Test 1 Kilómetro"
                + "</img>",
                true,
                cmdAdmTest1Kilometro);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Frecuencia Cardíaca Máxima"
                + "</img>",
                true,
                cmdAdmFrecuenciaCardiaca);
        retorno.addItem(
                "<img src='imagenes/iconos/icono2.ico'> "
                + "Test Bosco"
                + "</img>",
                true,
                cmdAdmBosco);

        return retorno;
    }

    /**
     * Método que instancia los comandos del menú
     */
    private void crearComandos() {
        cmdCerrarSesion = new Command() {

            public void execute() {
//                new Viewport( new PanelAcceso() );
                String url = GWT.getHostPageBaseURL() + "html/logout.html";
                redireccionarA(url);
            }
        };

        cmdInicio = new Command() {

            public void execute() {

                activarPanel(panelBase.getPanelInicio());
            }
        };

        cmdInformes = new Command() {

            public void execute() {

                PanelInformes panelInformes = new PanelInformes();
                panelInformes.show();

                // activarPanel(panelBase.getPanelInformes());
            }
        };
        cmdAdmTorneos = new Command() {

            public void execute() {

                PanelAdminTorneos panelAdminTorneos = new PanelAdminTorneos();
                panelAdminTorneos.show();

                // activarPanel(panelBase.getPanelInformes());
            }
        };

        cmdAdmDeportista = new Command() {

            public void execute() {
                activarPanel(panelBase.getPanelAdminDeportista());
            }
        };
        cmdAdmCompetencia = new Command() {

            public void execute() {
                activarPanel(panelBase.getPanelAdminCompetencia());
            }
        };
        cmdAdminRivales = new Command() {

            public void execute() {

                PanelAdminRivales panelAdminPosiciones = new PanelAdminRivales();
                panelAdminPosiciones.show();
            }
        };
        cmdAdmControlAsistencia = new Command() {

            public void execute() {
                activarPanel(panelBase.getAdminControlAsistencia());
            }
        };

        cmdAdmPersonal = new Command() {

            public void execute() {
                activarPanel(panelBase.getPanelAdminPersonal());
            }
        };

        cmdDatosUsuario = new Command() {

            public void execute() {
                ListenerGuardadoUsuario listenerGuardar = new ListenerGuardadoUsuario();

                FormularioUsuarios formularioCRUD = new FormularioUsuarios(listenerGuardar);
                formularioCRUD.setEntidad(usuarioLogueado);

                VentanaModificar ventanaModificar = new VentanaModificar(listenerGuardar, formularioCRUD,
                        "Modificar", "Permite modificar sus datos, para esto llene los campos del formulario y ejecute la opción guardar.");
                ventanaModificar.setEntidadManual(usuarioLogueado);
                ventanaModificar.show();
            }
        };

        cmdAdmUsuario = new Command() {

            public void execute() {
                activarPanel(panelBase.getPanelAdminstracionUsuarios());
            }
        };

        cmdAdmEps = new Command() {

            public void execute() {

                PanelAdminEps adminEps = new PanelAdminEps();
                adminEps.show();
            }
        };
        cmdInfoEstratificacion = new Command() {

            public void execute() {

                InformeEstratificacion informeEstratificacion = new InformeEstratificacion();
                informeEstratificacion.show();
            }
        };
        cmdInfoDeportistasPosicion = new Command() {

            public void execute() {

                InformeDeportistasPosicion informeDeportistasPosicion = new InformeDeportistasPosicion();
                informeDeportistasPosicion.show();
            }
        };
        cmdAdmCargos = new Command() {

            public void execute() {

                PanelAdminCargos adminCargos = new PanelAdminCargos();
                adminCargos.show();
            }
        };

        cmdAdmInstEducativa = new Command() {

            public void execute() {

                PanelAdminInsEducativa adminInstEducativa = new PanelAdminInsEducativa();
                adminInstEducativa.show();
            }
        };

        cmdAdmPosiciones = new Command() {

            public void execute() {

                PanelAdminPosiciones adminPosiciones = new PanelAdminPosiciones();
                adminPosiciones.show();
            }
        };

//        cmdAdmSituacionesJuego = new Command() {
//
//            public void execute() {
//
//                PanelAdminSituacionesJuego adminSituacionesJuego = new PanelAdminSituacionesJuego();
//                adminSituacionesJuego.show();
//            }
//        };

//        cmdAdmEstadoAsistencia = new Command() {
//
//            public void execute() {
//
//                PanelAdminEstadoAsistencia panelAdminEstadoAsistencia = new PanelAdminEstadoAsistencia();
//                panelAdminEstadoAsistencia.show();
//            }
//        };

        cmdAdmNivelEducativo = new Command() {

            public void execute() {

                PanelAdminNivelEducativo adminNivelEducativo = new PanelAdminNivelEducativo();
                adminNivelEducativo.show();
            }
        };

        cmdAdmTipoDocumento = new Command() {

            public void execute() {

                PanelAdminTipoDocumento adminTipoDocumento = new PanelAdminTipoDocumento();
                adminTipoDocumento.show();
            }
        };

        cmdAdmNivelEducativo = new Command() {

            public void execute() {

                PanelAdminNivelEducativo adminNivelEducativo = new PanelAdminNivelEducativo();
                adminNivelEducativo.show();
            }
        };

        cmdAdmCategorias = new Command() {

            public void execute() {

                PanelAdminCategoria adminCategoria = new PanelAdminCategoria();
                adminCategoria.show();
            }
        };

        cmdAdmTests = new Command() {

            public void execute() {

                activarPanel(panelBase.getAdminTests());
            }
        };
    }

    /**
     * Activa el panel elegido en el menu.
     *
     * @param widget
     */
    private void activarPanel(Component widget) {
        panelBase.getLayoutContenido().setActiveItem(widget);
//        validarActiveItem();
    }

    /**
     * Valido la activacion de paneles
     */
    private void validarActiveItem() {
        //mientras este inactivo el panel de reporte actual detengo el timer de este panel
//        if (panelBase.getLayoutContenido().getActiveItem() != panelBase.getPanelReporteActual()) {
//            panelBase.getPanelReporteActual().setActualizarMapa(false);
//        }
    }

    private static native void redireccionarA(String url) /*-{ $wnd.location = url;}-*/;

    private class ListenerGuardadoUsuario implements ListenerGuardado<Usuarios> {

        @Override
        public boolean permisoGuardado() {
            return true;
        }

        @Override
        public void finalizaGuardado() {
        }

        @Override
        public void cancelarGuardado() {
        }

        @Override
        public RPCMaestroAsync<Usuarios> getRPCMaestroAsync() {
            final RPCAdminUsuariosAsync svc = (RPCAdminUsuariosAsync) GWT.create(RPCAdminUsuarios.class);
            ServiceDefTarget endpoint = (ServiceDefTarget) svc;
            endpoint.setServiceEntryPoint("services/RPCAdminUsuarios");

            return svc;
        }
    }

    public void actualizarSesion() {

        getServiceSesion().estadoSesion(new AsyncCallback<Integer>() {
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            public void onSuccess(Integer result) {

                if (result == null || result == -1) {
                    Dialogo.sessionTimeout();
                }
            }
        });
    }

    public static RPCLoginAsync getServiceSesion() {
        final RPCLoginAsync svc = (RPCLoginAsync) GWT.create(RPCLogin.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCLogin");
        return svc;
    }

}
