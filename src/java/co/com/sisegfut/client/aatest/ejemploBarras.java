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

    public Long getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }
    List<Data2> situacionesJuego = new ArrayList<Data2>();

    @Override
    public Widget asWidget() {
        if (panel == null) {
            final ListStore<Data2> store = new ListStore<Data2>(dataAccess.nameKey());
//            store.addAll(DTOSituacionJuegoCompGraficas.getSituacionesCompetencia(4, 1, 10));
            store.clear();
            store.addAll(DTOSituacionJuegoCompGraficas.getSituacionesCompetenciaVacio(4, 1, 10));

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

            List<String> p = new ArrayList<String>();
            p.add("POLITECNICO");
            p.add("RIVAL");

            final BarSeries<Data2> bar = new BarSeries<Data2>();
            bar.setYAxisPosition(Position.BOTTOM);
            bar.addYField(dataAccess.cantidadAnfitrion());
            bar.addYField(dataAccess.cantidadRival());
            bar.setLabelConfig(labelConfig);
            bar.setLegendTitles(p);
            bar.addColor(new Color("Green"));
            bar.addColor(new RGB(197, 252, 18));
//      bar.addColor(new RGB(17, 95, 166));
//      bar.addColor(new RGB(166, 17, 32));

            final Legend<Data2> legend = new Legend<Data2>();
            legend.setItemHighlighting(true);
            legend.setItemHiding(true);
            legend.getBorderConfig().setStrokeWidth(0);

            final Chart<Data2> chart = new Chart<Data2>();
            chart.setStore(store);
            chart.setShadowChart(false);
            chart.addAxis(axis);
            chart.addAxis(catAxis);
            chart.addSeries(bar);
            chart.setLegend(legend);
            chart.setAnimated(true);
            chart.setDefaultInsets(19);

            TextButton regenerate = new TextButton("Recargar");
            regenerate.addSelectHandler(new SelectHandler() {
                @Override
                public void onSelect(SelectEvent event) {
                    buscarSituacionesxCompetencia(idCompetencia);
                    store.clear();
                    store.addAll(situacionesJuego);
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
        getServiceSituaciones().getSituacionesXCompetencia(idCompetenc, new AsyncCallback<List<SituacionesJuegoCompe>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("No fue posible consultar las situaciones");
            }

            @Override
            public void onSuccess(List<SituacionesJuegoCompe> result) {
                cargarSitucionesJuego(result);
            }
        });

    }
    final String[] vectorSituacionesJuego = new String[]{
        "Falta Z1", "Falta Z2", "Falta Z3", "Recuperación Z1", "Recuperación Z2", "Recuperación Z3", "Tiro Libre Z1", "Tiro Libre Z2", "Tiro Libre Z3", "Centrao Lateral",
        "Entradas Erradas", "Fuera Lugar", "Opción Gol", "Penalty", "Remate", "Tiro Esquina", "Tarjeta Roja", "Tarjeta Amarilla"};

    public void cargarSitucionesJuego(List<SituacionesJuegoCompe> situacionesJuegoCompes) {
        List<Data2> data = new ArrayList<Data2>();

        /*
         *Inicializo la lista de datas para poder modificarlos segun el equipo 
         */
        for (int i = 0; i < 4; i++) {
            data.add(new Data2("0", "vacio", 0, 0));

        }

        /**
         *Recorro los cuatro registros de la asistencias segun la competencia
         */
        for (SituacionesJuegoCompe situacionesJuegoCompe : situacionesJuegoCompes) {
            if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("POLITECNICO JIC")) {
                //Realizo un for manual para saber la posicion del nombre de la situación
                int i = 0;
                // Agrego por cada data un registro de data pero solo para insertar valor de anfitrion en este caso el Poli
                // esto funciona como un acumulador ya que va entrar dos veces por el tiempo de juego, esto se validará si se puede 
                // hacer en dos graficas. por el momento muestra todo
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getFaltaZona1(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getFaltaZona2(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getFaltaZona3(), data.get(i).getCantidadRival()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion() + situacionesJuegoCompe.getRecuperacionZona1(), data.get(i).getCantidadRival()));

            } else if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("RIVAL")) {
                int i = 0;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getFaltaZona1()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getFaltaZona2()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getFaltaZona3()));
                i++;
                data.set(i, new Data2(i + "", vectorSituacionesJuego[i % vectorSituacionesJuego.length], data.get(i).getCantidadAnfitrion(), data.get(i).getCantidadRival() + situacionesJuegoCompe.getRecuperacionZona1()));

                System.out.println("" + data);
            }
        }
        // Seteo la variable global de lista de Data2 para luego en el botón recargar tome esta lista.
        situacionesJuego = data;
    }

    public RPCAdminSituacionesJuegoAsync getServiceSituaciones() {
        RPCAdminSituacionesJuegoAsync svc = (RPCAdminSituacionesJuegoAsync) GWT.create(RPCAdminSituacionesJuego.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminSituacionesJuego");
        return svc;
    }

}
