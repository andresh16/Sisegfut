/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.CuerpoTecnicoCompe;
import co.com.sisegfut.client.datos.dominio.Personal;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface DaoCuerpoTecnicoCompe extends DaoGenerico<CuerpoTecnicoCompe>{
    
    public List<Personal> personalXCompetencia(Long idCompetencia)throws Exception;
    
    public List<CuerpoTecnicoCompe> eliminarCuerpoTecCompe(Long idCompetencia, Long IdPersonal);
}
