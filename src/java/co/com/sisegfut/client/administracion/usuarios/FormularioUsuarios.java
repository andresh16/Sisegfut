/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.usuarios;

import co.com.sisegfut.client.adminCRUDgral.FormularioCRUD;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.combox.ComboBoxRoles;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.*;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;

/**
 *
 *
 */
public class FormularioUsuarios extends FormularioCRUD<Usuarios> {


    private TextField<String> txtCorreo;
    private TextField<String> txtDocumento;
    private TextField<String> txtNombres;
    private TextField<String> txtApellidos;

    private ComboBoxRoles comboBoxRoles;
    

 
    /**
     * Constructor
     * @param listenerGuardado 
     */
     public FormularioUsuarios(ListenerGuardado listenerGuardado) {

        
        super(listenerGuardado);        

        txtCorreo = new TextField<String>();
        txtCorreo.setMaxLength(150);
        txtCorreo.setName("correoElectronico");
        txtCorreo.setToolTip(new ToolTipConfig("Correo electrónico", "Escriba la dirección de correo electrónico del usuario"));
        txtCorreo.setFieldLabel("Correo electrónico");
        txtCorreo.setEmptyText("ex. name@domain.com");
        txtCorreo.setRegex("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$");
        txtCorreo.getMessages().setRegexText("El correo indicado no tiene un formato válido.");
//        txtCorreo.setAllowBlank(false);
        txtCorreo.getFocusSupport().setPreviousId(getButtonBar().getId());
        add(txtCorreo, formData);

        txtDocumento = new TextField<String>();
        txtDocumento.setMaxLength(50);
        txtDocumento.setName("documento");
        txtDocumento.setToolTip(new ToolTipConfig("Documento", "Escriba el número de documento del usuario"));
        txtDocumento.setFieldLabel("Documento");
        txtDocumento.setAllowBlank(false);
        txtDocumento.setRegex("^[^\\s].*");
//        txtDocumento.setRegex("^((\\d{8})|(\\d{10})|(\\d{11})|(\\d{6}-\\d{5}))?$");
        txtDocumento.getMessages().setRegexText("El campo no puede contener solo espacios en blanco.");
        add(txtDocumento, formData);


        txtNombres = new TextField<String>();
        txtNombres.setMaxLength(150);
        txtNombres.setName("nombres");
        txtNombres.setToolTip(new ToolTipConfig("Nombres", "Escriba el nombre del usuario"));
        txtNombres.setFieldLabel("Nombres");
        txtNombres.setAllowBlank(false);
        txtNombres.setRegex("^[^\\s].*");
        txtNombres.getMessages().setRegexText("El campo no puede contener solo espacios en blanco.");
        add(txtNombres, formData);

        txtApellidos = new TextField<String>();
        txtApellidos.setMaxLength(150);
        txtApellidos.setName("apellidos");
        txtApellidos.setToolTip(new ToolTipConfig("Apellidos", "Escriba el apellido del usuario"));
        txtApellidos.setFieldLabel("Apellidos");
        txtApellidos.setAllowBlank(false);
        txtApellidos.setRegex("^[^\\s].*");
        txtApellidos.getMessages().setRegexText("El campo no puede contener solo espacios en blanco.");
        add(txtApellidos, formData);

        comboBoxRoles = new ComboBoxRoles();
        comboBoxRoles.setName("rol");
        comboBoxRoles.setToolTip(new ToolTipConfig("Rol", "Elija el rol del usuario"));
        comboBoxRoles.setFieldLabel("Rol");
        comboBoxRoles.setAllowBlank(false);
        add(comboBoxRoles, formData);

        Button btnAceptar = new Button("Guardar", Resources.ICONS.iconoGuardar(), listenerGuardar());
        Button btnLimpiar = new Button("Cancelar", Resources.ICONS.iconoCancelar(), listenerLimpiar());
        Button btnCambiarContraseña = new Button("Cambiar contraseña", Resources.ICONS.iconoModificar(),listenerCambiar());
        
        addButton(btnAceptar);
        addButton(btnLimpiar);
        addButton(btnCambiarContraseña);

        setButtonAlign(Style.HorizontalAlignment.CENTER);

        //Monitors the valid state of a form and enabled / disabled all buttons.
        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnAceptar);

        //Instancio el formBinding
        formBindings = new FormBinding(this, true);
    }

    
    /**
     * Setea de forma manual la entidad y alimenta el formulario
     * @param entidad 
     */
    @Override
    protected void setEntidadManual(Usuarios entidad){
        this.entidad = entidad;
        entidadAFormulario(entidad); 
        //organizo componentes que no se usaran
        comboBoxRoles.setVisible(false);
        comboBoxRoles.setAllowBlank(true);
        txtCorreo.setReadOnly(true);
    }

    /**
     * Lleno los datos del formulario con las datos de la entidad manualmente.
     *
     * @param usuario
     */
    @Override
    protected void entidadAFormulario(Usuarios usuario) {
        
        txtCorreo.setValue(usuario.getCorreoElectronico());
        txtNombres.setValue(usuario.getNombres());
        txtApellidos.setValue(usuario.getApellidos());
        txtDocumento.setValue(usuario.getDocumento());
        comboBoxRoles.seleccionar(usuario.getRol().getId());

    }


    

    /**
     * Carga el objeto usuario a partir de los datos del formulario
     */
    @Override
    protected Usuarios formularioAEntidad() {
        // Si no hay objeto usuario
        if (entidad == null) {
            entidad = new Usuarios();
        }

         entidad.setCorreoElectronico(txtCorreo.getValue().toLowerCase());
        entidad.setNombres(txtNombres.getValue());
        entidad.setApellidos(txtApellidos.getValue());
        entidad.setDocumento(txtDocumento.getValue());
        if (BeansLocales.getUsuario().isAdministradorSistema()) {
        } else {
        }
        
   
        //Si los componentes comboboxroles y cbinactivo estan visibles los uso para setearlos en la entidad
        if(comboBoxRoles.isVisible()){
            if (comboBoxRoles.getValue() != null) {
                entidad.setRol(comboBoxRoles.getRolElegido());
            }           
        }

        return entidad;
    }


    
}
