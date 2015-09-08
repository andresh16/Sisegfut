/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.vista.rest;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.Antropometrico;
import co.com.sisegfut.client.datos.dominio.ControlTecnico;
import co.com.sisegfut.client.datos.dominio.Lesiones;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.EstudiosRealizados;
import co.com.sisegfut.client.datos.dominio.Experiencia;
import co.com.sisegfut.client.datos.dominio.LogrosDeportivos;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.datos.dominio.TestCooper;
import co.com.sisegfut.client.datos.dominio.TestKarvonen;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntropometrico;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntropometricoxDeportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOControlTecnicoxCategoria;
import co.com.sisegfut.client.datos.dominio.dto.DTOControlTecnicoxDeportista;
import co.com.sisegfut.client.datos.dominio.dto.DTODeportistaxCategoria;
import co.com.sisegfut.client.datos.dominio.dto.DTOHVDeportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOHVPersonal;
import co.com.sisegfut.client.datos.dominio.dto.DTOReporteAsistenciaXMes;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestCooperxDeportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestKarvonenxCategoria;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestKarvonenxDeportista;
import co.com.sisegfut.server.util.Formatos;
import co.com.sisegfut.server.datos.dao.DaoAntecedentesDeportivos;
import co.com.sisegfut.server.datos.dao.DaoAntropometrico;
import co.com.sisegfut.server.datos.dao.DaoControlAsistencia;
import co.com.sisegfut.server.datos.dao.DaoControlTecnico;
import co.com.sisegfut.server.datos.dao.DaoLesiones;
import co.com.sisegfut.server.datos.dao.DaoDeportista;
import co.com.sisegfut.server.datos.dao.DaoEstudiosRealizados;
import co.com.sisegfut.server.datos.dao.DaoExperiencia;
import co.com.sisegfut.server.datos.dao.DaoLogrosDeportivos;
import co.com.sisegfut.server.datos.dao.DaoPersonal;
import co.com.sisegfut.server.datos.dao.DaoTestCooper;
import co.com.sisegfut.server.datos.dao.DaoTestKarvonen;
import com.sun.xml.messaging.saaj.util.ByteInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private DaoControlAsistencia daoControlAsistencia;

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

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

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
            if (dep.getFoto() != null) {
                InputStream foto = new ByteInputStream(dep.getFoto(), dep.getFoto().length);
                parameterMap.put("foto", foto);
            }
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
            parameterMap.put("camisa", dep.getNumeroCamisa());
            parameterMap.put("nivelEducativo", dep.getNivelEducativo().getNombreNivelEducativo());
            parameterMap.put("instEducativa", dep.getInstEducativa().getNombreInstEducativa());

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

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
                } else {
                    valorMayor = listLesion.size();
                }
            } else {
                if (listLogros.size() > listLesion.size()) {
                    valorMayor = listLogros.size();
                } else {
                    valorMayor = listLesion.size();
                }
            }

            if (valorMayor == 0) {
                DTOHVDeportista vacio = new DTOHVDeportista("", "", "", "", "", "", "", "");
                listaRetorno.add(vacio);
            } else {
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
            if (per.getFoto() != null) {
                InputStream foto = new ByteInputStream(per.getFoto(), per.getFoto().length);
                parameterMap.put("foto", foto);
            }
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

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

            List<EstudiosRealizados> listEst = new ArrayList<EstudiosRealizados>();
            List<Experiencia> listExp = new ArrayList<Experiencia>();

            List<DTOHVPersonal> listaRetornoPersonal = new ArrayList<DTOHVPersonal>();

            listEst = daoEstudiosRealizados.EstudiosRealizadosxPersonal(idPersonal);
            listExp = daoExperiencia.ExperienciaxPersonal(idPersonal);

            int valorMayor = 0;
            if (listEst.size() > listExp.size()) {
                valorMayor = listEst.size();
            } else {
                valorMayor = listExp.size();
            }

            if (valorMayor == 0) {
                DTOHVPersonal vacio = new DTOHVPersonal("", "", "", "", "", "", "");
                listaRetornoPersonal.add(vacio);
            } else {

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
            List<Antropometrico> listaDeportistaAntReport = daoAntropometrico.AntropometricoxCategoria(categoria);
//            List<Deportista> listaDep = daoDeportista.deportistaXCategoria(categoria);
            List<DTOAntropometrico> listaDeportistaAnt = new ArrayList<DTOAntropometrico>();
            String cat = null;
            for (Antropometrico antropometrico : listaDeportistaAntReport) {
                DTOAntropometrico dtoAntropometrico = new DTOAntropometrico();

                dtoAntropometrico.setFecha(Formatos.fecha2(antropometrico.getFecha()));
                dtoAntropometrico.setIdentificacion(antropometrico.getIdDeportista().getDocumento());
                dtoAntropometrico.setNombreCompleto(antropometrico.getIdDeportista().getLabel());

                dtoAntropometrico.setPerabdominal(antropometrico.getPerabdominal().toString());
                dtoAntropometrico.setPerbrazorelajado(antropometrico.getPerbrazorelajado().toString());
                dtoAntropometrico.setPercadera(antropometrico.getPercadera().toString());
                dtoAntropometrico.setPerpantorrilla(antropometrico.getPerpantorrilla().toString());

                dtoAntropometrico.setPliabdominal(antropometrico.getPliabdominal().toString());
                dtoAntropometrico.setPlisubescapular(antropometrico.getPlisubescapular().toString());
                dtoAntropometrico.setPlisupraescapular(antropometrico.getPlisupraescapular().toString());
                dtoAntropometrico.setPlitricipital(antropometrico.getPlitricipital().toString());

                dtoAntropometrico.setPorcentajeGrasa(antropometrico.getPorcentajeGrasa());
                dtoAntropometrico.setPesoGraso(antropometrico.getPesoGraso());
                dtoAntropometrico.setPesoMacro(antropometrico.getPesoMacro());

                listaDeportistaAnt.add(dtoAntropometrico);

                cat = antropometrico.getIdDeportista().getCategoria().getNombrecategoria();
            }

            System.out.println("nombre cat" + cat);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaAnt));
            parameterMap.put("categoria", nombreCategoria);

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

            if (tipo == TIPO_XLS) {
                modelAndView = new ModelAndView("xlsReporteAntropometrico", parameterMap);
            } else {
                modelAndView = new ModelAndView("pdfReporteAntropometrico", parameterMap);
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

    //Reporte Test de Cooper Deportistas X Categoria
    @RequestMapping(value = "/ReporteTestCooper/{nombreCategoria}/{categoria}/{tipo}",
            method = RequestMethod.GET)
    public ModelAndView doReportTestCopper(
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
            List<TestCooper> listaDeportistaTcReport = daoTestCooper.ultimoTesCoopertRealizadoXDeportista(categoria);
            List<DTOTestCooperxDeportista> listaDeportistaTc = new ArrayList<DTOTestCooperxDeportista>();
            String cat = null;
            for (TestCooper testCooper : listaDeportistaTcReport) {
                DTOTestCooperxDeportista dtoTestCooper = new DTOTestCooperxDeportista();

                dtoTestCooper.setFecha(Formatos.fecha2(testCooper.getFecha()));
                dtoTestCooper.setIdentificacion(testCooper.getIdDeportista().getDocumento());
                dtoTestCooper.setNombreCompleto(testCooper.getIdDeportista().getLabel());

                dtoTestCooper.setDistancia(testCooper.getDistancia().toString());
                dtoTestCooper.setCondicionFisica(testCooper.getCondicionFisica());
                dtoTestCooper.setConsumOxigeno(testCooper.getConsumOxigeno());
                dtoTestCooper.setVo2max(testCooper.getVo2max());
                dtoTestCooper.setVelocidad(testCooper.getVelocidad());
                listaDeportistaTc.add(dtoTestCooper);

                cat = testCooper.getIdDeportista().getCategoria().getNombrecategoria();
            }
            System.out.println("nombre cat" + cat);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaTc));
            parameterMap.put("categoria", nombreCategoria);

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

            if (tipo == TIPO_XLS) {
                modelAndView = new ModelAndView("xlsReporteTestCooper", parameterMap);
            } else {
                modelAndView = new ModelAndView("pdfReporteTestCooper", parameterMap);
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

    //Reporte Test de Karvonen Deportistas X Categoria
    @RequestMapping(value = "/ReporteTestKarvonen/{nombreCategoria}/{categoria}/{tipo}",
            method = RequestMethod.GET)
    public ModelAndView doReportTestKarvonen(
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
            List<TestKarvonen> listaDeportistaTkReport = daoTestKarvonen.ultimoTesKartRealizadoXDeportista(categoria);
            List<DTOTestKarvonenxCategoria> listaDeportistaTk = new ArrayList<DTOTestKarvonenxCategoria>();
            String cat = null;
            for (TestKarvonen testKarvonen : listaDeportistaTkReport) {
                DTOTestKarvonenxCategoria dtoTestKarvonen = new DTOTestKarvonenxCategoria();

                dtoTestKarvonen.setFecha(Formatos.fecha2(testKarvonen.getFecha()));
                dtoTestKarvonen.setIdentificacion(testKarvonen.getIdDeportista().getDocumento());
                dtoTestKarvonen.setNombreCompleto(testKarvonen.getIdDeportista().getLabel());

                dtoTestKarvonen.setFcReposo(testKarvonen.getFcReposo().toString());
                dtoTestKarvonen.setPorcentaje(testKarvonen.getPorcentaje().toString());
                dtoTestKarvonen.setResKarvonen(testKarvonen.getResultadoKarvonen());
                listaDeportistaTk.add(dtoTestKarvonen);

                cat = testKarvonen.getIdDeportista().getCategoria().getNombrecategoria();
            }
            System.out.println("nombre cat" + cat);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaTk));
            parameterMap.put("categoria", nombreCategoria);

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

            if (tipo == TIPO_XLS) {
                modelAndView = new ModelAndView("xlsReporteTestKarvonen", parameterMap);
            } else {
                modelAndView = new ModelAndView("pdfReporteTestKarvonen", parameterMap);
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

    //Reporte Test de Cooper Deportistas X Categoria
    @RequestMapping(value = "/ReporteControlTecnico/{nombreCategoria}/{categoria}/{tipo}",
            method = RequestMethod.GET)
    public ModelAndView doReportControlTecnico(
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
            List<ControlTecnico> listaDeportistaCtReport = daoControlTecnico.ultimoCtrlTecnicotRealizadoXDeportista(categoria);
            List<DTOControlTecnicoxCategoria> listaDeportistaCt = new ArrayList<DTOControlTecnicoxCategoria>();
            String cat = null;
            for (ControlTecnico controlTecnico : listaDeportistaCtReport) {
                DTOControlTecnicoxCategoria dtoControlTecnico = new DTOControlTecnicoxCategoria();

                dtoControlTecnico.setFecha(Formatos.fecha2(controlTecnico.getFecha()));
                dtoControlTecnico.setIdentificacion(controlTecnico.getIdDeportista().getDocumento());
                dtoControlTecnico.setNombreCompleto(controlTecnico.getIdDeportista().getLabel());

                dtoControlTecnico.setNombrerecepcion30seg(controlTecnico.getNombrerecepcion30seg().toString());
                dtoControlTecnico.setNombreprecisionpase15seg(controlTecnico.getNombreprecisionpase15seg().toString());
                dtoControlTecnico.setNombreprecisiondisparoempeine(controlTecnico.getNombreprecisiondisparoempeine().toString());
                dtoControlTecnico.setNombrepotenciaremate(controlTecnico.getNombrepotenciaremate().toString());
                dtoControlTecnico.setNombrecontrolbalon50seg(controlTecnico.getNombrecontrolbalon50seg().toString());
                dtoControlTecnico.setNombreconduccion(controlTecnico.getNombreconduccion().toString());
                dtoControlTecnico.setNombrecabeceodefensivo(controlTecnico.getNombrecabeceodefensivo().toString());
                dtoControlTecnico.setNombrecabeceoofensivo(controlTecnico.getNombrecabeceoofensivo().toString());
                dtoControlTecnico.setNombreaceleracion(controlTecnico.getNombreaceleracion().toString());
                listaDeportistaCt.add(dtoControlTecnico);

                cat = controlTecnico.getIdDeportista().getCategoria().getNombrecategoria();
            }
            System.out.println("nombre cat" + cat);

            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaCt));
            parameterMap.put("categoria", nombreCategoria);

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

            if (tipo == TIPO_XLS) {
                modelAndView = new ModelAndView("xlsReporteControlTecnico", parameterMap);
            } else {
                modelAndView = new ModelAndView("pdfReporteControlTecnico", parameterMap);
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

    //Reporte Asistencia X Mes
    @RequestMapping(value = "/ReporteAsistenciaxMes/{anio}/{idmes}/{categoria}/{nombreCategoria}/{mes}/{tipo}",
            method = RequestMethod.GET)
    public ModelAndView doReportAsistenciaMes(
            @PathVariable Integer anio,
            @PathVariable Integer idmes,
            @PathVariable Long categoria,
            @PathVariable String nombreCategoria,
            @PathVariable String mes,
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
            log.info("Entra a generar reporte asistencia");
            List<DTOReporteAsistenciaXMes> reporteAsistencia = daoControlAsistencia.obtenerReporteAsistenciaxMes(idmes, anio, categoria);
            Map<String, Object> parameterMap = new HashMap<String, Object>();

            parameterMap.put("datasource", new JRBeanCollectionDataSource(reporteAsistencia));
            parameterMap.put("categoria", nombreCategoria);
            parameterMap.put("mes", mes);
            parameterMap.put("anio", anio.toString());

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("imagenBanner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

            if (tipo == TIPO_XLS) {
                modelAndView = new ModelAndView("xlsReporteAsistencia", parameterMap);
            } else {
                modelAndView = new ModelAndView("pdfReporteAsistencia", parameterMap);
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
    
    @RequestMapping(value = "/HistoricoTestCooper/{idDeportista}",
            method = RequestMethod.GET)
    public ModelAndView doReportHistoricoTestCopper(
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
            log.info("Entra a generar reporte");
            Deportista dep = daoDeportista.getById(idDeportista);
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            
                       
            if (dep.getFoto() != null) {
                InputStream foto = new ByteInputStream(dep.getFoto(), dep.getFoto().length);
                parameterMap.put("foto", foto);
            }
            parameterMap.put("documento", dep.getDocumento());
            parameterMap.put("nombreCompleto", dep.getNombreCompleto());
            
            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);
            
            List<TestCooper> listaDeportistaTcReport = daoTestCooper.TestCooperXDeportista(idDeportista);
            List<DTOTestCooperxDeportista> listaDeportistaTc = new ArrayList<DTOTestCooperxDeportista>();
               //////
            
            if(listaDeportistaTcReport.isEmpty() || listaDeportistaTcReport.size()==0){
               listaDeportistaTc.add(new DTOTestCooperxDeportista("","","","","",""));
            }else{
                for (TestCooper testCooper : listaDeportistaTcReport) {
                    DTOTestCooperxDeportista dtoTestCooper = new DTOTestCooperxDeportista();

                    dtoTestCooper.setFecha(Formatos.fechaHoraMilitar(testCooper.getFecha()));

                    dtoTestCooper.setDistancia(testCooper.getDistancia().toString());
                    dtoTestCooper.setCondicionFisica(testCooper.getCondicionFisica());
                    dtoTestCooper.setConsumOxigeno(testCooper.getConsumOxigeno());
                    dtoTestCooper.setVo2max(testCooper.getVo2max());
                    dtoTestCooper.setVelocidad(testCooper.getVelocidad());
                    listaDeportistaTc.add(dtoTestCooper);

                }
            }
//           
            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaTc));

            
            modelAndView = new ModelAndView("pdfHistoricoTestCooper", parameterMap);
            
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
    
    @RequestMapping(value = "/HistoricoTestKarvonen/{idDeportista}",
            method = RequestMethod.GET)
    public ModelAndView doReportHistoricoTestKarvonen(
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
            log.info("Entra a generar reporte");
            Deportista dep = daoDeportista.getById(idDeportista);
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();            
                       
            if (dep.getFoto() != null) {
                InputStream foto = new ByteInputStream(dep.getFoto(), dep.getFoto().length);
                parameterMap.put("foto", foto);
            }
            parameterMap.put("nombreCompleto", dep.getNombreCompleto());
            
            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);
            
            List<TestKarvonen> listaDeportistaTkReport = daoTestKarvonen.TestKarvonenXDeportista(idDeportista);
            List<DTOTestKarvonenxDeportista> listaDeportistaTk = new ArrayList<DTOTestKarvonenxDeportista>();
            
            if(listaDeportistaTkReport.isEmpty() || listaDeportistaTkReport.size()==0){
               listaDeportistaTk.add(new DTOTestKarvonenxDeportista("","","",""));
            }else{
                for (TestKarvonen testkarvonen : listaDeportistaTkReport) {
                    DTOTestKarvonenxDeportista dtoTestKarvonen = new DTOTestKarvonenxDeportista();

                    dtoTestKarvonen.setFecha(Formatos.fechaHoraMilitar(testkarvonen.getFecha()));

                    dtoTestKarvonen.setFcReposo(testkarvonen.getFcReposo().toString());
                    dtoTestKarvonen.setPorcentaje(testkarvonen.getPorcentaje().toString());
                    dtoTestKarvonen.setResKarvonen(testkarvonen.getResultadoKarvonen());
                    listaDeportistaTk.add(dtoTestKarvonen);

                }
            }
//           
            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaTk));
            
            modelAndView = new ModelAndView("pdfHistoricoTestKarvonen", parameterMap);
            
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
    
    @RequestMapping(value = "/HistoricoAntropometrico/{idDeportista}",
            method = RequestMethod.GET)
    public ModelAndView doReportHistoricoAntropometrico(
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
            log.info("Entra a generar reporte");
            Deportista dep = daoDeportista.getById(idDeportista);
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            
                       
            if (dep.getFoto() != null) {
                InputStream foto = new ByteInputStream(dep.getFoto(), dep.getFoto().length);
                parameterMap.put("foto", foto);
            }
            parameterMap.put("documento", dep.getDocumento());
            parameterMap.put("nombreCompleto", dep.getNombreCompleto());
            
            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);
            
            List<Antropometrico> listaDeportistaAntReport = daoAntropometrico.AntropometricoxDeportista(idDeportista);
            List<DTOAntropometricoxDeportista> listaDeportistaAnt = new ArrayList<DTOAntropometricoxDeportista>();
               //////
            
            if(listaDeportistaAntReport.isEmpty() || listaDeportistaAntReport.size()==0){
               listaDeportistaAnt.add(new DTOAntropometricoxDeportista("","","","","","","","","","","",""));
            }else{
                for (Antropometrico antropometrico : listaDeportistaAntReport) {
                    DTOAntropometricoxDeportista dtoAntropometrico = new DTOAntropometricoxDeportista();

                    dtoAntropometrico.setFecha(Formatos.fechaHoraMilitar(antropometrico.getFecha()));

                    dtoAntropometrico.setPerabdominal(antropometrico.getPerabdominal().toString());
                    dtoAntropometrico.setPerbrazorelajado(antropometrico.getPerbrazorelajado().toString());
                    dtoAntropometrico.setPercadera(antropometrico.getPercadera().toString());
                    dtoAntropometrico.setPerpantorrilla(antropometrico.getPerpantorrilla().toString());
                    dtoAntropometrico.setPliabdominal(antropometrico.getPliabdominal().toString());
                    dtoAntropometrico.setPlisubescapular(antropometrico.getPlisubescapular().toString());
                    dtoAntropometrico.setPlisupraescapular(antropometrico.getPlisupraescapular().toString());
                    dtoAntropometrico.setPlitricipital(antropometrico.getPlitricipital().toString());
                    dtoAntropometrico.setPesoGraso(antropometrico.getPesoGraso());
                    dtoAntropometrico.setPesoMacro(antropometrico.getPesoMacro());
                    dtoAntropometrico.setPorcentajeGrasa(antropometrico.getPorcentajeGrasa());                                        
                    listaDeportistaAnt.add(dtoAntropometrico);

                }
            }
//           
            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaAnt));
            
            modelAndView = new ModelAndView("pdfHistoricoAntropometrico", parameterMap);
            
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
    
    @RequestMapping(value = "/HistoricoControlTecnico/{idDeportista}",
            method = RequestMethod.GET)
    public ModelAndView doReportHistoricoControlTecnico(
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
            log.info("Entra a generar reporte");
            Deportista dep = daoDeportista.getById(idDeportista);
            
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            
                       
            if (dep.getFoto() != null) {
                InputStream foto = new ByteInputStream(dep.getFoto(), dep.getFoto().length);
                parameterMap.put("foto", foto);
            }
            parameterMap.put("nombreCompleto", dep.getNombreCompleto());
            
            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);
            
            List<ControlTecnico> listaDeportistaCTReport = daoControlTecnico.CtrlTecXDeportista(idDeportista);
            List<DTOControlTecnicoxDeportista> listaDeportistaTk = new ArrayList<DTOControlTecnicoxDeportista>();
            
            if(listaDeportistaCTReport.isEmpty() || listaDeportistaCTReport.size()==0){
               listaDeportistaTk.add(new DTOControlTecnicoxDeportista("","","","","","","","","",""));
            }else{
                for (ControlTecnico controlTecnico : listaDeportistaCTReport) {
                    DTOControlTecnicoxDeportista dtoControlTecnico = new DTOControlTecnicoxDeportista();

                    dtoControlTecnico.setFecha(Formatos.fechaHoraMilitar(controlTecnico.getFecha()));

                    dtoControlTecnico.setNombreaceleracion(controlTecnico.getNombreaceleracion().toString());
                    dtoControlTecnico.setNombrecabeceodefensivo(controlTecnico.getNombrecabeceodefensivo().toString());
                    dtoControlTecnico.setNombrecabeceoofensivo(controlTecnico.getNombrecabeceoofensivo().toString());
                    dtoControlTecnico.setNombreconduccion(controlTecnico.getNombreconduccion().toString());
                    dtoControlTecnico.setNombrecontrolbalon50seg(controlTecnico.getNombrecontrolbalon50seg().toString());
                    dtoControlTecnico.setNombrepotenciaremate(controlTecnico.getNombrepotenciaremate().toString());
                    dtoControlTecnico.setNombreprecisiondisparoempeine(controlTecnico.getNombreprecisiondisparoempeine().toString());
                    dtoControlTecnico.setNombreprecisionpase15seg(controlTecnico.getNombreprecisionpase15seg().toString());
                    dtoControlTecnico.setNombrerecepcion30seg(controlTecnico.getNombrerecepcion30seg().toString());
                    listaDeportistaTk.add(dtoControlTecnico);

                }
            }
//           
            parameterMap.put("datasource", new JRBeanCollectionDataSource(listaDeportistaTk));
            
            modelAndView = new ModelAndView("pdfHistoricoControlTecnico", parameterMap);
            
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
    @RequestMapping(value = "/ReporteDeportistaPosicion/{nombreCategoria}/{categoria}/{tipo}",
            method = RequestMethod.GET)
    public ModelAndView doReportDeportistaPosicion(
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
            List<Deportista> listaDep = daoDeportista.deportistaPosicionXCategoria(categoria);

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

            java.net.URL banner = this.getClass().getResource("/imagenes/bannerPoli.jpg");
            parameterMap.put("banner", banner);
            java.net.URL logo = this.getClass().getResource("/imagenes/logo.png");
            parameterMap.put("logo", logo);

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
}
