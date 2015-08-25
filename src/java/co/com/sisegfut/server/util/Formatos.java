/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 
 */
public class Formatos 
{
    /**
     * Genera un formato de hora con presentación humana a partir de un Date
     * @param fecha
     * @return
     */
    public static String fechaHora( Date fecha )
    {
        if( fecha == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy hh:mm aaa" );
        return sdf.format( fecha );
    }

    /**
     * Genera un date a partir de una hora expresada en un string con el formato
     * hhmmssDDMMAA
     * @param fecha
     * @return
     * @throws ParseException
     */
    public static Date fechaHora( String fecha ) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "HHmmssddMMyy" );
        Date ret = sdf.parse( fecha );
        return ret;
    }

    /**
     * Genera un date a partir de una hora expresada en un string con el formato
     * DDMMAA
     * @param fecha
     * @return
     * @throws ParseException
     */
    public static Date fecha( String fecha ) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "ddMMyy" );
        return sdf.parse( fecha );
    }

    /**
     * Genera un date a partir de una hora expresada en un string con el formato
     * DDMMAA
     * @param fecha
     * @return
     * @throws ParseException
     */
    public static Date fecha2( String fecha ) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        return sdf.parse( fecha );
    }
    
    public static Date fechaAnio( String fecha ) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy" );
        return sdf.parse( fecha );
    }
    
     public static Date fechaMes( String fecha ) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "MM" );
        return sdf.parse( fecha );
    }
      public static Date fechaDia( String fecha ) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd" );
        return sdf.parse( fecha );
    }
     

    /**
     * Genera un String con el formato DDMMAA a partir de un Date
     * @param fecha
     * @return
     */
    public static String fecha( Date fecha )
    {
        if( fecha == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "ddMMyy" );
        return sdf.format( fecha );
    }

    /**
     * Genera un String con el formato DDMMAA a partir de un Date
     * @param fecha dd-MM-yyyy
     * @return
     */
    public static String fecha2( Date fecha )
    {
        if( fecha == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
        return sdf.format( fecha );
    }

    /**
     * Genera un String con el formato HHMMSS a partir de un date
     * @param date
     * @return
     */
    public static String hora( Date date )
    {
        if( date == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "HHmmss" );
        return sdf.format( date );
    }
    
    /**
     * Genera un String con el formato hh:mm aaa a partir de un date
     * @param date hh:mm aaa
     * @return
     */
    public static String hora2( Date date )
    {
        if( date == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm aaa" );
        return sdf.format( date );
    }

    /**
     * Genera un Date a partir de un String con el formato HHMMSS
     * @param fecha
     * @return
     * @throws ParseException 
     */
    public static Date hora( String fecha ) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm aaa" );
        return sdf.parse( fecha );
    }

    /**
     * Genera un formato de hora con presentación humana a partir de un Date
     * @param fecha dd/MM/yyyy kk:mm:ss
     * @return
     */
    public static String fechaHoraMilitar( Date fecha )
    {
        if( fecha == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MMM/yyyy HH:mm aaa" );
        return sdf.format( fecha );
    }
    /**
     * Genera un formato de hora con presentación humana a partir de un Date
     * @param fecha ddMMyyyykkmmss
     * @return
     */
    public static String fechaHoraMilitar2( Date fecha )
    {
        if( fecha == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "ddMMyyyykkmmss" );
        return sdf.format( fecha );
    }

    /**
     * Genera un formato de hora con presentación humana a partir de un Date
     * @param fecha yyyy-MM-dd kk:mm:ss
     * @return
     */
    public static String fechaHoraMilitar3( Date fecha )
    {
        if( fecha == null )
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        return sdf.format( fecha );
    }
    
       /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en el
     * formato HH:mm dd-MM-yyyy
     * @param fecha
     * @return
     */
    public static String fechaHora3( Date fecha )
    {
        SimpleDateFormat fmt = new SimpleDateFormat( "hh:mm a dd-MM-yyyy");
        return fmt.format( fecha );
    }
    
    /**
     * Genera un date a partir de una hora expresada en un string con el formato
     * yyyy-MM-dd
     * @param fecha
     * @return
     * @throws ParseException
     */
    public static Date parseFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ret = sdf.parse(fecha);
        return ret;
    }
    
    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en el
     * formato yyyy-MM-dd
     *
     * @param fecha - fecha tipo date
     * @return
     */
    public static String fechaHoraSimple(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    
    /**
     * convierte la cantidad de segundos a un formato hh:mm:ss
     * @param segundos
     * @return 
     */
    public static String segundosAHoras(long segundos){
        boolean positivo = true;
        //Primero determino si es una cifra positiva o negativa
        if(segundos<0){
            positivo = false;
        }
        long horas = 0;
        long minutos = 0;
        //Lo combierto usando valor absoluto a positivo
        segundos = Math.abs(segundos);

        //Obento numero de horas
        if(segundos >= (60*60)){
           long aux = segundos - (segundos%(60*60));
           horas = aux / (60 * 60);
           segundos = (segundos%(60*60));
        }

        //obtengo numero de minutos
        if(segundos >= 60){
            long aux = segundos - (segundos % 60);
            minutos = aux / 60;
            segundos = segundos % 60;
        }


        return ((positivo==false ? "-" : "")+""+rellenarCeros(horas,2) + ":"+rellenarCeros(minutos,2)+":"+rellenarCeros(segundos, 2));
    }
    
    public static String rellenarCeros(long numero, long longitud){
        String digito = numero+"";
        while(digito.length()<longitud){
            digito="0"+digito;
        }
        return digito;
    }

    /**
     * Retorna un string descriptivo de la fecha y hora dados como parámetro en el
     * formato yyyy_MM_dd
     *
     * @param fecha - fecha tipo date
     * @return
     */
    public static String fechaHoraSimple2(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        return sdf.format(date);
    }
    
    /**
     * Genera un date a partir de una hora expresada en un string con el formato
     * yyyy-MM-dd kk:mm
     * @param fecha
     * @return
     * @throws ParseException
     */
    public static Date parseFecha2(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        Date ret = sdf.parse(fecha);
        return ret;
    }
    
    /**
     * Genera un date a partir de una hora expresada en un string con el formato
     * yyyy-MM-dd kk:mm
     * @param fecha
     * @return
     * @throws ParseException
     */
    public static Date parseHoraMilitar(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        Date ret = sdf.parse(fecha);
        return ret;
    }
    
        public static Date RestarAnios(Date fecha, int anios) {
//
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.YEAR, anios);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
//
    }
        public static Integer getEdad(Date fechaNac) {
        Integer edad = 0;
        
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if(mes<0 || (mes==0 && dia<0)){
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        edad= año;
        return edad;

    }
        public static String ceroHoras(Date fecha){
        Calendar current = Calendar.getInstance();
        current.setTime(fecha);
        current.set(current.get(Calendar.YEAR),current.get(Calendar.MONTH),current.get(Calendar.DATE),0,0,0);
        fecha.setTime(current.getTime().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        return sdf.format( fecha );
        }
        
        public static String Horas23(Date fecha){
        Calendar current = Calendar.getInstance();
        current.setTime(fecha);
        current.set(current.get(Calendar.YEAR),current.get(Calendar.MONTH),current.get(Calendar.DATE),23,0,0);
        fecha.setTime(current.getTime().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        return sdf.format( fecha );
        }
        
        public static Double calcularFCM(Integer edad){
        Double fcm=0.0;
        
        fcm=208.75-(0.73-edad);
        
        return fcm;
       
        }
        
        
        
}
