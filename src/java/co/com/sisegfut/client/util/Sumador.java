/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util;

/**
 *
 * @author Juan David Botero <jdbotero@gmail.com>
 */
public class Sumador
{

    public static String suma( String valor )
    {
        String retorno;
        
        MD5 md5 = new MD5();
        retorno = md5.calcMD5( valor );

        return retorno;
    }
}
