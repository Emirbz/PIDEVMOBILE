/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import java.util.ArrayList;
import PIDEV.Services.DealService;
import PIDEV.Entities.Deal;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Toolbar;

/**
 *
 * @author Skan
 */
public class Statistic {

    DealService cs = new DealService();
    ArrayList<Deal> lis = cs.getListDeals2();

    public Form f;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset() {
        CategorySeries series = new CategorySeries("Stataistique des Clubs");
        int k = 0;

        for (int i = 0; i < lis.size(); i++) {

            series.add(lis.get(i).getNom(), lis.get(i).getRating());

        }

        return series;
    }

    public Form createPieChartForm() {
        // Generate the values

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.MAGENTA);
        r.setGradientStop(0, ColorUtil.CYAN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset(), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        f = new Form("Statisique des deals", new BorderLayout());
        Toolbar t = new Toolbar(true);
        f.setToolbar(t);
        f.getTitleArea().setUIID("Container");
        Form previous = getCurrentForm();
        f.getToolbar().setBackCommand("", (es) -> {
            previous.showBack();
        });
        f.add(BorderLayout.CENTER, c);
        return f;

    }

}
