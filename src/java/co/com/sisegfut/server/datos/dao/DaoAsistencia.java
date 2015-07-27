/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Asistencia;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author fhurtado
 */
public interface DaoAsistencia extends DaoGenerico<Asistencia>{
   public List<Asistencia> getAsistenciaxId(Long idControlAsistencia) throws DataAccessException;
   
}
