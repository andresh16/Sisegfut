/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Antropometrico;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntropometrico;
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

    @Transactional(readOnly = true)
    @Override
    public List<Antropometrico> AntropometricoxCategoria(Long idCategoria) throws Exception {
        List<Antropometrico> listaAntep = null;
        String sql = "select ta.* from antropometrico as ta Inner Join Deportista as d On ta.id_deportista=d.id and d.categoria=" + idCategoria;

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

    @Transactional(readOnly = true)
    @Override
    public List<Antropometrico> ultimaMedAntropoRealizadaXDeportista(Long idCategoria) throws Exception {

        List<Antropometrico> listaAntep = null;
        String sql = "select test.*"
                + " from antropometrico test"
                + " join (select "
                + " id_deportista, max(fecha) as fecha "
                + " from antropometrico "
                + " group by id_deportista) vi"
                + " on (test.id_deportista = vi.id_deportista and test.fecha = vi.fecha)"
                + " join deportista dep"
                + " on (dep.id = test.id_deportista and dep.categoria=" + idCategoria + ")";

        try {
            listaAntep = (List<Antropometrico>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("test", Antropometrico.class).list();
            return listaAntep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
