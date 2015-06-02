/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.ConvocadosCompe;
import co.com.sisegfut.client.datos.dominio.Deportista;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminConvocadosCompAsync extends RPCMaestroAsync<ConvocadosCompe>{
    
    public void guardarConvocadosComp(List<ConvocadosCompe> convocados, AsyncCallback<Void> callback);
    
    public void getConvocadosXTipo(Long idCompetencia, String tipoConvado,AsyncCallback<List<Deportista>> callback);
    
    public void getConvocados(Long idCompetencia, AsyncCallback<List<Deportista>> callback);
    
    
    
}
