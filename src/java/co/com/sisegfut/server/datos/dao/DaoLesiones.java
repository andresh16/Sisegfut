/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Lesiones;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public interface DaoLesiones extends DaoGenerico<Lesiones>{
    public List<Lesiones> AnteOsteoMuscularxDeportista(Long idDep)throws Exception;
}
