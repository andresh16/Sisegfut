/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.TestKarvonen;
import co.com.sisegfut.server.datos.dao.DaoTestKarvonen;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoTestKarvonenImpl extends DaoGenericoImpl<TestKarvonen> implements DaoTestKarvonen {

    @Transactional(readOnly = true)
    @Override
    public List<TestKarvonen> TestKarvonenXDeportista(Long idDep) throws Exception {
        List<TestKarvonen> listaKarvonen = null;

        String sql = "select * from test_karvonen where id_deportista=" + idDep + " order by fecha desc";
        try {
            listaKarvonen = (List<TestKarvonen>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("test_karvonen", TestKarvonen.class).list();
            return listaKarvonen;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<TestKarvonen> TestKarvonenxCategoria(Long idCategoria) throws Exception {
        List<TestKarvonen> listaKarvonen = null;
        String sql = "select ta.* from test_karvonen as ta Inner Join Deportista as d On ta.id_deportista=d.id and d.categoria=" + idCategoria;
        try {
            listaKarvonen = (List<TestKarvonen>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("test_karvonen", TestKarvonen.class).list();
            return listaKarvonen;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<TestKarvonen> ultimoTesKartRealizadoXDeportista(Long idCategoria) throws Exception {
        List<TestKarvonen> listaKarvonen = null;
        String sql = "select "
                + "    test.*"
                + "from test_karvonen test"
                + "join (select "
                + "    id_deportista, max(fecha) as fecha "
                + "from test_karvonen "
                + "group by id_deportista) vi"
                + "on (test.id_deportista = vi.id_deportista and test.fecha = vi.fecha)"
                + "join deportista dep"
                + "on (dep.id = test.id_deportista and dep.categoria="+idCategoria+")";
        
        try {
            listaKarvonen = (List<TestKarvonen>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("test", TestKarvonen.class).list();
            return listaKarvonen;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
