/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Goles;
import co.com.sisegfut.server.datos.dao.DaoGolesCompe;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoGolesCompeImpl extends DaoGenericoImpl<Goles> implements DaoGolesCompe {

    @Transactional(readOnly = true)
    @Override
    public List<Goles> getGolesXCompetencia(Long idCompetencia) throws Exception {
        List<Goles> listaReporte = null;
        String sql = "Select g.* from goles as g where id_competencia=" + idCompetencia;
        try {
            listaReporte = (List<Goles>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("g", Goles.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validarMinutoGolCompetencia(Long idCompetencia, Integer minuto) throws Exception {
        boolean existeMinutoComp = false;
        String sql = "select * from goles where id_competencia="+idCompetencia+" and minuto_gol=" + minuto;
        try {
            Goles gol = null;
            gol = (Goles) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("goles", Goles.class).uniqueResult();
            
            if (gol != null) {
                existeMinutoComp = true;
            }
            return existeMinutoComp;
        } catch (HibernateException e) {
            e.printStackTrace();
            return true;
        }

    }

}
