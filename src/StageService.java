import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Gestionnaire auto-hébergé de contenu de fenêtre
 */
public class StageService {//NOPMD
    /**
     * unique instance auto hébergée de la fenêtre
     */
    private static Stage mainStage;

    /**
     * getter : récupérer l'instance de fenêtre contrôlée
     * @return
     */
    private Stage getMainStage() {
        return mainStage;
    }

    /**
     * setter : modifier la fenêtre affichée/contrôlée
     * @param newStage fenêtre à contrôler
     * @param title titre de la fenêtre
     * @param icon icone de la fenêtre
     */
    public void setMainStage(final Stage newStage, final String title, Image icon) {
        this.mainStage = newStage;
        if (title != null) this.mainStage.setTitle(title);
        if (icon != null) this.mainStage.getIcons().add(icon);
    }


    /**
     * partie statique (nécessairement dans classe non statique) : accès et pilotage de l'instance
     */
    public static class Holder {//NOPMD
        /**
         * unique instance de la classe, accès à la scène
         */
        protected static final StageService INSTANCE = new StageService();

        /**
         * chemin d'accès aux fichiers fxml
         */
        private final static String RESOURCE_FOLDER_PATH = "view/";

        /**
         * getter : accès à l'unique instance de scène
         *
         * @return unique instance de la classe
         */
        public static StageService getInstance() {
            return INSTANCE;
        }

        /**
         * met à jour la scène affichée dans la fenêtre
         *
         * @param pageName nom du fichier fxml à afficher
         */
        public static void loadMainWindowsScene(final String pageName) {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StageService.class.getResource(RESOURCE_FOLDER_PATH+pageName+".fxml"));

            try {
                final VBox sceneRoot = loader.load();//VBox : rootNodeScene type
                INSTANCE.getMainStage().setScene(new Scene(sceneRoot));
                INSTANCE.getMainStage().show();
            } catch (IOException e) {
                e.printStackTrace();//passer en projet maven puis suivre https://www.baeldung.com/logback
            }
        }
    }
}
