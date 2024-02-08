/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import modeloDTO.SessionManager;

/**
 * Clase base para controladores de vistas.
 *
 * Esta clase proporciona funcionalidades comunes y métodos útiles para los
 * controladores de vistas en la aplicación. Sirve como punto de partida para la
 * implementación de controladores específicos de cada vista.
 */
public class BaseController {

    public ResourceBundle bundle;
    private Stage stage;

    /**
     * Setters y getters de elementos javafx
     *
     * @return
     */
    public ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * Set bundle
     *
     * @param bundle
     */
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Método para cargar una ventana en un contenedor específico.
     *
     * El método carga una ventana a partir de un archivo FXML y la inserta en
     * el contenedor identificado por su atributo de sesión.
     *
     * @param fxmlResource La ruta del archivo FXML que define la ventana a
     * cargar.
     * @param atributoSession El atributo de sesión que identifica al contenedor
     * donde se cargará la ventana.
     */
    protected void cargarVentana(String fxmlResource, String atributoSession) {
        Platform.runLater(() -> {
            try {
                GridPane bodyContainer = (GridPane) SessionManager.getAttribute(
                        atributoSession);
                bodyContainer.getChildren().clear();
                bodyContainer.getChildren().setAll((GridPane) FXMLLoader.load(
                        BaseController.class.getResource(fxmlResource),
                        bundle));
                bodyContainer.autosize();
            } catch (Exception e) {
                e.getStackTrace();
            }
        });
    }

    /**
     * Método para cargar la ventana de clientes en el contenedor principal.
     *
     * La ventana se carga a partir de un archivo FXML y se inserta en el
     * contenedor identificado por su ID en la jerarquía de la interfaz gráfica.
     */
    public void cargarVentanaClientes() {
        cargarVentana(
                "/vistaFXML/ClientesScene.fxml",
                "bodyContainer");
    }

    /**
     * Método para ajustar el tamaño de las columnas de una tabla con un único
     * encabezado.
     *
     * @param tabla La TableView cuyas columnas se ajustarán.
     */
    public void ajustarAnchorColumnasTablaUnicoHeader(TableView<?> tabla) {
        tabla.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tabla.getColumns().stream().forEach((columna) -> {
            modificarAnchorColumnas(tabla, columna);
        });
    }

    /**
     * Permite limpiar la vista que haya en el body de la ventana principal
     */
    protected void clearBody() {
        GridPane bodyContainer = (GridPane) SessionManager.getAttribute("bodyContainer");
        bodyContainer.getChildren().clear();
    }

    /**
     * Este método recibe cada columna de un método inicial doble header o único
     * header, calculamos el tamaño máximo validando qué tamaño es mayor, si el
     * label de la columna o el value de cada registro y basándonos en el máximo
     * modificamos el width de la columna
     *
     * @param tabla
     * @param columna
     */
    public void modificarAnchorColumnas(TableView<?> tabla, TableColumn columna) {
        //Obtenemos mínimo tamaño será el header
        Text t = new Text(columna.getText());
        double widthMaximo = t.getLayoutBounds().getWidth();

        for (int i = 0; i < tabla.getItems().size(); i++) {
            if (columna.getCellData(i) != null) {
                t = new Text(columna.getCellData(i).toString());
                double nuevoWidth = t.getLayoutBounds().getWidth();
                //cambiamos el tamaño maximo si la data es mayor que el header
                if (nuevoWidth > widthMaximo) {
                    widthMaximo = nuevoWidth;
                }
            }
        }
        columna.setPrefWidth(widthMaximo + 50.0d);
    }

    /**
     * Solo se permiten números se agrega regex para eliminar los ceros a la
     * izquierda
     *
     * @param texto
     */
    protected void validarSoloNumeros(TextField texto, int longitudMaxima) {
        texto.textProperty().addListener((obs, oldValue, newValue) -> {
            if (texto.getText().length() > longitudMaxima) {
                String substring = texto.getText().substring(0, longitudMaxima);
                texto.setText(substring);
            }
            if (!texto.getText().matches("\\d+") && !texto.getText().equals("")) {
                String s = texto.getText().substring(0, texto.getLength() - 1);
                texto.setText(s);
            }
            texto.setText(texto.getText().replaceAll("^0+(?=\\d+$)", ""));
        });
    }

    /**
     * Método para validar y convertir el texto ingresado a mayúsculas.
     *
     * @param textField El TextField en el que se aplicará la validación y
     * conversión.
     */
    public void convertirAMayusculas(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textField.setText(newValue.toUpperCase());
        });
    }

    /**
     * Método para iniciar una ventana modal.
     *
     * @param rutaFxml La ruta del archivo FXML de la ventana modal.
     * @return El objeto Stage que representa la ventana modal.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    public Stage iniciarModal(String rutaFxml) throws Exception {
        Parent root = FXMLLoader.load(BaseController.class.getResource(rutaFxml), getBundle());
        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("USUARIO");
        stage.setScene(scene);
        stage.getIcons().add(new Image("img/sena.jpg"));
        stage.setResizable(true);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initOwner(((Scene) SessionManager.getAttribute("escenaPrincipal")).getWindow());
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                restaurarCerrarModal();
            }
        });

        return stage;
    }

    /**
     * Este método se utiliza para mostrar una ventana modal, aplicando el
     * efecto de fondo gris
     *
     * @param rutaFxml
     * @throws IOException
     */
    public Stage iniciarModalConEfectoGris(String rutaFxml) throws Exception {
        GridPane gridPrincipal = (GridPane) SessionManager.getAttribute("gridPrincipal");
        gridPrincipal.setEffect(new ColorAdjust(0, 0, -0.35, 0));
        return iniciarModal(rutaFxml);
    }

    /**
     * Método que elimina el efecto de shadow al grid principal al cerrar una
     * modal
     */
    protected void restaurarCerrarModal() {
        GridPane gridPrincipal = (GridPane) SessionManager.getAttribute("gridPrincipal");
        gridPrincipal.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    /**
     * Método para seleccionar un valor en un ComboBox.
     *
     * @param comboBox El ComboBox en el que se realizará la selección.
     * @param value El valor a seleccionar.
     */
    protected void selectComboBoxValue(ComboBox<ComboBoxItem> comboBox, String value) {
        if (value != null) {
            for (ComboBoxItem item : comboBox.getItems()) {
                if (value.equals(item.getValor()) || value.equals(item.getEtiqueta())) {
                    comboBox.getSelectionModel().select(item);
                    break;
                }
            }
        }
    }

}
