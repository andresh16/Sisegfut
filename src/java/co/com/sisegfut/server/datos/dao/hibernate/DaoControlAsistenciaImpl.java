/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Asistencia;
import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOReporteAsistenciaXMes;
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
public class DaoControlAsistenciaImpl extends DaoGenericoImpl<ControlAsistencia> implements DaoControlAsistencia {

    @Transactional(readOnly = true)
    @Override
    public List<ControlAsistencia> obtenerPlanillaAsistenciaFiltro(Date fechaInicial, Date fechaFinal, Long idCategoria, String actividad) throws DataAccessException {

        List<ControlAsistencia> planillasAsistencias = new ArrayList<ControlAsistencia>();
        String condicionFiltro = "";

        if (fechaInicial != null && fechaFinal != null) {

//        fechaInicial.setTime(1435640400000);
//        fechaFinal.setTime(1435953514352);
            condicionFiltro += "fecha between '" + Formatos.ceroHoras(fechaInicial) + "' and '" + Formatos.Horas23(fechaFinal) + "'";
        }
        if (idCategoria != null) {
            if (condicionFiltro != "") {
                condicionFiltro += " and categoria=" + idCategoria;
            } else {
                condicionFiltro += "categoria=" + idCategoria;
            }
        }
        if (actividad != null) {
            condicionFiltro += " and actividad='" + actividad + "'";
        }

        String sql = "Select ca.* from control_asistencia as ca where " + condicionFiltro + " order by fecha desc";
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

    @Override
    public List<DTOReporteAsistenciaXMes> obtenerReporteAsistenciaxMes(Integer mes, Integer anio, Long idCategoria) throws DataAccessException {

        List<DTOReporteAsistenciaXMes> reporteAsistencia = new ArrayList<DTOReporteAsistenciaXMes>();
        String sql1 = "Select ca.* from control_asistencia as ca WHERE date_part('month', fecha) = " + mes + " and date_part('year', fecha) = " + anio + " and categoria =" + idCategoria;
        String sql2 = "Select d.* from deportista as d where categoria=" + idCategoria;
        

        List<ControlAsistencia> planillasAsistenciasAlMes = (List<ControlAsistencia>) sessionFactory.getCurrentSession()
                .createSQLQuery(sql1).addEntity("ca", ControlAsistencia.class).list();

        List<Deportista> deportistaxCategoria = (List<Deportista>) sessionFactory.getCurrentSession()
                .createSQLQuery(sql2).addEntity("d", Deportista.class).list();
        
        String planillas="";
        for (ControlAsistencia planilla : planillasAsistenciasAlMes) {
            planillas+=planilla.getId()+",";
        }
        planillas=planillas.substring(0,planillas.length()-1);
        
        for (Deportista deportistaxCategoria1 : deportistaxCategoria) {
                String sql3 = "select * from asistencia where id_deportista="+deportistaxCategoria1.getId()+" and estado='ASISTE' and  id_control_asistencia in("+planillas+")";
                List<Asistencia> asistencias = (List<Asistencia>) sessionFactory.getCurrentSession()
                        .createSQLQuery(sql3).addEntity("asistencia", Asistencia.class).list();
                
                DTOReporteAsistenciaXMes dtoraxm = new DTOReporteAsistenciaXMes();
                
                dtoraxm.setNombreDeportista(deportistaxCategoria1.getLabel());
                dtoraxm.setDiasAsistenciaTotal(asistencias.size()+"/"+planillasAsistenciasAlMes.size());
                float porcentajeAsistencia= (float)(asistencias.size()*100)/planillasAsistenciasAlMes.size();
                dtoraxm.setPorcentajeAsistenciaTotal(porcentajeAsistencia+"%");
               reporteAsistencia.add(dtoraxm);

        }

        return reporteAsistencia;
    }

}
