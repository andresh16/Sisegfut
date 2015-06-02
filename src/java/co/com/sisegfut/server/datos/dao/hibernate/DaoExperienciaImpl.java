

package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Experiencia;
import co.com.sisegfut.server.datos.dao.DaoExperiencia;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Camilo
 */
@Repository
public class DaoExperienciaImpl extends DaoGenericoImpl<Experiencia> implements DaoExperiencia{
    @Transactional(readOnly = true)
    @Override
    public List<Experiencia> ExperienciaxPersonal(Long idPer) throws Exception {
        List<Experiencia> listaExp = null;

//        try {
//            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AntecedentesDeportivos.class);
//            criteria.add(Restrictions.eq("id_deportista", idDep));
//            listaAntep = (List<AntecedentesDeportivos>) criteria.list();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listaAntep;
             String sql = "Select * from experiencia where id_personal="+idPer+ " order by tiempo_laborado desc";
         try {
             listaExp = (List<Experiencia>)sessionFactory.getCurrentSession()
                     .createSQLQuery(sql)
                     .addEntity("experiencia", Experiencia.class).list();
             return listaExp;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
    }
    
}
