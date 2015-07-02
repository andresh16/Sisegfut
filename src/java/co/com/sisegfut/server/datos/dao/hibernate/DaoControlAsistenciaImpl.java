/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.server.datos.dao.DaoControlAsistencia;
import co.com.sisegfut.server.util.Formatos;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoControlAsistenciaImpl  extends DaoGenericoImpl<ControlAsistencia> implements DaoControlAsistencia{
    
    @Transactional(readOnly = true)
    @Override
    public List<ControlAsistencia> obtenerPlanillaAsistenciaFiltro(Date fechaInicial, Date fechaFinal, Long idCategoria, String actividad) throws DataAccessException {

        List<ControlAsistencia> planillasAsistencias = new ArrayList<ControlAsistencia>();
        String condicionFiltro = "";
        
        

        if (fechaInicial != null && fechaFinal!=null) {
        
//        fechaInicial.setTime(0000);
//        fechaFinal.setTime(240000);
            
            condicionFiltro += "fecha between '" + Formatos.ceroHoras(fechaInicial)+"' and '"+ Formatos.Horas23(fechaFinal)+"'";
        }
        if (idCategoria != null) {
            if (condicionFiltro != "") {
                condicionFiltro += " and categoria=" + idCategoria;
            } else {
                condicionFiltro += "categoria=" + idCategoria;
            }
        }
        if (actividad != null) {
            condicionFiltro += " and actividad='" + actividad+"'";
        }

        String sql = "Select ca.* from control_asistencia as ca where " + condicionFiltro+" order by fecha desc";
        try {
            planillasAsistencias = (List<ControlAsistencia>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("ca", ControlAsistencia.class).list();
            return planillasAsistencias;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    
    }
    
}
