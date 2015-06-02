/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.TipoCuenta;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author fhurtado
 */
public interface DaoTipoCuenta extends DaoGenerico<TipoCuenta>{
     public List<TipoCuenta> getTipoCuenta() throws DataAccessException;
}
