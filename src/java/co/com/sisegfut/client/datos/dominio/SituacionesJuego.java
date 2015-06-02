///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.com.sisegfut.client.datos.dominio;
//
//import co.com.sisegfut.client.datos.dominio.dto.DTOSituacionJuegoComp;
//import com.extjs.gxt.ui.client.data.BeanModelTag;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
///**
// *
// * @author Andres Hurtado
// */
//@Entity
//@Table(name = "situacionesJuego")
//public class SituacionesJuego extends EntidadPerpetua implements BeanModelTag, Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_situacionesJuego")
//    @SequenceGenerator(name = "gen_situacionesJuego", sequenceName = "situacionesJuego_id_seq")
//    private Long Id;
//
//    @Column(name = "situacionesJuego", nullable = false, length = 80)
//    private String situacionesJuego;
//
//    public SituacionesJuego() {
//    }
//
//    public SituacionesJuego(Long Id, String situacionesJuego) {
//        this.Id = Id;
//        this.situacionesJuego = situacionesJuego;
//    }
//
//    @Override
//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long Id) {
//        this.Id = Id;
//    }
//
//    public String getSituacionesJuego() {
//        return situacionesJuego;
//    }
//
//    public void setSituacionesJuego(String situacionesJuego) {
//        this.situacionesJuego = situacionesJuego;
//    }
//
//    @Override
//    public String getLabel() {
//        return situacionesJuego;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public static List<DTOSituacionJuegoComp> getSituacionesJuegoCompetencia() {
//        List<DTOSituacionJuegoComp> situacionJuego = new ArrayList<DTOSituacionJuegoComp>();
//
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Falta Zona 1</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Falta Zona 2</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Falta Zona 3</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Recuperacion balon Zona 1</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Recuperacion balon Zona 2</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Recuperacion balonZona 3</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Tiro libre Zona 1</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Tiro libre Zona 2</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Fuera de lugar</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Tiro esquina</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Opciones gol</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Gol</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Remates</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Centros</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Penalty</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Entradas erradas</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//        situacionJuego.add(new DTOSituacionJuegoComp("<b>Asistencias gol</b>", "1L", 0, "2L", 0, "1R", 0, "2R", 0));
//
//        return situacionJuego;
//    }
//
//}
