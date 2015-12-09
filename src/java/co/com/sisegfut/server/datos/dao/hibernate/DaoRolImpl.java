/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.server.datos.dao.DaoRol;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author anfeh_000
 */
@Repository
public class DaoRolImpl extends DaoGenericoImpl<Rol> implements DaoRol {

    /** {@inheritdoc } **/
    @Transactional(readOnly=true)
    @Override
    public List<Rol> getActivos() throws DataAccessException {
        List<Rol> retorno = null;

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rol.class);
        criteria.add(Restrictions.isNull("fechaInactivado"));
        retorno = (List<Rol>) criteria.list();

        return retorno;
    }
}