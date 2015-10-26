/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.dto.DTODeportistaPosicion;
import co.com.sisegfut.client.datos.dominio.dto.DTOEstratosCantidad;
import co.com.sisegfut.client.datos.dominio.dto.DTOPosicionesCantidad;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Consulta;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Andres Hurtado
 */
public interface DaoDeportista extends DaoGenerico<Deportista>{
    
      public Pair<Long, List<Deportista>> listarPorConsulta(Consulta consulta,Long Usuariologeado, Integer offset, Integer limit) throws Exception;
      
      public Deportista obtenerDepxId(Long id)throws DataAccessException ;
      
      public List<Deportista> deportistaXCategoria(Long idCategoria)throws Exception;
      
      public List<Deportista> getDeportistas()throws Exception;
      
      public List<DTOEstratosCantidad> getCantidadPorEstrato()throws Exception;
      
      public List<Deportista> deportistaPosicionXCategoria(Long idCategoria)throws Exception;
      
      public List<DTOPosicionesCantidad> getCantidadPorPosicion()throws Exception;
      
      public List<Deportista> deportistaEstratoXCategoria(Long idCategoria) throws Exception;      
      
}
