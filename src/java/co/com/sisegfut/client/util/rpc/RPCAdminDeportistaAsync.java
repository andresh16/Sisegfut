/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.aatest.model.Estratos;
import co.com.sisegfut.client.aatest.model.Posicion;
import co.com.sisegfut.client.aatest.model.TipoDeportista;
import co.com.sisegfut.client.datos.dominio.Deportista;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anfeh_000
 */
public interface RPCAdminDeportistaAsync extends RPCMaestroAsync<Deportista> {

    public void updateConFoto(Deportista dep, AsyncCallback<Deportista> callback);

    public void actulizarGrasa(Long idDep,String grasa, AsyncCallback<Void> callback);

    public void CalcularFCM(Date fechaNacimiento, AsyncCallback<String> callback);

    public void CalcularEdad(Date fechaNacimiento, AsyncCallback<Integer> callback);

    public void getDeportistaxCategoria(Long idCategoria, AsyncCallback<PagingLoadResult<Deportista>> callback);
    
    public void getFiltroDeportistas(String filtro, AsyncCallback<PagingLoadResult<Deportista>> callback);
    
    public void getDeportistas(AsyncCallback<PagingLoadResult<Deportista>> callback);
    
    public void getDeportistasEstratificacion(AsyncCallback<List<Estratos>> callback);
    
    public void getDeportistasPosicion(AsyncCallback<List<Posicion>> callback);
    
    public void getDeportistasTipoDeportista(AsyncCallback<List<TipoDeportista>> callback);

}
