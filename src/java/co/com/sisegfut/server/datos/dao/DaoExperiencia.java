/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Experiencia;
import java.util.List;

/**
 *
 * @author Camilo
 */
public interface DaoExperiencia extends DaoGenerico<Experiencia>{
    public List<Experiencia> ExperienciaxPersonal(Long idPer)throws Exception;
}
