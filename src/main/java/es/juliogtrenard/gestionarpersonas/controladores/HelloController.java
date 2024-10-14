package es.juliogtrenard.gestionarpersonas.controladores;

import es.juliogtrenard.gestionarpersonas.modelos.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controlador para la interfaz gráfica de la gestión de personas.
 * Esta clase maneja la lógica para agregar personas a una lista,
 * validando la entrada del usuario y actualizando la vista.
 */
public class HelloController {
    /**
     * Tabla que muestra la lista de personas en la interfaz gráfica.
     */
    @FXML
    private TableView<Persona> tvTabla;

    /**
     * Campo de texto para ingresar el apellido de la persona.
     */
    @FXML
    private TextField txtApellidos;

    /**
     * Campo de texto para ingresar la edad de la persona.
     */
    @FXML
    private TextField txtEdad;

    /**
     * Campo de texto para ingresar el nombre de la persona.
     */
    @FXML
    private TextField txtNombre;

    /**
     * Lista que almacena las instancias de {@link Persona} creadas.
     */
    @FXML
    private ArrayList<Persona> listaPersonas;

    /**
     * Método llamado al inicializar el controlador.
     * Se utiliza para inicializar la lista de personas.
     */
    @FXML
    public void initialize() {
        listaPersonas = new ArrayList<>();
    }

    /**
     * Maneja el evento de agregar una nueva persona a la lista.
     * Valida las entradas del usuario y, si son correctas,
     * crea una nueva instancia de {@link Persona} y la añade a la tabla.
     *
     * @param event El evento que activa este método.
     */
    @FXML
    void agregarPersona(ActionEvent event) {
        String errores = validarEntradas();

        if (!errores.isEmpty()) {
            mostrarAlerta(errores);
            return;
        }

        if (esPersonaRepetida()) {
            mostrarAlerta("Persona repetida");
        } else {
            crearPersona();
        }

        limpiarCampos();
    }

    /**
     * Crea una nueva instancia de {@link Persona} con los datos ingresados
     * y la añade a la lista y a la tabla. Muestra una alerta de éxito.
     */
    private void crearPersona() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        int edad = Integer.parseInt(txtEdad.getText());

        Persona persona = new Persona(nombre, apellidos, edad);
        listaPersonas.add(persona);

        tvTabla.getItems().add(persona);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Persona añadida correctamente.");
        alerta.setHeaderText(null);
        alerta.setTitle("INFO:");
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/img/library_icon.png"))));
        alerta.showAndWait();
    }

    /**
     * Valida las entradas del usuario y devuelve un string
     * con los errores encontrados, si los hay.
     *
     * @return Un string con los errores de validación.
     */
    private String validarEntradas() {
        return errores();
    }

    /**
     * Verifica si la persona que se intenta agregar ya existe
     * en la lista.
     *
     * @return {@code true} si la persona ya está en la lista,
     *         {@code false} en caso contrario.
     */
    private boolean esPersonaRepetida() {
        for (Persona p : listaPersonas) {
            if (esIgualPersona(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compara una persona existente con los datos ingresados.
     *
     * @param p La persona a comparar.
     * @return {@code true} si los datos son iguales,
     *         {@code false} en caso contrario.
     */
    private boolean esIgualPersona(Persona p) {
        return p.getNombre().equalsIgnoreCase(txtNombre.getText().trim()) &&
                p.getApellidos().equalsIgnoreCase(txtApellidos.getText().trim()) &&
                p.getEdad() == Integer.parseInt(txtEdad.getText());
    }

    /**
     * Muestra una alerta con el mensaje de error proporcionado.
     *
     * @param errores El mensaje de error a mostrar.
     */
    private void mostrarAlerta(String errores) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, errores);
        alerta.setHeaderText(null);
        alerta.setTitle("ERROR:");
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/img/library_icon.png"))));
        alerta.showAndWait();
    }

    /**
     * Realiza las validaciones de las entradas del usuario y
     * devuelve un string con los errores encontrados.
     *
     * @return Un string con los errores de validación.
     */
    private String errores() {
        String errores = "";

        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String edadInput = txtEdad.getText().trim();

        if (nombre.isEmpty()) {
            errores += "Debes introducir tu nombre.\n";
        } else if (!nombre.matches("^[A-Za-záéíóúÁÉÍÓÚÑñ\\s]+$")) {
            errores += "El nombre no puede contener números.\n";
        }

        if (apellidos.isEmpty()) {
            errores += "Debes introducir al menos un apellido.\n";
        } else if (!apellidos.matches("^[A-Za-záéíóúÁÉÍÓÚÑñ\\s]+$")) {
            errores += "Los apellidos no pueden contener números.\n";
        }

        int edad;
        try {
            edad = Integer.parseInt(edadInput);
        } catch (NumberFormatException e) {
            return errores + "El campo 'Edad' debe ser numérico.\n";
        }

        if (edad < 1 || edad > 100) {
            errores += "Introduce una edad válida (1-100).\n";
        }

        return errores;
    }

    /**
     * Limpia los campos de entrada de texto.
     */
    private void limpiarCampos() {
        txtApellidos.clear();
        txtEdad.clear();
        txtNombre.clear();
    }
}