/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.ControlTecnico;
import co.com.sisegfut.client.datos.dominio.TestKarvonen;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public interface DaoTestKarvonen extends DaoGenerico<TestKarvonen>{
    
    public List<TestKarvonen> TestKarvonenXDeportista(Long idDep)throws Exception;
    
}
