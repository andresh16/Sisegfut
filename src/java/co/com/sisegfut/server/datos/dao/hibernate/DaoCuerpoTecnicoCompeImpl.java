/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.CuerpoTecnicoCompe;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.server.datos.dao.DaoCuerpoTecnicoCompe;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoCuerpoTecnicoCompeImpl extends DaoGenericoImpl<CuerpoTecnicoCompe> implements DaoCuerpoTecnicoCompe {

    @Transactional(readOnly = true)
    @Override
    public List<Personal> personalXCompetencia(Long idCompetencia) throws Exception {
        List<Personal> listaReporte = null;
        String sql = "select p.* from cuerpo_tecnico_competencia as c Inner join personal as p on c.id_personal=p.id and c.id_competencia=" + idCompetencia + " order by p.nombres asc";
        try {
            listaReporte = (List<Personal>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("c", Personal.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CuerpoTecnicoCompe> eliminarCuerpoTecCompe(Long idCompetencia, Long IdPersonal) {
        List<CuerpoTecnicoCompe> listaReporte = null;
        String sql = "select * from cuerpo_tecnico_competencia where id_competencia=" + idCompetencia + " and id_personal=" + IdPersonal;
        try {
            listaReporte = (List<CuerpoTecnicoCompe>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("cuerpo_tecnico_competencia", CuerpoTecnicoCompe.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
