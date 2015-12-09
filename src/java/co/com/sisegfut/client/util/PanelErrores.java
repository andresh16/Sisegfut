
package co.com.sisegfut.client.util;

import com.extjs.gxt.ui.client.widget.Html;
/**
 * 
 * @author anfeh_000
 */
public class PanelErrores extends Html
{
    private StringBuffer errores;
    private Integer cantidadErrores;

    final private String claseCss = "listaErrores";
    final private String preListaErrores = "<ul class='" + claseCss + "'>";
    final private String preError = "<li>";
    final private String postError = "</li>";
    final private String postListaErrores = "</ul>";

    private String cacheHTML;
    
    /**
     * Método constructor
     */
    public PanelErrores()
    {
        errores = new StringBuffer();
        setStyleName( "panelErrores" );           
        setBorders( true );
        cantidadErrores = new Integer( 0 );
        setVisible( false );

        cacheHTML = "";
    }
    
    public void aniadir( String error )
    {
        String contenido;
        
        errores.append(preError).append(error).append( postError);
        
        contenido = preListaErrores +
                errores + 
                postListaErrores;

        cacheHTML = contenido;
        setHtml( contenido );
        
        cantidadErrores = new Integer( cantidadErrores.intValue() + 1 );
    }

    public String getHTML()
    {
        return cacheHTML;
    }

    public Integer getCantidadErrores()
    {
        return cantidadErrores;
    }

    public void setCantidadErrores( Integer cantidadErrores )
    {
        this.cantidadErrores = cantidadErrores;
    }
    
    public void limpiar()
    {
        errores = new StringBuffer();
        cantidadErrores = new Integer( 0 );
    }
}
