/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Goles;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface DaoGolesCompe extends DaoGenerico<Goles>{
    
    public List<Goles> getGolesXCompetencia(Long idCompetencia)throws Exception;
    
    public boolean validarMinutoGolCompetencia(Long idCompetencia, Integer minuto)throws Exception;
}
