package co.com.sisegfut.client.util;

import com.extjs.gxt.ui.client.widget.Html;


public class PanelExito extends Html
{
    public PanelExito()
    {
        setStyleName( "panelExito" );   
        setVisible( false );
    }
    
    public void definirTexto( String texto )
    {
        setHtml( texto );
    }    

}
