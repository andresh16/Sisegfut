/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.aatest.model.Estratos;
import co.com.sisegfut.client.aatest.model.Posicion;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOEstratosCantidad;
import co.com.sisegfut.client.datos.dominio.dto.DTOPosicionesCantidad;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.server.datos.dao.DaoDeportista;
import co.com.sisegfut.server.util.Formatos;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminDeportistaImpl extends RPCMaestroImpl<Deportista> implements RPCAdminDeportista {

    private Usuarios usuarioSession;
    private DaoDeportista daoDeportista;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoDeportista(DaoDeportista daoDeportista) {
        this.daoDeportista = daoDeportista;
        super.setDaoGenerico(daoDeportista);
    }

    @Override
    public Deportista updateConFoto(Deportista dep) {
        try {
            Deportista deportista = daoDeportista.getById(dep.getId());
            dep.setFoto(deportista.getFoto());
            dep.setContentTypeFoto(deportista.getContentTypeFoto());
            daoDeportista.guardar(dep);
            return dep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public PagingLoadResult<Deportista> getDeportistaxCategoria(Long idCategoria) {

        List<Deportista> listaRetorno = new ArrayList<Deportista>();
        try {
            listaRetorno = daoDeportista.deportistaXCategoria(idCategoria);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminAsistenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        PagingLoadResult<Deportista> loadResult = new BasePagingLoadResult<Deportista>(listaRetorno, 1, 1000);
        return loadResult;

    }

    @Override
    public String CalcularFCM(Date fechaNacimiento) {
        Long fcm = Math.round(Formatos.calcularFCM(Formatos.getEdad(fechaNacimiento)));
        return fcm.toString();
    }

    @Override
    public Integer CalcularEdad(Date fechaNacimiento) {
        return Formatos.getEdad(fechaNacimiento);
    }

    @Override
    public void actulizarGrasa(Long idDep, String grasa) {

        try {
            Deportista deportista = daoDeportista.getById(idDep);
            deportista.setGrasa(grasa);
            daoDeportista.guardar(deportista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PagingLoadResult<Deportista> getDeportistas() {
        
        List<Deportista> listaRetorno = new ArrayList<Deportista>();
        try {
            listaRetorno = daoDeportista.getDeportistas();
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminAsistenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        PagingLoadResult<Deportista> loadResult = new BasePagingLoadResult<Deportista>(listaRetorno, 1, 1000);
        return loadResult;
    }

    @Override
    public List<Estratos> getDeportistasEstratificacion() {
        List<DTOEstratosCantidad> cantidadEstratos=null;
        System.out.println("" + cantidadEstratos);
        List<Estratos> estratos= new ArrayList<Estratos>();
        try {
            cantidadEstratos=daoDeportista.getCantidadPorEstrato();
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminDeportistaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i=1;
        for (DTOEstratosCantidad estrato : cantidadEstratos) {
            estratos.add(new Estratos((i++)+"","Estrato "+estrato.getEstrato(),estrato.getCantidad() ));
        }
        return estratos;
        
    }
    
    @Override
    public List<Posicion> getDeportistasPosicion() {
        List<DTOPosicionesCantidad> cantidadPosicion=null;
        System.out.println("" + cantidadPosicion);
        List<Posicion> posicion= new ArrayList<Posicion>();
        try {
            cantidadPosicion=daoDeportista.getCantidadPorPosicion();
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminDeportistaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i=1;
        for (DTOPosicionesCantidad posiciones : cantidadPosicion) {
            posicion.add(new Posicion((i++)+"",posiciones.getPosicion(),posiciones.getCantidad() ));
        }
        return posicion;
        
    }
    
    final String[] vectorPosiciones = new String[]{
        "Portero", "Defensa", "Volante", "Delantero"};
    
//    @Override
//    public List<Posiciones> getDeportistasPosicionD() {
//        List<Posiciones> cantidadPosicion=null;
//        System.out.println("" + cantidadPosicion);
//        List<Posiciones> posiciones= new ArrayList<Posiciones>();
//        try {
////            cantidadPosicion=daoDeportista.getCantidadPorPosicion();
//        } catch (Exception ex) {
//            Logger.getLogger(RPCAdminDeportistaImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        List<Posicion> data = new ArrayList<Posicion>();
//        /*
//         *Inicializo la lista de datas para poder modificarlos segun el equipo 
//         */
//        for (int i = 0; i < 3; i++) {
//            data.add(new Posicion(i + "", vectorPosiciones[i % vectorPosiciones.length], 0));
//        }
//        
//        for (Posiciones posicion : cantidadPosicion) {
//            int i = 0;
//            data.set(i, new Posicion(i + "", vectorPosiciones[i % vectorPosiciones.length],  data.get(i).getCantidad() + posicion.getId()));
//                i++;
//                data.set(i, new Posicion(i + "", vectorPosiciones[i % vectorPosiciones.length], data.get(i).getCantidad() + posicion.getId()));
//                i++;
//                data.set(i, new Posicion(i + "", vectorPosiciones[i % vectorPosiciones.length], data.get(i).getCantidad() + posicion.getId()));
//                i++;
//                data.set(i, new Posicion(i + "", vectorPosiciones[i % vectorPosiciones.length], data.get(i).getCantidad() + posiciones.getId()));
//                i++; 
//                
//                
//        }    
//         return data;  
//        
//        
//    }

    @Override
    public PagingLoadResult<Deportista> getFiltroDeportistas(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
