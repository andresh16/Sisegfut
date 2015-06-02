/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Categoria;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author fhurtado
 */
public interface DaoCategoria extends DaoGenerico<Categoria>{
    
    public List<Categoria> getCategorias(String query,Long idUsuarioLogeado) throws DataAccessException;
    
    
    public List<Categoria> getCategoria2(Long idUsuarioLogeado) throws DataAccessException;
}
