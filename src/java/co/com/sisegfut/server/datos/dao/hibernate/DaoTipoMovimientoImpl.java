/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;
import co.com.sisegfut.client.datos.dominio.Movimientos;
import co.com.sisegfut.client.datos.dominio.TipoCuenta;
import co.com.sisegfut.client.datos.dominio.TipoMovimiento;
import co.com.sisegfut.server.datos.dao.DaoTipoMovimiento;
import java.util.List;
import org.hibernate.Criteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoTipoMovimientoImpl extends DaoGenericoImpl<TipoMovimiento> implements DaoTipoMovimiento{
    
    @Transactional(readOnly=true)
    @Override
    public List<TipoMovimiento> getTipoMovimiento() throws DataAccessException {

        List<TipoMovimiento> retorno = null;

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TipoMovimiento.class);
        retorno = (List<TipoMovimiento>) criteria.list();

        return retorno; 
    }
    
}
