/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.personal;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.administracion.personal.PanelAdminPersonal;
import co.com.sisegfut.client.util.Resources;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import java.util.Date;

/**
 *
 * @author fhurtado
 */
public class PanelCargarFotoPer extends Window {

    ContentPanel contenedorFotoUsuario;
    FileUploadField file;
    Button btnGuardar = new Button("Guardar", listenerGuardar());
    Button btnCancelar = new Button("Cancelar", listenerCancelar());
    Long id = null;
    String base;//= GWT.getHostPageBaseURL() + "html/foto/cargar/"+ 1;// bno 
    private Image foto = new Image("imagenes/fotoNoDisponible.jpg");
    FormPanel panel = new FormPanel();
    private Main myConstants = (Main) GWT.create(Main.class);

    private PanelAdminPersonal panelAdminPersonal;
    Label lbNombrePer = new Label();

    public PanelCargarFotoPer(final PanelAdminPersonal adminPersonal) {
        setSize(375, 345);
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Cargar foto");
        setLayout(new FillLayout());
        setResizable(false);
//        setAutoHeight(true);

        this.panelAdminPersonal = adminPersonal;

        panel.setFrame(true);
//        panel.setSize("100%", "100%");
        panel.setHeaderVisible(false);

        panel.setAction(base);
        panel.setEncoding(Encoding.MULTIPART);
        panel.setMethod(Method.POST);
        panel.setLabelAlign(LabelAlign.TOP);

        panel.addListener(Events.Submit, new Listener<FormEvent>() {

            @Override
            public void handleEvent(FormEvent be) {
//                boxWait.close();
                String result = be.getResultHtml();

                if (result != null && result.contains("true")) {
                    if (id > -1) {
                        cargarfoto(id);
                        Info.display("Cargar foto", "Se cargó correctamente la foto");
                        panelAdminPersonal.recargarFoto(id);
                        
                    }

                } else {
                    MessageBox.alert("Error", "No se pudo cargar la imagen\n" + result, null);
                }

            }
        });

        lbNombrePer.setPosition(500, 30);
        panel.add(lbNombrePer);

        LayoutContainer LayoutFoto = new LayoutContainer();
        LayoutFoto.setStyleAttribute("paddingRight", "10px");

        HBoxLayout HBlayout = new HBoxLayout();
        HBlayout.setPadding(new Padding(10));
        HBlayout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.TOP);
        HBlayout.setPack(BoxLayout.BoxLayoutPack.CENTER);
        LayoutFoto.setLayout(HBlayout);

        contenedorFotoUsuario = new ContentPanel();
        contenedorFotoUsuario.setHeaderVisible(false);
        contenedorFotoUsuario.setVisible(true);
//        contenedorFotoUsuario.setToolTip("La foto debe tener un tamaño de 4x4");

        foto.setWidth("150px");
        foto.setHeight("160px");
        contenedorFotoUsuario.add(foto);
        LayoutFoto.add(contenedorFotoUsuario);

        foto.setWidth("150px");
        foto.setHeight("160px");
        contenedorFotoUsuario.add(foto);
        panel.add(LayoutFoto);

        file = new FileUploadField();
        file.setAllowBlank(false);
        file.setName("foto");
        file.setEmptyText("Seleccione una foto, de formato .png ó .jpg");
        file.setToolTip("Seleccione una foto con formato .png ó .jp \ndebe tener un tamaño de 4x4");
        file.setFieldLabel("Imagen personal");

        panel.add(file, new FormData("-10"));
        panel.addButton(btnGuardar);
        panel.addButton(btnCancelar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        btnGuardar.setIcon(Resources.ICONS.iconoGuardar());
        btnCancelar.setIcon(Resources.ICONS.iconoCancelar());

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnGuardar);
//        panel.add(fieldSet);
//            if(id!=null){
//        cargarfoto(id);
//            }else{
//        foto.setUrl("/imagenes/fotoNoDisponible.jpg");
//            }

        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana("Guarda foto del un personal");
            }
        }));

        add(panel, new MarginData(0));

        setFocusWidget(this.getButtonBar().getItem(0));
    }

    public void setId(Long id) {
        this.id = id;

        cargarfoto(id);

    }

    public void cargarfoto(Long idUsuario) {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        foto.setUrl(GWT.getHostPageBaseURL() + "html/foto/descargarp/" + idUsuario + "?milis=" + milis);

    }

//    public void cargarfotoNoDisponilble() {
//
//        contenedorFotoUsuario.addText("<img height='150' width='150' src='imagenes/fotoNoDisponible.jpg'/>");
//
//    }
    protected SelectionListener<ButtonEvent> listenerCancelar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                   PanelCargarFotoPer.this.hide();
                  panelAdminPersonal.recargarFoto(id);
            }
        };
    }
   



    protected SelectionListener<ButtonEvent> listenerGuardar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

//                 MessageBox.alert("Error","id deportista "+id,null);
                if (panel.isValid() && id != null) {
//                    boxWait = MessageBox.wait("Progreso",
//                            "Procesando los datos, por favor espere...", "Procesando...");
                    String nombreArchivo;
                    if (file.getValue() != null && !file.getValue().isEmpty()) {
                        nombreArchivo = file.getValue();
                        String ultimalinea = (nombreArchivo.substring(nombreArchivo.lastIndexOf("."))).toLowerCase();
                        if (ultimalinea.equalsIgnoreCase(".jpg") || ultimalinea.equalsIgnoreCase(".png")) {
                            base = GWT.getHostPageBaseURL() + "html/foto/cargarp/" + id;
                            panel.setAction(base);
                            panel.submit();
                        } else {
//                            boxWait.close();

                            MessageBox.alert("Error", "La imagen debe de tener la extensión .jpg o .png", null);
                        }
                    } else {
                        base = GWT.getHostPageBaseURL() + "html/foto/cargarp/" + id;
//                        nombreArchivo = urlFoto;
                        panel.submit();

                    }
                } else // Si hay errores
                {

                    MessageBox.alert("Advertencia!", "Debe seleccionar un deportista", null);
                }
            }
        };
    }

    public void limpiar() {
        panel.reset();
        id = null;

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
