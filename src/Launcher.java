import entity.Glacier;
import entity.Temperature;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * classe de lancement : acquisition des données et lancement de la fenêtre
 */
public class Launcher extends Application {
    private static ArrayList<Glacier> dataGlaciers = new ArrayList<>();
    private static ArrayList<Temperature> dataTemperature = new ArrayList<>();

    /**
     * Fonction de lancement de la fenêtre
     * @param primaryStage fenêtre initiale
     * @throws Exception lecture fichier
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        //get glaciers.txt content
        {
            File file = new java.io.File("data/glaciers.txt");
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();//first unusable line
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] parts = line.split(",");
                dataGlaciers.add(new Glacier(new SimpleDateFormat("yyyy").parse(parts[0]), Double.parseDouble(parts[1])));
            }
            reader.close();

            Collections.sort(dataGlaciers);
        }

        //get temperature.txt content
        {
            File file = new java.io.File("data/temperature.txt");
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();//first unusable line
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] parts = line.split(",");
                dataTemperature.add(new Temperature(parts[0],new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]), Double.parseDouble(parts[2])));
            }
            reader.close();

            Collections.sort(dataTemperature);
        }

        //load & display window
        StageService.Holder.getInstance().setMainStage(primaryStage, "Scientific", null);
        StageService.Holder.loadMainWindowsScene("dashBoard");
    }

    /**
     * lanceur de l'application
     * @param args arguments
     */
    public static final void main(final String[] args) {
        launch(args);
    }

    /**
     * getter de la liste des glaciers
     * @return liste d'instances de glaciers
     */
    public static ArrayList<Glacier> getDataGlaciers() {
        return dataGlaciers;
    }

    /**
     * getter de la liste des températures
     * @return liste d'instances de températures
     */
    public static ArrayList<Temperature> getDataTemperature() {
        return dataTemperature;
    }
}
