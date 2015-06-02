/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Rol;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author agomez
 */
public interface DaoRol extends DaoGenerico<Rol> {

    public List<Rol> getActivos() throws DataAccessException;
}
