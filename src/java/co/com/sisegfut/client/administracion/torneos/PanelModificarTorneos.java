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
import co.com.sisegfut.client.util.combox.ComboBoxTorneo;
import co.com.sisegfut.client.util.rpc.RPCAdminTorneos;
import co.com.sisegfut.client.util.rpc.RPCAdminTorneosAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.Date;

/**
 *
 * @author Malejandro
 */
public class PanelModificarTorneos extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxTorneo cbxTorneo;
    private ComboBoxCategoria cbxCategoria;

    TextField<String> txtTorneo = new TextField<String>();
    TextField<String> txtAnno = new TextField<String>();

    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelModificarTorneos() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtTorneo.setName("torneo");
        txtTorneo.setFieldLabel("Nombre Torneo");
        txtTorneo.setAllowBlank(false);
        txtTorneo.setToolTip("Digite un torneo que desee guardar");

        cbxTorneo = new ComboBoxTorneo(ComboBoxTorneo.ACTIVOS);
        cbxTorneo.setLabelSeparator("Torneo:");
        cbxTorneo.setAllowBlank(false);

        txtAnno.setFieldLabel("Año");
        txtAnno.setAllowBlank(false);
        txtAnno.setName("anno");
        txtAnno.setMaxLength(4);
        txtAnno.setMinLength(4);
        Date anio = new Date();
        txtAnno.setRegex("^[.0-9]*$");
        txtAnno.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
        txtAnno.setValue(Formatos.anio(anio));

        cbxCategoria = new ComboBoxCategoria(ComboBoxCategoria.ACTIVOS);
        cbxCategoria.setLabelSeparator("Categoría:");
        cbxCategoria.setAllowBlank(false);

        // 
        Button btnModificar = new Button(" Modificar", ListenerModificar());
        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnModificar.setIcon(Resources.ICONS.iconoModificar());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(cbxTorneo, formData);
        panel.add(txtTorneo, formData);
        panel.add(txtAnno, formData);
        panel.add(cbxCategoria, formData);
        panel.addButton(btnModificar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnModificar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        formBindings = new FormBinding(panel, true);

        cbxTorneo.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtTorneo.setValue(cbxTorneo.getTorneosElegido().getNombreTorneo().toString());
                txtAnno.setValue(cbxTorneo.getTorneosElegido().getAnno());
                cbxCategoria.seleccionar(cbxTorneo.getTorneosElegido().getCategoria().getId());
                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);

            }
        });
        /**
         * Para escuchar la tecla enter
         */
        cbxCategoria.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)  
                    modificar();
                }
            }
        });

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

    }

    public SelectionListener<ButtonEvent> ListenerModificar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                modificar();
            }
        };
    }

    public RPCAdminTorneosAsync getServiceTorneos() {
        RPCAdminTorneosAsync svc = (RPCAdminTorneosAsync) GWT.create(RPCAdminTorneos.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTorneos");
        return svc;
    }

    public void modificar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            Torneos torneo = new Torneos();
            torneo.setId(cbxTorneo.getTorneosElegido().getId());
            torneo.setCategoria(cbxCategoria.getCategoriaElegida());
            torneo.setNombreTorneo(txtTorneo.getValue().toUpperCase().toUpperCase());
            torneo.setAnno(txtAnno.getValue());

            getServiceTorneos().guardarEntidad(torneo, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No Modifico el torneo");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Modificó correctamente el torneo");
                    pnlExito.setVisible(true);
                }

            });
            panel.reset();
//                    txtTorneo.reset();
//                    cbxTorneo.recargar();
        } else {
            pnlErrores.limpiar();
            pnlErrores.aniadir("Debe llenar todos los campos");
            pnlErrores.setVisible(true);
        }
    }
}
