/**
* RPCAdminCuentaImpl
* Versión 1.0
* 15/09/2013
*
* Copyright(c) 2007-2012, Boos IT.
* admin@boos.com.co
*
* http://boos.com.co/license
**/

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Cuentas;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Comparacion;
import co.com.sisegfut.client.util.consulta.Consulta;
import co.com.sisegfut.client.util.consulta.Orden;
import co.com.sisegfut.client.util.rpc.RPCAdminCuenta;
import co.com.sisegfut.server.datos.dao.DaoCuenta;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.FilterConfig;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author josorio
 */
public class RPCAdminCuentaImpl  extends RPCMaestroImpl<Cuentas> implements RPCAdminCuenta {

    private Usuarios usuarioSession;
    private DaoCuenta daoCuenta;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoCuenta(DaoCuenta daoCuenta) {
        this.daoCuenta = daoCuenta;
        super.setDaoGenerico(daoCuenta); 
    }
    
    
    
    @Transactional
    @Override
    public Pair<Long, List<Cuentas>> procesarConsulta(FilterPagingLoadConfig config) {
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
            Pair<Long, List<Cuentas>> result = daoCuenta.listarPorConsulta(consulta,usuarioSession.getId(), start, limit);

            //this.antesRetorno(result.getB());
            
            return result;
        }catch(Exception e){
            System.err.println("ERROR: error en la consulta. "+e.getMessage());
            Pair<Long, List<Cuentas>> result = new Pair<Long, List<Cuentas>>(0l, new ArrayList<Cuentas>());
            return result;
        }

        
    }
    
    
    
    
    
    

    @Override
    public PagingLoadResult<Cuentas> getConsulta(FilterPagingLoadConfig config){
       Pair<Long, List<Cuentas>> result = procesarConsulta(config);
        PagingLoadResult<Cuentas> loadResult = new BasePagingLoadResult<Cuentas>(result.getB(), config.getOffset(), result.getA().intValue());

        return loadResult;
    }

    @Override
    public List<Cuentas> getCuentas(Long IdUsuarioLogeado) {

     return daoCuenta.getCuentas(IdUsuarioLogeado);
      
         
    }

    @Override
    public void getUpdateCuenta(Cuentas cuenta) {
        try {
            daoCuenta.guardar(cuenta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    @Override
    public void getUpdateCuentaTransferir(Long idCuenta, Double saldoTrans) {
        try {
            daoCuenta.updateCuentaTran(idCuenta, saldoTrans);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
