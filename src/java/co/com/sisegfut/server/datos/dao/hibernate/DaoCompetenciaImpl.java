/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.server.datos.dao.DaoCompetencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoCompetenciaImpl extends DaoGenericoImpl<Competencia> implements DaoCompetencia {

    @Transactional(readOnly = true)
    @Override
    public Competencia obtenerCompetenciaxId(Long idCompetencia) {
        try {
            Competencia competencia = new Competencia();
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Competencia.class);
            criteria.add(Restrictions.eq("id", idCompetencia));
//            criteria.add(Restrictions.eq("finaliza_compentcia",false));
            competencia = (Competencia) criteria.uniqueResult();
            return competencia;
        } catch (HibernateException e) {

            e.printStackTrace();
            return null;

        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<Competencia> obtenerCompetenciaFiltro(Date fechaCompetencia, Long idTorneo, Long idRival) throws DataAccessException {
        List<Competencia> competencias = new ArrayList<Competencia>();
        String condicionFiltro = "";

        if (fechaCompetencia != null) {
            condicionFiltro += "fecha='" + fechaCompetencia + "'";
        }
        if (idTorneo != null) {
            if (condicionFiltro != "") {
                condicionFiltro += " and torneo=" + idTorneo;
            } else {
                condicionFiltro += "torneo=" + idTorneo;
            }
        }
        if (idRival != null) {
            condicionFiltro += " and rival=" + idRival;
        }

        String sql = "select c.* from competencia as c where " + condicionFiltro+" order by fecha desc";
        try {
            competencias = (List<Competencia>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("c", Competencia.class).list();
            return competencias;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
