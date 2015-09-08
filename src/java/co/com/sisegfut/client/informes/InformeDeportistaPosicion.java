/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.informes;

import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author fhurtado
 */
public class InformeDeportistaPosicion extends FormPanel {

    private static final int TIPO_XLS = 1;
    private static final int TIPO_PDF = 2;
    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxCategoria cbxCategoria;

    public InformeDeportistaPosicion() {
        
        setHeaderVisible(false);
        setLayout(new FitLayout());

        FormData formData = new FormData("-20");

        cbxCategoria = new ComboBoxCategoria(ComboBoxCategoria.ACTIVOS);

        cbxCategoria.setLabelSeparator("Categoría");
        cbxCategoria.setAllowBlank(false);

        Button btnPDF = new Button(" Generar PDF", ListenerGenerarReporte(TIPO_PDF));
        btnPDF.setIcon(Resources.ICONS.iconoPDF());

        Button btnExcel = new Button("Generar Excel", ListenerGenerarReporte(TIPO_XLS));
        btnExcel.setIcon(Resources.ICONS.iconoExcel());

        panel.setFrame(true);
        panel.setHeaderVisible(false);
        
        panel.add(cbxCategoria, formData);

        panel.addButton(btnPDF);
        panel.addButton(btnExcel);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnPDF);
        binding.addButton(btnExcel);
        formBindings = new FormBinding(panel, true);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

         add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));
    }

    public SelectionListener<ButtonEvent> ListenerGenerarReporte(final int tipo) {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (panel.isValid()) {
                    Long idCategoria = cbxCategoria.getCategoriaElegida().getId();
                    String nombreCategoria=cbxCategoria.getCategoriaElegida().getNombrecategoria();

                    String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteDeportistaPosicion/";
                    // usuarioSession
                    redireccionarA(base + nombreCategoria + "/"+idCategoria+"/"+ + tipo);
                    panel.reset();
                } else {

                    MessageBox.alert("ERROR", "No envio al redireccionar panel invalido", null);
                }

            }
        };
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