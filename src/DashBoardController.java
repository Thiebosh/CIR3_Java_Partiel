import entity.Glacier;
import entity.Temperature;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controlleur de la vue
 */
public class DashBoardController implements Initializable {
    @FXML
    private LineChart lineChartTemperature;

    @FXML
    private LineChart lineChartTaille;

    @FXML
    private BarChart barChartTemperatureMoyenne;

    /**
     * fonction d'initalisation de la fenêtre : remplissage initial
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayTemperatureGraph();
        displayTailleGraph();
        displayMoyenneTempGraph();

    }

    /**
     * fonction de remplissage initial du graphe température
     */
    private void displayTemperatureGraph() {
        LineChart.Series<String, Number> serieGCAG = new LineChart.Series<>();
        serieGCAG.setName("GCAG");

        LineChart.Series<String, Number> serieGISTEMP = new LineChart.Series<>();
        serieGISTEMP.setName("GISTEMP");

        for (Temperature current : Launcher.getDataTemperature()) {
            XYChart.Data<String, Number> data = new LineChart.Data<>(new SimpleDateFormat("yyyy").format(current.getDateMesure()), current.getTempMoyenne());
            switch (current.getSource()) {
                case "GCAG":
                    serieGCAG.getData().add(data);
                    break;
                case "GISTEMP":
                    serieGISTEMP.getData().add(data);
                    break;
            }
        }

        lineChartTemperature.setData(FXCollections.observableArrayList(serieGCAG, serieGISTEMP));

        // convert line color to CSS format and set line color on Series node
        serieGCAG.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: rgba(0,255,0, 0.4)");
        serieGISTEMP.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: rgba(0,0,255, 0.4)");
    }

    /**
     * fonction de remplissage initial du graphe taille
     */
    private void displayTailleGraph() {
        LineChart.Series<String, Number> serie = new AreaChart.Series<>();

        for (Glacier current : Launcher.getDataGlaciers()) {
            serie.getData().add(new AreaChart.Data<>(new SimpleDateFormat("yyyy").format(current.getYear()), current.getDeltaMass()));
        }

        lineChartTaille.setData(FXCollections.observableArrayList(serie));
    }

    /**
     * fonction de remplissage initial du graphe de la moyenne
     */
    private void displayMoyenneTempGraph() {
        HashMap<String, Double> moyenne = new HashMap();
        for (Temperature current : Launcher.getDataTemperature()) {
            String date = (new SimpleDateFormat("yyy").format(current.getDateMesure()))+"0";
            if (moyenne.containsKey(date)) {
                moyenne.put(date, (moyenne.get(date)+current.getTempMoyenne())/2);
            }
            else {
                moyenne.put(date, current.getTempMoyenne());
            }
        }
        //...

        BarChart.Series<String, Number> serie = new BarChart.Series<>();

        for (Temperature current : Launcher.getDataTemperature()) {
            serie.getData().add(new BarChart.Data<>(new SimpleDateFormat("yyyy").format(current.getDateMesure()), current.getTempMoyenne()));
        }
        barChartTemperatureMoyenne.setData(FXCollections.observableArrayList(serie));
    }
}
