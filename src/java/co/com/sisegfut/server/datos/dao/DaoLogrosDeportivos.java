/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.LogrosDeportivos;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public interface DaoLogrosDeportivos extends DaoGenerico<LogrosDeportivos> {
    
    public List<LogrosDeportivos> LogroDepxDeportista(Long idDep)throws Exception;
    
}
