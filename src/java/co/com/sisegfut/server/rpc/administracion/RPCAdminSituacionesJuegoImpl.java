/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.aatest.model.Data2;
import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import co.com.sisegfut.client.datos.dominio.SituacionesJuegoCompe;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuego;
import co.com.sisegfut.server.datos.dao.DaoCompetencia;
import co.com.sisegfut.server.datos.dao.DaoControlDisciplinarioComp;
import co.com.sisegfut.server.datos.dao.DaoSituacionesJuego;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminSituacionesJuegoImpl extends RPCMaestroImpl<SituacionesJuegoCompe> implements RPCAdminSituacionesJuego {

    private Usuarios usuarioSession;
    private DaoSituacionesJuego daoSituacionesJuegos;
    private DaoControlDisciplinarioComp daoControlDisciplinarioComp;
    private DaoCompetencia daoCompetencia;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoSituacionesJuego(DaoSituacionesJuego daoSituacionesJuegos) {
        this.daoSituacionesJuegos = daoSituacionesJuegos;
        super.setDaoGenerico(daoSituacionesJuegos);
    }

    @Autowired
    public void setDaoControlD(DaoControlDisciplinarioComp daoControlDisciplinario) {
        this.daoControlDisciplinarioComp = daoControlDisciplinario;
    }

    @Autowired
    public void setDaoCompetencia(DaoCompetencia daoCompetencia) {
        this.daoCompetencia = daoCompetencia;
    }

    @Override
    public List<SituacionesJuegoCompe> getSituacionesXCompetencia(Long idCompetencia) {
        List<SituacionesJuegoCompe> situacionesJuego = new ArrayList<SituacionesJuegoCompe>();

        try {
            situacionesJuego = daoSituacionesJuegos.getSituacionesJxCompetencia(idCompetencia);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminSituacionesJuegoImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return situacionesJuego;
    }

    final String[] vectorSituacionesJuego = new String[]{
        "Falta Z1", "Falta Z2", "Falta Z3", "Recuperación Z1", "Recuperación Z2", "Recuperación Z3", "Tiro Libre Z1", "Tiro Libre Z2", "Tiro Libre Z3", "Centro Lateral",
        "Entradas Erradas", "Fuera Lugar", "Opción Gol", "Penalty", "Remate", "Tiro Esquina", "Tarjeta Roja", "Tarjeta Amarilla", "Gol"};

    @Override
    public List<Data2> getSituacionesXCompetenciaGrafica(Long idCompetencia) {
        List<SituacionesJuegoCompe> situacionesJuego = null;
        List<ControlDisciplinarioCompe> controlDisciplinarioCompes = null;
        Competencia competencia = null;
        try {
            controlDisciplinarioCompes = daoControlDisciplinarioComp.getTarjetasXCompetencia(idCompetencia);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminSituacionesJuegoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        competencia = daoCompetencia.getById(idCompetencia);
        try {
            situacionesJuego = daoSituacionesJuegos.getSituacionesJxCompetencia(idCompetencia);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminSituacionesJuegoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Tarjeta Roja Rival
        Integer tarjetaRojaR = 0;
        // Tarjeta Amarrilla Rival
        Integer tarjetaAmarillaR = 0;
        // Tarjeta Roja Anfitrion
        Integer tarjetaRojaA = 0;
        // Tarjeta Amarrilla Anfitrion
        Integer tarjetaAmarillaA = 0;

        for (ControlDisciplinarioCompe controlDisciplinarioCompe : controlDisciplinarioCompes) {
            if (controlDisciplinarioCompe.getEquipotarjeta().equalsIgnoreCase("POLITECNICO JIC")) {
                if (controlDisciplinarioCompe.getTipoTarjeta().equalsIgnoreCase("AMARILLA")) {
                    tarjetaAmarillaA += 1;
                } else {
                    tarjetaRojaR += 1;
                }

            } else if (controlDisciplinarioCompe.getEquipotarjeta().equalsIgnoreCase("RIVAL")) {
                if (controlDisciplinarioCompe.getTipoTarjeta().equalsIgnoreCase("AMARILLA")) {
                    tarjetaAmarillaR += 1;
                } else {
                    tarjetaRojaR += 1;
                }
            }

        }

        List<Data2> data = new ArrayList<Data2>();
        /*
         *Inicializo la lista de datas para poder modificarlos segun el equipo 
         */
        for (int i = 0; i < 19; i++) {
            data.add(new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], 0, 0));
        }
        /**
         * Recorro los cuatro registros de la asistencias segun la competencia
         */
        for (SituacionesJuegoCompe situacionesJuegoCompe : situacionesJuego) {
            if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("POLITECNICO JIC")) {
                //Realizo un for manual para saber la posicion del nombre de la situación
                int i = 0;
                // Agrego por cada data un registro de data pero solo para insertar valor de anfitrion en este caso el Poli
                // esto funciona como un acumulador ya que va entrar dos veces por el tiempo de juego, esto se validará si se puede 
                // hacer en dos graficas. por el momento muestra todo
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getFaltaZona1(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getFaltaZona2(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getFaltaZona3(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getRecuperacionZona1(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getRecuperacionZona2(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getRecuperacionZona3(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getTiroLibreZona1(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getTiroLibreZona2(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getTiroLibreZona3(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getCentrolLateral(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getEntregasErradas(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getFueraLugar(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getOpcionGol(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getPenalty(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getRemates(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getTiroEsquina(), data.get(i).getCantidadRival()));
                i++;

            } else if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("RIVAL")) {
                int i = 0;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getFaltaZona1()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getFaltaZona2()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getFaltaZona3()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getRecuperacionZona1()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getRecuperacionZona2()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getRecuperacionZona3()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getTiroLibreZona1()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getTiroLibreZona2()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getTiroLibreZona3()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getCentrolLateral()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getEntregasErradas()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getFueraLugar()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getOpcionGol()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getPenalty()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getRemates()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getTiroEsquina()));
                i++;
            }
        }
        data.set(16, new Data2(16 + "", vectorSituacionesJuego[16 % vectorSituacionesJuego.length], tarjetaRojaA, tarjetaRojaR));
        data.set(17, new Data2(17 + "", vectorSituacionesJuego[17 % vectorSituacionesJuego.length], tarjetaAmarillaA, tarjetaAmarillaR));
        data.set(18, new Data2(18 + "", vectorSituacionesJuego[18 % vectorSituacionesJuego.length], competencia.getGolesAnfitrion(), competencia.getGolesRival()));

        // Seteo la variable global de lista de Data2 para luego en el botón recargar tome esta lista.
        return data;
    }

}
