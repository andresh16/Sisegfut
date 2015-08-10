/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetenciaAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import static org.mortbay.util.LazyList.add;

/**
 *
 * @author fhurtado
 */
public class PanelReactivarCompetencia extends Window {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxCompetencia comboBoxCompetencia;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    private Main myConstants = (Main) GWT.create(Main.class);

    public PanelReactivarCompetencia() {
        setSize(350, 130);
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Reactivar competencia");
        setLayout(new FillLayout());
        setResizable(false);
        setAutoHeight(true);
        
        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        panel.setAutoHeight(true);

        comboBoxCompetencia = new ComboBoxCompetencia(ComboBoxCompetencia.INACTIVOS);

        comboBoxCompetencia.setLabelSeparator("Competencia");
        comboBoxCompetencia.setAllowBlank(false);

        // 
        Button btnReactivar = new Button("Reactivar", ListenerReactivar());
        btnReactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnReactivar.setIcon(Resources.ICONS.iconoRefrescar());
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(comboBoxCompetencia, formData);

        panel.addButton(btnReactivar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnReactivar);

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        comboBoxCompetencia.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)
                    reactivar();
                }
            }
        });
        
        //Agrego boton al panel principal que permite desplegar la ayuda.
        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana("Panel que permite reactivar una competencia");
            }
        }));
        
        setFocusWidget(this.getButtonBar().getItem(0));

    }

    public SelectionListener<ButtonEvent> ListenerReactivar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                reactivar();
            }
        };
    }

    public void reactivar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            getServiceCompetencia().reactivarEntidad(comboBoxCompetencia.getCompetenciaElegida().getIdCompetencia(), new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No se Reactivo la competencia");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Reactivo correctamente la compentencia");
                    pnlExito.setVisible(true);
                }

            });
            comboBoxCompetencia.recargar();

        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe seleccionar una competencia");
        }
    }

    public RPCAdminCompetenciaAsync getServiceCompetencia() {
        RPCAdminCompetenciaAsync svc = (RPCAdminCompetenciaAsync) GWT.create(RPCAdminCompetencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCompetencia");
        return svc;
    }
    
      /**
     * Abre ventana de ayuda.
     */
    private void abrirVentana(String texto) {
        final Dialog simple = new Dialog();
        simple.setHeading("Ayuda");
        simple.setButtons(Dialog.OK);
        simple.setBodyStyleName("pad-text");
        simple.addText(texto);
        simple.getItem(0).getFocusSupport().setIgnore(true);
        simple.setScrollMode(Style.Scroll.AUTO);
        simple.setHideOnButtonClick(true);
        simple.setWidth(550);
        //simple.setSize(550, 255);

        simple.show();
    }

}
