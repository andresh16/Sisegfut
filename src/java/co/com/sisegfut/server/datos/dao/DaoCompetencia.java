/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.datos.dominio.Competencia;
import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Andres Hurtado
 */
public interface DaoCompetencia extends DaoGenerico<Competencia> {

    public Competencia obtenerCompetenciaxId(Long idCompetencia) throws DataAccessException;

    public List<Competencia> obtenerCompetenciaFiltro(Date fechaCompetencia, Long idTorneo, Long idRival) throws DataAccessException;

}
