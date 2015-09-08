/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.aatest.model.Estratos;
import co.com.sisegfut.client.aatest.model.Posicion;
import co.com.sisegfut.client.datos.dominio.Deportista;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anfeh_000
 */
public interface RPCAdminDeportista extends RPCMaestro<Deportista>{
    
     public Deportista updateConFoto(Deportista dep);
     
     public void actulizarGrasa(Long idDep,String grasa);
     
     public PagingLoadResult<Deportista> getDeportistaxCategoria(Long idCategoria);
     
     public PagingLoadResult<Deportista> getDeportistas();
     
     public String CalcularFCM(Date fechaNacimiento);
     
     public Integer CalcularEdad(Date fechaNacimiento);
     
     public List<Estratos> getDeportistasEstratificacion();
     
     public List<Posicion> getDeportistasPosicion();
    
}
