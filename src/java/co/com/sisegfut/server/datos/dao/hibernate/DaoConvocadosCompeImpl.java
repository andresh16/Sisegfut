/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.ConvocadosCompe;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.server.datos.dao.DaoConvocadosCompe;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoConvocadosCompeImpl extends DaoGenericoImpl<ConvocadosCompe> implements DaoConvocadosCompe {

     @Transactional(readOnly = true)
    @Override
    public List<Deportista> getConvocadosXTipo(Long idCompetencia, String tipoConvado) throws DataAccessException {

        List<Deportista> listaDepConvocaTipo = null;

        String sql = "Select d.* from convocados_competencia as c inner "
                + "join deportista as d on d.id=c.id_deportista and "
                + "id_competencia="+idCompetencia+" and tipo_convocado='"+tipoConvado+"' "
                + "order by nombres asc; ";
        try {
            listaDepConvocaTipo = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("d", Deportista.class).list();
            return listaDepConvocaTipo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     @Transactional(readOnly = true)
    @Override
    public List<Deportista> getConvocados(Long idCompetencia) throws DataAccessException {

        List<Deportista> listaDepConvoca = null;

        String sql = "Select d.* from convocados_competencia as c inner "
                + "join deportista as d on d.id=c.id_deportista and "
                + "c.id_competencia="+idCompetencia+" order by d.nombres asc;";
        try {
            listaDepConvoca = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("d", Deportista.class).list();
            return listaDepConvoca;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

    

}
