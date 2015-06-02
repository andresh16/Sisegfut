/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.informes;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCuenta;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

/**
 *
 * @author fhurtado
 */
public class InformeCuenta extends LayoutContainer {

    private Usuarios usuarioSession;
    private FormBinding formBindings;
    private static final int TIPO_XLS = 1;
    private static final int TIPO_PDF = 2;
    FormPanel panel = new FormPanel();
    private ComboBoxCuenta cbxCuenta = new ComboBoxCuenta();
    DateField DtFechaIncial = new DateField();
    DateField DtFechaFinal = new DateField();
    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    private Main myConstants;

    public InformeCuenta() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);
       // setLayout(new FillLayout());
        myConstants = (Main) GWT.create(Main.class);

        usuarioSession = BeansLocales.getUsuario();
        setLayout(new FitLayout());

        ToolBar toolBar = new ToolBar();

        Button btnLimpiar = new Button(" Limpiar",ListenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoLimpiar());
        toolBar.add(btnLimpiar);
        toolBar.add(new SeparatorToolItem());


        Button btnPDF = new Button(" Generar PDF", ListenerGenerarReporte(2));
        btnPDF.setIcon(Resources.ICONS.iconoPDF());
        toolBar.add(btnPDF);
        toolBar.add(new SeparatorToolItem());


        Button btnExcel = new Button("Generar Excel", ListenerGenerarReporte(1));
        btnExcel.setIcon(Resources.ICONS.iconoExcel());
        toolBar.add(btnExcel);

        add(toolBar,new FlowData(0));
        
        panel.setFrame(true);
        panel.setHeaderVisible(false);
       // panel.setHeight(195);


        FormData formData = new FormData("-20");

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());

        LayoutContainer Columna = new LayoutContainer();
        Columna.setStyleAttribute("paddingRight", "20px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna.setLayout(layout);

        cbxCuenta = new ComboBoxCuenta();
        cbxCuenta.setName("ncuenta");
        cbxCuenta.setToolTip(new ToolTipConfig("Cuentas", "Elija una cuenta.."));
        cbxCuenta.setLabelSeparator("Cuenta");
        cbxCuenta.setAllowBlank(false);
        cbxCuenta.setEditable(false);
        Columna.add(cbxCuenta);

        DtFechaIncial.setEditable(false);
        DtFechaIncial.setFieldLabel("Fecha Inicial");
        DtFechaIncial.setAllowBlank(false);
        Columna.add(DtFechaIncial);

        DtFechaFinal.setEditable(false);
        DtFechaFinal.setAllowBlank(false);
        DtFechaFinal.setFieldLabel("Fecha Final");
        Columna.add(DtFechaFinal);


        main.add(Columna);

        panel.add(main, new FormData("100%"));
        

//        panel.setTopComponent(toolBar);

        //Monitors the valid state of a form and enabled / disabled all buttons.
        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnPDF);
        binding.addButton(btnExcel);

        formBindings = new FormBinding(panel, true);

        // cp.add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));


        //Agrego boton al panel principal que permite desplegar la ayuda.
        panel.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana(myConstants.ayudaPanelInformes());
            }
        }));

    }

    public SelectionListener<ButtonEvent> ListenerGenerarReporte(final int tipo) {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                String fechaI, fechaF;
                Long idCuenta;
                fechaI = Formatos.fecha2(DtFechaIncial.getValue());
                fechaF = Formatos.fecha2(DtFechaFinal.getValue());
                idCuenta = cbxCuenta.getCuentaElegido().getId();


                if (panel.isValid()) {

                    String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteMovimientos/";
                    // usuarioSession
                    redireccionarA(base + idCuenta + "/" + fechaI + "/" + fechaF + "/" + tipo);
                    limpiar();
                } else {

                    MessageBox.alert("ERROR", "NO envio al redireccionar panel invalido", null);
                }

            }
        };
    }

    public SelectionListener<ButtonEvent> ListenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                limpiar();
            }
        };
    }

    public void limpiar() {
        DtFechaFinal.reset();
        DtFechaIncial.reset();
        cbxCuenta.reset();
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

    /**
     * Mediante una llamada nativa redirecciona el browser a la dirección
     * especificada, en el caso de descargar archivos el contenido del browser
     * se conserva y simplemente lanza el archivo ;)
     *
     * @param url URL a ser cargada
     */
    private static native void redireccionarA(String url)/*-{
     $wnd.location = url;
     }-*/;
}
