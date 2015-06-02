/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface DaoControlDisciplinarioComp extends DaoGenerico<ControlDisciplinarioCompe>{
    
    public List<ControlDisciplinarioCompe> getTarjetasXCompetencia(Long idCompetencia)throws Exception;
    
    
}
