/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.torneos;

import co.com.sisegfut.client.datos.dominio.Torneos;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminTorneos;
import co.com.sisegfut.client.util.rpc.RPCAdminTorneosAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.Date;

/**
 *
 * @author Malejandro
 */
public class PanelCrearTorneos extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    TextField<String> txtTorneo = new TextField<String>();
    TextField<String> txtAnno = new TextField<String>();
    private ComboBoxCategoria cbxCategoria;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    public PanelCrearTorneos() {

    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);
        // setLayout(new FillLayout());
//        myConstants = (Main) GWT.create(Main.class);

        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtTorneo.setName("torneo");
        txtTorneo.setFieldLabel("Nombre Torneo");
        txtTorneo.setToolTip("Digite un torneo que desee guardar");

        txtAnno.setName("anno");
        txtAnno.setFieldLabel("Año");
        Date anio = new Date();
        txtAnno.setValue(Formatos.anio(anio));
        txtAnno.setToolTip("Digite un año que desee guardar");

        cbxCategoria = new ComboBoxCategoria(ComboBoxCategoria.ACTIVOS);
        cbxCategoria.setLabelSeparator("Categoría:");
        cbxCategoria.setAllowBlank(false);

        Button btnCrear = new Button(" Crear", ListenerCrear(1));
        btnCrear.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnCrear.setIcon(Resources.ICONS.iconoCrear());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(txtTorneo);
        panel.add(txtAnno);
        panel.add(cbxCategoria);
        panel.addButton(btnCrear);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnCrear);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        cbxCategoria.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)
                    guardar();

                }
            }
        });

    }

    public SelectionListener<ButtonEvent> ListenerCrear(final int tipo) {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                guardar();
            }
        };
    }

    public void guardar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {
            Torneos torneos = new Torneos();
            torneos.setNombreTorneo(txtTorneo.getValue().toUpperCase());
            torneos.setAnno(txtAnno.getValue());
            torneos.setCategoria(cbxCategoria.getCategoriaElegida());

            getServiceTorneos().guardarEntidad(torneos, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No guardó el torneo");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Guardó correctamente el torneo");
                    pnlExito.setVisible(true);
                }

            });
            panel.reset();
        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe llenar todos los campos");
        }

    }

    public RPCAdminTorneosAsync getServiceTorneos() {
        RPCAdminTorneosAsync svc = (RPCAdminTorneosAsync) GWT.create(RPCAdminTorneos.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTorneos");
        return svc;
    }

}
