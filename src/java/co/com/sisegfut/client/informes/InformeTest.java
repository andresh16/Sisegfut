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
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author ManuelAlejandro
 */
public class InformeTest extends FormPanel {

    private static final int TIPO_XLS = 1;
    private static final int TIPO_PDF = 2;
    private FormBinding formBindings;
    private ComboBoxCategoria cbxCategoria;
    private SimpleComboBox<String> comboTests = new SimpleComboBox<String>();

    public InformeTest() {
        setHeaderVisible(false);
//        setLayout(new FitLayout());
        setFrame(true);

        FormData formData = new FormData("-20");

        cbxCategoria = new ComboBoxCategoria(ComboBoxCategoria.ACTIVOS);

        cbxCategoria.setLabelSeparator("Categoría");
        cbxCategoria.setAllowBlank(false);
        cbxCategoria.setEditable(false);

        comboTests.setLabelSeparator("Tests");
        comboTests.setEditable(false);
        comboTests.setEmptyText("Seleccione un test");
        comboTests.setForceSelection(true);
        comboTests.setAllowBlank(false);
        comboTests.setTriggerAction(ComboBox.TriggerAction.ALL);
        comboTests.add("Test de Karvonen");
        comboTests.add("Test de Cooper");
        comboTests.add("Medidas Antropométricas");
        comboTests.add("Control Técnico");

        Button btnPDF = new Button(" Generar PDF", ListenerGenerarReporte(TIPO_PDF));
        btnPDF.setIcon(Resources.ICONS.iconoPDF());

        Button btnExcel = new Button("Generar Excel", ListenerGenerarReporte(TIPO_XLS));
        btnExcel.setIcon(Resources.ICONS.iconoExcel());

        add(cbxCategoria, formData);
        add(comboTests, formData);

        addButton(btnPDF);
//        panel.addButton(btnExcel);

        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnPDF);
        binding.addButton(btnExcel);
        formBindings = new FormBinding(this, true);
        setButtonAlign(Style.HorizontalAlignment.CENTER);

    }

    public SelectionListener<ButtonEvent> ListenerGenerarReporte(final int tipo) {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (isValid()) {
                    Long idCategoria = cbxCategoria.getCategoriaElegida().getId();
                    if (comboTests.getSimpleValue().equalsIgnoreCase("Test de Karvonen")) {
                        System.out.println("Test Karvonen " + idCategoria);
                        idCategoria = cbxCategoria.getCategoriaElegida().getId();
                        String nombreCategoria = cbxCategoria.getCategoriaElegida().getNombrecategoria();
                        String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteTestKarvonen/";
                        redireccionarA(base + nombreCategoria + "/" + idCategoria + "/" + tipo);
                    } else if (comboTests.getSimpleValue().equalsIgnoreCase("Test de Cooper")) {
                        System.out.println("Test Cooper " + idCategoria);
                        idCategoria = cbxCategoria.getCategoriaElegida().getId();
                        String nombreCategoria = cbxCategoria.getCategoriaElegida().getNombrecategoria();
                        String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteTestCooper/";
                        redireccionarA(base + nombreCategoria + "/" + idCategoria + "/" + tipo);
                    } else if (comboTests.getSimpleValue().equalsIgnoreCase("Medidas Antropométricas")) {
                        System.out.println("Test Antropometrico " + idCategoria);
                        idCategoria = cbxCategoria.getCategoriaElegida().getId();
                        String nombreCategoria = cbxCategoria.getCategoriaElegida().getNombrecategoria();
                        String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteAntropometrico/";
                        redireccionarA(base + nombreCategoria + "/" + idCategoria + "/" + tipo);
                    } else if (comboTests.getSimpleValue().equalsIgnoreCase("Control Técnico")) {
                        System.out.println("Control Técnico " + idCategoria);
                        idCategoria = cbxCategoria.getCategoriaElegida().getId();
                        String nombreCategoria = cbxCategoria.getCategoriaElegida().getNombrecategoria();
                        String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteControlTecnico/";
                        redireccionarA(base + nombreCategoria + "/" + idCategoria + "/" + tipo);
                    }
                    reset();
                } else {

                    MessageBox.alert("ERROR", "No envio al redireccionar, panel inválido", null);
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
