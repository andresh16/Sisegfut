/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.EstudiosRealizados;
import java.util.List;

/**
 *
 * @author Camilo
 */
public interface DaoEstudiosRealizados extends DaoGenerico<EstudiosRealizados>{
    public List<EstudiosRealizados> EstudiosRealizadosxPersonal(Long idPer)throws Exception;
}
