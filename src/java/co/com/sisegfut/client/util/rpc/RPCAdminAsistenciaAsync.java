/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Asistencia;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminAsistenciaAsync extends RPCMaestroAsync<Asistencia> {

    public void guardarGridAsistencia(Long IdControlAsistencia, List<String[]> asistencias, AsyncCallback<Void> callback);

}
