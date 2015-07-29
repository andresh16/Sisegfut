/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.CambiosCompe;
import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import co.com.sisegfut.client.datos.dominio.ConvocadosCompe;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminConvocadosComp;
import co.com.sisegfut.server.datos.dao.DaoCambiosCompe;
import co.com.sisegfut.server.datos.dao.DaoControlDisciplinarioComp;
import co.com.sisegfut.server.datos.dao.DaoConvocadosCompe;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminConvocadosCompImpl extends RPCMaestroImpl<ConvocadosCompe> implements RPCAdminConvocadosComp {

    private Usuarios usuarioSession;
    private DaoConvocadosCompe daoConvocadosCompe;
    private DaoCambiosCompe daoCambiosCompe;
    private DaoControlDisciplinarioComp daoControlDisciplinarioComp;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoConvocados(DaoConvocadosCompe daoConvocadosCompe) {
        this.daoConvocadosCompe = daoConvocadosCompe;
        super.setDaoGenerico(daoConvocadosCompe);
    }

    @Autowired
    public void setDaoCambiosCom(DaoCambiosCompe daoCambiosCompe) {
        this.daoCambiosCompe = daoCambiosCompe;
//        super.setDaoGenerico(daoCambiosCompe);
    }

    @Autowired
    public void setDaoControlDisciplinarioCom(DaoControlDisciplinarioComp daoControlDisciplinarioComp) {
        this.daoControlDisciplinarioComp = daoControlDisciplinarioComp;
//        super.setDaoGenerico(daoCambiosCompe);
    }

    @Override
    public void guardarConvocadosComp(List<ConvocadosCompe> convocados) {

        for (ConvocadosCompe convocado : convocados) {
            daoConvocadosCompe.guardar(convocado);
        }

    }

    @Override
    public List<Deportista> getConvocadosXTipo(Long idCompetencia, String tipoConvado) {
        List<CambiosCompe> cambiosEliminar = new ArrayList<CambiosCompe>();
        List<Deportista> deportistas = daoConvocadosCompe.getConvocadosXTipo(idCompetencia, tipoConvado);
        cambiosEliminar = daoCambiosCompe.getCambiosXCompetencia(idCompetencia);
        List<ControlDisciplinarioCompe> controlDisciplinarioCompes = null;
        try {
            controlDisciplinarioCompes = daoControlDisciplinarioComp.getTarjetasXCompetencia(idCompetencia);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminConvocadosCompImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Deportista> eliminar = new ArrayList<Deportista>();

        for (Deportista jugador : deportistas) {
            if (!cambiosEliminar.isEmpty() || cambiosEliminar.size() != 0) {
                for (CambiosCompe cambios : cambiosEliminar) {

                    if (tipoConvado.equals("s")) {
                        if (jugador.getId().equals(cambios.getIdDeportisteEntra().getId())) {
                            eliminar.add(jugador);
                        }
                    } else {
                        if (jugador.getId().equals(cambios.getIdDeportistaSale().getId())) {
                            eliminar.add(jugador);
                        }
                    }
                }
            }
            if (!controlDisciplinarioCompes.isEmpty() || controlDisciplinarioCompes.size() != 0) {

                for (ControlDisciplinarioCompe disciplinarioCompe : controlDisciplinarioCompes) {

                    if (jugador.getId().equals(disciplinarioCompe.getIdDeportista().getId())
                            && disciplinarioCompe.getTipoTarjeta().equalsIgnoreCase("ROJA")) {
                        eliminar.add(jugador);
                    }
                }
            }

        }

        deportistas.removeAll(eliminar);

        return deportistas;

    }

    @Override
    public List<Deportista> getConvocadosXTipoGrids(Long idCompetencia, String tipoConvado) {
        try {
            return daoConvocadosCompe.getConvocadosXTipo(idCompetencia, tipoConvado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Deportista> getConvocados(Long idCompetencia) {

        List<Deportista> deportistas = daoConvocadosCompe.getConvocados(idCompetencia);
        List<ControlDisciplinarioCompe> controlDisciplinarioCompes = null;
        try {
            controlDisciplinarioCompes = daoControlDisciplinarioComp.getTarjetasXCompetencia(idCompetencia);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminConvocadosCompImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<CambiosCompe> JugadoresSalenEliminar = daoCambiosCompe.getCambiosXCompetencia(idCompetencia);

        List<Deportista> eliminar = new ArrayList<Deportista>();

        for (Deportista jugador : deportistas) {
            if (!controlDisciplinarioCompes.isEmpty() || controlDisciplinarioCompes.size() != 0) {
                //consulto por cada jugador si tiene tarjeta roja para eliminarlo de la lista 
                for (ControlDisciplinarioCompe disciplinarioCompe : controlDisciplinarioCompes) {

                    if (jugador.getId().equals(disciplinarioCompe.getIdDeportista().getId()) && disciplinarioCompe.getTipoTarjeta().equalsIgnoreCase("ROJA")) {
                        eliminar.add(jugador);
                    }
                }
            }

            if (!JugadoresSalenEliminar.isEmpty() || JugadoresSalenEliminar.size() != 0) {
                //Consulto los por cada jugador si ya salio del partido para eliminarlo de la lista 
                for (CambiosCompe cambios : JugadoresSalenEliminar) {

                    if (jugador.getId().equals(cambios.getIdDeportistaSale().getId())) {
                        eliminar.add(jugador);
                    }

                }
            }
        }
        deportistas.removeAll(eliminar);

        return deportistas;
    }

    @Override
    public Boolean validarConsultarConvocados(Long idCompetencia) {
        boolean habilitarEdicionConvocados = false;
        List<Deportista> titulares = null;
        List<Deportista> suplentes = null;

        titulares = daoConvocadosCompe.getConvocadosXTipo(idCompetencia, "t");
        suplentes = daoConvocadosCompe.getConvocadosXTipo(idCompetencia, "s");

        if ((titulares.isEmpty() ||  titulares.size()==0) && (suplentes.isEmpty() ||  suplentes.size()==0) ) {
            habilitarEdicionConvocados = true;
        }
        return habilitarEdicionConvocados;

    }

}
