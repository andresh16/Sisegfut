
package co.com.sisegfut.client.aatest;

import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.PanelInformativo;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.*;
import com.google.gwt.user.client.Element;

/**
 *
 * @author Andres Hurtado
 */
public class PanelPrueba extends LayoutContainer {

    private PanelInformativo pnlInformativo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setScrollMode(Scroll.AUTOY);
//        setLayout(new CenterLayout());

        pnlInformativo = new PanelInformativo("<b>Es un</b> texto",null);

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        pnlErrores.aniadir("<b>Es error un</b> texto");
        pnlExito.definirTexto("<b>Es exito un</b> texto");
        pnlErrores.setVisible(true);
        pnlExito.setVisible(true);

//        ContentPanel cp = new ContentPanel(new FitLayout());
//        cp.setHeading("Informativo");
//        cp.add(pnlInformativo);
//
//        ContentPanel cp2 = new ContentPanel(new FitLayout());
//        cp2.setHeading("Exito");
//        cp2.add(pnlExito);
//
//        ContentPanel cp3 = new ContentPanel(new FitLayout());
//        cp3.setHeading("Error");
//        cp3.add(pnlErrores);
//
//        LayoutContainer lc = new LayoutContainer();
//        RowLayout rl = new RowLayout();
//        lc.setLayout(rl);
//        //lc.setSize(800, 600);
//
//        lc.add(cp);
//        lc.add(cp2);
//        lc.add(cp3);

        ContentPanel panel = new ContentPanel();
        panel.setHeading("RowLayout: Orientation set to vertical");
        panel.setLayout(new RowLayout(Orientation.VERTICAL));
        panel.setSize(800, 300);
        panel.setFrame(true);
        panel.setCollapsible(true);

        Text label1 = new Text("Test Label 1");
        label1.addStyleName("pad-text");
        label1.setStyleAttribute("backgroundColor", "white");
        label1.setBorders(true);

        Text label2 = new Text("Test Label 2");
        label2.addStyleName("pad-text");
        label2.setStyleAttribute("backgroundColor", "white");
        label2.setBorders(true);

        Text label3 = new Text("Test Label 3");
        label3.addStyleName("pad-text");
        label3.setStyleAttribute("backgroundColor", "white");
        label3.setBorders(true);

//        panel.add(label1, new RowData(1, -1, new Margins(4)));
//        panel.add(label2, new RowData(1, 1, new Margins(0, 4, 0, 4)));
//        panel.add(pnlExito, new RowData(1, -1, new Margins(4)));

        panel.add(pnlInformativo, new RowData(1, -1, new Margins(4)));
        panel.add(pnlExito, new RowData(1, 1, new Margins(0, 4, 0, 4)));
        panel.add(pnlErrores, new RowData(1, -1, new Margins(4)));
        panel.addButton(new Button("Mostar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //MessageBox.alert("Mensaje", "Ejecuto boton " + ce.getButton().getId(), null);
                if(pnlExito.isVisible()){
                    pnlExito.setVisible(false);
                }else{
                    pnlExito.setVisible(true); 
                }
            }
        }));

        add(panel,new FlowData(10));
    }
}
