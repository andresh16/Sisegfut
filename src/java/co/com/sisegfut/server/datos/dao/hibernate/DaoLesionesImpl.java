/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Lesiones;
import co.com.sisegfut.server.datos.dao.DaoLesiones;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoLesionesImpl extends DaoGenericoImpl<Lesiones> implements DaoLesiones {

    @Transactional(readOnly = true)
    @Override
    public List<Lesiones> AnteOsteoMuscularxDeportista(Long idDep) throws Exception {
        List<Lesiones> listaAntep = null;
        String sql = "Select * from lesiones where id_deportista=" + idDep + " order by fecha_lesion desc";
        try {
            listaAntep = (List<Lesiones>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("lesiones", Lesiones.class).list();
            return listaAntep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
