/**
 * PanelInformativo
 */

package co.com.sisegfut.client.util;

import co.com.sisegfut.client.aaI18N.Main;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

/**
 *
 * @author 
 */
public class PanelInformativo extends HorizontalPanel {

    private Html html;
    private String titulo;
    private String textoAyuda;
    private Button btnAyuda;
    /** Contiene los textos a presentar en la interfaz web segun el idioma*/
    private Main myConstants;

    /**
     * Constructor
     * @param titulo
     * @param textoAyuda 
     */
    public PanelInformativo(String titulo, String textoAyuda) {
        myConstants = (Main) GWT.create(Main.class);
        
        setStyleName("panelInformativo");
        this.titulo=titulo;
        this.textoAyuda=textoAyuda;
        html = new Html();
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);        
        setSpacing(3);         
        
        html.setHtml(titulo);  
        html.setStyleAttribute("font", "12px verdana,arial"); 
        
        if(textoAyuda!=null){
            btnAyuda = new Button("", Resources.ICONS.iconoAyuda(), listenerAyuda());
            add(btnAyuda);//, new RowData(-1, 1, new Margins(4))); 
        }
        
        add(html);//, new RowData(1, 1, new Margins(4))); 
        
    }
    
    /**
     * Escucha el boton ayuda para abrir la ventana
     * @return 
     */
    private SelectionListener<ButtonEvent> listenerAyuda(){
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                abrirVentana();
            }
        };
    }

    /**
     * Actualiza el titulo del panel
     * @param texto
     */
    public void actualizarTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Abre la ventana con el texto de la ayuda
     */
    private void abrirVentana() {
        final Dialog simple = new Dialog();
        simple.setHeading("Ayuda");
        simple.setButtons(Dialog.OK);
        simple.setBodyStyleName("pad-text");
        simple.addText(textoAyuda);
        simple.setWidth(600);
        //simple.getItem(0).getFocusSupport().setIgnore(true);
        simple.setScrollMode(Scroll.AUTO);
        simple.setHideOnButtonClick(true);
        simple.setModal(true); 
        
        simple.show();
    }
}
