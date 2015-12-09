
package co.com.sisegfut.client.administracion.usuarios;

import co.com.sisegfut.client.adminCRUDgral.PaginGridEntidades;
import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.ListenerBuscarEntidad;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.ListFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anfeh_000
 */
public class PaginGridUsuarios extends PaginGridEntidades<Usuarios>{
   

    /**
     * Constructor
     * @param listenerBuscar
     * @param rPCMaestroAsync
     * @param titulo 
     */
    public PaginGridUsuarios(ListenerBuscarEntidad<Usuarios> listenerBuscar, 
            RPCMaestroAsync<Usuarios> rPCMaestroAsync, String titulo) {
        super(listenerBuscar, rPCMaestroAsync, titulo);
    }

        
    
    @Override
    protected ColumnModel crearColumnModel() {
        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(new ColumnConfig("nombreCompleto", "Nombre", 300));
        columns.add(new ColumnConfig("documento", "Documento", 150));
        columns.add(new ColumnConfig("correoElectronico", "Correo", 150));
        columns.add(new ColumnConfig("rol.nombre", "Rol", 150));

        ColumnModel cm = new ColumnModel(columns);  
        
        return cm;
    }

    @Override
    protected GridFilters crearFilters() {
        //Configuro los filtros de la tabla
        GridFilters filters = new GridFilters();
        StringFilter nombreFilter = new StringFilter("nombreCompleto");
        StringFilter documentoFilter = new StringFilter("documento");
        
        ListFilter rolFilter = new ListFilter("rol.nombre", getRolesUsuario());
        rolFilter.setDisplayProperty("label");
        
        filters.addFilter(nombreFilter);
        filters.addFilter(documentoFilter);
        filters.addFilter(rolFilter);
        
        return filters;
    }
    
    private ListStore<ModelData> getRolesUsuario() {

        ArrayList<ModelData> listaEstados = new ArrayList<ModelData>();
        listaEstados.add(estado("Administrador sistema", Rol.ROL_ADMINISTRADOR_CLUB));
        listaEstados.add(estado("Entrenador", Rol.ROL_ADMINISTRADOR_ENTRENADOR));
        
        ListStore<ModelData> estadoStore = new ListStore<ModelData>();
        estadoStore.add(listaEstados);

        return estadoStore;
    }
    
    private static ModelData estado(String label, Long estado) {
        ModelData model = new BaseModelData();
        model.set("label", label);
        model.set("estado", estado);
        return model;
    }

}
