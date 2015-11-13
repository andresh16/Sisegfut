/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.informes;

import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxTipoDeportista;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author ManuelAlejandro
 */
public class InformeTipoDeportista extends FormPanel{
    
     private static final int TIPO_XLS = 1;
    private static final int TIPO_PDF = 2;
    private FormBinding formBindings;
    private ComboBoxTipoDeportista cbxTipoDeportista;

    public InformeTipoDeportista() {
        
       setHeaderVisible(false);
//        setLayout(new FillLayout());
        setFrame(true);

        FormData formData = new FormData("-20");

        cbxTipoDeportista = new ComboBoxTipoDeportista(ComboBoxTipoDeportista.ACTIVOS);
        cbxTipoDeportista.setLabelSeparator("Tipo Deportista");
        cbxTipoDeportista.setAllowBlank(false);
        cbxTipoDeportista.setEditable(false);

        Button btnPDF = new Button(" Generar PDF", ListenerGenerarReporte(TIPO_PDF));
        btnPDF.setIcon(Resources.ICONS.iconoPDF());

        Button btnExcel = new Button("Generar Excel", ListenerGenerarReporte(TIPO_XLS));
        btnExcel.setIcon(Resources.ICONS.iconoExcel());
                
        add(cbxTipoDeportista,formData);

        addButton(btnPDF);
//        panel.addButton(btnExcel);

        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnPDF);
//        binding.addButton(btnExcel);
        formBindings = new FormBinding(this, true);
        setButtonAlign(Style.HorizontalAlignment.CENTER);                
    }
    
    public SelectionListener<ButtonEvent> ListenerGenerarReporte(final int tipo) {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (isValid()) {
                    Long idTipoDeportista = cbxTipoDeportista.getTipoDeportistaElegido().getId();
                    String nombreTipoDeportista=cbxTipoDeportista.getTipoDeportistaElegido().getNombreTipoDeportista();

                    String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteDeportistaTipoDeportista/";
                    // usuarioSession
                    redireccionarA(base + nombreTipoDeportista + "/"+idTipoDeportista+"/"+ + tipo);
                   reset();
                } else {

                    MessageBox.alert("ERROR", "No envio al redireccionar panel inválido", null);
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
