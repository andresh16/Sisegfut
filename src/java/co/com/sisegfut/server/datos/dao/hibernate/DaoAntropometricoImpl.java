/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Antropometrico;
import co.com.sisegfut.server.datos.dao.DaoAntropometrico;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Malejandro
 */
@Repository
public class DaoAntropometricoImpl extends DaoGenericoImpl<Antropometrico> implements DaoAntropometrico {

    @Transactional(readOnly = true)
    @Override
    public List<Antropometrico> AntropometricoxDeportista(Long idDep) throws Exception {
        List<Antropometrico> listaAntep = null;
        String sql = "Select * from antropometrico where id_deportista=" + idDep + " order by fecha desc";
        try {
            listaAntep = (List<Antropometrico>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("antropometrico", Antropometrico.class).list();
            return listaAntep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
