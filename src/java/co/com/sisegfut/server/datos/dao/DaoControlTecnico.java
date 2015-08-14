/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.ControlTecnico;
import java.util.List;

/**
 *
 * @author Malejandro
 */
public interface DaoControlTecnico extends DaoGenerico<ControlTecnico>{
    public List<ControlTecnico> CtrlTecXDeportista(Long idDep)throws Exception;
    
    public List<ControlTecnico> ControlTecnicoxCategoria(Long idCategoria)throws Exception;
    
    public List<ControlTecnico> ultimoCtrlTecnicotRealizadoXDeportista(Long idCategoria)throws Exception;
}
