package co.com.sisegfut.server.vista.rest;

import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.server.datos.dao.DaoDeportista;
import co.com.sisegfut.server.datos.dao.DaoPersonal;
import co.com.sisegfut.server.datos.dao.DaoUsuario;
import co.com.sisegfut.server.util.Formatos;
import co.com.sisegfut.server.util.ImageUtil;
import co.com.sisegfut.server.util.UploadItem;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import mundial.client.datos.dominio.ExtendedDisc;
//import mundial.client.datos.dominio.Usuarios;
//import mundial.server.datos.dao.DaoExtendedDisc;
//import mundial.server.datos.dao.DaoUsuarios;
//import mundial.server.util.ImageUtil;
//import mundial.server.util.Propiedades;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 
 */
@Controller
//@RequestMapping("/foto")
public class CargaFotoController {

    @Autowired
    private DaoUsuario daoUsuario;
    @Autowired
    private DaoDeportista daoDeportista;
    @Autowired
    private DaoPersonal daoPersonal;
    @Autowired
    private Usuarios usuarioSession;
//    private DaoExtendedDisc daoExtendedDisc;
    private static final Logger log = Logger.getLogger(CargaFotoController.class);
//    Usuarios usuarioSession = new Usuarios();
//    Long idDeportista;
    
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/foto/cargar/{idDeportista}")
    public ModelAndView crearFoto(@PathVariable String idDeportista, UploadItem uploadItem, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {

//        if (usuarioSession == null || usuarioSession.getId() == null) {
//            ModelAndView retorno = new ModelAndView("respuestaUpload");
//            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
//            retorno.addObject("mensaje", "Debe tener una sesion activa para mostrar este contenido.");
//            response.setHeader("Pragma", "public");
//            response.setHeader("Cache-Control", "max-age=0");
//
//            return retorno;
//        } //Ya ?
        System.out.println("aaa"+idDeportista+request);
        try {
            if (result.hasErrors()) {
                String errores = "";
                for (ObjectError error : result.getAllErrors()) {
                    errores += error.toString();
                    log.warn("Error: " + error.getCode() + " - " + error.getDefaultMessage());
                }

                throw new Exception(errores);
            } else {
                try {
                    if (!(request instanceof MultipartHttpServletRequest)) {
                        ModelAndView retorno = new ModelAndView("errorJson");
                        retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
                        retorno.addObject("mensaje", "unexpected.noseatingplan");
                        response.setHeader("Pragma", "public");
                        response.setHeader("Cache-Control", "max-age=0");
                        return retorno;
                    }

                    Deportista deportista = null;
//                    Usuarios usuario = null;
                    Long id = Long.parseLong(idDeportista);
                    try {
                       deportista = daoDeportista.getById(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    deportista = daoDeportista.getById(id);
                    
//                    if (id < 1) {
//                        id = null;
//                        deportista = new Deportista();
//                    } else {
//                        deportista = daoDeportista.getById(id);
//                    }
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

                    MultipartFile multipartFile = multipartRequest.getFile("foto");

//                    Empresa empresa = daoEmpresa.getById(idUsuario);

                    //Se crea un BufferedImage a partir del arreglo de bits del uploadItem
                    BufferedImage img = ImageIO.read(multipartFile.getInputStream());

                    if (img != null) {
                        //BufferedImage img = ImageIO.read(new ByteArrayInputStream(uploadItem.getFileData().getBytes()));
                        //Obtenemos las nuevas medidas de la imagen y redimensionamos
                        int largo = Math.round(img.getHeight() * 150 / img.getWidth());
                        img = ImageUtil.resize(img, 150, largo);
                        //Luego creamos el arreglo de bytes del BufferedImage
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        //log.warn("CT: "+multipartFile.getContentType());
                            String formatoImagen = multipartFile.getContentType().substring(6);
                        //Si el formato es bmp (Windows)
                        if (formatoImagen.equals("x-ms-bmp")) {
                            formatoImagen = "bmp";
                        }
                        ImageIO.write(img, formatoImagen, baos);
                        baos.flush();
                        byte[] resultImageAsRawBytes = baos.toByteArray();

                        if (resultImageAsRawBytes == null || resultImageAsRawBytes.length <= 0) {
                            log.warn("No se pudo redimensionar la imagen, se deja del tamaño original. (IE problem)");
                            resultImageAsRawBytes = multipartFile.getBytes();
                        }
                        //Asignamos al usuario el arreglo de bytes obtenidos de la BufferedImage
                        deportista.setFoto(resultImageAsRawBytes);
                        deportista.setContentTypeFoto(multipartFile.getContentType());
//                        usuario.setFoto(resultImageAsRawBytes);
//                        usuario.setContentTypeFoto(multipartFile.getContentType());
                    }

                  


//                     daoUsuario.guardar(usuario);
                    daoDeportista.guardar(deportista);


                    ModelAndView retorno = new ModelAndView("exitoJson");
                    retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
                    retorno.addObject("mensaje", "Se procesa con exito el archivo.");
                    response.setHeader("Pragma", "public");
                    response.setHeader("Cache-Control", "max-age=0");
                    return retorno;
                } catch (IOException | DataAccessException e) {
                    ModelAndView retorno = new ModelAndView("errorJson");
                    retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
                    retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el Área de soporte técnico.");
                    response.setHeader("Pragma", "public");
                    response.setHeader("Cache-Control", "max-age=0");

                    log.error("Error cargando archivo", e);

                    return retorno;
                }
            }
        } catch (Exception e) {
            ModelAndView retorno = new ModelAndView("respuestaUpload");
            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
            retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el Ã¡rea de soporte tÃ©cnico.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            log.error("Error cargando archivo", e);

                return retorno;
        }
    }
    
        @RequestMapping(method = RequestMethod.GET, value = "/foto/descargar/{idUsuario}")
    public String descargarFoto(@PathVariable String idUsuario,
            ServletResponse servletResponse, ServletRequest servletRequest)
            throws IOException, Exception {
        try {
            OutputStream out = servletResponse.getOutputStream();
            
            Deportista deportista =null;
            if(idUsuario!=null){
             deportista=daoDeportista.getById(Long.parseLong(idUsuario));           
            }

            byte[] foto = deportista == null ? null : deportista.getFoto();
            if (deportista != null && foto != null) {
                servletResponse.setContentType(deportista.getContentTypeFoto());
                servletResponse.setContentLength(foto.length);
                out.write(foto, 0, foto.length);
            } else {
                return "redirect:../../../imagenes/fotoNoDisponible.jpg";
            }
            out.close();
            return "exito";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error descargando.", e);
            return "redirect:../../../imagenes/fotoNoDisponible.jpg";
        }
    }
    
   @RequestMapping(method = RequestMethod.POST, value = "/foto/cargarp/{idPersonal}")
    public ModelAndView crearFotoPersonal(@PathVariable String idPersonal, UploadItem uploadItem, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {

//        if (usuarioSession == null || usuarioSession.getId() == null) {
//            ModelAndView retorno = new ModelAndView("respuestaUpload");
//            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
//            retorno.addObject("mensaje", "Debe tener una sesion activa para mostrar este contenido.");
//            response.setHeader("Pragma", "public");
//            response.setHeader("Cache-Control", "max-age=0");
//
//            return retorno;
//        } //Ya ?
        System.out.println("aaa"+idPersonal+request);
        try {
            if (result.hasErrors()) {
                String errores = "";
                for (ObjectError error : result.getAllErrors()) {
                    errores += error.toString();
                    log.warn("Error: " + error.getCode() + " - " + error.getDefaultMessage());
                }

                throw new Exception(errores);
            } else {
                try {
                    if (!(request instanceof MultipartHttpServletRequest)) {
                        ModelAndView retorno = new ModelAndView("errorJson");
                        retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
                        retorno.addObject("mensaje", "unexpected.noseatingplan");
                        response.setHeader("Pragma", "public");
                        response.setHeader("Cache-Control", "max-age=0");
                        return retorno;
                    }

                    Personal personal = null;
//                    Usuarios usuario = null;
                    Long id = Long.parseLong(idPersonal);
                    try {
                       personal = daoPersonal.getById(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    deportista = daoDeportista.getById(id);
                    
//                    if (id < 1) {
//                        id = null;
//                        deportista = new Deportista();
//                    } else {
//                        deportista = daoDeportista.getById(id);
//                    }
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

                    MultipartFile multipartFile = multipartRequest.getFile("foto");

//                    Empresa empresa = daoEmpresa.getById(idUsuario);

                    //Se crea un BufferedImage a partir del arreglo de bits del uploadItem
                    BufferedImage img = ImageIO.read(multipartFile.getInputStream());

                    if (img != null) {
                        //BufferedImage img = ImageIO.read(new ByteArrayInputStream(uploadItem.getFileData().getBytes()));
                        //Obtenemos las nuevas medidas de la imagen y redimensionamos
                        int largo = Math.round(img.getHeight() * 150 / img.getWidth());
                        img = ImageUtil.resize(img, 150, largo);
                        //Luego creamos el arreglo de bytes del BufferedImage
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        //log.warn("CT: "+multipartFile.getContentType());
                            String formatoImagen = multipartFile.getContentType().substring(6);
                        //Si el formato es bmp (Windows)
                        if (formatoImagen.equals("x-ms-bmp")) {
                            formatoImagen = "bmp";
                        }
                        ImageIO.write(img, formatoImagen, baos);
                        baos.flush();
                        byte[] resultImageAsRawBytes = baos.toByteArray();

                        if (resultImageAsRawBytes == null || resultImageAsRawBytes.length <= 0) {
                            log.warn("No se pudo redimensionar la imagen, se deja del tamaño original. (IE problem)");
                            resultImageAsRawBytes = multipartFile.getBytes();
                        }
                        //Asignamos al usuario el arreglo de bytes obtenidos de la BufferedImage
                        personal.setFoto(resultImageAsRawBytes);
                        personal.setContentTypeFoto(multipartFile.getContentType());
//                        usuario.setFoto(resultImageAsRawBytes);
//                        usuario.setContentTypeFoto(multipartFile.getContentType());
                    }

                  
//                     daoUsuario.guardar(usuario);
                    daoPersonal.guardar(personal);


                    ModelAndView retorno = new ModelAndView("exitoJson");
                    retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
                    retorno.addObject("mensaje", "Se procesa con exito el archivo.");
                    response.setHeader("Pragma", "public");
                    response.setHeader("Cache-Control", "max-age=0");
                    return retorno;
                } catch (IOException | DataAccessException e) {
                    ModelAndView retorno = new ModelAndView("errorJson");
                    retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
                    retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el Área de soporte técnico.");
                    response.setHeader("Pragma", "public");
                    response.setHeader("Cache-Control", "max-age=0");

                    log.error("Error cargando archivo", e);

                    return retorno;
                }
            }
        } catch (Exception e) {
            ModelAndView retorno = new ModelAndView("respuestaUpload");
            retorno.addObject("fecha_actual", Formatos.fechaHora(new Date()));
            retorno.addObject("mensaje", "Ha ocurrido un error inesperado, por favor comuniquese con el Ã¡rea de soporte tÃ©cnico.");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");

            log.error("Error cargando archivo", e);

                return retorno;
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/foto/descargarp/{idPersonal}")
    public String descargarFotoPersonal(@PathVariable String idPersonal,
            ServletResponse servletResponse, ServletRequest servletRequest)
            throws IOException, Exception {
        try {
            OutputStream out = servletResponse.getOutputStream();
            
            Personal personal =null;
            if(idPersonal!=null){
             personal=daoPersonal.getById(Long.parseLong(idPersonal));           
            }

            byte[] foto = personal == null ? null : personal.getFoto();
            if (personal != null && foto != null) {
                servletResponse.setContentType(personal.getContentTypeFoto());
                servletResponse.setContentLength(foto.length);
                out.write(foto, 0, foto.length);
            } else {
                return "redirect:../../../imagenes/fotoNoDisponible.jpg";
            }
            out.close();
            return "exito";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error descargando.", e);
            return "redirect:../../../imagenes/fotoNoDisponible.jpg";
        }
    }
    
    @RequestMapping(value = "/foto/crear/{idUsuario}")
    public ModelAndView crearFoto(UploadItem uploadItem, BindingResult result, @PathVariable int idUsuario) {
        ModelAndView retorno = new ModelAndView();

        int fotoHeight;
        int fotoWidth;
      
        try {
            if (result.hasErrors()) {
                log.warn("warnin cargando foto" + result.toString());
                String errores = "";
                for (ObjectError error : result.getAllErrors()) {
                    errores += error.toString();
                    log.info("Error: " + error.getCode() + " - " + error.getDefaultMessage());
                }
                retorno.setViewName("errorJson");
                retorno.addObject("errors", errores);
            } else {
                try {
                    Usuarios usuario = daoUsuario.getById(idUsuario);
                    System.out.println("aa"+usuario);
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(uploadItem.getFileData().getBytes()));
                    fotoHeight = img.getHeight();
                    fotoWidth = img.getWidth();

                    //Obtenemos las nuevas medidas de la imagen y redimensionamos
                    int largo = Math.round(img.getHeight() * 340 / img.getWidth());
                    img = ImageUtil.resize(img, 340, largo);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    String formatoImagen = uploadItem.getFileData().getContentType().substring(6);
                    if (formatoImagen.equals("x-ms-bmp")) {
                        formatoImagen = "bmp";
                    }
                    ImageIO.write(img, formatoImagen, baos);
                    baos.flush();
                    byte[] resultImageAsRawBytes = baos.toByteArray();
                    if (resultImageAsRawBytes.length > 0) {
                        //Asignamos al usuario el arreglo de bytes obtenidos de la BufferedImage
                        usuario.setFoto(resultImageAsRawBytes);
                    } else {
                        usuario.setFoto(uploadItem.getFileData().getBytes());
                    }
                    usuario.setContentTypeFoto(uploadItem.getFileData().getContentType());                    
                    daoUsuario.guardar(usuario);
                    
                    if (fotoHeight > 340 || fotoWidth > 340 || 
                            fotoHeight < 100 || fotoWidth < 100) {
                        log.error("error dimensiones imagen");
                        retorno.setViewName("errorXML");
                    }else{
                        retorno.setViewName("exitoJson");
                        retorno.addObject("success", true);
                    }                    
                } catch (Exception e) {
                    log.error("error cargando imagen", e);
                    e.printStackTrace();
                    retorno.setViewName("errorJson");
                    retorno.addObject("errors", e.toString());
                }
                log.info("Finaliza cargado de imagen.");
            }
        } catch (Exception e) {
            log.error("error cargando imagen 22", e);
            e.printStackTrace();
            retorno.setViewName("errorJson");
            retorno.addObject("success", false);
            retorno.addObject("errors", e.toString());
        }        
        return retorno;
    }
//    @RequestMapping(value = "/perfil/crear/{idUsuario}")
//    public ModelAndView crearFotoPerfil(UploadItem uploadItem, BindingResult result, @PathVariable int idUsuario) {
//        ModelAndView retorno = new ModelAndView();
//        
//        /**
//         * 0002319: hoja de vida - adicionar la pestaña de extended disc.
//         * Se crea el controller para cargar la foto de perfil.
//         * @jlosorio 15-07-2013
//         */
//
//        int fotoHeight;
//        int fotoWidth;
//        try {
//            if (result.hasErrors()) {
//                log.warn("warnin cargando foto" + result.toString());
//                String errores = "";
//                for (ObjectError error : result.getAllErrors()) {
//                    errores += error.toString();
//                    log.info("Error: " + error.getCode() + " - " + error.getDefaultMessage());
//                }
//                retorno.setViewName("errorJson");
//                retorno.addObject("errors", errores);
//            } else {
//                try {
//                    ExtendedDisc ed = daoExtendedDisc.getByIdUsuario(idUsuario);
//                    if(ed == null && idUsuario > 0){
//                        ed = new ExtendedDisc();
//                        ed.setUsuario(new Usuarios(idUsuario));
//                    }
//                    
//                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(uploadItem.getFileData().getBytes()));
//                    fotoHeight = img.getHeight();
//                    fotoWidth = img.getWidth();
//
//                    //Obtenemos las nuevas medidas de la imagen y redimensionamos
//                    int largo = Math.round(img.getHeight() * 340 / img.getWidth());
//                    img = ImageUtil.resize(img, 340, largo);
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    String formatoImagen = uploadItem.getFileData().getContentType().substring(6);
//                    if (formatoImagen.equals("x-ms-bmp")) {
//                        formatoImagen = "bmp";
//                    }
//                    ImageIO.write(img, formatoImagen, baos);
//                    baos.flush();
//                    byte[] resultImageAsRawBytes = baos.toByteArray();
//                    if (resultImageAsRawBytes.length > 0) {
//                        //Asignamos al usuario el arreglo de bytes obtenidos de la BufferedImage
//                        ed.setFotoPerfil(resultImageAsRawBytes);
//                    } else {
//                        ed.setFotoPerfil(uploadItem.getFileData().getBytes());
//                    }
//                    ed.setContentTypeFotoPerfil(uploadItem.getFileData().getContentType());                    
//                    daoExtendedDisc.guardar(ed);
//                    
////                    if (fotoHeight > 340 || fotoWidth > 340 || 
////                            fotoHeight < 100 || fotoWidth < 100) {
////                        log.error("error dimensiones imagen");
////                        retorno.setViewName("errorXML");
////                    }else{
//                        retorno.setViewName("exitoJson");
//                        retorno.addObject("success", true);
////                    }                    
//                } catch (Exception e) {
//                    log.error("error cargando imagen", e);
//                    e.printStackTrace();
//                    retorno.setViewName("errorJson");
//                    retorno.addObject("errors", e.toString());
//                }
//                log.info("Finaliza cargado de imagen.");
//            }
//        } catch (Exception e) {
//            log.error("error cargando imagen 22", e);
//            e.printStackTrace();
//            retorno.setViewName("errorJson");
//            retorno.addObject("success", false);
//            retorno.addObject("errors", e.toString());
//        }        
//        return retorno;
//    }
//    
//    @RequestMapping(value = "/estilos/crear/{idUsuario}")
//    public ModelAndView crearEstilos(UploadItem uploadItem, BindingResult result, @PathVariable int idUsuario) {
//        ModelAndView retorno = new ModelAndView();
//
//        int fotoHeight;
//        int fotoWidth;
//        try {
//            if (result.hasErrors()) {
//                log.warn("warnin cargando foto" + result.toString());
//                String errores = "";
//                for (ObjectError error : result.getAllErrors()) {
//                    errores += error.toString();
//                    log.info("Error: " + error.getCode() + " - " + error.getDefaultMessage());
//                }
//                retorno.setViewName("errorJson");
//                retorno.addObject("errors", errores);
//            } else {
//                try {
//                    ExtendedDisc ed = daoExtendedDisc.getByIdUsuario(idUsuario);
//                    if(ed == null && idUsuario > 0){
//                        ed = new ExtendedDisc();
//                         ed.setUsuario(new Usuarios(idUsuario));
//                    }
//                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(uploadItem.getFileData().getBytes()));
//                    fotoHeight = img.getHeight();
//                    fotoWidth = img.getWidth();
//
//                    //Obtenemos las nuevas medidas de la imagen y redimensionamos
//                    int largo = Math.round(img.getHeight() * 340 / img.getWidth());
//                    img = ImageUtil.resize(img, 340, largo);
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    String formatoImagen = uploadItem.getFileData().getContentType().substring(6);
//                    if (formatoImagen.equals("x-ms-bmp")) {
//                        formatoImagen = "bmp";
//                    }
//                    ImageIO.write(img, formatoImagen, baos);
//                    baos.flush();
//                    byte[] resultImageAsRawBytes = baos.toByteArray();
//                    if (resultImageAsRawBytes.length > 0) {
//                        //Asignamos al usuario el arreglo de bytes obtenidos de la BufferedImage
//                        ed.setFotoEstilos(resultImageAsRawBytes);
//                    } else {
//                        ed.setFotoEstilos(uploadItem.getFileData().getBytes());
//                    }
//                    ed.setContentTypeFotoEstilos(uploadItem.getFileData().getContentType());                    
//                    daoExtendedDisc.guardar(ed);
//                    
////                    if (fotoHeight > 340 || fotoWidth > 340 || 
////                            fotoHeight < 100 || fotoWidth < 100) {
////                        log.error("error dimensiones imagen");
////                        retorno.setViewName("errorXML");
////                    }else{
//                        retorno.setViewName("exitoJson");
//                        retorno.addObject("success", true);
////                    }                    
//                } catch (Exception e) {
//                    log.error("error cargando imagen", e);
//                    e.printStackTrace();
//                    retorno.setViewName("errorJson");
//                    retorno.addObject("errors", e.toString());
//                }
//                log.info("Finaliza cargado de imagen.");
//            }
//        } catch (Exception e) {
//            log.error("error cargando imagen 22", e);
//            e.printStackTrace();
//            retorno.setViewName("errorJson");
//            retorno.addObject("success", false);
//            retorno.addObject("errors", e.toString());
//        }        
//        return retorno;
//    }
    /**
     * json que carga el informe completo del usuario a el servidor
     * @param uploadItem
     * @param result
     * @param idUsuario
     * @return 
     */
//    @RequestMapping(value = "/informe/crear/{idUsuario}")
//    public ModelAndView cargarInforme(UploadItem uploadItem, BindingResult result, 
//    @PathVariable int idUsuario) {
//        ModelAndView retorno = new ModelAndView();
//        
//        /**
//         * 0002349: hoja de vida - extended disc adicionar nuevos cambios y
//         * carga de pdf. 
//         * Se crea el json "cargarInforme" para cargar el informe des local a
//         * el servidor y lo guarda en la ruta parametrizada.
//         * @jlosorio 18-07-2013
//         */
//        
//        
//        try {
//            if (result.hasErrors()) {
//
//                String errores = "";
//                for (ObjectError error : result.getAllErrors()) {
//                    errores += error.toString();
//                    log.info("Error: " + error.getCode() + " - " + error.getDefaultMessage());
//                }
//                retorno.setViewName("errorJson");
//                retorno.addObject("errors", errores);
//            } else {
//                try {
//                    
//                    /**
//                     * verificamos la ruta final del archivo y la cargamos al igual 
//                     * que el usuario, y escribimos el archivo con el OutputStream
//                     */
//                    Usuarios usuario = new Usuarios(idUsuario);                    
//                    verificarRuta(Propiedades.getValue("ruta.informe.extended.disc"));
//                    
//                    String archivoDestino = Propiedades.getValue("ruta.informe.extended.disc") +
//                            usuario.getCodigo() + 
//                            Propiedades.getValue("nombre.informe.extended.disc");
//
//                    try {
//                        
//                        OutputStream out = new FileOutputStream(archivoDestino);
//                        out.write(uploadItem.getFileData().getBytes());
//                        out.close();
//                        
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    
//                    retorno.setViewName("exitoJson");
//                    retorno.addObject("success", true);
//                } catch (Exception e) {
//                    log.error("error!!", e);
//                    retorno.setViewName("errorJson");
//                    retorno.addObject("errors", e.toString());
//                }
//            }
//        } catch (Exception e) {
//            log.error("error!!", e);
//            retorno.setViewName("errorJson");
//            retorno.addObject("success", false);
//            retorno.addObject("errors", e.toString());
//        }
//        return retorno;
//    }
    /**
     * descarga el informe completo desde el servidor.
     * @param idUsuario
     * @param servletResponse
     * @param servletRequest
     * @return
     * @throws IOException
     * @throws Exception 
     */
//    @RequestMapping(method = RequestMethod.GET, value = "/informe/descargar/{idUsuario}")
//    public String descargarInforme(@PathVariable int idUsuario,
//            ServletResponse servletResponse, ServletRequest servletRequest)
//            throws IOException, Exception {
//
//        /**
//         * 0002349: hoja de vida - extended disc adicionar nuevos cambios y
//         * carga de pdf. 
//         * Se crea el json "descargarInforme" para descargar 
//         * el informe completo en un pdf.
//         * @jlosorio 18-07-2013
//         */
//
//        /**
//         * verificamos la ruta y cargamos el usuario y el archivo.
//         */
//        verificarRuta(Propiedades.getValue("ruta.informe.extended.disc"));
//        Usuarios usuario = new Usuarios(idUsuario);
//
//        String fileInServer = Propiedades.getValue("ruta.informe.extended.disc")
//                + usuario.getCodigo()
//                + Propiedades.getValue("nombre.informe.extended.disc");
//
//        File file = new File(fileInServer);
//
//        /**
//         * si el archivo es valido, lo cargamos en el FileInputStream y los 
//         * escribimos con ByteArrayOutputStream para que se descargue
//         */
//        if (file != null && file.length() > 0) {
//            
//            OutputStream out = servletResponse.getOutputStream();
//            
//            byte[] buffer = new byte[(int) file.length()];
//
//            try {
//                servletResponse.setContentType("application/pdf");
//                servletResponse.setContentLength(buffer.length);
//
//                ByteArrayOutputStream ous = null;
//                InputStream ios = null;
//                try {
//                    ous = new ByteArrayOutputStream();
//                    ios = new FileInputStream(file);
//                    int read = 0;
//                    while ((read = ios.read(buffer)) != -1) {
//                        ous.write(buffer, 0, read);
//                    }
//                } finally {
//                    try {
//                        if (ous != null) {
//                            ous.close();
//                        }
//                    } catch (IOException e) {
//                    }
//
//                    try {
//                        if (ios != null) {
//                            ios.close();
//                        }
//                    } catch (IOException e) {
//                    }
//                }
//                out.write(buffer);
//            } catch (FileNotFoundException e) {
//
//                System.out.println("File Not Found.");
//                e.printStackTrace();
//            } catch (IOException e1) {
//
//                System.out.println("Error Reading The File.");
//                e1.printStackTrace();
//            }
//            out.close();
//            return "exitoJson";
//        }else{
//            return "fail";
//        }        
//    }
    

//    @RequestMapping(method = RequestMethod.GET, value = "/perfil/descargar/{idUsuario}")
//    public String descargarPerfil(@PathVariable int idUsuario,
//            ServletResponse servletResponse, ServletRequest servletRequest)
//            throws IOException, Exception {
//        try {
//            OutputStream out = servletResponse.getOutputStream();
//            
//            ExtendedDisc ed = daoExtendedDisc.getByIdUsuario(idUsuario);
//            
//
//            byte[] foto = ed == null ? null : ed.getFotoPerfil();
//            if (ed != null && foto != null) {
//                servletResponse.setContentType(ed.getContentTypeFotoPerfil());
//                servletResponse.setContentLength(foto.length);
//                out.write(foto, 0, foto.length);
//            } else {
//                return "redirect:../../../imagenes/noimagen.jpg";
//            }
//            out.close();
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Error descargando.", e);
//            return "redirect:../../../imagenes/noimagen.jpg";
//        }
//    }    
//    @RequestMapping(method = RequestMethod.GET, value = "/estilo/descargar/{idUsuario}")
//    public String descargarEstilo(@PathVariable int idUsuario,
//            ServletResponse servletResponse, ServletRequest servletRequest)
//            throws IOException, Exception {
//        try {
//            OutputStream out = servletResponse.getOutputStream();
//            ExtendedDisc ed = daoExtendedDisc.getByIdUsuario(idUsuario);
//
//            byte[] foto = ed == null ? null : ed.getFotoEstilos();
//            if (ed != null && foto != null) {
//                servletResponse.setContentType(ed.getContentTypeFotoEstilos());
//                servletResponse.setContentLength(foto.length);
//                out.write(foto, 0, foto.length);
//            } else {
//                return "redirect:../../../imagenes/noimagen.jpg";
//            }
//            out.close();
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Error descargando.", e);
//            return "redirect:../../../imagenes/noimagen.jpg";
//        }
//    }
    
    
//    @RequestMapping(method = RequestMethod.GET, value = "/login/descargarImagenLogin")
//    public String cargarImageLogin(ServletResponse servletResponse, ServletRequest servletRequest){
//        try {
//            String valueTemaCliente = Propiedades.getValue("temacliente.gwt");
//            if( valueTemaCliente.equalsIgnoreCase("1") ){
//                return "redirect:../../imagenes/logoTalento.png";
//            } else if( valueTemaCliente.equalsIgnoreCase("2") ){
//                return "redirect:../../imagenes/logoHacebLogin.png";
//            } else if( valueTemaCliente.equalsIgnoreCase("3") ){
//                return "redirect:../../imagenes/logoBvc.png";
//            } else if( valueTemaCliente.equalsIgnoreCase("4") ){
//                return "redirect:../../imagenes/LogoTCC.png";
//            } else if( valueTemaCliente.equalsIgnoreCase("5") ){
//                return "redirect:../../imagenes/logoEPM.png";
//            } else if( valueTemaCliente.equalsIgnoreCase("6") ){
//                return "redirect:../../imagenes/logoProplasPanel.png";
//            } else if( valueTemaCliente.equalsIgnoreCase("7") ){
//                return "redirect:../../imagenes/logoMundualLogin.jpg";
//            } else if( valueTemaCliente.equalsIgnoreCase("8") ){
//                return "redirect:../../imagenes/segurosConfianzaLogin.jpg";
//            } else {
//                return "redirect:../../imagenes/logoTalento.png";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Error descargando.", e);
//            return "redirect:../../../imagenes/logoTalento.png";
//        }
//    }
    
    
    /**
     * verifica si una ruta enviada pro parametro esta creada y si no la crea.
     * @param ruta ruta a verificar.
     * @throws Exception 
     */
    public void verificarRuta(String ruta) throws Exception {

        //verifica si las carpetas existen, en caso contrario las crea
        File carpetaInforme = new File(ruta);
        if (!carpetaInforme.exists() || !carpetaInforme.isDirectory()) {
            boolean creacion23 = carpetaInforme.mkdirs();
            if (!creacion23) {
                throw new Exception("No se pudo crear la ruta"
                        + ruta);
            } else {
                log.info("creo la carpeta: " + ruta);
            }
        }
    }
}
