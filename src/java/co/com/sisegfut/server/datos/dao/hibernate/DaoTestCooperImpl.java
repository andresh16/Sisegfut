/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.TestCooper;
import co.com.sisegfut.server.datos.dao.DaoTestCooper;
import com.google.gwt.maps.jsio.client.ReadOnly;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoTestCooperImpl extends DaoGenericoImpl<TestCooper> implements DaoTestCooper {

    @Transactional(readOnly = true)
    @Override
    public List<TestCooper> TestCooperXDeportista(Long idDep) throws Exception {
        List<TestCooper> listaAntep = null;

        String sql = "select * from test_cooper where id_deportista=" + idDep + " order by fecha desc";
        try {
            listaAntep = (List<TestCooper>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("test_cooper", TestCooper.class).list();
            return listaAntep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//"select id_deportista,max(fecha) from test_cooper where categoria ="+ idCategoria+"group by id_deportista";
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<TestCooper> TestCooperXCategoria(Long idCategoria) throws Exception {
        List<TestCooper> listaTestCooper = null;

        String sql = "select id_deportista,max(fecha) from test_cooper where categoria ="+ idCategoria+ "group by id_deportista";
        try {
            listaTestCooper = (List<TestCooper>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("test_cooper", TestCooper.class).list();
            return listaTestCooper;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//"select id_deportista,max(fecha) from test_cooper where categoria ="+ idCategoria+"group by id_deportista";
    }

    @Transactional(readOnly = true)
    @Override
    public List<TestCooper> TCXDeportista(Long idDep) throws Exception {
        List<TestCooper> listaAntep = null;

        String sql = "select d.documento,d.nombres, d.apellidos,tc.fecha, tc.condicion_fisica, tc.distancia,tc.consumo_oxigeno,tc.vo2max,tc.velocidad \n" +
                     "from test_cooper as tc Inner Join Deportista as d On tc.id_deportista=d.id where id_deportista=" + idDep + " order by fecha desc";
        try {
            listaAntep = (List<TestCooper>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("test_cooper", TestCooper.class).list();
            return listaAntep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }}
}
