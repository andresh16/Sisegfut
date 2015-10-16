/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.deportista;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxAutoCategoria;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import co.com.sisegfut.client.util.combox.ComboBoxEps;
import co.com.sisegfut.client.util.combox.ComboBoxInsEducativa;
import co.com.sisegfut.client.util.combox.ComboBoxNivelEducativo;
import co.com.sisegfut.client.util.combox.ComboBoxPosiciones;
import co.com.sisegfut.client.util.combox.ComboBoxTipoDeportista;
import co.com.sisegfut.client.util.combox.ComboBoxTipoDocumento;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.core.client.GWT;
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
    ComboBoxCategoria cbxCategoria;
    ComboBoxPosiciones cbxPosiciones;
    DateField DtFecha = new DateField();
    TextField<String> txtNombreMadre = new TextField<String>();
    TextField<String> txtNumeroMadre = new TextField<String>();
    TextField<String> txtNombrePadre = new TextField<String>();
    TextField<String> txtNumeroPadre = new TextField<String>();
    ComboBoxEps cbxEps;
    final SimpleComboBox<String> cbxEstratos;
    TextField<String> txtDireccion = new TextField<String>();
    TextField<String> txtCorreo = new TextField<String>();
    TextField<String> txtTelefono = new TextField<String>();
    TextField<String> txtBarrio = new TextField<String>();
    ComboBoxInsEducativa cbxInstEducativa;
    ComboBoxNivelEducativo cbxNivelEdu;
    ComboBoxTipoDeportista cbxTipoDeportista;
    TextField<String> txtPeso = new TextField<String>();
    TextField<String> txtNumCamisa = new TextField<String>();
    TextField<String> txtEstatura = new TextField<String>();
    TextField<String> txtImc = new TextField<String>();
    TextField<String> txtGrasa = new TextField<String>();
    TextField<String> txtFCM = new TextField<String>();
    ContentPanel contenedorFotoUsuario;
//    Button btnIMC = new Button("Calcular IMC", listenerCalcularImc());
    private Image foto = new Image();

    TextField<String> txtIngreso = new TextField<String>();

    TextField<String> txtEgreso = new TextField<String>();
    TextArea txtDescripcion = new TextArea();    
    ComboBoxAutoCategoria cbxAutoCategoria;
    Long idDeportista = null;
    String nombreCompleto;
    FileUploadField file;

    Radio rdMasculino;
    Radio rdFemenino;

    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    PanelAdminDeportista adminDeportista;
    private Main myConstants = (Main) GWT.create(Main.class);

    //Creo el toolbar de paginacion de el grid
    public PanelInfoGeneral(PanelAdminDeportista panelPadre) {
        adminDeportista = panelPadre;
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

        ///////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("paddingRight", "10px");

        FormLayout layout = new FormLayout();
//        layout.setLabelAlign(LabelAlign.TOP);
        Columna1.setLayout(layout);

        cbxTipoDocumento = new ComboBoxTipoDocumento(ACTIVOS);
        cbxTipoDocumento.setName("tipo_documento.tipoDocumento");
        cbxTipoDocumento.setToolTip(new ToolTipConfig("Tipo de documento", "Seleccione un tipo de documento.."));
        cbxTipoDocumento.setLabelSeparator("<font color='red'>*</font>Tipo documento");
        cbxTipoDocumento.setAllowBlank(false);
//        cbxTipoDocumento.setEditable(false);
        Columna1.add(cbxTipoDocumento, formData);

        txtDocumento.setName("documento");
        txtDocumento.setFieldLabel("<font color='red'>*</font>Documento");
        txtDocumento.setRegex("^[.0-9]*$");
        txtDocumento.setEmptyText("");
        txtDocumento.setAllowBlank(false);
        txtDocumento.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDocumento.setEnabled(false);
        Columna1.add(txtDocumento, formData);

        txtNombres.setName("nombres");
        txtNombres.setFieldLabel("<font color='red'>*</font>Nombres");
        txtNombres.setAllowBlank(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtNombres.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombres.setEnabled(false);
        Columna1.add(txtNombres, formData);
        
        
        txtNombres.addListener(Events.OnBlur, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                txtNombres.setValue(txtNombres.getValue().toUpperCase());
            }
        });

        txtApellidos.setName("apellidos");
        txtApellidos.setFieldLabel("<font color='red'>*</font>Apellidos");
        txtApellidos.setAllowBlank(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtApellidos.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtApellidos.setEnabled(false);
        
        txtApellidos.addListener(Events.OnBlur, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                txtApellidos.setValue(txtApellidos.getValue().toUpperCase());
            }
        });
        Columna1.add(txtApellidos, formData);

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
        Columna1.add(DtFecha, formData);

        Columna1.add(radioGroup, formData);

        txtTelefono.setName("telefono");
        txtTelefono.setAllowBlank(false);
        txtTelefono.setFieldLabel("<font color='red'>*</font>Télefono");
        txtTelefono.setRegex("^[.0-9]*$");
        txtTelefono.setEmptyText("");
        txtTelefono.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        Columna1.add(txtTelefono, formData);

        txtDireccion.setName("direccion");
        txtDireccion.setAllowBlank(false);
        txtDireccion.setFieldLabel("<font color='red'>*</font>Dirección");
//        txtNombres.setRegex("^[.0-9]*$");
        txtDireccion.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        Columna1.add(txtDireccion, formData);

        txtCorreo.setMaxLength(100);
        txtCorreo.setEmptyText("ejem. ejemplo@dominio.com");
        txtCorreo.setToolTip(new ToolTipConfig("Correo", "Digite el correo electrónico"));
        txtCorreo.setFieldLabel("<font color='red'>*</font>Correo");
        txtCorreo.setRegex("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$");
        txtCorreo.getMessages().setRegexText("Formato no valido, ej: ejemplo@dominio.com");
        txtCorreo.setAllowBlank(false);
        Columna1.add(txtCorreo, formData);

        txtBarrio.setName("barrio");
        txtBarrio.setFieldLabel("<font color='red'>*</font>Barrio");
        txtBarrio.setAllowBlank(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtBarrio.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        
        txtBarrio.addListener(Events.OnBlur, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                txtBarrio.setValue(txtBarrio.getValue().toUpperCase());
            }
        });
        Columna1.add(txtBarrio, formData);

        txtNombreMadre.setName("nombre_madre");
        txtNombreMadre.setAllowBlank(false);
        txtNombreMadre.setFieldLabel("<font color='red'>*</font>Nombre Madre");
//        txtNombres.setRegex("^[.0-9]*$");
        txtNombreMadre.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombreMadre.setEnabled(false);
        txtNombreMadre.addListener(Events.OnBlur, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                txtNombreMadre.setValue(txtNombreMadre.getValue().toUpperCase());
            }
        });
        
        Columna1.add(txtNombreMadre, formData);

        txtNumeroMadre.setName("numero_madre");

        txtNumeroMadre.setFieldLabel("<font color='red'>*</font>N° contacto");
        txtNumeroMadre.setRegex("^[.0-9]*$");
        txtNumeroMadre.setEmptyText("");
        txtNumeroMadre.setAllowBlank(false);
        txtNumeroMadre.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombreMadre.setEnabled(false);
        
        Columna1.add(txtNumeroMadre, formData);

        txtNombrePadre.setName("nombre_padre");
        txtNombrePadre.setAllowBlank(false);
        txtNombrePadre.setFieldLabel("<font color='red'>*</font>Nombre Padre");
//        txtNombres.setRegex("^[.0-9]*$");
        txtNombrePadre.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombrePadre.setEnabled(false);
         txtNombrePadre.addListener(Events.OnBlur, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                txtNombrePadre.setValue(txtNombrePadre.getValue().toUpperCase());
            }
        });
        
        Columna1.add(txtNombrePadre, formData);

        txtNumeroPadre.setName("numero_madre");
        txtNumeroPadre.setFieldLabel("N° contacto");
        txtNumeroPadre.setRegex("^[.0-9]*$");
        txtNumeroPadre.setEmptyText("");
        txtNumeroPadre.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombreMadre.setEnabled(false);
        Columna1.add(txtNumeroPadre, formData);

        cbxInstEducativa = new ComboBoxInsEducativa(ACTIVOS);
        cbxInstEducativa.setName("inst_educativa.nombre_inst_educativa");
        cbxInstEducativa.setToolTip(new ToolTipConfig("Institución educativa", "Seleccione una institución educativa"));
        cbxInstEducativa.setFieldLabel("<font color='red'>*</font>Inst. Educativa");
        cbxInstEducativa.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna1.add(cbxInstEducativa, formData);
        
        cbxNivelEdu = new ComboBoxNivelEducativo(ACTIVOS);
        cbxNivelEdu.setName("nombre_inst_educativa.nombre_nivel_educativo");
        cbxNivelEdu.setToolTip(new ToolTipConfig("INivel de educación", "Seleccione el nivel de educación"));
        cbxNivelEdu.setFieldLabel("<font color='red'>*</font>Escolaridad");
        cbxNivelEdu.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna1.add(cbxNivelEdu, formData);

        cbxEps = new ComboBoxEps(ACTIVOS);
        cbxEps.setName("eps.nombre_eps");
        cbxEps.setToolTip(new ToolTipConfig("Eps", "Seleccione una eps"));
        cbxEps.setFieldLabel("<font color='red'>*</font>Eps");
        cbxEps.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna1.add(cbxEps, formData);

//        //  cbxCategoria.setEditable(false);
//        Columna1.add(cbxEstratos, formData);
//        FieldSet fieldSet = new FieldSet();
//        fieldSet = new FieldSet();
//        fieldSet.setHeading("IMC");
//        fieldSet.setCollapsible(true);
//        layout = new FormLayout();
//        layout.setLabelWidth(75);
//        fieldSet.setLayout(layout);
//        txtImc.setName("imc");
//        txtImc.setFieldLabel("IMC");
////        txtNombres.setRegex("^[.0-9]*$");
//        txtImc.setEmptyText("");
////        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
////        txtImc.setEnabled(false);
////        fieldSet.add(txtImc, formData);
////        fieldSet.add(btnIMC);
//
////        Columna1.add(fieldSet, formData);
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

//        FieldSet fieldSet = new FieldSet();
//        fieldSet = new FieldSet();
//        fieldSet.setHeading("Foto");
//
//        fieldSet.setLayout(new CenterLayout());
//        fieldSet.setCollapsible(true);
//
//        layout = new FormLayout();
//
//        
//        fieldSet.setLayout(layout);
        contenedorFotoUsuario = new ContentPanel();
        contenedorFotoUsuario.setHeaderVisible(false);
        contenedorFotoUsuario.setVisible(true);

//        contenedorFotoUsuario.setToolTip("La foto debe tener un tamaño de 4x4");
        foto.setWidth("150px");
        foto.setHeight("160px");
        contenedorFotoUsuario.addListener(Events.OnClick, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                cargarFoto();
            }
        });
        contenedorFotoUsuario.add(foto);
        LayoutFoto.add(contenedorFotoUsuario);
//        fieldSet.add(contenedorFotoUsuario);
        Columna2.add(LayoutFoto, formData);
        
        txtNumCamisa.setName("numero_camisa");
        txtNumCamisa.setFieldLabel("<font color='red'>*</font> # Camisa");
        txtNumCamisa.setMaxLength(3);
        txtNumCamisa.setRegex("^[.0-9]*$");
        txtNumCamisa.setEmptyText("");
        txtNumCamisa.setAllowBlank(false);
        txtNumCamisa.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txttxtPesoPeso.setEnabled(false);
        Columna2.add(txtNumCamisa, formData);

        cbxTipoDeportista = new ComboBoxTipoDeportista(ACTIVOS);
        cbxTipoDeportista.setName("tipo_deportista");
        cbxTipoDeportista.setToolTip(new ToolTipConfig("Tipo Deportista", "Seleccione un tipo deportista"));
        cbxTipoDeportista.setFieldLabel("<font color='red'>*</font> Tipo Deportista");
        cbxTipoDeportista.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna2.add(cbxTipoDeportista, formData);
        
        cbxCategoria = new ComboBoxCategoria(ACTIVOS);
        cbxCategoria.setName("categoria.nombrecategoria");
        cbxCategoria.setToolTip(new ToolTipConfig("Categoria", "Seleccione una categoria"));
        cbxCategoria.setFieldLabel("<font color='red'>*</font> Categoria");
        cbxCategoria.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna2.add(cbxCategoria, formData);

        cbxPosiciones = new ComboBoxPosiciones(ACTIVOS);
        cbxPosiciones.setName("posicion.nombreposicion");
        cbxPosiciones.setToolTip(new ToolTipConfig("Posición", "Seleccione una posición"));
        cbxPosiciones.setFieldLabel("<font color='red'>*</font>Posición");
        cbxPosiciones.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        Columna2.add(cbxPosiciones, formData);

        cbxEstratos = new SimpleComboBox<String>();
        cbxEstratos.setFieldLabel("<font color='red'>*</font>Estrato");
//        cbxEstratos.setDisplayField("estrato");
        cbxEstratos.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbxEstratos.setAllowBlank(false);

        llenarEstractos();
        Columna2.add(cbxEstratos, formData);

       
        txtPeso.setName("peso");
        txtPeso.setFieldLabel("<font color='red'>*</font> Peso (Kg)");
        txtPeso.setMaxLength(4);
        txtPeso.setRegex("^[.0-9]*$");
        txtPeso.setEmptyText("");
        txtPeso.setAllowBlank(false);
        txtPeso.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txttxtPesoPeso.setEnabled(false);
        Columna2.add(txtPeso, formData);

        txtEstatura.setName("estatura ");
        txtEstatura.setFieldLabel("<font color='red'>*</font> Estatura (mt)");
        txtEstatura.setMaxLength(4);
        txtEstatura.setRegex("^[.0-9]*$");
        txtEstatura.setEmptyText("");
        txtEstatura.setAllowBlank(false);
        txtEstatura.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtEstatura.setEnabled(false);
        Columna2.add(txtEstatura, formData);

        txtImc.setName("imc");
        txtImc.setFieldLabel("IMC");
        txtImc.setAllowBlank(true);
        txtImc.setWidth(100);
        txtImc.setReadOnly(true);
        txtImc.setEmptyText("Indice masa corporal");
//
//        Label labelImc = new Label();
//
//        labelImc.setText(
//                "<font size='2' ><font color='red'>*</font>"
//                + "IMC: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>");

//        FormPanel cimc = new FormPanel();
//        cimc.setHeaderVisible(false);
//
//        cimc.setLayout(new ColumnLayout());
//
//       FormButtonBinding binding2 = new FormButtonBinding(cimc);
//       binding2.addButton(btnIMC);
//
//        cimc.add(labelImc);
//        cimc.setPadding(5);
//        cimc.add(txtImc, formData);
//        cimc.add(btnIMC, formData);
        Columna2.add(txtImc, formData);

//        Columna2.add(btnIMC);
        txtGrasa.setName("grasa");
        txtGrasa.setAllowBlank(true);
        txtGrasa.setFieldLabel("Grasa %");
        txtGrasa.setRegex("^[.0-9]*$");
        txtGrasa.setEmptyText("");
        txtGrasa.setReadOnly(true);

        Columna2.add(txtGrasa, formData);

        txtFCM.setName("grasa");
        txtFCM.setAllowBlank(true);
        txtFCM.setReadOnly(true);
        txtFCM.setFieldLabel("FCM");
        txtFCM.setMaxLength(4);
        txtFCM.setToolTip("Frecuencia Cardiáca Máxima");
        txtFCM.setEmptyText("Frecuencia Cardiáca Máxima");
//        txtFCM.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtGrasa.setEnabled(false);
        Columna2.add(txtFCM, formData);
//        Columna2.add(btnGrasa);

        cbxTipoDocumento.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

//                if (cbxTipoMovimiento.getTipoMovimientoElegido().getNombTipoMov().equals("Debito ( - )")) {
//
//                    cbxCuentaTranferir.setAllowBlank(true);
//                    cbxCuentaTranferir.setVisible(false);
//                } else if (cbxTipoMovimiento.getTipoMovimientoElegido().getNombTipoMov().equals("Credito  ( + )")) {
//
//                    cbxCuentaTranferir.setAllowBlank(true);
//                    cbxCuentaTranferir.setVisible(false);
//                } else if (cbxTipoMovimiento.getTipoMovimientoElegido().getNombTipoMov().equals("Transferencia")) {
//
//                    cbxCuentaTranferir.setAllowBlank(false);
//                    cbxCuentaTranferir.setVisible(true);
//                } else if (cbxTipoMovimiento.getTipoMovimientoElegido().getNombTipoMov().equals("POS ( - )")) {
//                    cbxCuentaTranferir.setVisible(false);
//                    cbxCuentaTranferir.setAllowBlank(true);
//                }
//                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);
            }
        });

        main.add(Columna1, new ColumnData(.5));
        main.add(Columna2, new ColumnData(.5));
//        main.add(Columna3, new ColumnData(.25));
//        main.add(Columna4, new ColumnData(.25));

        add(main, new FormData("100%"));

        //   panel.setTopComponent(toolBar);
        Button btnGuardar = new Button(" Guardar", listenerGuardar());
        btnGuardar.setIcon(Resources.ICONS.iconoGuardar());

        Button btnLimpiar = new Button("Nuevo", listenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoNuevaCita());

//        ToolBar toolBar = new ToolBar();
//        toolBar.setSpacing(2);
//        toolBar.add(btnGuardar);
////        toolBar.add(new SeparatorToolItem());
//        toolBar.add(btnLimpiar);
//        
//        setTopComponent(toolBar);
//        setBottomComponent(toolBar);
        // panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        //Monitors the valid state of a form and enabled / disabled all buttons.
        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnGuardar);

        setButtonAlign(Style.HorizontalAlignment.CENTER);
        addButton(btnGuardar);
        addButton(btnLimpiar);
//        addButton(btnIMC);

        if (idDeportista != null) {
            cargarfoto(idDeportista);
        } else {
            cargarFotoNoDiposible();
        }
    }

    public void setId(Long id) {
        this.idDeportista = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void cargarFoto() {

        if (idDeportista != null) {
            String espacios = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
//                    PanelInfoGeneral panelInfoGeneral = new PanelInfoGeneral(PanelAdminDeportista.this);

            PanelCargarFotoDep panelCargarFotoDep = new PanelCargarFotoDep(adminDeportista);
            panelCargarFotoDep.setId(idDeportista);
            panelCargarFotoDep.lbNombreDep.setText(espacios + "<b>" + nombreCompleto + "</b>");
            panelCargarFotoDep.show();

        } else {
            MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
        }
    }

    private String calcularImc(double peso, double estatura) {

        Double imc;

        imc = peso / (Math.pow(estatura, 2));
        
        //                Menor a 18 	 Peso bajo. Necesario valorar signos de desnutrición
        //18 a 24.9 	 Normal
        //25 a 26.9 	 Sobrepeso
        //Mayor a 27 	 Obesidad
        //27 a 29.9 	 Obesidad grado I. Riesgo relativo alto para desarrollar enfermedades cardiovasculares
        //30 a 39.9 	 Obesidad grado II. Riesgo relativo muy alto para el desarrollo de enfermedades cardiovasculares
        //Mayor a 40 	 Obesidad grado III Extrema o Mórbida. Riesgo relativo extremadamente alto para el desarrollo de enfermedades cardiovasculares
        String resultadoImc = null;
        if (imc < 18) {
            resultadoImc = "Peso bajo";
        } else if (imc >= 18 && imc < 25) {
            resultadoImc = "Normal";
        } else if (imc >= 25 && imc <27) {
            resultadoImc = "Sobrepeso";
        } else if (imc >= 27 && imc < 30) {
            resultadoImc = "Obesidad grado I";
        } else if (imc >= 30 && imc < 40) {
            resultadoImc = "Obesidad grado II";
        } else if (imc >= 40) {
            resultadoImc = "Obesidad grado III";
        }

        int cifras = (int) Math.pow(10, 2);
        imc = Math.rint(imc * cifras) / cifras;

//                    DecimalFormat formateador = new DecimalFormat("####.00");
//                    txtImc.setEnabled(true);
        resultadoImc = imc + " = " + resultadoImc;
//                    txtImc.setValue(formateador.format(imc) + " = " + resultadoImc);
        return resultadoImc;

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

                if (idDeportista != null) {

                    Deportista deportista = new Deportista(idDeportista);
                    deportista = obtenerDatosFormulario(deportista);
                                        
                    getServiceDeportista().updateConFoto(deportista, new AsyncCallback<Deportista>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Error", "No actualizo el deportista", null);
                        }

                        @Override
                        public void onSuccess(Deportista result) {
                            result.getFoto();
                            Info.display("Guardar", "Guardó correctamente el deportista");
                            limpiar();
                        }
                    });
                } else {
                    Deportista deportista = new Deportista();
                    deportista=obtenerDatosFormulario(deportista);
                    deportista.setGrasa("0");
                    guardar(deportista);
                }

            }
        };

    }

    public Deportista obtenerDatosFormulario(Deportista deportista) {

        deportista.setTipoDocumento(cbxTipoDocumento.getTipoDocumentoElegido());
        deportista.setDocumento(txtDocumento.getValue());
        deportista.setNombres(txtNombres.getValue().toUpperCase());
        deportista.setApellidos(txtApellidos.getValue().toUpperCase());
        deportista.setFechaNacimiento(DtFecha.getValue());
        deportista.setDireccion(txtDireccion.getValue());
        deportista.setTelefono(txtTelefono.getValue());
        deportista.setCorreoElectronico(txtCorreo.getValue());
        deportista.setNombreMadre(txtNombreMadre.getValue().toUpperCase());
        deportista.setNombrePadre(txtNombrePadre.getValue().toUpperCase());
        deportista.setNumeroMadre(txtNumeroMadre.getValue());
        deportista.setNumeroPadre(txtNumeroPadre.getValue());
        deportista.setNumeroCamisa(txtNumCamisa.getValue());
        deportista.setCategoria(cbxCategoria.getCategoriaElegida());
        deportista.setBarrio(txtBarrio.getValue().toUpperCase());
        deportista.setEstatura(txtEstatura.getValue());
        deportista.setImc(calcularImc(Double.parseDouble(txtPeso.getValue()), Double.parseDouble(txtEstatura.getValue())));
        deportista.setPeso(txtPeso.getValue());
        deportista.setGrasa(txtGrasa.getValue());
        deportista.setJugadorComodin(false);

        String genero;
        if (rdMasculino.getValue()) {
            genero = "MASCULINO";
        } else {
            genero = "FEMENINO";
        }
        deportista.setGenero(genero);
        deportista.setEstrato(cbxEstratos.getSimpleValue());
        deportista.setPosicion(cbxPosiciones.getPosicionesElegido());
        deportista.setEps(cbxEps.getEpsElegido());
        deportista.setInstEducativa(cbxInstEducativa.getInstEducativaElegida());
        deportista.setNivelEducativo(cbxNivelEdu.getNivelEducativoElegido());
        deportista.setTipoDeportista(cbxTipoDeportista.getTipoDeportistaElegido());

        return deportista;
    }

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

    public void llenarEstractos() {
        for (int i = 0; i < 6; i++) {
            cbxEstratos.add((i + 1) + "");
        }
    }

    public void setidDeportista(Long id) {
        this.idDeportista = id;

    }

    public void limpiar() {
        reset();
        idDeportista = null;
        adminDeportista.cargar();

        cargarFotoNoDiposible();

    }

    public void cargarfoto(Long idUsuario) {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        foto.setUrl(GWT.getHostPageBaseURL() + "html/foto/descargar/" + idUsuario + "?milis=" + milis);

    }

    public void cargarfotoThis(Long id) {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        foto.setUrl(GWT.getHostPageBaseURL() + "html/foto/descargar/" + id + "?milis=" + milis);

    }

    public void cargarFotoNoDiposible() {

        foto.setUrl("imagenes/fotoNoDisponible.jpg");
    }

    public void muestraFoto(Long id) {
        cargarfoto(id);
    }

    public void calcularFCM() {

        getServiceDeportista().CalcularFCM(DtFecha.getValue(), new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                txtFCM.setValue("");
            }

            @Override
            public void onSuccess(String result) {
                txtFCM.setValue(result);
            }
        });

    }

    public void guardar(Deportista deportista) {

        getServiceDeportista().guardarEntidad(deportista, new AsyncCallback() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Error", "No guardo el Deportista", null);
            }

            @Override
            public void onSuccess(Object result) {
                Info.display("Guardar", "Guardó correctamente el deportista");
                limpiar();
//                        MessageBox.alert("Guardar", "Guardo correctamente el deportista", null);
            }
        });
    }
}
