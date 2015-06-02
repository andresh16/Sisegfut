/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.CambiosCompe;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author fhurtado
 */
public interface DaoCambiosCompe extends DaoGenerico<CambiosCompe>{
    
    public List<CambiosCompe> getCambiosXCompetencia(Long idCompetencia);
    
    
}
