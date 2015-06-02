/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Rivales;
import java.util.List;

public interface DaoRivales extends DaoGenerico<Rivales>{
    
    public List<Rivales> rivalesXTorneo(Long idTorneo);
    
}
