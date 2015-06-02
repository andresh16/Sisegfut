/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.TipoCuenta;
import co.com.sisegfut.server.datos.dao.DaoTipoCuenta;
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
public class DaoTipoCuentaImpl extends DaoGenericoImpl<TipoCuenta> implements DaoTipoCuenta{
 @Transactional(readOnly=true)
    @Override
    public List<TipoCuenta> getTipoCuenta() throws DataAccessException {
  
        List<TipoCuenta> retorno = null;

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TipoCuenta.class);
        retorno = (List<TipoCuenta>) criteria.list();

        return retorno;
    
    
    }
    
}
