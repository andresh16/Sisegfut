/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest;


import co.com.sisegfut.client.aatest.model.Data;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.BarSeries;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.fx.client.Draggable;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.Resizable.Dir;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 *
 * @author Andres Hurtado
 */
public class ejemploBarras implements IsWidget{

    public interface DataPropertyAccess extends PropertyAccess<Data> {
    ValueProvider<Data, Double> data1();
 
    ValueProvider<Data, Double> data2();
 
    ValueProvider<Data, Double> data3();
 
    ValueProvider<Data, String> name();
 
    @Path("id")
    ModelKeyProvider<Data> nameKey();
  }
 
  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class);
 
  private FramedPanel panel;
 
  @Override
  public Widget asWidget() {
    if (panel == null) {
      final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey());
//      store.addAll(TestData.getData(12, 20, 100));
 
      TextSprite title = new TextSprite("Number of Hits");
      title.setFontSize(18);
 
      NumericAxis<Data> axis = new NumericAxis<Data>();
      axis.setPosition(Position.BOTTOM);
      axis.addField(dataAccess.data1());
      axis.addField(dataAccess.data2());
      axis.addField(dataAccess.data3());
      axis.setTitleConfig(title);
      axis.setDisplayGrid(true);
      axis.setMinimum(0);
      axis.setMaximum(100);
       
      title = new TextSprite("Month of the Year");
      title.setFontSize(18);
 
      CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>();
      catAxis.setPosition(Position.LEFT);
      catAxis.setField(dataAccess.name());
      catAxis.setTitleConfig(title);
       
      final BarSeries<Data> bar = new BarSeries<Data>();
      bar.setYAxisPosition(Position.BOTTOM);
      bar.addYField(dataAccess.data1());
      bar.addYField(dataAccess.data2());
      bar.addYField(dataAccess.data3());
      bar.addColor(new RGB(148, 174, 10));
      bar.addColor(new RGB(17, 95, 166));
      bar.addColor(new RGB(166, 17, 32));
       
      final Legend<Data> legend = new Legend<Data>();
      legend.setItemHighlighting(true);
      legend.setItemHiding(true);
      legend.getBorderConfig().setStrokeWidth(0);
       
      final Chart<Data> chart = new Chart<Data>();
      chart.setStore(store);
      chart.setShadowChart(false);
      chart.addAxis(axis);
      chart.addAxis(catAxis);
      chart.addSeries(bar);
      chart.setLegend(legend);
      chart.setDefaultInsets(20);
 
      TextButton regenerate = new TextButton("Reload Data");
      regenerate.addSelectHandler(new SelectHandler() {
        @Override
        public void onSelect(SelectEvent event) {
          store.clear();
//          store.addAll(TestData.getData(12, 0, 100));
          chart.redrawChart();
        }
      });
 
      ToggleButton animation = new ToggleButton("Animate");
      animation.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
        @Override
        public void onValueChange(ValueChangeEvent<Boolean> event) {
          chart.setAnimated(event.getValue());
        }
      });
      animation.setValue(true, true);
 
      ToggleButton shadow = new ToggleButton("Shadow");
      shadow.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
        @Override
        public void onValueChange(ValueChangeEvent<Boolean> event) {
          chart.setShadowChart(event.getValue());
          chart.redrawChart();
        }
      });
      shadow.setValue(false);
 
      ToolBar toolBar = new ToolBar();
      toolBar.add(regenerate);
      toolBar.add(animation);
      toolBar.add(shadow);
 
      VerticalLayoutContainer layout = new VerticalLayoutContainer();
      layout.add(toolBar, new VerticalLayoutData(1, -1));
      layout.add(chart, new VerticalLayoutData(1, 1));
 
      panel = new FramedPanel();
      panel.setLayoutData(new MarginData(10));
      panel.setCollapsible(true);
      panel.setHeadingText("Grouped Bar Chart");
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
    
}
