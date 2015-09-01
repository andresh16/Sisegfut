/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest;

import co.com.sisegfut.client.aatest.model.Data;
import co.com.sisegfut.client.aatest.model.Data2;
import co.com.sisegfut.client.aatest.model.Estratos;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.series.PieSeries;
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelProvider;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.Gradient;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.Stop;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextAnchor;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite.TextBaseline;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public class PieExample implements IsWidget, EntryPoint {

    private List<Estratos> estratos = new ArrayList<Estratos>();
    private Chart<Estratos> chart;

    public interface DataPropertyAccess extends PropertyAccess<Data> {
//    ValueProvider<Data, Double> data1();
// 
//    ValueProvider<Data, String> name();
// 

        ValueProvider<Estratos, Double> cantidad();

        ValueProvider<Estratos, String> estrato();

        @Path("id")
        ModelKeyProvider<Estratos> nameKey();
    }

    private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

    private FramedPanel panel;

    @Override
    public Widget asWidget() {
        if (panel == null) {
            final ListStore<Estratos> store = new ListStore<Estratos>(dataAccess.nameKey());
//      store.addAll(TestData.getEstratos2(6, 20, 100));
//      store.addAll(Estratos.getEstratos());
            getEstratificacion();

            Gradient slice1 = new Gradient("s1", 45);
            slice1.addStop(new Stop(0, new RGB(148, 174, 10)));
            slice1.addStop(new Stop(100, new RGB(107, 126, 7)));

            Gradient slice2 = new Gradient("s2", 45);
            slice2.addStop(new Stop(0, new RGB(17, 95, 166)));
            slice2.addStop(new Stop(100, new RGB(12, 69, 120)));

            Gradient slice3 = new Gradient("s3", 45);
            slice3.addStop(new Stop(0, new RGB(166, 17, 32)));
            slice3.addStop(new Stop(100, new RGB(120, 12, 23)));

            Gradient slice4 = new Gradient("s4", 45);
            slice4.addStop(new Stop(0, new RGB(255, 136, 9)));
            slice4.addStop(new Stop(100, new RGB(213, 110, 0)));

            Gradient slice5 = new Gradient("s5", 45);
            slice5.addStop(new Stop(0, new RGB(255, 209, 62)));
            slice5.addStop(new Stop(100, new RGB(255, 197, 11)));

            Gradient slice6 = new Gradient("s6", 45);
            slice6.addStop(new Stop(0, new RGB(166, 17, 135)));
            slice6.addStop(new Stop(100, new RGB(120, 12, 97)));

            TextSprite textConfig = new TextSprite();
            textConfig.setFont("Arial");
            textConfig.setTextBaseline(TextBaseline.MIDDLE);
            textConfig.setFontSize(18);
            textConfig.setTextAnchor(TextAnchor.MIDDLE);
            textConfig.setZIndex(15);

            SeriesLabelConfig<Estratos> labelConfig = new SeriesLabelConfig<Estratos>();
            labelConfig.setSpriteConfig(textConfig);
            labelConfig.setLabelPosition(LabelPosition.START);
            labelConfig.setValueProvider(dataAccess.estrato(), new StringLabelProvider<String>());

            SeriesToolTipConfig<Estratos> tooltip = new SeriesToolTipConfig<Estratos>();
            tooltip.setLabelProvider(new SeriesLabelProvider<Estratos>() {
                @Override
                public String getLabel(Estratos item, ValueProvider<? super Estratos, ? extends Number> valueProvider) {
                    return NumberFormat.getFormat("0").format(dataAccess.cantidad().getValue(item)) + " deportistas en "
                            + dataAccess.estrato().getValue(item);
                }
            });
            tooltip.setDismissDelay(3000);

            final PieSeries<Estratos> series = new PieSeries<Estratos>();
            series.setAngleField(dataAccess.cantidad());
            series.addColor(slice1);
            series.addColor(slice2);
            series.addColor(slice3);
            series.addColor(slice4);
            series.addColor(slice5);
            series.addColor(slice6);
            series.setLabelConfig(labelConfig);
            series.setHighlighting(true);
            series.setToolTipConfig(tooltip);
            series.setLegendValueProvider(dataAccess.estrato(), new LabelProvider<String>() {
                @Override
                public String getLabel(String item) {
                    return item;
//                    return item.substring(0, 5);
                }
            });

            final Legend<Estratos> legend = new Legend<Estratos>();
            legend.setPosition(Position.BOTTOM);
            legend.setItemHighlighting(true);
            legend.setItemHiding(true);
            legend.getBorderConfig().setStrokeWidth(0);
            legend.setLegendInset(6);
            legend.setPadding(10);
            

            chart = new Chart<Estratos>();
            chart.setDefaultInsets(6);
            chart.setStore(store);
            chart.setShadowChart(false);
            chart.addGradient(slice1);
            chart.addGradient(slice2);
            chart.addGradient(slice3);
            chart.addGradient(slice4);
            chart.addGradient(slice5);
            chart.addGradient(slice6);
            chart.addSeries(series);
            chart.setLegend(legend);
            chart.setAnimated(true);

//      TextButton regenerate = new TextButton("Recargar");
//      regenerate.addSelectHandler(new SelectHandler() {
//        @Override
//        public void onSelect(SelectEvent event) {
//          store.clear();
//          store.addAll(estratos);
//          chart.redrawChart();
//        }
//      });
// 
            ToggleButton donut = new ToggleButton("Ver en dona");
            donut.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
                @Override
                public void onValueChange(ValueChangeEvent<Boolean> event) {
                    if (event.getValue()) {
                        series.setDonut(35);
                    } else {
                        series.setDonut(0);
                    }
                    chart.redrawChart();
                }
            });

//      ToggleButton animation = new ToggleButton("Animate");
//      animation.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//        @Override
//        public void onValueChange(ValueChangeEvent<Boolean> event) {
//          chart.setAnimated(event.getValue());
//        }
//      });
//      animation.setValue(true, true);
// 
//      ToggleButton shadow = new ToggleButton("Shadow");
//      shadow.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//        @Override
//        public void onValueChange(ValueChangeEvent<Boolean> event) {
//          chart.setShadowChart(event.getValue());
//          chart.redrawChart();
//        }
//      });
//      shadow.setValue(false);
            ToolBar toolBar = new ToolBar();
//      toolBar.add(regenerate);
//      toolBar.add(animation);
//      toolBar.add(shadow);
            toolBar.add(donut);

            VerticalLayoutContainer layout = new VerticalLayoutContainer();
            layout.add(toolBar, new VerticalLayoutData(1, -1));
            layout.add(chart, new VerticalLayoutData(1, 1));

            panel = new FramedPanel();
            panel.setLayoutData(new MarginData(10));
            panel.setCollapsible(true);
            panel.setHeadingText("Pie Chart");
            panel.setPixelSize(620, 500);
            panel.setBodyBorder(true);
            panel.add(layout);

            final Resizable resize = new Resizable(panel, Dir.E, Dir.SE, Dir.S);
            resize.setMinHeight(400);
            resize.setMinWidth(400);
            panel.addExpandHandler(new ExpandHandler() {
                @Override
                public void onExpand(ExpandEvent event) {
                    resize.setEnabled(true);
                }
            });
            panel.addCollapseHandler(new CollapseHandler() {
                @Override
                public void onCollapse(CollapseEvent event) {
                    resize.setEnabled(false);
                }
            });
            new Draggable(panel, panel.getHeader()).setUseProxy(false);
        }

        return panel;
    }

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(asWidget());
    }

    public void getEstratificacion() {
        getServiceDeportista().getDeportistasEstratificacion(new AsyncCallback<List<Estratos>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Estratos> result) {
                chart.getStore().addAll(result);
                chart.redrawChart();
            }
        });

    }

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

}
