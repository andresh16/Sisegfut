/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.TestCooper;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public interface DaoTestCooper extends DaoGenerico<TestCooper> {

    public List<TestCooper> TestCooperXDeportista(Long idDep) throws Exception;
    
    public List<TestCooper> TestCooperXCategoria(Long idCategoria) throws Exception;
    
    public List<TestCooper> ultimoTesCoopertRealizadoXDeportista(Long idCategoria) throws Exception;

}
