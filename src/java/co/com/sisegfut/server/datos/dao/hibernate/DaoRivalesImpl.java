/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Rivales;
import co.com.sisegfut.server.datos.dao.DaoRivales;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ManuelAlejandro
 */
@Repository
public class DaoRivalesImpl extends DaoGenericoImpl<Rivales> implements DaoRivales {

    @Transactional(readOnly = true)
    @Override
    public List<Rivales> rivalesXTorneo(Long idTorneo) {
        List<Rivales> listaRetorno = null;
        String sqlActivos = "Select r.* from rivales as r where fechainactivado isNull and torneo=" + idTorneo;
        try {
            listaRetorno = (List<Rivales>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sqlActivos)
                    .addEntity("r", Rivales.class).list();
            return listaRetorno;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
