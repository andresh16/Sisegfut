/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Cuentas;
import co.com.sisegfut.client.datos.dominio.EntidadPerpetua;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Comparacion;
import co.com.sisegfut.client.util.consulta.Consulta;
import co.com.sisegfut.client.util.consulta.Orden;
import co.com.sisegfut.server.datos.dao.DaoCuenta;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoCuentaImpl extends DaoGenericoImpl<Cuentas> implements DaoCuenta{
    
    private Logger logger = Logger.getLogger(DaoGenericoImpl.class);
    
    @Transactional
    @Override
    public Pair<Long, List<Cuentas>> listarPorConsulta(Consulta consulta,Long Usuariologeado, Integer offset, Integer limit) throws Exception {
       Map<String, Pair<Comparacion, Object>> restricciones = consulta.getRestricciones();
        List<Pair<String, Orden>> orden = consulta.getOrden();
        List<Pair<String, String>> aliasC = consulta.getAlias();

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cuentas.class);
        
        for (Pair<String, String> pair : aliasC) {
            criteria.createAlias(pair.getA(), pair.getB());
        }
        
            criteria.add(Restrictions.eq("usuario.id", Usuariologeado));
        //si es una entidad marcada como entidad perpetua solo listo las activas.
        if (extraerClaseDominio().newInstance() instanceof EntidadPerpetua) {
            criteria.add(Restrictions.isNull("fechaInactivado"));
        }

        for (String prop : restricciones.keySet()) {

            Pair<Comparacion, Object> pair = restricciones.get(prop);
            
            if (pair.getB()==null) {
                if (pair.getA()!= Comparacion.EQ && pair.getA()!= Comparacion.NE) {
                    logger.error("No pueden haber campos con valor null con una comparacion diferente a EQ o NE");
                    throw new RuntimeException("No pueden haber haber campos en null que no sean EQ o NE");
                }
            }

            try {

                if (pair.getA() == Comparacion.EQ) {
                    if (pair.getB() instanceof Collection) {
                        Collection params = (Collection) pair.getB();

                        if (!params.isEmpty()) {
                            criteria.add(Restrictions.in(prop, (Collection) pair.getB()));
                        }
                    } else if (pair.getB() == null) {
                        criteria.add(Restrictions.isNull(prop));
                    } else {
                        criteria.add(Restrictions.eq(prop, pair.getB()));
                    }
                } else if (pair.getA() == Comparacion.GE) {
                    criteria.add(Restrictions.ge(prop, pair.getB()));
                } else if (pair.getA() == Comparacion.GT) {
                    criteria.add(Restrictions.gt(prop, pair.getB()));
                } else if (pair.getA() == Comparacion.LE) {
                    criteria.add(Restrictions.le(prop, pair.getB()));
                } else if (pair.getA() == Comparacion.LIKE) {
                    criteria.add(Restrictions.ilike(prop, pair.getB()));
                } else if (pair.getA() == Comparacion.LT) {
                    criteria.add(Restrictions.lt(prop, pair.getB()));
                } else if (pair.getA() == Comparacion.NE) {
                    if (pair.getB() instanceof Collection) {
                        Collection params = (Collection) pair.getB();

                        if (!params.isEmpty()) {
                            criteria.add(Restrictions.not(Restrictions.in(prop, (Collection) pair.getB())));
                        }
                    } else if (pair.getB() == null) {
                        criteria.add(Restrictions.isNotNull(prop));

                    } else {
                        criteria.add(Restrictions.ne(prop, pair.getB()));
                    }
                } else {
                    throw new UnsupportedOperationException("El criterio no está soportado");
                }

            } catch (Exception e) {
                logger.error(String.format("Errror adicionando el criterio con nombre \"%s\"", pair.getB()));
                throw new RuntimeException(e);
            }
        }
        Long cantidad = null;
        try {
            cantidad = Long.parseLong("" + criteria.setProjection(Projections.projectionList()
                    .add(Projections.rowCount())).uniqueResult());
        } catch (Exception e) {
            logger.error("Error en el query", e);
            throw new RuntimeException(e);
        }
        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.ROOT_ENTITY);

        for (Pair<String, Orden> pair : orden) {

            try {

                if (pair.getB() == Orden.ASC) {
                    criteria.addOrder(Order.asc("nombrecuenta"));
                } else if (pair.getB() == Orden.DESC) {
                    criteria.addOrder(Order.desc("nombrecuenta"));
                } else {
                    throw new UnsupportedOperationException("El orden no está soportado");
                }

            } catch (Exception e) {
                logger.error(String.format("Error adicionando el orden con nombre \"%s\"", pair.getA()));
                throw new RuntimeException(e);
            }

        }
        List<Cuentas> resultado;
        if (offset != null && limit != null) {
            resultado = criteria.setFirstResult(offset).setMaxResults(limit).list();
        } else {
            resultado = criteria.list();
        }
        return new Pair(cantidad, resultado);
    }
    
    
    @Transactional(readOnly=true)
    @Override
    public List<Cuentas> getCuentas(Long IdUsuarioLogeado) throws DataAccessException {
  
    
        List<Cuentas> retorno = null;
        
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cuentas.class);
        criteria.add(Restrictions.eq("usuario.id", IdUsuarioLogeado));
        retorno = (List<Cuentas>) criteria.list();

        return retorno; 
    
    }
    @Transactional(readOnly=true)
    @Override
    public void updateCuentaTran(Long idCuenta, Double saldoTran) throws DataAccessException {

        String sql ="Update cuentas set saldo=(saldo+:updsaldo) where id=:idcuenta";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.addEntity("cuentas", Cuentas.class);
        query.setDouble("updsaldo", saldoTran);
        query.setLong("idcuenta", idCuenta);
        query.executeUpdate();
        
    }
}
