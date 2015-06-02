/*
 * Para Boos IT
 * Ext GWT 2.2.5 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package co.com.sisegfut.client;

import co.com.sisegfut.client.base.PanelBase;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.ingreso.login.PanelAcceso;
import co.com.sisegfut.client.util.rpc.RPCLogin;
import co.com.sisegfut.client.util.rpc.RPCLoginAsync;
import co.com.sisegfut.client.util.BeansLocales;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


import com.google.gwt.user.client.ui.RootPanel;

/**
 * Punto de entrada de la aplicación
 *
 * @author 
 */
public class MainEntryPoint implements EntryPoint {

    private Viewport viewport;

    /**
     * Método constructor de MainEntryPoint
     */
    public MainEntryPoint() {
    }

    /**
     * Este es el método de entrada, es llamado de forma automática al cargar el
     * módulo que implementa el entry-point
     */
    public void onModuleLoad() {

//        PanelPrueba panelPrueba = new PanelPrueba();        
//        viewport.add(panelPrueba, new BorderLayoutData(Style.LayoutRegion.CENTER));
//        RootPanel.get().add(viewport);

        // Hacemos un llamado para validar si tenemos sessión y damos acceso al
        // interior o debemos mostrar el login
        final RPCLoginAsync svc = (RPCLoginAsync) GWT.create(RPCLogin.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCLogin");

        svc.obtenerDatosUsuario(new AsyncCallback<Usuarios>() {

            public void onFailure(Throwable caught) {
                viewport = new Viewport();
                viewport.setLayout(new BorderLayout());
                viewport.add(new PanelAcceso(), new BorderLayoutData(Style.LayoutRegion.CENTER));
                RootPanel.get().add(viewport);
            }

            public void onSuccess(Usuarios usuario) {
                // Miramos si el usuario está logueado
                if (usuario.getDocumento() != null && !usuario.getDocumento().isEmpty()) {
                    BeansLocales.setUsuario(usuario);
                    viewport = new Viewport();
                    viewport.setLayout(new BorderLayout());
                    viewport.add(new PanelBase(usuario), new BorderLayoutData(Style.LayoutRegion.CENTER));
                    RootPanel.get().add(viewport);
                } else // En caso de no estar logueado lo enviamos al login
                {
                    viewport = new Viewport();
                    viewport.setLayout(new BorderLayout());
                    viewport.add(new PanelAcceso(), new BorderLayoutData(Style.LayoutRegion.CENTER));
                    RootPanel.get().add(viewport);
                }
            }
        });
    }
//    /**
//     * Servicio RPC para el login y los datos de session de usuario
//     * @return 
//     */
//    private RPCLoginAsync getService()
//    {
//        return (RPCLoginAsync)PuertoRPC.setRpcEndPointUrl( GWT.create( RPCLogin.class ) );
//    }
}
