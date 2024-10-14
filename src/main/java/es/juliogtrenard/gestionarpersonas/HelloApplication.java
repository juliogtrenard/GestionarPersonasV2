package es.juliogtrenard.gestionarpersonas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

/**
 * Clase principal de la aplicación que extiende {@link Application}.
 * Esta clase se encarga de iniciar la interfaz gráfica de usuario
 * y cargar la vista definida en el archivo FXML.
 */
public class HelloApplication extends Application {
    /**
     * Método que se llama al iniciar la aplicación.
     *
     * @param stage El escenario principal donde se muestra la interfaz gráfica.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        // Crear el FontIcon
        FontIcon fontIcon = new FontIcon(FontAwesome.BOOK);
        fontIcon.setIconSize(16);
        fontIcon.setIconColor(Color.BLUE);

        // Convertir el FontIcon a una Image
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image icon = fontIcon.snapshot(params, null);

        // Añadir la imagen como icono de la ventana
        stage.getIcons().add(icon);

        stage.setTitle("PERSONAS");
        stage.setScene(scene);

        stage.setMinWidth(700);
        stage.setMinHeight(400);

        stage.show();
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        launch();
    }
}