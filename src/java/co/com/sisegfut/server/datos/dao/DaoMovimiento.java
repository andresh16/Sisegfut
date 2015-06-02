/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Movimientos;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Consulta;
import co.com.sisegfut.client.datos.dominio.dto.DTOMovimientoXCuenta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface DaoMovimiento extends DaoGenerico<Movimientos>{
    public Pair<Long, List<Movimientos>> listarPorConsulta(Consulta consulta,Long Usuariologeado, Integer offset, Integer limit) throws Exception;
  /**
   * Busca los movimiento por cuenta elegida y un intervalo de fechas
   * @param idCuenta
   * @param fechaInicial
   * @param fechaFinal
   * @param usuarioLogeado
   * @return
   * @throws Exception 
   */
  public List<Movimientos> BuscarMovimientosXCuenta(Long idCuenta,String fechaInicial,String fechaFinal,Long usuarioLogeado)throws Exception;
   
  
   public List<Movimientos> BuscarMovimientosXCategoria(Long idCategoria,String fechaInicial,String fechaFinal,Long usuarioLogeado)throws Exception;
}
