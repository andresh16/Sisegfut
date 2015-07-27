/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Asistencia;
import co.com.sisegfut.server.datos.dao.DaoAsistencia;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoAsistenciaImpl extends DaoGenericoImpl<Asistencia> implements DaoAsistencia {

    @Transactional(readOnly = true)
    @Override
    public List<Asistencia> getAsistenciaxId(Long idControlAsistencia) throws DataAccessException {
        List<Asistencia> listaRetorno = null;
        String sql = "select a.* from asistencia as a inner join control_asistencia as ca on a.id_control_asistencia=ca.id and ca.id=" + idControlAsistencia;
        try {
            listaRetorno = (List<Asistencia>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("a", Asistencia.class).list();
            return listaRetorno;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
