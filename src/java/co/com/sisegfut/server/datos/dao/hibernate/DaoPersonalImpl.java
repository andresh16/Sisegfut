/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.server.datos.dao.DaoPersonal;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoPersonalImpl extends DaoGenericoImpl<Personal> implements DaoPersonal{
    
    @Transactional(readOnly = true)
    @Override
    public Personal obtenerPerxId(Long id) {
        try {
            Personal personal = new Personal();
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuarios.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.isNull("fechaInactivado"));
            personal = (Personal) criteria.list();
            return personal;
        } catch (HibernateException e) {

            e.printStackTrace();
            return null;

        }

    }
    
}
