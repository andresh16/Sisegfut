/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.server.datos.dao.DaoAntecedentesDeportivos;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoAntecedentesDeportivosImpl extends DaoGenericoImpl<AntecedentesDeportivos> implements DaoAntecedentesDeportivos {

    @Transactional(readOnly = true)
    @Override
    public List<AntecedentesDeportivos> AnteDepxDeportista(Long idDep) throws Exception {
        List<AntecedentesDeportivos> listaAntep = null;

//        try {
//            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AntecedentesDeportivos.class);
//            criteria.add(Restrictions.eq("id_deportista", idDep));
//            listaAntep = (List<AntecedentesDeportivos>) criteria.list();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listaAntep;
             String sql = "Select * from antecedentes_deportivos where id_deportista="+idDep+ " order by anno desc";
         try {
             listaAntep = (List<AntecedentesDeportivos>)sessionFactory.getCurrentSession()
                     .createSQLQuery(sql)
                     .addEntity("antecedentes_deportivos", AntecedentesDeportivos.class).list();
             return listaAntep;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
    }

}
