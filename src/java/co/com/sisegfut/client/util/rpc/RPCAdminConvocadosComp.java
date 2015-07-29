/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.ConvocadosCompe;
import co.com.sisegfut.client.datos.dominio.Deportista;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminConvocadosComp extends RPCMaestro<ConvocadosCompe>{
    
    public void guardarConvocadosComp(List<ConvocadosCompe> convocados);
    
    public List<Deportista> getConvocadosXTipo(Long idCompetencia, String tipoConvado);
    
    public List<Deportista> getConvocadosXTipoGrids(Long idCompetencia, String tipoConvado);
    
    public List<Deportista> getConvocados(Long idCompetencia);
    
    public Boolean validarConsultarConvocados(Long idCompetencia);
    
    
}
