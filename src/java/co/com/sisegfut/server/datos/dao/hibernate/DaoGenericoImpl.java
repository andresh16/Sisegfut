package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.EntidadPerpetua;
import co.com.sisegfut.client.util.Pair;
import co.com.sisegfut.client.util.consulta.Comparacion;
import co.com.sisegfut.client.util.consulta.Consulta;
import co.com.sisegfut.client.util.consulta.Orden;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import co.com.sisegfut.server.datos.dao.DaoGenerico;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

/**
 * {@inheritDoc}
 */
public class DaoGenericoImpl<T> implements DaoGenerico<T> {

    protected Class<T> domainClass = extraerClaseDominio();
    @Autowired
    protected SessionFactory sessionFactory;

    private Logger logger = Logger.getLogger(DaoGenericoImpl.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    protected Class<T> extraerClaseDominio() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public T getById(int id) {
        return (T) sessionFactory.getCurrentSession().get(extraerClaseDominio(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public T getById(Long id) {
        return (T) sessionFactory.getCurrentSession().get(extraerClaseDominio(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void guardar(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
    @Transactional
    public void eliminar(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void borrar(T entity) {
        //Si es una entidad perpetua hago borrado logico
        if (entity instanceof EntidadPerpetua) {
            ((EntidadPerpetua) entity).setFechaInactivado(new Date());
            guardar(entity);
        } else {
            sessionFactory.getCurrentSession().delete(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void reactivar(T entity) throws Exception {
        //Si es una entidad perpetua hago borrado logico
        if (entity instanceof EntidadPerpetua) {
            ((EntidadPerpetua) entity).setFechaInactivado(null);
            guardar(entity);
        } else {
            //la entidad no se puede reactivar.
            throw new Exception("La entidad indicada no se puede reactivar");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void refrescarBloquear(T entity) {
        sessionFactory.getCurrentSession().refresh(entity, LockMode.UPGRADE);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<T> listar() {
        try {
            if (extraerClaseDominio().newInstance() instanceof EntidadPerpetua) {
                StringBuilder sb = new StringBuilder("from ");
                sb.append(extraerClaseDominio().getName());
                sb.append(" entity");
                sb.append(" where fechaInactivado is null ");
                return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
            } else {
                StringBuilder sb = new StringBuilder("from ");
                sb.append(extraerClaseDominio().getName());
                sb.append(" entity");
                return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
            }
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        StringBuilder sb = new StringBuilder("from ");
        sb.append(extraerClaseDominio().getName());
        sb.append(" entity");
        return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<T> listarInactivos() throws DataAccessException {
        try {
            if (extraerClaseDominio().newInstance() instanceof EntidadPerpetua) {
                StringBuilder sb = new StringBuilder("from ");
                sb.append(extraerClaseDominio().getName());
                sb.append(" entity");
                sb.append(" where fechaInactivado is not null ");
                return sessionFactory.getCurrentSession().createQuery(sb.toString()).list();
            } else {
                return new ArrayList<T>();
            }
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<T>();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public Pair<Long, List<T>> listarPorConsulta(Consulta consulta, Integer offset, Integer limit) throws Exception {
        Map<String, Pair<Comparacion, Object>> restricciones = consulta.getRestricciones();
        List<Pair<String, Orden>> orden = consulta.getOrden();
        List<Pair<String, String>> aliasC = consulta.getAlias();

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(domainClass);

        for (Pair<String, String> pair : aliasC) {
            criteria.createAlias(pair.getA(), pair.getB());
        }

        //si es una entidad marcada como entidad perpetua solo listo las activas.
        if (extraerClaseDominio().newInstance() instanceof EntidadPerpetua) {
            criteria.add(Restrictions.isNull("fechaInactivado"));
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
                    criteria.addOrder(Order.asc(pair.getA()));
                } else if (pair.getB() == Orden.DESC) {
                    criteria.addOrder(Order.desc(pair.getA()));
                } else {
                    throw new UnsupportedOperationException("El orden no está soportado");
                }

            } catch (Exception e) {
                logger.error(String.format("Error adicionando el orden con nombre \"%s\"", pair.getA()));
                throw new RuntimeException(e);
            }

        }
        List<T> resultado;
        if (offset != null && limit != null) {
            resultado = criteria.setFirstResult(offset).setMaxResults(limit).list();
        } else {
            resultado = criteria.list();
        }
        return new Pair(cantidad, resultado);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public int ejecutarUpdateDelete(String consulta) {
        return sessionFactory.getCurrentSession()
                .createSQLQuery(consulta).executeUpdate();
    }
}
