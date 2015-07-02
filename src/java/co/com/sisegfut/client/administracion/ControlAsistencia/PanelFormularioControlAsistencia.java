/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.ControlAsistencia;

import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Time;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;

/**
 *
 * @author fhurtado
 */
public class PanelFormularioControlAsistencia extends FormPanel {

    ComboBoxCategoria cbxCategoria;
    DateField DtFecha = new DateField();
    TextArea txtObservacion = new TextArea();
    TimeField tmHora = new TimeField();
    TextField<String> txtLugar = new TextField<String>();
    Radio rdCompetencia;
    Radio rdEntrenamiento;
    RadioGroup radioGroup;
    Long id = null;
    String fechaLarga;
    Date fechaActual = new Date();
    Date fechaActividad = new Date();

    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    PanelAdminControlAsistencia adminControlAsistencia;

    //Creo el toolbar de paginacion de el grid
    public PanelFormularioControlAsistencia(PanelAdminControlAsistencia panelPadre) {
        adminControlAsistencia = panelPadre;
        setScrollMode(Style.Scroll.AUTOX);
        setScrollMode(Style.Scroll.AUTOY);

        setFrame(true);
        setSize("100%", "100%");
        setHeaderVisible(false);

        // Layout Main que contiene todas las columnas 
        FormData formData = new FormData("-20");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        main.setAutoHeight(afterRender);

        ComponentPlugin plugin = new ComponentPlugin() {
            public void init(Component component) {
                component.addListener(Events.Render, new Listener<ComponentEvent>() {
                    public void handleEvent(ComponentEvent be) {
                        El elem = be.getComponent().el().findParent(".x-form-element", 3);
                        // should style in external CSS  rather than directly  
                        elem.appendChild(XDOM.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>" + be.getComponent().getData("text") + "</div>"));
                    }
                });
            }
        };
        ///////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("paddingRight", "10px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.LEFT);
        Columna1.setLayout(layout);

        cbxCategoria = new ComboBoxCategoria(ACTIVOS);
        cbxCategoria.setName("categoria.nombrecategoria");
        cbxCategoria.setToolTip(new ToolTipConfig("Categoria", "Seleccione una categoria"));
        cbxCategoria.setFieldLabel("<font color='red'>*</font> Categoria");
        cbxCategoria.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna1.add(cbxCategoria, formData);

        DtFecha.setName("fecha_nacimiento");
        DtFecha.setAllowBlank(false);
        DtFecha.setEnabled(false);
        DtFecha.setMinValue(fechaActual);
        DtFecha.setEditable(false);

        DtFecha.setValue(new Date());

        DtFecha.setFieldLabel("<font color='red'>*</font> Fecha ");
        Columna1.add(DtFecha, formData);

        tmHora.setFieldLabel("<font color='red'>*</font> Hora");
//        tmHora.addPlugin(plugin);
//        tmHora.setData("text", "Seleccione la hora");
        tmHora.setEmptyText("Seleccione la hora");
        DateTimeFormat fmt = DateTimeFormat.getFormat("hh:mm aa");
        tmHora.setFormat(fmt);
        tmHora.setIncrement(30);
        tmHora.setForceSelection(true);
        tmHora.setEnabled(false);
        tmHora.setTriggerAction(ComboBox.TriggerAction.ALL);
        tmHora.setEditable(false);
        tmHora.setAllowBlank(false);
        Columna1.add(tmHora, formData);

        cbxCategoria.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
           if(!cbxCategoria.getValue().equals("") || !cbxCategoria.getValueField().equals("")) {
                adminControlAsistencia.cargar(cbxCategoria.getCategoriaElegida().getId());
                adminControlAsistencia.fechaAsistencia(Formatos.fechaLarga2(new Date()));
                DtFecha.setEnabled(true);
                tmHora.setEnabled(true);

                radioGroup.setEnabled(true);
                txtLugar.setEnabled(true);
                txtObservacion.setEnabled(true);
           }
            }
        });

        DtFecha.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                adminControlAsistencia.fechaAsistencia(Formatos.fechaLarga2(DtFecha.getValue()));
            }
        });
        rdEntrenamiento = new Radio();
        rdEntrenamiento.setBoxLabel("Entrenamiento");
        rdEntrenamiento.setValue(true);

        rdCompetencia = new Radio();
        rdCompetencia.setBoxLabel("Competencia");
//        rdCompetencia.setValue(true);

        radioGroup = new RadioGroup();
        radioGroup.setFieldLabel("<font color='red'>*</font> Actividad");
        radioGroup.setEnabled(false);
        radioGroup.add(rdCompetencia);
        radioGroup.add(rdEntrenamiento);
        Columna1.add(radioGroup, formData);

        txtLugar.setName("lugar");
        txtLugar.setFieldLabel("<font color='red'>*</font> Lugar");
        txtLugar.setAllowBlank(false);
        txtLugar.setEnabled(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtLugar.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        Columna1.add(txtLugar, formData);

        txtObservacion.setName("observaciones");
        txtObservacion.setFieldLabel("Observaciones");
        txtObservacion.setMaxLength(500);
        txtObservacion.setValue("  ");
        txtObservacion.setEnabled(false);
        txtObservacion.setEmptyText("Escriba un comentario");
        Columna1.add(txtObservacion, formData);

        main.add(Columna1, new ColumnData(1.0));

//        main.add(Columna3, new ColumnData(.25));
//        main.add(Columna4, new ColumnData(.25));
        add(main, new FormData("100%"));

////        //   panel.setTopComponent(toolBar);
////        Button btnGuardar = new Button(" Guardar");
////        btnGuardar.setIcon(Resources.ICONS.iconoGuardar());
////
////        Button btnLimpiar = new Button("Nuevo", listenerLimpiar());
////        btnLimpiar.setIcon(Resources.ICONS.iconoNuevaCita());
//
////        FormButtonBinding binding = new FormButtonBinding(this);
////        binding.addButton(btnGuardar);
////
////        setButtonAlign(Style.HorizontalAlignment.CENTER);
////        addButton(btnGuardar);
////        addButton(btnLimpiar);
    }

    public void setId(Long id) {
        this.id = id;
    }

//    protected SelectionListener<ButtonEvent> listenerLimpiar() {
//        return new SelectionListener<ButtonEvent>() {
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                limpiar();
//            }
//        };
//
//    }

    public void setidDeportista(Long id) {
        this.id = id;

    }

    public void limpiar() {
        
        id = null;
        DtFecha.setEnabled(false);
        DtFecha.setValue(new Date());
        tmHora.setEnabled(false);
        tmHora.setValue(new Time());
        radioGroup.setEnabled(false);
        txtLugar.setEnabled(false);
        txtLugar.setValue("");
        txtObservacion.setEnabled(false);
        txtObservacion.setValue(null);
        cbxCategoria.reset();
        cbxCategoria.recargar();
//        reset();

    }

    public ControlAsistencia ObtenerFormulario(ControlAsistencia controlAsistencia) {

        controlAsistencia.setCategoria(cbxCategoria.getCategoriaElegida());
        ////////////////////////////////////////////////////
        fechaActividad = DtFecha.getValue();
        System.out.println("hora actividad" + Formatos.Hora(tmHora.getDateValue()));
        fechaActividad.setTime(tmHora.getDateValue().getTime());
        System.out.println("fecha  " + fechaActividad);
        controlAsistencia.setFecha(fechaActual);
        ///////////////////////////////////////////////////
        String actividad = "";
        if (rdCompetencia.getValue() == true) {
            actividad = "Competencia";
        } else {
            actividad = "Entrenamiento";
        }
        controlAsistencia.setActividad(actividad);
        controlAsistencia.setLugar(txtLugar.getValue());
        controlAsistencia.setObservaciones(txtObservacion.getValue());

        return controlAsistencia;
    }

  
}
