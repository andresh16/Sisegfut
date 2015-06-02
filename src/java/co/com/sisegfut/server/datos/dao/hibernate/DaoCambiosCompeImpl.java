/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.CambiosCompe;
import co.com.sisegfut.server.datos.dao.DaoCambiosCompe;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import static org.hibernate.criterion.Projections.id;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoCambiosCompeImpl extends DaoGenericoImpl<CambiosCompe> implements DaoCambiosCompe {

    @Transactional(readOnly = true)
    @Override
    public List<CambiosCompe> getCambiosXCompetencia(Long idCompetencia) {
        List<CambiosCompe> listaRetorno = null;
        String sql = "Select c.* from cambios_competencia as c where id_competencia=" + idCompetencia;
        try {
            listaRetorno = (List<CambiosCompe>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("c", CambiosCompe.class).list();
            return listaRetorno;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
