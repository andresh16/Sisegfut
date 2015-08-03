/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.aatest.model.Data2;
import co.com.sisegfut.client.datos.dominio.SituacionesJuegoCompe;
import java.util.List;

/**
 *
 * @author anfeh_000
 */
public interface RPCAdminSituacionesJuego extends RPCMaestro<SituacionesJuegoCompe>{
    
    public List<SituacionesJuegoCompe> getSituacionesXCompetencia(Long idCompetencia);
    
    public List<Data2> getSituacionesXCompetenciaGrafica(Long idCompetencia);
    
}
