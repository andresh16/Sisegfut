/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author anfeh_000 
 */
public class Dialogo extends MessageBox {

    public static void sessionTimeout() {
        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent ce) {
                //Button btn = ce.getButtonClicked();
                HTTP.redireccionarA(GWT.getHostPageBaseURL());
            }
        };
        
        MessageBox box = new MessageBox();  
        box.setButtons(MessageBox.OK);  
        box.setIcon(MessageBox.WARNING);  
        box.setTitle("Su sesión ha expirado");  
        box.addCallback(l);  
        box.setMessage("A continuación se reiniciará la aplicación");  
        box.show();
        
        
//        MessageBoxConfig config = new MessageBoxConfig();
//        config.setButtons(MessageBox.OK);
//        config.setIconCls("iconoWarning");
//        config.setMsg("A continuación se reiniciará la aplicación");
//        config.setTitle( "Su sesión ha expirado" );
//        config.setCallback(new PromptCallback() {
//
//            public void execute(String btnID, String text)
//            {
//                HTTP.redireccionarA(GWT.getHostPageBaseURL());
//            }
//        });
//        show(config);
        
    }
}
