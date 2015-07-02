/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Personal;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Andres Hurtado
 */
public interface DaoPersonal extends DaoGenerico<Personal>{
    
    public Personal obtenerPerxId(Long id)throws DataAccessException ;
    
}
