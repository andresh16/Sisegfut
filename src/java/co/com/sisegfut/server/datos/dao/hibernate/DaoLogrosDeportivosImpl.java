/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.LogrosDeportivos;
import co.com.sisegfut.server.datos.dao.DaoLogrosDeportivos;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoLogrosDeportivosImpl extends DaoGenericoImpl<LogrosDeportivos> implements DaoLogrosDeportivos {

    @Transactional(readOnly = true)
    @Override
    public List<LogrosDeportivos> LogroDepxDeportista(Long idDep) throws Exception {
        List<LogrosDeportivos> listaLogroDep = null;
        String sql = "Select * from logros_deportivos where id_deportista=" + idDep+ " order by anio_logro desc";
        try {
            listaLogroDep = (List<LogrosDeportivos>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("logros_deportivos", LogrosDeportivos.class).list();
            return listaLogroDep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
