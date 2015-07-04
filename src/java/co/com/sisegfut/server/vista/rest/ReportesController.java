/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.vista.rest;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.Antropometrico;
import co.com.sisegfut.client.datos.dominio.Lesiones;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.EstudiosRealizados;
import co.com.sisegfut.client.datos.dominio.Experiencia;
import co.com.sisegfut.client.datos.dominio.LogrosDeportivos;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntropometricoxCategoria;
import co.com.sisegfut.client.datos.dominio.dto.DTODeportistaxCategoria;
import co.com.sisegfut.client.datos.dominio.dto.DTOHVDeportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOHVPersonal;
import co.com.sisegfut.server.util.Formatos;
import co.com.sisegfut.server.datos.dao.DaoAntecedentesDeportivos;
import co.com.sisegfut.server.datos.dao.DaoAntropometrico;
import co.com.sisegfut.server.datos.dao.DaoControlTecnico;
import co.com.sisegfut.server.datos.dao.DaoLesiones;
import co.com.sisegfut.server.datos.dao.DaoDeportista;
import co.com.sisegfut.server.datos.dao.DaoEstudiosRealizados;
import co.com.sisegfut.server.datos.dao.DaoExperiencia;
import co.com.sisegfut.server.datos.dao.DaoLogrosDeportivos;
import co.com.sisegfut.server.datos.dao.DaoPersonal;
import co.com.sisegfut.server.datos.dao.DaoTestCooper;
import co.com.sisegfut.server.datos.dao.DaoTestKarvonen;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fhurtado
 */
@Controller
@RequestMapping("/reportes")
public class ReportesController {

    private static Logger log = Logger.getLogger(ReportesController.class);
    @Autowired
    private Usuarios usuarioSession;    
    @Autowired
    private DaoDeportista daoDeportista;
    @Autowired
    private DaoAntecedentesDeportivos daoAntecedentesDeportivos;
    @Autowired
    private DaoLogrosDeportivos daoLogrosDeportivos;
    @Autowired
    private DaoLesiones daoLesiones;
    @Autowired
    private DaoPersonal daoPersonal;
    @Autowired
    private DaoEstudiosRealizados daoEstudiosRealizados;
    @Autowired
    private DaoExperiencia daoExperiencia;
    @Autowired
    private DaoTestCooper daoTestCooper;
    @Autowired
    private DaoTestKarvonen daoTestKarvonen;
    @Autowired
    private DaoAntropometrico daoAntropometrico;
    @Autowired
    private DaoControlTecnico daoControlTecnico;

    private static final int TIPO_XLS = 1;
    private static final int TIPO_PDF = 2;

    /**
     * Reporte de deportistas por categoria
     *
     * @param nombreCategoria
     * @param categoria
     * @param tipo
     * @param modelAndView
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ReporteDeportista/{nombreCategoria}/{categoria}/{tipo}",
            method = RequestMethod.GET)
    public ModelAndView doReportDeportista(
            @PathVariable String nombreCategoria,
            @PathVariable Long categoria,
            @PathVariable Long tipo,
            ModelAndView modelAndView,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (usuarioSession == null || usuarioSession.getId() == null) {
            ModelAndView retorno = new ModelAndView("errores");
            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
            retorno.addObject("mensaje", "Debe tener una sesion activa para mostrar este contenido.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            return retorno;
        }
        try {
            log.info("Entra a generar reporte");
            List<Deportista> listaDep = daoDeportista.deportistaXCategoria(categoria);

            List<DTODeportistaxCategoria> listaDeportistaReport = new ArrayList<DTODeportistaxCategoria>();

            for (Deportista deportista : listaDep) {

                DTODeportistaxCategoria dtoDeportista = new DTODeportistaxCategoria();

                dtoDeportista.setFechaNacimiento(Formatos.fecha2(deportista.getFechaNacimiento()));
                dtoDeportista.setNombres(deportista.getNombres());
                dtoDeportista.setApellidos(deportista.getApellidos());
                dtoDeportista.setIdentificacion(deportista.getDocumento());
                dtoDeportista.setPosicion(deportista.getPosicion().getNombrePosicion());

                listaDeportistaReport.add(dtoDeportista);
            }

            if (listaDeportistaReport.isEmpty()) {
                listaDeportistaReport.add(new DTODeportistaxCategoria());
            }
            System.out.println("nombre cat" + nombreCategoria);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaReport));
            parameterMap.put("categoria", nombreCategoria);

            if (tipo == TIPO_XLS) {
                modelAndView = new ModelAndView("xlsReporteDeportista", parameterMap);
            } else {
                modelAndView = new ModelAndView("pdfReporteDeportista", parameterMap);
            }

            return modelAndView;

        } catch (Exception e) {
            ModelAndView retorno = new ModelAndView("errores");

            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));

            retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el area de soporte técnico.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            log.error("Error generando reporte", e);

            return retorno;
        }
    }

    @RequestMapping(value = "/ReporteDeportistaHV/{idDeportista}",
            method = RequestMethod.GET)
    public ModelAndView doReportHVDeportista(
            @PathVariable Long idDeportista,
            ModelAndView modelAndView,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (usuarioSession == null || usuarioSession.getId() == null) {
            ModelAndView retorno = new ModelAndView("errores");
            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
            retorno.addObject("mensaje", "Debe tener una sesion activa para mostrar este contenido.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            return retorno;
        }
        try {
            log.info("Entra a generar reporte deportista HV");
            Deportista dep = daoDeportista.getById(idDeportista);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("documento", dep.getDocumento());
            parameterMap.put("tipoDocumento", dep.getTipoDocumento().getNombreTipoDocumento());
            parameterMap.put("nombres", dep.getNombres());
            parameterMap.put("apellidos", dep.getApellidos());
            parameterMap.put("fechaNacimiento", Formatos.fecha2(dep.getFechaNacimiento()));
            parameterMap.put("correo", dep.getCorreoElectronico());
            parameterMap.put("direccion", dep.getDireccion());
            parameterMap.put("telefono", dep.getTelefono());
            parameterMap.put("barrio", dep.getBarrio());
            parameterMap.put("categoria", dep.getCategoria().getNombrecategoria());
            parameterMap.put("nombreMadre", dep.getNombreMadre());
            if (dep.getNombrePadre() == null) {
                parameterMap.put("nombrePadre", "N/A");
            } else {
                parameterMap.put("nombrePadre", dep.getNombrePadre());
            }
            if (dep.getNumeroMadre() == null) {
                parameterMap.put("numeroMadre", "0");
            } else {
                parameterMap.put("numeroMadre", dep.getNumeroMadre());
            }
            if (dep.getNumeroPadre() == null) {
                parameterMap.put("numeroPadre", "0");
            } else {
                parameterMap.put("numeroPadre", dep.getNumeroPadre());
            }
            parameterMap.put("estatura", dep.getEstatura());
            parameterMap.put("eps", dep.getEps().getNombreEps());
            parameterMap.put("estrato", dep.getEstrato());
            System.out.println("edad" + Formatos.getEdad(dep.getFechaNacimiento()));
            System.out.println("fcm" + Formatos.calcularFCM(Formatos.getEdad(dep.getFechaNacimiento())).toString());
            parameterMap.put("fcm", Formatos.calcularFCM(Formatos.getEdad(dep.getFechaNacimiento())).toString());
            parameterMap.put("peso", dep.getPeso());
            parameterMap.put("posicion", dep.getPosicion().getNombrePosicion());
            parameterMap.put("imc", dep.getImc());
            parameterMap.put("genero", dep.getGenero());
            parameterMap.put("nivelEducativo", dep.getNivelEducativo().getNombreNivelEducativo());
            parameterMap.put("instEducativa", dep.getInstEducativa().getNombreInstEducativa());

            List<AntecedentesDeportivos> listAnt = new ArrayList<AntecedentesDeportivos>();
            List<LogrosDeportivos> listLogros = new ArrayList<LogrosDeportivos>();
            List<Lesiones> listLesion = new ArrayList<Lesiones>();

            List<DTOHVDeportista> listaRetorno = new ArrayList<DTOHVDeportista>();

            listAnt = daoAntecedentesDeportivos.AnteDepxDeportista(idDeportista);
            listLogros = daoLogrosDeportivos.LogroDepxDeportista(idDeportista);
            listLesion = daoLesiones.AnteOsteoMuscularxDeportista(idDeportista);

            int valorMayor = 0;
            if (listAnt.size() > listLogros.size()) {
                if (listAnt.size() > listLesion.size()) {
                    valorMayor = listAnt.size();
                }
            } else {
                if (listLogros.size() > listLesion.size()) {
                    valorMayor = listLogros.size();
                } else {
                    valorMayor = listLesion.size();
                }
            }

            for (int i = 0; i < valorMayor; i++) {
                DTOHVDeportista agg = new DTOHVDeportista();

                if (listAnt.size() > i) {
                    if (listAnt.get(i) != null) {
                        agg.setClubAnterior(listAnt.get(i).getClubAnterior());
                        agg.setAnnoAntDep(listAnt.get(i).getAnno());
                        agg.setCategoriaAnteriorAntDep(listAnt.get(i).getCategoriaAnterior().getNombrecategoria());
                    }
                } else {
                    agg.setClubAnterior("");
                    agg.setAnnoAntDep("");
                    agg.setCategoriaAnteriorAntDep("");

                }
                if (listLogros.size() > i) {
                    if (listLogros.get(i) != null) {
                        agg.setLogro(listLogros.get(i).getLogroDeportivo());
                        agg.setCategoriaAnteriorLogro(listLogros.get(i).getCategoriaLogro().getNombrecategoria());
                        agg.setAnnoLogro(listLogros.get(i).getAnioLogro());
                    }
                } else {
                    agg.setLogro("");
                    agg.setCategoriaAnteriorLogro("");
                    agg.setAnnoLogro("");
                }
                if (listLesion.size() > i) {
                    if (listLesion.get(i) != null) {
                        agg.setLesion(listLesion.get(i).getNombreLesion());
                        agg.setFechaLesion(listLesion.get(i).getFechaLesion().toString());
                    }
                } else {
                    agg.setLesion("");
                    agg.setFechaLesion("");
                }
                listaRetorno.add(agg);

            }

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaRetorno));

            modelAndView = new ModelAndView("pdfReporteHV", parameterMap);

            return modelAndView;

        } catch (Exception e) {
            ModelAndView retorno = new ModelAndView("errores");

            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));

            retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el area de soporte técnico.\n" + e);
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            log.error("Error generando reporte", e);

            return retorno;
        }
    }
    
    @RequestMapping(value = "/HVPersonal/{idPersonal}",
            method = RequestMethod.GET)         
    public ModelAndView doReportHVPersonal(
            @PathVariable Long idPersonal,
            ModelAndView modelAndView,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (usuarioSession == null || usuarioSession.getId() == null) {
            ModelAndView retorno = new ModelAndView("errores");
            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
            retorno.addObject("mensaje", "Debe tener una sesion activa para mostrar este contenido.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            return retorno;
        }
        try {
            log.info("Entra a generar reporte personal HV");
            Personal per = daoPersonal.getById(idPersonal);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("documento", per.getDocumento());
            parameterMap.put("tipoDocumento", per.getTipoDocumento().getNombreTipoDocumento());
            parameterMap.put("nombres", per.getNombres());
            parameterMap.put("apellidos", per.getApellidos());
            parameterMap.put("fechaNacimiento", Formatos.fecha2(per.getFechaNacimiento()));
            parameterMap.put("correo", per.getCorreoElectronico());
            parameterMap.put("direccion", per.getDireccion());
            parameterMap.put("celular", per.getCelular());
            parameterMap.put("telefono", per.getTelefono());
            parameterMap.put("barrio", per.getBarrio());
            parameterMap.put("cargo", per.getCargo().getNombrecargo());
            parameterMap.put("genero", per.getGenero());
            parameterMap.put("nivelEducativo", per.getNivelEducativo().getNombreNivelEducativo());

            List<EstudiosRealizados> listEst = new ArrayList<EstudiosRealizados>();
            List<Experiencia> listExp = new ArrayList<Experiencia>();

            List<DTOHVPersonal> listaRetornoPersonal = new ArrayList<DTOHVPersonal>();

            listEst = daoEstudiosRealizados.EstudiosRealizadosxPersonal(idPersonal);
            listExp = daoExperiencia.ExperienciaxPersonal(idPersonal);

            int valorMayor = 0;
            if (listEst.size() > listExp.size()) {
                    valorMayor = listEst.size();
                }
             else {
                valorMayor = listExp.size();
            }
            

            for (int i = 0; i < valorMayor; i++) {
                DTOHVPersonal agg = new DTOHVPersonal();

                if (listEst.size() > i) {
                    if (listEst.get(i) != null) {
                        agg.setTitulo(listEst.get(i).getTitulo());
                        agg.setInstitucionEducativa(listEst.get(i).getInstitucion());
                        agg.setEscolaridad(listEst.get(i).getNivelEducativo().getNombreNivelEducativo());
                        agg.setAnnoGraduacion(listEst.get(i).getAnioGraduacion());
                    }
                } else {
                    agg.setTitulo("");
                    agg.setInstitucionEducativa("");
                    agg.setEscolaridad("");
                    agg.setAnnoGraduacion("");

                }
                if (listExp.size() > i) {
                    if (listExp.get(i) != null) {
                        agg.setEmpresa(listExp.get(i).getEmpresa());
                        agg.setCargo(listExp.get(i).getCargo().getNombrecargo());
                        agg.setTiempoLaborado(listExp.get(i).getTiempoLaborado());
                    }
                } else {
                    agg.setEmpresa("");
                    agg.setCargo("");
                    agg.setTiempoLaborado("");
                }
                listaRetornoPersonal.add(agg);

            }

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaRetornoPersonal));

            modelAndView = new ModelAndView("pdfReporteHVPersonal", parameterMap);

            return modelAndView;

        } catch (Exception e) {
            ModelAndView retorno = new ModelAndView("errores");

            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));

            retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el area de soporte técnico.\n" + e);
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            log.error("Error generando reporte", e);

            return retorno;
        }
    } 
    
    //Reporte Antropometrico Deportistas X Categoria
    @RequestMapping(value = "/ReporteAntropometrico/{nombreCategoria}/{categoria}/{tipo}",
            method = RequestMethod.GET)
    public ModelAndView doReportAntropometrico(
            @PathVariable String nombreCategoria,
            @PathVariable Long categoria,
            @PathVariable Long tipo,
            ModelAndView modelAndView,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (usuarioSession == null || usuarioSession.getId() == null) {
            ModelAndView retorno = new ModelAndView("errores");
            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
            retorno.addObject("mensaje", "Debe tener una sesion activa para mostrar este contenido.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            return retorno;
        }
        try {
            log.info("Entra a generar reporte");
//            List<Deportista> listaDep = daoDeportista.deportistaXCategoria(categoria);
            List <Antropometrico> listAnt = daoAntropometrico.AntropometricoxDeportista(tipo);
            List<Deportista> listaDep = daoDeportista.deportistaXCategoria(categoria);
            List<DTOAntropometricoxCategoria> listaDeportistaAntReport = new ArrayList<DTOAntropometricoxCategoria>();

            List<DTODeportistaxCategoria> listaDeportistaReport = new ArrayList<DTODeportistaxCategoria>();

            for (Deportista deportista : listaDep) {

                DTODeportistaxCategoria dtoDeportista = new DTODeportistaxCategoria();

                dtoDeportista.setFechaNacimiento(Formatos.fecha2(deportista.getFechaNacimiento()));
                dtoDeportista.setNombres(deportista.getNombres());
                dtoDeportista.setApellidos(deportista.getApellidos());
                dtoDeportista.setIdentificacion(deportista.getDocumento());
                
                for (Antropometrico antropometrico : listAnt) {
                    DTOAntropometricoxCategoria dtoAntropometrico = new DTOAntropometricoxCategoria();
                    
                    dtoAntropometrico.setFecha(Formatos.fecha2(antropometrico.getFecha()));
                    dtoAntropometrico.setPerabdominal(antropometrico.getPerabdominal().toString());
                    dtoAntropometrico.setPerbrazorelajado(antropometrico.getPerbrazorelajado().toString());
                    dtoAntropometrico.setPercadera(antropometrico.getPercadera().toString());
                    dtoAntropometrico.setPerpantorrilla(antropometrico.getPerpantorrilla().toString());
                    dtoAntropometrico.setPliabdominal(antropometrico.getPliabdominal().toString());
                    dtoAntropometrico.setPlisubescapular(antropometrico.getPlisubescapular().toString());
                    dtoAntropometrico.setPlisupraescapular(antropometrico.getPlisupraescapular().toString());
                    dtoAntropometrico.setPlitricipital(antropometrico.getPlitricipital().toString());
                    
                    listaDeportistaAntReport.add(dtoAntropometrico);
                    
                }

                listaDeportistaReport.add(dtoDeportista);
            }
            if (listaDeportistaReport.isEmpty()) {
                listaDeportistaReport.add(new DTODeportistaxCategoria());
            }
            if (listaDeportistaAntReport.isEmpty()) {
                listaDeportistaAntReport.add(new DTOAntropometricoxCategoria());
            }
            System.out.println("nombre cat" + nombreCategoria);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaReport));
            parameterMap.put("categoria", nombreCategoria);

            if (tipo == TIPO_XLS) {
                modelAndView = new ModelAndView("xlsReporteDeportista", parameterMap);
            } else {
                modelAndView = new ModelAndView("pdfReporteDeportista", parameterMap);
            }

            return modelAndView;

        } catch (Exception e) {
            ModelAndView retorno = new ModelAndView("errores");

            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));

            retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el area de soporte técnico.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            log.error("Error generando reporte", e);

            return retorno;
        }
    }

//            parameterMap.put("documento", dep.getDocumento());
//            parameterMap.put("nombres", dep.getNombres());
//            parameterMap.put("apellidos", dep.getApellidos());
//            parameterMap.put("fecha", ant.getFecha());
//            parameterMap.put("perbrazorelajado", ant.getPerbrazorelajado());
//            parameterMap.put("perabdominal", ant.getPerabdominal());
//            parameterMap.put("percadera", ant.getPercadera());
//            parameterMap.put("perpantorrilla", ant.getPerpantorrilla());
//            parameterMap.put("plisubescapular", ant.getPlisubescapular());
//            parameterMap.put("plitricipital", ant.getPlitricipital());
//            parameterMap.put("plisupraescapular", ant.getPlisupraescapular());
//            parameterMap.put("pliabdominal", ant.getPliabdominal());
//            parameterMap.put("porcentajeGrasa", ant.getPorcentajeGrasa());        
           
    
}
