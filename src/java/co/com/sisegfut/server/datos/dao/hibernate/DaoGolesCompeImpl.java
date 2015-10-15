/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Goles;
import co.com.sisegfut.client.datos.dominio.dto.DTOGolesDepTorneo;
import co.com.sisegfut.server.datos.dao.DaoGolesCompe;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoGolesCompeImpl extends DaoGenericoImpl<Goles> implements DaoGolesCompe {

    @Transactional(readOnly = true)
    @Override
    public List<Goles> getGolesXCompetencia(Long idCompetencia) throws Exception {
        List<Goles> listaReporte = null;
        String sql = "Select g.* from goles as g where id_competencia=" + idCompetencia;
        try {
            listaReporte = (List<Goles>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("g", Goles.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validarMinutoGolCompetencia(Long idCompetencia, Integer minuto) throws Exception {
        boolean existeMinutoComp = false;
        String sql = "select * from goles where id_competencia="+idCompetencia+" and minuto_gol=" + minuto;
        try {
            Goles gol = null;
            gol = (Goles) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("goles", Goles.class).uniqueResult();
            
            if (gol != null) {
                existeMinutoComp = true;
            }
            return existeMinutoComp;
        } catch (HibernateException e) {
            e.printStackTrace();
            return true;
        }

    }
    @Transactional(readOnly = true)
    @Override
    public List<DTOGolesDepTorneo> golesDeportistXTorneo(Long idTorneo) throws Exception {
        List<Object[]> listaResultado=null;
        List<DTOGolesDepTorneo> ListDepGolesXTorneo = new ArrayList<DTOGolesDepTorneo>();
        String sql1 = "Select count(id_deportista)as goles ,d.nombres|| ' ' ||d.apellidos as nombre_completo from goles g "
                + " inner join deportista d on  g.id_deportista=d.id "
                + " where id_competencia in(Select c.id from competencia c where torneo =" + idTorneo + ") "
                + " and d.jugador_comodin=false "
                + "  group by id_deportista,d.nombres,d.apellidos "
                + "   order by count(id_deportista)";
        try {
              listaResultado = (List<Object[]>) sessionFactory.getCurrentSession()
                .createSQLQuery(sql1).list();
              for (Object[] resultado : listaResultado) {
            System.out.println("-----------");
            System.out.println("Goles "+resultado[0]);
            System.out.println("Jugador "+resultado[1]);
            System.out.println("--------------------");
            DTOGolesDepTorneo dTOGolesDepTorneo = new DTOGolesDepTorneo((String)resultado[0],(String)resultado[1]);
            ListDepGolesXTorneo.add(dTOGolesDepTorneo);
        }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return ListDepGolesXTorneo;
    }

}
