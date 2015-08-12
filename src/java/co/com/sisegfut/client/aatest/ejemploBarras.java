/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest;

import co.com.sisegfut.client.aatest.model.Data2;
import co.com.sisegfut.client.datos.dominio.SituacionesJuegoCompe;
import co.com.sisegfut.client.datos.dominio.dto.DTOSituacionJuegoCompGraficas;
import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuego;
import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuegoAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.chart.series.SeriesToolTipConfig;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.button.TextButton;
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
 * @author Andres Hurtado
 */
public class ejemploBarras implements IsWidget {

    public interface DataPropertyAccess extends PropertyAccess<Data2> {

        ValueProvider<Data2, Double> cantidadAnfitrion();

        ValueProvider<Data2, Double> cantidadRival();

        ValueProvider<Data2, String> nombreSituacionJuego();

        @Path("id")
        ModelKeyProvider<Data2> nameKey();
    }

    private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);

    private FramedPanel panel;
    private Long idCompetencia;
    private ListStore<Data2> store;
    private Chart<Data2> chart;

    public Long getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    @Override
    public Widget asWidget() {
        if (panel == null) {
            store = new ListStore<Data2>(dataAccess.nameKey());
//            store.addAll(DTOSituacionJuegoCompGraficas.getSituacionesCompetencia(4, 1, 10));
//            store.clear();
//            store.addAll(DTOSituacionJuegoCompGraficas.getSituacionesCompetenciaVacio(19, 1, 10));
//            
            TextSprite title = new TextSprite("Cantidad");
            title.setFontSize(18);

            NumericAxis<Data2> axis = new NumericAxis<Data2>();
            axis.setPosition(Position.BOTTOM);
            axis.addField(dataAccess.cantidadAnfitrion());
            axis.addField(dataAccess.cantidadRival());
            axis.setTitleConfig(title);
            axis.setDisplayGrid(true);
            axis.setMinimum(0);
            axis.setInterval(1);
            axis.setMaximum(15);

            title = new TextSprite("Situaciones de Juego");
            title.setFontSize(18);

            CategoryAxis<Data2, String> catAxis = new CategoryAxis<Data2, String>();
            catAxis.setPosition(Position.LEFT);
            catAxis.setField(dataAccess.nombreSituacionJuego());
            catAxis.setTitleConfig(title);

            SeriesLabelConfig<Data2> labelConfig = new SeriesLabelConfig<Data2>();
            labelConfig.setLabelPosition(LabelPosition.OUTSIDE);

            SeriesToolTipConfig<Data2> tooltipConfig = new SeriesToolTipConfig<Data2>();

            List<String> resultadoBarra = new ArrayList<String>();
            resultadoBarra.add("POLITECNICO");
            resultadoBarra.add("RIVAL");

            final BarSeries<Data2> bar = new BarSeries<Data2>();
            bar.setYAxisPosition(Position.BOTTOM);
            bar.addYField(dataAccess.cantidadAnfitrion());
            bar.addYField(dataAccess.cantidadRival());
//            bar.setLabelConfig(labelConfig);
            bar.setLegendTitles(resultadoBarra);
            bar.setToolTipConfig(tooltipConfig);
            bar.addColor(new Color("Green"));
            bar.addColor(new RGB(197, 252, 18));
//      bar.addColor(new RGB(17, 95, 166));
//      bar.addColor(new RGB(166, 17, 32));

            final Legend<Data2> legend = new Legend<Data2>();
            legend.setItemHighlighting(true);
            legend.setItemHiding(true);
            legend.getBorderConfig().setStrokeWidth(0);

            chart = new Chart<Data2>();
            chart.setStore(store);
            chart.setShadowChart(false);
            chart.addAxis(axis);
            chart.addAxis(catAxis);
            chart.addSeries(bar);
            chart.setLegend(legend);
            chart.setAnimated(true);
            chart.setDefaultInsets(19);
            chart.setAnimationDuration(3000);

            TextButton regenerate = new TextButton("Recargar");
            regenerate.addSelectHandler(new SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
//                    store.clear();
//                    store.addAll(buscarSituacionesxCompetencia(idCompetencia));
                    chart.redrawChart();
                }
            });

//      ToggleButton animation = new ToggleButton("Animación");
//      animation.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//        @Override
//        public void onValueChange(ValueChangeEvent<Boolean> event) {
//          chart.setAnimated(event.getValue());
//        }
//      });
//      animation.setValue(true, true);
// 
//      ToggleButton shadow = new ToggleButton("Sombra");
//      shadow.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
//        @Override
//        public void onValueChange(ValueChangeEvent<Boolean> event) {
//          chart.setShadowChart(event.getValue());
//          chart.redrawChart();
//        }
//      });
//      shadow.setValue(false);
// 
            ToolBar toolBar = new ToolBar();
            toolBar.add(regenerate);
//      toolBar.add(animation);
//      toolBar.add(shadow);

            VerticalLayoutContainer layout = new VerticalLayoutContainer();
            layout.add(toolBar, new VerticalLayoutData(1, -1));
            layout.add(chart, new VerticalLayoutData(1, 1));

            panel = new FramedPanel();
            panel.setLayoutData(new MarginData(0));
            panel.setCollapsible(true);
            panel.setHeadingText("Grafico barras agrupado");
            panel.setPixelSize(520, 560);
            panel.setHeaderVisible(false);
            panel.setBodyBorder(true);
            panel.add(layout);

            final Resizable resize = new Resizable(panel, Dir.E, Dir.SE, Dir.S);
            resize.setMinHeight(400);
            resize.setMinWidth(400);
            panel.addExpandHandler(new ExpandHandler() {
                @Override
                public void onExpand(ExpandEvent event) {
                    resize.setEnabled(false);
                }
            });
            panel.addCollapseHandler(new CollapseHandler() {
                @Override
                public void onCollapse(CollapseEvent event) {
                    resize.setEnabled(false);
                }
            });
//      new Draggable(panel, panel.getHeader()).setUseProxy(false);
        }

        return panel;
    }

    public void buscarSituacionesxCompetencia(Long idCompetenc) {
        getServiceSituaciones().getSituacionesXCompetenciaGrafica(idCompetencia, new AsyncCallback<List<Data2>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("No fue posible consultar las situaciones");
            }

            @Override
            public void onSuccess(List<Data2> result) {
//            situacionesJuego=result;
                chart.getStore().addAll(result);
                chart.redrawChart();
            }
        });
    }

    public RPCAdminSituacionesJuegoAsync getServiceSituaciones() {
        RPCAdminSituacionesJuegoAsync svc = (RPCAdminSituacionesJuegoAsync) GWT.create(RPCAdminSituacionesJuego.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminSituacionesJuego");
        return svc;
    }

    public void limpiargrafica() {
        store.clear();
        chart.redrawChart();
    }

}
