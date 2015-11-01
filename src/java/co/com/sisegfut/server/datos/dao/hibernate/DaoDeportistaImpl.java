/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.EntidadPerpetua;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTODeportistasxTipoDeportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOEstratosCantidad;
import co.com.sisegfut.client.datos.dominio.dto.DTOPosicionesCantidad;
import co.com.sisegfut.client.datos.dominio.dto.DTOTipoDeportistasCantidad;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Comparacion;
import co.com.sisegfut.client.util.consulta.Consulta;
import co.com.sisegfut.client.util.consulta.Orden;
import co.com.sisegfut.server.datos.dao.DaoDeportista;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
@Repository
public class DaoDeportistaImpl extends DaoGenericoImpl<Deportista> implements DaoDeportista {

    private Logger logger = Logger.getLogger(DaoGenericoImpl.class);

    @Override
    public Pair<Long, List<Deportista>> listarPorConsulta(Consulta consulta, Long Usuariologeado, Integer offset, Integer limit) throws Exception {

        Map<String, Pair<Comparacion, Object>> restricciones = consulta.getRestricciones();
        List<Pair<String, Orden>> orden = consulta.getOrden();
        List<Pair<String, String>> aliasC = consulta.getAlias();

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Deportista.class);

        for (Pair<String, String> pair : aliasC) {
            criteria.createAlias(pair.getA(), pair.getB());
        }

//        criteria.add(Restrictions.eq("usuario.id", Usuariologeado));
        //si es una entidad marcada como entidad perpetua solo listo las activas.
        if (extraerClaseDominio().newInstance() instanceof EntidadPerpetua) {
            criteria.add(Restrictions.isNull("fechaInactivado"));
            // y solo lo que no son comodines
//            
        }

        for (String prop : restricciones.keySet()) {

            Pair<Comparacion, Object> pair = restricciones.get(prop);

            if (pair.getB() == null) {
                if (pair.getA() != Comparacion.EQ && pair.getA() != Comparacion.NE) {
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
                    criteria.addOrder(Order.asc("apellidos"));
                } else if (pair.getB() == Orden.DESC) {
                    criteria.addOrder(Order.desc("apellidos"));
                } else {
                    throw new UnsupportedOperationException("El orden no está soportado");
                }

            } catch (Exception e) {
                logger.error(String.format("Error adicionando el orden con nombre \"%s\"", pair.getA()));
                throw new RuntimeException(e);
            }

        }
        List<Deportista> resultado = null;
        try {
            if (offset != null && limit != null) {
                resultado = criteria.setFirstResult(offset).setMaxResults(limit).list();
            } else {
                resultado = criteria.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pair(cantidad, resultado);
    }

    @Transactional(readOnly = true)
    @Override
    public Deportista obtenerDepxId(Long id) {
        try {
            Deportista deportista = new Deportista();
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuarios.class);
            criteria.add(Restrictions.eq("id", id));
            criteria.add(Restrictions.isNull("fechaInactivado"));
            criteria.add(Restrictions.eq("jugador_comodin", false));
            deportista = (Deportista) criteria.list();
            return deportista;
        } catch (HibernateException e) {

            e.printStackTrace();
            return null;

        }

    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<Deportista> getDeportistas() {
//        try {
//            List<Deportista> deportista = new ArrayList<Deportista>();
//            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuarios.class);
//            criteria.add(Restrictions.isNull("fechaInactivado"));
//            criteria.add(Restrictions.eq("jugador_comodin", false));
//            criteria.add(Restrictions.sqlRestriction("jugador_comodin=false"));
//            deportista = criteria.list();
//            return deportista;
//        } catch (HibernateException e) {
//
//            e.printStackTrace();
//            return null;
//
//        }
//
//    }
    @Transactional(readOnly = true)
    @Override
    public List<Deportista> deportistaXCategoria(Long idCategoria) throws Exception {
        List<Deportista> listaReporte = null;
        String sql = "Select d.* from deportista d where jugador_comodin=false and categoria=" + idCategoria + " and fechainactivado is null order by nombres asc";
        try {
            listaReporte = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("d", Deportista.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<Deportista> getDeportistas() throws Exception {
        List<Deportista> listaReporte = null;
        String sql = "Select d.* from deportista d where jugador_comodin=false and fechainactivado is null order by nombres asc";
        try {
            listaReporte = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("d", Deportista.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<DTOEstratosCantidad> getCantidadPorEstrato() throws Exception {
        List<DTOEstratosCantidad> listaReporte = new ArrayList<DTOEstratosCantidad>();
        String sql = "select estrato,Count(*)as cantidad from deportista as d group by estrato";
// Criteria que sirve para traer agrupado los estratos y la cantidad, pero no fue posible capturar los valores de los objets
//            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Deportista.class)
//                    .setProjection(Projections.projectionList()
//                            .add(Projections.groupProperty("estrato"))
//                            .add(Projections.count("nombres").as("cantidad")));
//            List resultado = criteria.list();
        for (int i = 1; i < 7; i++) {
            try {

                String sql1 = "select d.* from deportista as d where d.fechainactivado is null and d.estrato='" + i + "'";
                List<Deportista> cantidadPorestrato = (List<Deportista>) sessionFactory.getCurrentSession()
                        .createSQLQuery(sql1)
                        .addEntity("d", Deportista.class).list();
                if (cantidadPorestrato != null) {
                    DTOEstratosCantidad estratosCantidad = new DTOEstratosCantidad("" + i, cantidadPorestrato.size());
                    listaReporte.add(estratosCantidad);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return listaReporte;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Deportista> deportistaPosicionXCategoria(Long idCategoria) throws Exception {
        List<Deportista> listaReporte = null;
        String sql = "Select d.* from deportista d where jugador_comodin=false and categoria=" + idCategoria + " and fechainactivado is null order by posicion asc";
        try {
            listaReporte = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("d", Deportista.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<DTOPosicionesCantidad> getCantidadPorPosicion() throws Exception {
        List<DTOPosicionesCantidad> listaReporte = new ArrayList<DTOPosicionesCantidad>();
        String sql = "select posicion,Count(*)as cantidad from deportista as d group by posicion";
// Criteria que sirve para traer agrupado los estratos y la cantidad, pero no fue posible capturar los valores de los objets
//            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Deportista.class)
//                    .setProjection(Projections.projectionList()
//                            .add(Projections.groupProperty("estrato"))
//                            .add(Projections.count("nombres").as("cantidad")));
//            List resultado = criteria.list();
        for (int i = 1; i < 5; i++) {
            try {
                
                String sql1 = "select d.* from deportista as d where d.fechainactivado is null and d.posicion='"+i+"'";
                List<Deportista> cantidadPorPosicion = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql1)
                    .addEntity("d", Deportista.class).list();
                if(cantidadPorPosicion!=null){
                DTOPosicionesCantidad posicionCantidad = new DTOPosicionesCantidad(cantidadPorPosicion.get(1).getPosicion().getNombrePosicion(),cantidadPorPosicion.size());
                listaReporte.add(posicionCantidad);
                //
                }
            } catch (Exception e) {
                    e.printStackTrace();
                return null;
            }
        }
        return listaReporte;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Deportista> deportistaEstratoXCategoria(Long idCategoria) throws Exception {
        List<Deportista> listaReporte = null;
        String sql = "Select d.* from deportista d where jugador_comodin=false and categoria=" + idCategoria + " and fechainactivado is null order by estrato asc";
        try {
            listaReporte = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("d", Deportista.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Deportista> filtrarDeportista(String filtro) throws Exception {
        List<Deportista> listaReporte = null;
        String query="select d.* from deportista d where d.nombres like '%"+filtro.toUpperCase()+"%' or d.apellidos like '%"+filtro.toUpperCase()+"%' AND d.jugador_comodin=false and d.fechainactivado is null;";
        try {
            listaReporte = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .addEntity("d", Deportista.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Deportista> deportistaTipoDeportista(Long idTipoDeportista) throws Exception {
        List<Deportista> listaReporte = null;
        String sql = "Select d.* from deportista d where jugador_comodin=false and tipo_deportista=" + idTipoDeportista + " and fechainactivado is null order by tipo_deportista asc";
        try {
            listaReporte = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql)
                    .addEntity("d", Deportista.class).list();
            return listaReporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<DTOTipoDeportistasCantidad> getCantidadPorTipoDeportista() throws Exception {
        List<DTOTipoDeportistasCantidad> listaReporte = new ArrayList<DTOTipoDeportistasCantidad>();
        String sql = "select tipo_deportista,Count(*)as cantidad from deportista as d group by tipo_deportista";
// Criteria que sirve para traer agrupado los estratos y la cantidad, pero no fue posible capturar los valores de los objets
//            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Deportista.class)
//                    .setProjection(Projections.projectionList()
//                            .add(Projections.groupProperty("estrato"))
//                            .add(Projections.count("nombres").as("cantidad")));
//            List resultado = criteria.list();
        for (int i = 1; i < 5; i++) {
            try {
                
                String sql1 = "select d.* from deportista as d where d.fechainactivado is null and d.tipo_deportista='"+i+"'";
                List<Deportista> cantidadPorTipoDeportista = (List<Deportista>) sessionFactory.getCurrentSession()
                    .createSQLQuery(sql1)
                    .addEntity("d", Deportista.class).list();
                if(cantidadPorTipoDeportista!=null){
                DTOTipoDeportistasCantidad tipoDeportistaCantidad = new DTOTipoDeportistasCantidad(cantidadPorTipoDeportista.get(1).getTipoDeportista().getNombreTipoDeportista(),cantidadPorTipoDeportista.size());
                listaReporte.add(tipoDeportistaCantidad);
                //
                }
            } catch (Exception e) {
                    e.printStackTrace();
                return null;
            }
        }
        return listaReporte;
    }
}
