/**
* RPCMaestroImpl
* Versión 1.0
* 15/09/2013
*
* Copyright(c) 2007-2012, Boos IT.
* admin@boos.com.co
*
* http://boos.com.co/license
**/

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.EntidadPerpetua;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Comparacion;
import co.com.sisegfut.client.util.consulta.Consulta;
import co.com.sisegfut.client.util.consulta.Orden;
import co.com.sisegfut.client.util.rpc.RPCMaestro;
import co.com.sisegfut.server.datos.dao.DaoGenerico;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;


public abstract class RPCMaestroImpl<T extends Serializable> extends RemoteServiceServlet implements RPCMaestro<T>{

    private DaoGenerico<T> daoGenerico;
    
    private Usuarios usuarioSession;

    /**
     * Metodo a usar a la hora de inyectar el dao de la entidad a trabajar.
     * @param daoGenerico 
     */
    public void setDaoGenerico(DaoGenerico<T> daoGenerico) {
        this.daoGenerico = daoGenerico;
    }

    /**
     * Metodo a usar a la hora de inyectar el usuario en sesion
     * @param usuarioSession 
     */
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
    }
    
    
    
    /**
     * Procesa la consulta armando la estructura de esta segun los parametros recibidos y retornando la lista.
     * @param config
     * @return 
     */
    @Override
    public Pair<Long, List<T>> procesarConsulta(FilterPagingLoadConfig config) {
        Consulta consulta = new Consulta();

        //Determino los paginadores.
        int start = config.getOffset();
        int limit = 100;
        if (config.getLimit() > 0) {
            //limit = Math.min(start + config.getLimit(), limit);
            limit = config.getLimit();
        }

        //Determino los filtros a usar enviados en el config.
        // Tienen prelación los filtros

        List<FilterConfig> filters = config.getFilterConfigs();
        if (filters != null) {
            for (FilterConfig f : filters) {

                Object value = f.getValue();
                String field = f.getField();
                String comparison = f.getComparison();

                if (value != null) {
                    
                    //necesito que cree los lefjoins de manera que pueda consultar datos de la tabla relacionada.
                    if(field.contains(".") && !field.contains(".id")){
                        consulta.getAlias().add(new Pair<String, String>(
                                field.substring(0, field.indexOf(".")),
                                field.substring(0, field.indexOf("."))));
                    }

                    if (comparison == null) {
                        //consulta.getRestricciones().put(field, new Pair(Comparacion.EQ, value));
                        if (f.getType().equals("string")) {
                            consulta.getRestricciones().put(field, new Pair(Comparacion.LIKE, "%"+value+"%"));
                        } else {
                            consulta.getRestricciones().put(field, new Pair(Comparacion.EQ, value));
                        }
                    } else if (comparison.equalsIgnoreCase("lt") || comparison.equalsIgnoreCase("before")) {
                        consulta.getRestricciones().put(field, new Pair(Comparacion.LT, value));
                    } else if (comparison.equalsIgnoreCase("gt") || comparison.equalsIgnoreCase("after")) {
                        consulta.getRestricciones().put(field, new Pair(Comparacion.GT, value));
                    } else if (comparison.equalsIgnoreCase("eq")) {
                        if (f.getType().equals("string")) {
                            consulta.getRestricciones().put(field, new Pair(Comparacion.LIKE, "%"+value+"%"));
                        } else {
                            consulta.getRestricciones().put(field, new Pair(Comparacion.EQ, value));
                        }
                    } else if (comparison.equalsIgnoreCase("on")) {
                        consulta.getRestricciones().put(field, new Pair(Comparacion.EQ, value));
                    } else {
                        throw new UnsupportedOperationException("El tipo de comparación no está soportado");
                    }
                }
            }
        }
        if (config.getSortField() != null) {
            if (config.getSortDir() == Style.SortDir.ASC) {
                consulta.getOrden().add(new Pair(config.getSortField(), Orden.ASC));
            } else if (config.getSortDir() == Style.SortDir.DESC) {
                consulta.getOrden().add(new Pair(config.getSortField(), Orden.DESC));
            }
        }

        try{
            //Cargo la lsita de usuarios de la BD
            Pair<Long, List<T>> result = daoGenerico.listarPorConsulta(consulta, start, limit);

            //this.antesRetorno(result.getB());
            
            return result;
        }catch(Exception e){
            System.err.println("ERROR: error en la consulta. "+e.getMessage());
            Pair<Long, List<T>> result = new Pair<Long, List<T>>(0l, new ArrayList<T>());
            return result;
        }

        
    }

    /**
     * Retorna la lista paginada segun los filtros enviados 
     * @param config
     * @return 
     */
    @Override
    public PagingLoadResult<T> getConsulta(FilterPagingLoadConfig config) {

        Pair<Long, List<T>> result = procesarConsulta(config);
        PagingLoadResult<T> loadResult = new BasePagingLoadResult<T>(result.getB(), config.getOffset(), result.getA().intValue());

        return loadResult;
    }
    
    /**
     * Guarda la entidad entregada, si no existía lo crea
     *
     * @param usuario
     * @return Alguno de los siguientes valores:
     */
    @Override
    public RespuestaRPC<T> guardarEntidad(T entidad) {
        RespuestaRPC<T> retorno = new RespuestaRPC<T>();

        // Validamos session
        if (usuarioSession == null || usuarioSession.getId() == null || usuarioSession.getId().intValue() == 0) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_NO_SESSION);
            return retorno;
        }

        try {       
            daoGenerico.guardar(entidad);
            retorno.setObjetoRespuesta(entidad);
            retorno.setResultado(RespuestaRPC.RESULTADO_OK);

        } catch (ConstraintViolationException e) {
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
            retorno.setDescripcionError(retorno.getDescripcionError());
        } catch (DataIntegrityViolationException e) {
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
            retorno.setDescripcionError(retorno.getDescripcionError());
        } catch (Exception e) {
            e.printStackTrace();
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
        }
        return retorno;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public RespuestaRPC<T> eliminarEntidad(Long idEntidad) {
        RespuestaRPC<T> retorno = new RespuestaRPC<T>();
        // Validamos session
        if (usuarioSession == null || usuarioSession.getId() == null || usuarioSession.getId().intValue() == 0) {

            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_NO_SESSION);
            return retorno;
        }
        try {
            T borrar = daoGenerico.getById(idEntidad);
            
            //Ejecuta borrado dependiendo del tipo de entidad es eliminado logico o fisico.
            daoGenerico.borrar(borrar);
            retorno.setResultado(RespuestaRPC.RESULTADO_OK);
        } catch (Exception e) {
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
        }

        return retorno;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public RespuestaRPC<T> eliminarEntidad(T entidad) {
        RespuestaRPC<T> retorno = new RespuestaRPC<T>();
        // Validamos session
        if (usuarioSession == null || usuarioSession.getId() == null || usuarioSession.getId().intValue() == 0) {

            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_NO_SESSION);
            return retorno;
        }
        try {
            
            //Ejecuta borrado dependiendo del tipo de entidad es eliminado logico o fisico.
            daoGenerico.borrar(entidad);
            retorno.setResultado(RespuestaRPC.RESULTADO_OK);
        } catch (Exception e) {
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
        }

        return retorno;
    }
    
    /**
     * {@inheritdoc}
     */
    public RespuestaRPC<T> reactivarEntidad(Long idEntidad) {
        RespuestaRPC<T> retorno = new RespuestaRPC<T>();
        // Validamos session
        if (usuarioSession == null || usuarioSession.getId() == null || usuarioSession.getId().intValue() == 0) {

            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_NO_SESSION);
            return retorno;
        }
        try {
            T reactivar = daoGenerico.getById(idEntidad);
            
            daoGenerico.reactivar(reactivar);
            retorno.setResultado(RespuestaRPC.RESULTADO_OK);
        } catch (Exception e) {
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
        }

        return retorno;
    }
    
    /**
     * {@inheritdoc}
     */
    @Override
    public List<T> getEntidades(int tipo) {
        if (tipo == EntidadPerpetua.TIPO_UNDELETED) {
            return daoGenerico.listar();
        } else if (tipo == EntidadPerpetua.TIPO_DELETED) {
            return daoGenerico.listarInactivos();
        } else {
            return new ArrayList<T>();
        }
    }
    
    
    /**
     * {@inheritdoc}
     */
    @Override
    public List<T> getEntidades(ModelData loadConfig, String campofiltro, int tipo) {
        
        ModelData m = (ModelData) loadConfig;
        //Get query string
        String query = (String) m.get("query");
        //System.out.println("aa "+query);

        String consultax = null;
        if (query != null && !query.isEmpty()) {
            consultax = query;
        } 
        
        if (tipo == EntidadPerpetua.TIPO_UNDELETED) {
            Consulta consulta = new Consulta();
            //indico que se ordena por el campo
             consulta.getOrden().add(new Pair(campofiltro, Orden.ASC));
             //activo filtrado de resultado.
            if(consultax!=null){
                consulta.getRestricciones().put(campofiltro, new Pair(Comparacion.LIKE, "%"+consultax+"%"));
            }
            try{
                //Cargo la lsita de datos de la BD
                Pair<Long, List<T>> result = daoGenerico.listarPorConsulta(consulta, 0, 1000);
                return result.getB();
            }catch(Exception e){
                log("Error", e); 
                return new ArrayList<T>();
            }
            
        } else if (tipo == EntidadPerpetua.TIPO_DELETED) {
            return daoGenerico.listarInactivos();
        } else {
            return new ArrayList<T>();
        }
    }
    
   
  
}
