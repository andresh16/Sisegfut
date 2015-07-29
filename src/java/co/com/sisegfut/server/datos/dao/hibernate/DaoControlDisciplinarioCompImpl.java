/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import co.com.sisegfut.server.datos.dao.DaoControlDisciplinarioComp;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoControlDisciplinarioCompImpl extends DaoGenericoImpl<ControlDisciplinarioCompe> implements DaoControlDisciplinarioComp {

    @Transactional(readOnly = true)
    @Override
    public List<ControlDisciplinarioCompe> getTarjetasXCompetencia(Long idCompetencia) throws Exception {
        List<ControlDisciplinarioCompe> listaReporte = null;
        String sql = "Select c.* from control_disciplinario as c where id_competencia=" + idCompetencia;
        try {
            listaReporte = (List<ControlDisciplinarioCompe>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("c", ControlDisciplinarioCompe.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
   @Transactional(readOnly = true)
    @Override
    public boolean validarMinutoTarCompetencia(Long idCompetencia, Integer minuto) throws Exception {

    boolean existeMinutoComp = false;
        String sql = "select * from control_disciplinario where id_competencia="+idCompetencia+" and minuto_tarjeta=" + minuto;
        try {
            ControlDisciplinarioCompe controlDisciplinarioCompe = null;
            controlDisciplinarioCompe = (ControlDisciplinarioCompe) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("control_disciplinario", ControlDisciplinarioCompe.class).uniqueResult();
            
            if (controlDisciplinarioCompe != null) {
                existeMinutoComp = true;
            }
            return existeMinutoComp;
        } catch (HibernateException e) {
            e.printStackTrace();
            return true;
        }
    }

}
