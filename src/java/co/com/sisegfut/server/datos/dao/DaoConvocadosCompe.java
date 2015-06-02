/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.ConvocadosCompe;
import co.com.sisegfut.client.datos.dominio.Deportista;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author fhurtado
 */
public interface DaoConvocadosCompe extends DaoGenerico<ConvocadosCompe>{
    
     public List<Deportista> getConvocadosXTipo(Long idCompetencia, String tipoConvado) throws DataAccessException;
     
     public List<Deportista> getConvocados(Long idCompetencia) throws DataAccessException;
     
     
}
