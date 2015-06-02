/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntecedentesDeportivos;

import co.com.sisegfut.client.util.rpc.RPCAdminAntDeportivos;
import co.com.sisegfut.server.datos.dao.DaoAntecedentesDeportivos;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fhurtado
 */
public class RPCAdminAntDeportivosImpl extends RPCMaestroImpl<AntecedentesDeportivos> implements RPCAdminAntDeportivos {

    private Usuarios usuarioSession;
    private DaoAntecedentesDeportivos daoAntecedentesDeportivos;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoAntDep(DaoAntecedentesDeportivos daoantDeportivos) {
        this.daoAntecedentesDeportivos = daoantDeportivos;
        super.setDaoGenerico(daoantDeportivos);
    }

    @Transactional
    @Override
    public void guardarGridAntDep(Long IdDep, List<AntecedentesDeportivos> antecedentesDeportivos) {

        for (AntecedentesDeportivos antDeportivos : antecedentesDeportivos) {

            AntecedentesDeportivos nuevo = new AntecedentesDeportivos();

            nuevo.setIdDeportista(new Deportista(IdDep));
            nuevo.setId((Long) antDeportivos.get("id"));
            nuevo.setClubAnterior((String) antDeportivos.get("clubAnterior"));

            nuevo.setCategoriaAnterior(new Categoria((Long) antDeportivos.get("categoriaAnterior")));

            nuevo.setAnno((String) antDeportivos.get("anno"));
            System.out.println("---------Insertara------------");
            System.out.println(antDeportivos.get("anno"));
            System.out.println(antDeportivos.get("clubAnterior"));
            System.out.println(antDeportivos.get("categoriaAnterior"));
            System.out.println("-----------------------------");

            daoAntecedentesDeportivos.guardar(nuevo);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingLoadResult<DTOAntecedentesDeportivos> getAntecedentesDeportivos(Long idDeportista) {

        List<AntecedentesDeportivos> listAnt = new ArrayList<AntecedentesDeportivos>();
        List<DTOAntecedentesDeportivos> listaRetorno = new ArrayList<DTOAntecedentesDeportivos>();
        try {
            listAnt = daoAntecedentesDeportivos.AnteDepxDeportista(idDeportista);
            if (listAnt != null) {
                for (AntecedentesDeportivos valorAntecedente : listAnt) {
                    DTOAntecedentesDeportivos agg = new DTOAntecedentesDeportivos();
                    agg.setClubAnterior(valorAntecedente.getClubAnterior());
                    agg.setAnno(valorAntecedente.getAnno());
                    agg.setCategoriaAnterior(valorAntecedente.getCategoriaAnterior().getNombrecategoria());
                    agg.setIdCategoria(valorAntecedente.getCategoriaAnterior().getId());
                    agg.setIdAntecedente(valorAntecedente.getId());
                    listaRetorno.add(agg);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminAntDeportivosImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        PagingLoadResult<DTOAntecedentesDeportivos> loadResult = new BasePagingLoadResult<DTOAntecedentesDeportivos>(listaRetorno, 1, 1000);
        return loadResult;
    }

}
