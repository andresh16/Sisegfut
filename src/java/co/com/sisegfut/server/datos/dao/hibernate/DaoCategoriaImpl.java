/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.datos.dao.hibernate;

import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.server.datos.dao.DaoCategoria;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
@Repository
public class DaoCategoriaImpl extends DaoGenericoImpl<Categoria> implements DaoCategoria {

    @Transactional(readOnly = true)
    @Override
    public List<Categoria> getCategorias(String likeCategoria, Long idUsuarioLogeado) throws DataAccessException {
        List<Categoria> retorno = null;
        try {

            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Categoria.class);
            //criteria.add(Restrictions.eq("usuario.id", idUsuarioLogeado));
            
            if (likeCategoria != null && !likeCategoria.isEmpty()) {
                
                criteria.add(Restrictions.ilike("nombrecategoria", "%" + likeCategoria + "%"));
            }

            criteria.addOrder(Order.asc("nombrecategoria"));
            retorno = (List<Categoria>) criteria.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }
//   
   @Transactional(readOnly=true)
    @Override
    public List<Categoria> getCategoria2(Long idUsuarioLogeado) throws DataAccessException {
   
    List<Categoria> retorno = null;
        
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Categoria.class);
        //criteria.add(Restrictions.eq("usuario.id", idUsuarioLogeado));
        retorno = (List<Categoria>) criteria.list();

        return retorno; 
    
    
    }
}
