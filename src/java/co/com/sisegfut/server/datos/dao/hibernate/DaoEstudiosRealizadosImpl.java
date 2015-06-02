/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.EstudiosRealizados;
import co.com.sisegfut.server.datos.dao.DaoEstudiosRealizados;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Camilo
 */
@Repository
public class DaoEstudiosRealizadosImpl extends DaoGenericoImpl<EstudiosRealizados> implements DaoEstudiosRealizados {
    @Transactional(readOnly = true)
    @Override
    public List<EstudiosRealizados> EstudiosRealizadosxPersonal(Long idPer) throws Exception {
        List<EstudiosRealizados> listaEstRea = null;

//        try {
//            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AntecedentesDeportivos.class);
//            criteria.add(Restrictions.eq("id_deportista", idDep));
//            listaAntep = (List<AntecedentesDeportivos>) criteria.list();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listaAntep;
             String sql = "Select * from estudios where id_personal="+idPer+ " order by anio_graduacion desc";
         try {
             listaEstRea = (List<EstudiosRealizados>)sessionFactory.getCurrentSession()
                     .createSQLQuery(sql)
                     .addEntity("estudios", EstudiosRealizados.class).list();
             return listaEstRea;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
    }

    
}
