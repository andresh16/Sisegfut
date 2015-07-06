/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.categoria;

import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminCategoriaAsync;
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
import com.extjs.gxt.ui.client.widget.MessageBox;
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

/**
 *
 * @author anfeh_000
 */
public class PanelModificarCategoria extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxCategoria cbxCategoria;
    TextField<String> txtCategoria = new TextField<String>();
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelModificarCategoria() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtCategoria.setName("categoria");
        txtCategoria.setFieldLabel("Categoria");
        txtCategoria.setAllowBlank(false);
        txtCategoria.setToolTip("Digite una Categoria que desee guardar");

        cbxCategoria = new ComboBoxCategoria(ComboBoxCategoria.ACTIVOS);

        cbxCategoria.setLabelSeparator("Categoria");
        cbxCategoria.setAllowBlank(false);

       // 
        Button btnModificar = new Button(" Modificar", ListenerModificar());
        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnModificar.setIcon(Resources.ICONS.iconoModificar());
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(cbxCategoria, formData);
        panel.add(txtCategoria, formData);
        panel.addButton(btnModificar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnModificar);

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        formBindings = new FormBinding(panel, true);

        cbxCategoria.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtCategoria.setValue(cbxCategoria.getCategoriaElegida().getNombrecategoria().toString());
                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);

            }
        });

        /**
         * Para escuchar la tecla enter
         */
        txtCategoria.addKeyListener(new KeyListener() {

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

    public void modificar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            Categoria categoria = new Categoria();
            categoria.setId(cbxCategoria.getCategoriaElegida().getId());
            categoria.setNombrecategoria(txtCategoria.getValue().toUpperCase());

            getServiceCategoria().guardarEntidad(categoria, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No Modifico el Tipo de Documento");
                    MessageBox.alert("Error", "No Modifico la Categoria", null);
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Modifico correctamente la Categoria");
                    pnlExito.setVisible(true);
                }

            });

            cbxCategoria.recargar();
            txtCategoria.reset();
        } else {
            pnlErrores.limpiar();
            pnlErrores.aniadir("Debe llenar el campo");
            pnlErrores.setVisible(true);
        }
    }

    public RPCAdminCategoriaAsync getServiceCategoria() {
        RPCAdminCategoriaAsync svc = (RPCAdminCategoriaAsync) GWT.create(RPCAdminCategoria.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCategoria");
        return svc;
    }

}
