/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Cuentas;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Consulta;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author fhurtado
 */
public interface DaoCuenta extends DaoGenerico<Cuentas>{

    public List<Cuentas> getCuentas(Long idUsuarioLogeado) throws DataAccessException;
    
    public Pair<Long, List<Cuentas>> listarPorConsulta(Consulta consulta,Long Usuariologeado, Integer offset, Integer limit) throws Exception;
    
    public void updateCuentaTran(Long idCuenta, Double saldoTran) throws DataAccessException;
    
}
