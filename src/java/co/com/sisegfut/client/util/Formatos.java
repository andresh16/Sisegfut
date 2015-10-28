/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author
 */
public class Formatos {

    /**
     * Retorna un string descriptivo de la fecha dada como parámetro en el
     * formato dd-MM-yyyy
     *
     * @param fecha
     * @return
     */
    public static String fecha(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy");
        return fmt.format(fecha);
    }

    public static String fechaMes(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MMM-yyyy");
        return fmt.format(fecha);
    }

//    public static String fechaLarga(Date fecha) {
//        DateFormat fechaLarga = DateFormat.getDateInstance(DateFormat.LONG);
//        return fechaLarga.format(fecha);
//    }
    public static String fechaLarga(Date fecha) {
        DateTimeFormat fechaLarga = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_FULL);
        return fechaLarga.format(fecha);
    }

    public static String fechaLarga2(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("EEEE dd MMMM yyyy");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha dada como parámetro en el
     * formato yyyy-MM-dd
     *
     * @param fecha
     * @return
     */
    public static String fecha2(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
        return fmt.format(fecha);
    }

    public static String anio(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy");
        return fmt.format(fecha);
    }

    public static String mes(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("MM");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato dd-MM-yy HH:mm:ss
     *
     * @param fecha
     * @return
     */
    public static String fechaHora(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yy hh:mm:ss a");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato dd-MM-yyyy HH:mm:ss
     *
     * @param fecha
     * @return
     */
    public static String fechaHora2(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy hh:mm:ss a");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato kk:mm:ss
     *
     * @param fecha
     * @return
     */
    public static Date fechaHora2(String fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("kk:mm:ss");
        return fmt.parse(fecha);
    }

    public static Date FechaMes(String fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MMM-yyyy");
        return fmt.parse(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato HH:mm dd-MM-yyyy
     *
     * @param fecha
     * @return
     */
    public static String fechaHora3(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("hh:mm a dd-MM-yyyy");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato yyyy-MM-dd kk:mm
     *
     * @param fecha
     * @return
     */
    public static String fechaHora4(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd kk:mm");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato HH:mm:ss
     *
     * @param fecha
     * @return
     */
    public static String Hora(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("hh:mm aa");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato dd/MMM/yyyy
     *
     * @param fecha
     * @return
     */
    public static String fecha4(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MMM/yyyy");
        return fmt.format(fecha);
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en
     * el formato kk:mm
     *
     * @param fecha
     * @return
     */
    public static String HoraMilitar(Date fecha) {
        DateTimeFormat fmt = DateTimeFormat.getFormat("kk:mm");
        return fmt.format(fecha);
    }

    public static Date RestarAnios(Date fecha, int anios) {
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(fecha); // Configuramos la fecha que se recibe
//        calendar.add(Calendar.YEAR, anios);  // numero de días a añadir, o restar en caso de días<0
//        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
        return fecha;
//
    }

    public static Date FechaConHora(Date fecha, Date hora) {
        Date fechaRetorno = new Date();

        String fechaHora=fecha2(fecha)+" "+HoraMilitar(hora);
        
            DateTimeFormat formatter = DateTimeFormat.getFormat("yyyy-MM-dd kk:mm");
            fechaRetorno=formatter.parse(fechaHora);
       
        return fechaRetorno;
    }

}
