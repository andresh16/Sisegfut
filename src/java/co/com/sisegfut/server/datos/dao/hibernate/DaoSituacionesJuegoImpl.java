/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.SituacionesJuegoCompe;
import co.com.sisegfut.server.datos.dao.DaoSituacionesJuego;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoSituacionesJuegoImpl extends DaoGenericoImpl<SituacionesJuegoCompe> implements DaoSituacionesJuego {

    @Transactional(readOnly = true)
    @Override
    public List<SituacionesJuegoCompe> getSituacionesJxCompetencia(Long idCompetencia) throws Exception {

        List<SituacionesJuegoCompe> listaSituacionesJuego = null;

        String sql = "SELECT * FROM situaciones_juego as s where id_competencia=" + idCompetencia;
        try {
            listaSituacionesJuego = (List<SituacionesJuegoCompe>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("s", SituacionesJuegoCompe.class).list();
            return listaSituacionesJuego;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
