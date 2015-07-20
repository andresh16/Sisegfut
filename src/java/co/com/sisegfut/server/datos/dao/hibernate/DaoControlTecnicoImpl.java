/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.ControlTecnico;
import co.com.sisegfut.server.datos.dao.DaoControlTecnico;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Malejandro
 */
@Repository
public class DaoControlTecnicoImpl extends DaoGenericoImpl<ControlTecnico> implements DaoControlTecnico{
     @Transactional(readOnly = true)
    @Override
    public List<ControlTecnico> CtrlTecXDeportista(Long idDep) throws Exception {
         List<ControlTecnico> listaAntep = null;
         
         String sql = "select * from control_tecnico where id_deportista="+idDep + " order by fecha desc";
         try {
             listaAntep = (List<ControlTecnico>)sessionFactory.getCurrentSession()
                     .createSQLQuery(sql)
                     .addEntity("control_tecnico", ControlTecnico.class).list();
             return listaAntep;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
        
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<ControlTecnico> CtrlTecXCategoria(Long idCategoria) throws Exception {
         List<ControlTecnico> listaAntep = null;
         
         String sql = "select id_deportista,max(fecha) from control_tecnico where categoria ="+ idCategoria+ "group by id_deportista";
         try {
             listaAntep = (List<ControlTecnico>)sessionFactory.getCurrentSession()
                     .createSQLQuery(sql)
                     .addEntity("control_tecnico", ControlTecnico.class).list();
             return listaAntep;
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
        
    }
}
