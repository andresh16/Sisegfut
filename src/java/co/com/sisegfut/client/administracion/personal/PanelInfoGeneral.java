package co.com.sisegfut.client.administracion.personal;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCargos;

import co.com.sisegfut.client.util.combox.ComboBoxNivelEducativo;

import co.com.sisegfut.client.util.combox.ComboBoxTipoDocumento;
import co.com.sisegfut.client.util.rpc.RPCAdminPersonal;
import co.com.sisegfut.client.util.rpc.RPCAdminPersonalAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Image;
import java.util.Date;

/**
 *
 * @author anfeh_000
 */
public final class PanelInfoGeneral extends FormPanel {

    ComboBoxTipoDocumento cbxTipoDocumento;
    TextField<String> txtDocumento = new TextField<String>();
    TextField<String> txtNombres = new TextField<String>();
    TextField<String> txtApellidos = new TextField<String>();
    DateField DtFecha = new DateField();
    ComboBoxNivelEducativo cbxNivelEdu;
    ComboBoxCargos cbxCargo;
    TextField<String> txtDireccion = new TextField<String>();
    TextField<String> txtCorreo = new TextField<String>();
    TextField<String> txtTelefono = new TextField<String>();
    TextField<String> txtCelular = new TextField<String>();
    TextField<String> txtBarrio = new TextField<String>();
    TimeField tmHoraInicio = new TimeField();
    TimeField tmHoraFin = new TimeField();
    ContentPanel contenedorFotoUsuario;

    CheckBox chLunes;
    CheckBox chMartes;
    CheckBox chMiercoles;
    CheckBox chJueves;
    CheckBox chViernes;
    CheckBox chSabado;

//    Button btnIMC = new Button("Calcular IMC", listenerCalcularImc());
    private Image foto = new Image();

    Long idPersonal = null;
    String nombreCompleto;
    FileUploadField file;

    Radio rdMasculino;
    Radio rdFemenino;

    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    PanelAdminPersonal adminPersonal;
    private Main myConstants = (Main) GWT.create(Main.class);

    //Creo el toolbar de paginacion de el grid
    public PanelInfoGeneral(PanelAdminPersonal panelPadre) {
        adminPersonal = panelPadre;
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
        LayoutContainer ColumnaDias = new LayoutContainer();
        ColumnaDias.setStyleAttribute("paddingRight", "10px");

        FormLayout layout = new FormLayout();
//        layout.setLabelAlign(LabelAlign.TOP);
        ColumnaDias.setLayout(layout);

        cbxTipoDocumento = new ComboBoxTipoDocumento(ACTIVOS);
        cbxTipoDocumento.setName("tipo_documento.tipoDocumento");
        cbxTipoDocumento.setToolTip(new ToolTipConfig("Tipo de documento", "Seleccione un tipo de documento.."));
        cbxTipoDocumento.setLabelSeparator("<font color='red'>*</font>Tipo documento");
        cbxTipoDocumento.setAllowBlank(false);
//        cbxTipoDocumento.setEditable(false);
        ColumnaDias.add(cbxTipoDocumento, formData);

        txtDocumento.setName("documento");
        txtDocumento.setFieldLabel("<font color='red'>*</font>Documento");
        txtDocumento.setRegex("^[.0-9]*$");
        txtDocumento.setEmptyText("");
        txtDocumento.setAllowBlank(false);
        txtDocumento.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDocumento.setEnabled(false);
        ColumnaDias.add(txtDocumento, formData);

        txtNombres.setName("nombres");
        txtNombres.setFieldLabel("<font color='red'>*</font>Nombres");
        txtNombres.setAllowBlank(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtNombres.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombres.setEnabled(false);
        ColumnaDias.add(txtNombres, formData);

        txtApellidos.setName("apellidos");
        txtApellidos.setFieldLabel("<font color='red'>*</font>Apellidos");
        txtApellidos.setAllowBlank(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtApellidos.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtApellidos.setEnabled(false);
        ColumnaDias.add(txtApellidos, formData);

        rdMasculino = new Radio();
        rdMasculino.setBoxLabel("Masculino");
        rdMasculino.setValue(true);

        rdFemenino = new Radio();
        rdFemenino.setBoxLabel("Femenino");
////        rdFemenino.setValue(true);

        RadioGroup radioGroup = new RadioGroup();
        radioGroup.setFieldLabel("Genero");
        radioGroup.add(rdMasculino);
        radioGroup.add(rdFemenino);

        DtFecha.setName("fecha_nacimiento");
        DtFecha.setAllowBlank(false);
//        DtFecha.setValue(new Date());
        Date fechaRestada = Formatos.RestarAnios(new Date(), -8);
//        Date fechaRestada = (new Date());

        DtFecha.setMaxValue(fechaRestada);

        DtFecha.setEditable(false);
        // DtFecha.setEnabled(false);
        DtFecha.setFieldLabel("<font color='red'>*</font>Fecha Nacimiento");
        ColumnaDias.add(DtFecha, formData);

        ColumnaDias.add(radioGroup, formData);

        txtTelefono.setName("telefono");
        txtTelefono.setAllowBlank(false);
        txtTelefono.setFieldLabel("<font color='red'>*</font>Télefono");
        txtTelefono.setRegex("^[.0-9]*$");
        txtTelefono.setEmptyText("");
        txtTelefono.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        ColumnaDias.add(txtTelefono, formData);

        txtCelular.setName("celular");
        txtCelular.setAllowBlank(false);
        txtCelular.setFieldLabel("<font color='red'>*</font>Celular");
        txtCelular.setRegex("^[.0-9]*$");
        txtCelular.setEmptyText("");
        txtCelular.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
        ColumnaDias.add(txtCelular, formData);

        txtDireccion.setName("direccion");
        txtDireccion.setAllowBlank(false);
        txtDireccion.setFieldLabel("<font color='red'>*</font>Dirección");
//        txtNombres.setRegex("^[.0-9]*$");
        txtDireccion.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        ColumnaDias.add(txtDireccion, formData);

        txtBarrio.setName("barrio");
        txtBarrio.setFieldLabel("<font color='red'>*</font>Barrio");
        txtBarrio.setAllowBlank(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtBarrio.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        ColumnaDias.add(txtBarrio, formData);

        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);

//        VBoxLayout layout2 = new VBoxLayout();  
//        layout2.setPadding(new Padding(20));  
//        layout2.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);  
//        Columna2.setLayout(layout2); 
        Columna2.setLayout(layout);

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
        contenedorFotoUsuario.addListener(Events.OnClick, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                abrirCargarFoto();
            }
        });
        contenedorFotoUsuario.add(foto);
        LayoutFoto.add(contenedorFotoUsuario);
//        fieldSet.add(contenedorFotoUsuario);
        Columna2.add(LayoutFoto, formData);

        cbxNivelEdu = new ComboBoxNivelEducativo(ACTIVOS);
        cbxNivelEdu.setName("nombre_inst_educativa.nombre_nivel_educativo");
        cbxNivelEdu.setToolTip(new ToolTipConfig("INivel de educación", "Seleccione el nivel de educación"));
        cbxNivelEdu.setFieldLabel("<font color='red'>*</font>Escolaridad");
        cbxNivelEdu.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna2.add(cbxNivelEdu, formData);

        cbxCargo = new ComboBoxCargos(ComboBoxCargos.ACTIVOS);
        cbxCargo.setName("cargo.nombrecargo");
        cbxCargo.setToolTip(new ToolTipConfig("Cargos", "Elija un cargo"));
        cbxCargo.setLabelSeparator("<font color='red'>*</font>Cargo");
        cbxCargo.setAllowBlank(false);
        Columna2.add(cbxCargo, formData);

        txtCorreo.setMaxLength(100);
        txtCorreo.setEmptyText("ejem. ejemplo@dominio.com");
        txtCorreo.setToolTip(new ToolTipConfig("Correo", "Digite el correo electrónico"));
        txtCorreo.setFieldLabel("<font color='red'>*</font>Correo");
        txtCorreo.setRegex("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$");
        txtCorreo.getMessages().setRegexText("Formato no valido, ej: ejemplo@dominio.com");
        txtCorreo.setAllowBlank(false);
        Columna2.add(txtCorreo, formData);

        main.add(ColumnaDias, new ColumnData(.5));
        main.add(Columna2, new ColumnData(.5));
//        main.add(Columna3, new ColumnData(.25));
//        main.add(Columna4, new ColumnData(.25));

        add(main, new FormData("100%"));
        FieldSet flHorario = new FieldSet();
        flHorario = new FieldSet();
        flHorario.setHeading("Horario");

        flHorario.setCollapsible(true);
        flHorario.setLayout(new ColumnLayout());
        flHorario.setAutoWidth(true);

        ///////////////////// Columna 1 ////////////////////////////  
        ColumnaDias = new LayoutContainer();
        ColumnaDias.setStyleAttribute("paddingRight", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        ColumnaDias.setLayout(layout);

        chLunes = new CheckBox();
        chLunes.setBoxLabel("Lunes");
        chLunes.setValue(true);

        chMartes = new CheckBox();
        chMartes.setBoxLabel("Martes");
        chMartes.setValue(true);

        chMiercoles = new CheckBox();
        chMiercoles.setBoxLabel("Miercoles");
        chMiercoles.setValue(true);

        CheckBoxGroup chGroup1 = new CheckBoxGroup();
        chGroup1.setOrientation(Style.Orientation.HORIZONTAL);
        chGroup1.setLabelSeparator("");
        chGroup1.add(chLunes);
        chGroup1.add(chMartes);
        chGroup1.add(chMiercoles);
        ColumnaDias.add(chGroup1);

        //////////////////////// Columna 2/////////////////////////
        LayoutContainer ColumnaDias2 = new LayoutContainer();
        ColumnaDias2.setStyleAttribute("paddingRight", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        ColumnaDias2.setLayout(layout);

        chJueves = new CheckBox();
        chJueves.setBoxLabel("Jueves");
        chJueves.setValue(true);

        chViernes = new CheckBox();
        chViernes.setBoxLabel("Viernes");
        chViernes.setValue(true);

        chSabado = new CheckBox();
        chSabado.setBoxLabel("Sábado");

        CheckBoxGroup chGroup2 = new CheckBoxGroup();
        chGroup2.setOrientation(Style.Orientation.HORIZONTAL);
        chGroup2.setLabelSeparator("");
        chGroup2.add(chJueves);
        chGroup2.add(chViernes);
        chGroup2.add(chSabado);
        ColumnaDias.add(chGroup2);

        //////////////////////// Columna 3/////////////////////////
        LayoutContainer ColumnaHoras = new LayoutContainer();
        ColumnaHoras.setStyleAttribute("paddingRight", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.TOP);
        ColumnaHoras.setLayout(layout);

        tmHoraInicio.setFieldLabel("<font color='red'>*</font> Hora Entrada");
//        tmHoraInicio.addPlugin(plugin);
        tmHoraInicio.setData("text", "Seleccione la hora");
        DateTimeFormat fmt = DateTimeFormat.getFormat("hh:mm aa");
        tmHoraInicio.setFormat(fmt);
        tmHoraInicio.setIncrement(30);
        tmHoraInicio.setForceSelection(true);
        tmHoraInicio.setTriggerAction(ComboBox.TriggerAction.ALL);
        tmHoraInicio.setEditable(false);
        tmHoraInicio.setAllowBlank(false);
        ColumnaHoras.add(tmHoraInicio, formData);
//        Columna1.add(tmHoraInicio, formData);

        tmHoraFin.setFieldLabel("<font color='red'>*</font> Hora Salida");
//        tmHoraFin.addPlugin(plugin);
        tmHoraFin.setData("text", "Seleccione la hora");
        DateTimeFormat fmtt = DateTimeFormat.getFormat("hh:mm aa");
        tmHoraFin.setFormat(fmt);
        tmHoraFin.setIncrement(30);
        tmHoraFin.setForceSelection(true);
        tmHoraFin.setTriggerAction(ComboBox.TriggerAction.ALL);
        tmHoraFin.setEditable(false);
        tmHoraFin.setAllowBlank(false);
        ColumnaHoras.add(tmHoraFin, formData);

        flHorario.add(ColumnaDias, new ColumnData(.5));
//        flHorario.add(ColumnaDias2, new ColumnData(.3));
        flHorario.add(ColumnaHoras, new ColumnData(.5));

        add(flHorario, new FormData("100%"));

        //   panel.setTopComponent(toolBar);
        Button btnGuardar = new Button(" Guardar", listenerGuardar());
        btnGuardar.setIcon(Resources.ICONS.iconoGuardar());

        Button btnLimpiar = new Button("Nuevo", listenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoNuevaCita());

        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnGuardar);

        setButtonAlign(Style.HorizontalAlignment.CENTER);
        addButton(btnGuardar);
        addButton(btnLimpiar);
//        addButton(btnIMC);

        if (idPersonal != null) {
            cargarfoto(idPersonal);
        } else {
            cargarFotoNoDiposible();
        }
    }

    public void setId(Long id) {
        this.idPersonal = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void abrirCargarFoto() {

        if (idPersonal != null) {
            String espacios = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
//                    PanelInfoGeneral panelInfoGeneral = new PanelInfoGeneral(PanelAdminDeportista.this);
            PanelCargarFotoPer panelCargarFotoPer = new PanelCargarFotoPer(adminPersonal);
            panelCargarFotoPer.setId(idPersonal);
            panelCargarFotoPer.lbNombrePer.setText(espacios + "<b>" + nombreCompleto + "</b>");
            panelCargarFotoPer.show();

        } else {
            MessageBox.alert("Alerta", "Debe seleccionar primero un personal", null);
        }
    }

    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                limpiar();
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerGuardar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (tmHoraFin.getValue().getDate().compareTo(tmHoraInicio.getValue().getDate()) > 0) {

                    if (isValid()) {

                        if (idPersonal != null) {

                            Personal personal = new Personal(idPersonal);
                            personal = obtenerDatosFormulario(personal);

                            getServicePersonal().updateConFoto(personal, new AsyncCallback<Personal>() {

                                @Override
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert("Error", "No actualizo el personal", null);
                                }

                                @Override
                                public void onSuccess(Personal result) {
                                    result.getFoto();

                                    Info.display("Guardar", "Guardo correctamente el deportista");
                                    limpiar();

                                }
                            });
                        } else {
                            Personal personal = new Personal();
                            personal = obtenerDatosFormulario(personal);
                            guardar(personal);
                        }
                    }
                } else {
                    MessageBox.alert("Error Horario", "La hora de entrada debe ser menor a la hora de salida", null);
                }
            }
        };

    }

    public Personal obtenerDatosFormulario(Personal personal) {

        personal.setTipoDocumento(cbxTipoDocumento.getTipoDocumentoElegido());
        personal.setDocumento(txtDocumento.getValue());
        personal.setNombres(txtNombres.getValue().toUpperCase());
        personal.setApellidos(txtApellidos.getValue().toUpperCase());
        personal.setFechaNacimiento(DtFecha.getValue());
        personal.setDireccion(txtDireccion.getValue());
        personal.setTelefono(txtTelefono.getValue());
        personal.setCelular(txtCelular.getValue());
        personal.setCorreoElectronico(txtCorreo.getValue());
        personal.setBarrio(txtBarrio.getValue().toUpperCase());
        String genero;
        if (rdMasculino.getValue()) {
            genero = "Masculino";
        } else {
            genero = "Femenino";
        }
        personal.setGenero(genero);
        personal.setNivelEducativo(cbxNivelEdu.getNivelEducativoElegido());
        personal.setCargo(cbxCargo.getCargoElegido());

        Date horaInicio = new Date();
        horaInicio.setTime(tmHoraInicio.getDateValue().getTime());
        personal.setHoraInicio(horaInicio);
        Date horaFin = new Date();
        horaFin.setTime(tmHoraFin.getDateValue().getTime());
        personal.setHoraFin(horaFin);

        personal.setLunes(chLunes.getValue());
        personal.setMartes(chMartes.getValue());
        personal.setMiercoles(chMiercoles.getValue());
        personal.setJueves(chJueves.getValue());
        personal.setViernes(chViernes.getValue());
        personal.setSabado(chSabado.getValue());

        return personal;
    }

    public RPCAdminPersonalAsync getServicePersonal() {
        RPCAdminPersonalAsync svc = (RPCAdminPersonalAsync) GWT.create(RPCAdminPersonal.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminPersonal");
        return svc;
    }

    public void setidPersonal(Long id) {
        this.idPersonal = id;

    }

    public void limpiar() {
        reset();
        idPersonal = null;
        adminPersonal.cargar();

        cargarFotoNoDiposible();

    }

    public void cargarfoto(Long idUsuario) {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        foto.setUrl(GWT.getHostPageBaseURL() + "html/foto/descargarp/" + idUsuario + "?milis=" + milis);

    }

    public void cargarfotoThis(Long id) {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        foto.setUrl(GWT.getHostPageBaseURL() + "html/foto/descargarp/" + id + "?milis=" + milis);

    }

    public void cargarFotoNoDiposible() {

        foto.setUrl("imagenes/fotoNoDisponible.jpg");
    }

    public void muestraFoto(Long id) {
        cargarfoto(id);
    }

    public void guardar(Personal personal) {

        getServicePersonal().guardarEntidad(personal, new AsyncCallback() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Error", "No guardo el personal", null);
            }

            @Override
            public void onSuccess(Object result) {
                Info.display("Guardar", "Guardo correctamente el personal");
                limpiar();
//                        MessageBox.alert("Guardar", "Guardo correctamente el deportista", null);
            }
        });
    }

}
