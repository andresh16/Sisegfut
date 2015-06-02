package co.com.sisegfut.server.datos.dao;

import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Consulta;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Componente de acceso a datos reutilizable para no reescribir las
 * operaciones basicas sobre los objetos del dominio.
 * @param <T>
 */
public interface DaoGenerico<T extends Object> {

    /**
     * Permite cargar una entidad de manera generica
     * @param id el id de la entidad a cargar
     * @return La entidad cargada
     * @throws DataAccessException
     */
    public T getById(int id) throws DataAccessException;
    /**
     * Permite cargar una entidad de manera generica
     * @param id el id de la entidad a cargar
     * @return La entidad cargada
     * @throws DataAccessException
     */
    public T getById( Long id ) throws DataAccessException;

    /**
     * Permite guardar una entidad de manera generica
     * @param entidad la entidad a guardar
     * @throws DataAccessException
     */
    public void guardar(T entidad) throws DataAccessException;

    /**
     * Permite borrar una entidad de manera generica
     * @param entidad La entidad a borrar
     * @throws DataAccessException
     */
    public void borrar(T entidad) throws DataAccessException;
    
    public void eliminar(T entidad) throws DataAccessException;
    
    /**
     * Permite reactivar una entidad que ha sido previamente eliminada de la BD de forma logica.
     * @param entity
     * @throws Exception 
     */
    public void reactivar(T entity) throws Exception;

    /**
     * Refresca el objeto desde la base de datos aplicando bloqueo para update
     * @param entity
     */
    public void refrescarBloquear(T entity);

    /**
     * Lista todas las entidades en el repositorio del tipo definido,
     * si es de tipo perpetualentity solo muestra los activos
     * @return El listado de instancias
     * @throws DataAccessException
     */
    public List<T> listar() throws DataAccessException;
    
    /**
     * Lista las entidades marcadas como inactivadas.
     * @return
     * @throws DataAccessException 
     */
    public List<T> listarInactivos() throws DataAccessException;
    
    /**
     * Para ejecutar consultas de forma dinamica.
     * @param consulta
     * @param offset
     * @param limit
     * @return 
     */
    public Pair<Long, List<T>> listarPorConsulta(Consulta consulta, Integer offset, Integer limit) throws Exception;
    
    /**
     * Usado para ejecutar updates o deletes en la Base de Datos.
     * @param consulta
     * @return 
     */
    public int ejecutarUpdateDelete(String consulta);
}